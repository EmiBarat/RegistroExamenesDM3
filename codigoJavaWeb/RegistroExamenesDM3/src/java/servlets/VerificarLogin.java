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
import model.Usuario;
import org.jboss.weld.module.web.servlet.SessionHolder;

/**
 *
 * @author Emiliano Barat
 */
@WebServlet(name = "VerificarLogin", urlPatterns = {"/VerificarLogin"})
public class VerificarLogin extends HttpServlet {

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
        
        
            String msjNoUser = "No hay usuario logueado.\nIngrese sus credenciales.";
            request.setAttribute("msj", msjNoUser);
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        
    }

    
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String usuario = request.getParameter("txtUser");
        String pass = request.getParameter("txtPass");

        AccesoBaseDatos gestor = new AccesoBaseDatos();

        Usuario user = new Usuario();
        user = gestor.buscarUsuario(usuario);

        //VERIFICAR QUE EL TXTUSER HAYA TRAIDO UN USUARIO DE LA DB
        if (user != null) {

            if (pass.equals(user.getPassword().trim())) {
                session.setAttribute("usuario", user);
                session.setAttribute("rol", user.getTipoUsuario().getIdTipoUsuario());
            } else {
                String msjErrorPass = "Contraseña incorrecta";
                request.setAttribute("contrasenaIncorrecta", msjErrorPass);
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }
            
            if(user.getFechaBaja() == null){
                if (user.getTipoUsuario().getIdTipoUsuario() == 1) {
                    RequestDispatcher rd = request.getRequestDispatcher("menuAdmin.jsp");
                    rd.forward(request, response);
                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("menuUsuario.jsp");
                    rd.forward(request, response);
                }
            }else{
                String msjErrorBaja = "Su usuario fue dado de baja. Solicite el alta a un administrador.";
                request.setAttribute("usuarioBaja", msjErrorBaja);
                RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
                rd.forward(request, response);
            }

            
        }else{
            session.setAttribute("usuario", "");
            String msjErrorUser = "Usuario no registrado";
            request.setAttribute("usuarioIncorrecta", msjErrorUser);
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
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
