/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.AccesoBaseDatos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Pesaje;
import model.Usuario;

/**
 *
 * @author Emiliano Barat
 */
@WebServlet(name = "EliminarPesaje", urlPatterns = {"/EliminarPesaje"})
public class EliminarPesaje extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EliminarPesaje</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EliminarPesaje at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");
        
        if (user != null && !user.getNombreUsuario().equals("")) {
//            verifico que el usuario tenga los permisos de administrador
            if (user.getTipoUsuario().getIdTipoUsuario() == 1) {

                try {
                    int idPesaje = Integer.parseInt(request.getParameter("idPesaje"));
                    request.setAttribute("idPesaje", idPesaje);
                    RequestDispatcher rd = request.getRequestDispatcher("confirmarEliminacionPeso.jsp");
                    rd.forward(request, response);
                } catch (Exception e) {
                    String path = request.getContextPath();
                    response.sendRedirect(path + "/errorCarga.jsp");
                }

            } else {
//                cuando no tiene los permisos de edicion de examen lo redirijo al listado de examenes con un mensaje
                String msj = "No tiene permisos para eliminar un pesaje. Comunicarse con un administrador";
                request.setAttribute("msj", msj);
                RequestDispatcher rd = request.getRequestDispatcher("ListarPeso");
                rd.forward(request, response);
            }
        } else {
            String msjNoUser = "No hay usuario logueado.\nIngrese sus credenciales.";
            request.setAttribute("msj", msjNoUser);
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            AccesoBaseDatos gestor = new AccesoBaseDatos();
            int idPesaje = Integer.parseInt(request.getParameter("txtIdPesaje"));
            gestor.eliminarPesaje(idPesaje);
            String path = request.getContextPath();
            response.sendRedirect(path + "/exitoCarga.jsp");
        } catch (Exception ex) {
            String path = request.getContextPath();
            response.sendRedirect(path + "/errorCarga.jsp");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
