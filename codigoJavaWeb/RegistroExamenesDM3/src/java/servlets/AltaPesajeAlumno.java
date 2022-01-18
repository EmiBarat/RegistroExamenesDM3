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
import model.Alumno;
import model.Pesaje;
import model.TipoDeEstadoPeso;
import model.Usuario;

/**
 *
 * @author Emiliano Barat
 */
@WebServlet(name = "AltaPesajeAlumno", urlPatterns = {"/AltaPesajeAlumno"})
public class AltaPesajeAlumno extends HttpServlet {

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
            out.println("<title>Servlet AltaPesajeAlumno</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AltaPesajeAlumno at " + request.getContextPath() + "</h1>");
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
        
        AccesoBaseDatos gestor = new AccesoBaseDatos();
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");
        if (user != null && !user.getNombreUsuario().equals("")) {
            int idAlumno = Integer.parseInt(request.getParameter("idAlumno"));
            Alumno a = gestor.buscarAlumnoModel(idAlumno);
            request.setAttribute("alumno", a);
            RequestDispatcher rd = request.getRequestDispatcher("altaPesajeAlumno.jsp");
            rd.forward(request, response);
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
        
        AccesoBaseDatos gestor = new AccesoBaseDatos();
        
        int idAlumno = Integer.parseInt(request.getParameter("txtIdAlumno"));
        double talla = Double.parseDouble(request.getParameter("txtTalla"));
        String fechaPesaje = request.getParameter("dtpFechaPesaje");
        boolean bajoSeguimiento = false;
        if (request.getParameter("chkTratamiento") != null) {
            bajoSeguimiento = true;
        }
        
        String observaciones = request.getParameter("txtObservaciones");
        
        double peso = Double.parseDouble(request.getParameter("txtPeso"));
        double imc = Math.round((peso / (talla*talla))*100.0)/100.0;
        
        TipoDeEstadoPeso estadoPeso = gestor.buscarEstadoPeso(imc);
        
        Alumno a = gestor.buscarAlumnoModel(idAlumno);
        
        Pesaje p = new Pesaje(0, a, estadoPeso, fechaPesaje, peso, observaciones, bajoSeguimiento, imc);
        gestor.registrarPeso(p);
        
        String path = request.getContextPath();
        response.sendRedirect(path + "/exitoCarga.jsp");
        
//        RequestDispatcher rd = request.getRequestDispatcher("exitoCarga.jsp");
//        rd.forward(request, response);
        
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
