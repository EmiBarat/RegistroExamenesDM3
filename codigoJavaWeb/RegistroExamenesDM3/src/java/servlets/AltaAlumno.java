/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.AccesoBaseDatos;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.Alert;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static javax.xml.bind.DatatypeConverter.parseDouble;
import model.Alumno;
import model.Usuario;

/**
 *
 * @author Emiliano Barat
 */
@WebServlet(name = "AltaAlumno", urlPatterns = {"/AltaAlumno"})
public class AltaAlumno extends HttpServlet {

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

        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");
        if (user != null && !user.getNombreUsuario().equals("")) {
            RequestDispatcher rd = request.getRequestDispatcher("altaAlumno.jsp");
            rd.forward(request, response);
        } else {
            String msjNoUser = "No hay usuario logueado.\nIngrese sus credenciales.";
            request.setAttribute("msj", msjNoUser);
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        Date nacimimento = new Date();
//        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        Alumno alumno = new Alumno();
        AccesoBaseDatos gestor = new AccesoBaseDatos();
        //uso el booleano para validar si el dni del alumno existe en la DB
        boolean alumnoExiste = false;

        try {
            String nombre = request.getParameter("txtNombre");
            String apellido = request.getParameter("txtApellido");
            int grado = parseInt(request.getParameter("cmbGrados"));
            int genero = parseInt(request.getParameter("cmbGeneros"));
            int grupoSang = parseInt(request.getParameter("cmbGrSang"));
            int facSang = parseInt(request.getParameter("cmbFacSang"));
            int codEstadistico = parseInt(request.getParameter("numCodEstadistico"));
            int dni = parseInt(request.getParameter("numDni"));
            int iosfa = parseInt(request.getParameter("numIosfa"));

            //Verifico por el dni que el alumno no exista en la base de datos
            ArrayList listaDni = gestor.listarDni(); //listarDni ya esta hecho
            //ahora tengo que validar que no exista ya ese DNI.
            for (Object object : listaDni) {
                if ((Integer) object == dni) {
                    alumnoExiste = true;
                    String msj = "El alumno que intenta dar de alta ya esta en el registro.";
                    request.setAttribute("msj", msj);
                    RequestDispatcher rd = request.getRequestDispatcher("errorCarga.jsp");
                    rd.forward(request, response);
                }
            }

            String fechaNatalicio = request.getParameter("dateFechaNac");
            int idCategoria = alumno.calcularCategConSql(fechaNatalicio);

            String infoSan = request.getParameter("infoSanitaria");
            String infoGreneral = request.getParameter("infoGeneral");
            double altura = parseDouble(request.getParameter("txtAltura"));

            //esta parte es para calcular la fecha de hoy y ponerla como fecha de alta
            Date fechaActual = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechaAlta = dateFormat.format(fechaActual);

            Alumno a = new Alumno(grado, genero, nombre, apellido, fechaNatalicio, codEstadistico, dni, iosfa, grupoSang, facSang, infoSan, infoGreneral, altura, fechaAlta, null, idCategoria);

            if (!alumnoExiste) {
                gestor.nuevoAlumno(a);
                request.setAttribute("a", a);

                String msj = "Dió de alta ";
                request.setAttribute("msj", msj);

                String path = request.getContextPath();
                response.sendRedirect(path + "/exitoCargaAlumno.jsp");
//                RequestDispatcher rd = request.getRequestDispatcher("exitoCargaAlumno.jsp");
//                rd.forward(request, response);
            }

        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("errorCarga.jsp");
            rd.forward(request, response);
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
