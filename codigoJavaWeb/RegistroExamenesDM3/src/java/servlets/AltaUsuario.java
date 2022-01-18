/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.AccesoBaseDatos;
import dtoModel.ExamenDto;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Examen;
import model.TipoUsuario;
import model.Usuario;

/**
 *
 * @author Emiliano Barat
 */
@WebServlet(name = "AltaUsuario", urlPatterns = {"/AltaUsuario"})
public class AltaUsuario extends HttpServlet {

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

//      verifico que haya usuario logueado
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");
        if (user != null && !user.getNombreUsuario().equals("")) {
//            verifico que el usuario tenga los permisos de administrador
            if (user.getTipoUsuario().getIdTipoUsuario() == 1) {
                ArrayList<TipoUsuario> lista = gestor.listarTiposUsuarios();
                request.setAttribute("tiposUsuarios", lista);
                RequestDispatcher rd = request.getRequestDispatcher("altaUsuario.jsp");
                rd.forward(request, response);

            } else {
//                cuando no tiene los permisos de edicion de examen lo redirijo al listado de examenes con un mensaje
                String msj = "No tiene permisos para dar de alta un usuario. Comunicarse con un administrador";
                request.setAttribute("msj", msj);
                RequestDispatcher rd = request.getRequestDispatcher("menuUsuario.jsp");
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

        AccesoBaseDatos gestor = new AccesoBaseDatos();

        try {
            String nombreUsuario = request.getParameter("txtNombreUsuario");
            String password = request.getParameter("txtPassword");
            String preguntaSecreta = request.getParameter("txtPreguntaSecreta");
            String respuestaSecreta = request.getParameter("txtRespuestaSecreta");

            //traigo los datos para instanciar un Usuario
            int idTipoUsuario = Integer.parseInt(request.getParameter("cmbTipoUsuario"));

            TipoUsuario tu = gestor.buscarTipoUsuario(idTipoUsuario);

            //paso a String una fecha actual
            Date fechaActual = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechaAlta = dateFormat.format(fechaActual);

            Usuario u = new Usuario(0, tu, nombreUsuario, password, preguntaSecreta, respuestaSecreta, true, null, fechaAlta);

            gestor.registrarUsuario(u);

            String path = request.getContextPath();
            response.sendRedirect(path + "/exitoCarga.jsp");
            
        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("errorCarga.jsp");
            rd.forward(request, response);
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
