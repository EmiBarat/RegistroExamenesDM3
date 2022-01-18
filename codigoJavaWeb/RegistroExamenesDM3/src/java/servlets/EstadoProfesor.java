/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.AccesoBaseDatos;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Usuario;

/**
 *
 * @author Emiliano Barat
 */
@WebServlet(name = "EstadoProfesor", urlPatterns = {"/EstadoProfesor"})
public class EstadoProfesor extends HttpServlet {

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

        Date fecha = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaActual = dateFormat.format(fecha);
        
        

        //      verifico que haya usuario logueado
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");
        if (user != null && !user.getNombreUsuario().equals("")) {
//            verifico que el usuario tenga los permisos de administrador
            if (user.getTipoUsuario().getIdTipoUsuario() == 1) {

                int estado = Integer.parseInt(request.getParameter("estado"));
                int idProfe = Integer.parseInt(request.getParameter("idProfe"));
                
                if (estado == 1) {
                    gestor.updateBajaProfesor(fechaActual, idProfe);
                }
                if (estado == 2) {
                    gestor.updateCancelarBajaProfesor(idProfe);
                }
                //pincho el btn eliminacion...
                if (estado == 3) {
                    //lo redirijo a un .jsp de confirmacion
                    request.setAttribute("idProfe", idProfe);
                    RequestDispatcher rd = request.getRequestDispatcher("confirmEliminProfe.jsp");
                    rd.forward(request, response);
                }

                

                RequestDispatcher rd = request.getRequestDispatcher("listadoProfesores.jsp");
                rd.forward(request, response);

            } else {
//                cuando no tiene los permisos de edicion de examen lo redirijo al listado de examenes con un mensaje
                String msj = "No tiene permisos para editar un examen. Comunicarse con un administrador";
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
        
        //inicializo en 0 la confirmacion
        int confirm = 0;
        
        //en este if verifico si confirma la eliminicion
        //obtengo el input hidden confirmacion del form de 'confirmarEliminacion.jsp'
        confirm = Integer.parseInt(request.getParameter("confirmacion"));
        int idProfe = Integer.parseInt(request.getParameter("txtIdProfe"));
        if (confirm == 1) {
            gestor.eliminarProfe(idProfe);
            
        }
        RequestDispatcher rd = request.getRequestDispatcher("listadoProfesores.jsp");
            rd.forward(request, response);
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
