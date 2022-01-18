/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.AccesoBaseDatos;
import dtoModel.ListadoAlumnosDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.DetalleExamen;
import model.Examen;
import model.Usuario;

/**
 *
 * @author Emiliano Barat
 */
@WebServlet(name = "AltaExamen", urlPatterns = {"/AltaExamen"})
public class AltaExamen extends HttpServlet {

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

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");
        if (user != null && !user.getNombreUsuario().equals("")) {
            try {
                AccesoBaseDatos gestor = new AccesoBaseDatos();
                int idAlumno = Integer.parseInt(request.getParameter("idAlumno"));

                ListadoAlumnosDTO dtoAlumno = gestor.buscarAlumno(idAlumno);

                String nombreCategoria = dtoAlumno.getCategoria().getDescripcion();
                int idCategoria = dtoAlumno.getCategoria().getIdCategoria();

                request.setAttribute("categoria", nombreCategoria);
                request.setAttribute("idCategoria", idCategoria);
                request.setAttribute("alumnoDto", dtoAlumno);

                RequestDispatcher rd = request.getRequestDispatcher("altaExamen.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                RequestDispatcher rd = request.getRequestDispatcher("ListarAlumnos");
                rd.forward(request, response);
            }

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
        int puntajeAcum = 0;
        int cantPruebasRendidas = 0;

        int idAluno = Integer.parseInt(request.getParameter("txtIdAlumno"));
        int idCateg = Integer.parseInt(request.getParameter("txtIdCategoria"));
        int tipoExamen = Integer.parseInt(request.getParameter("cmbTipoExamen"));
        int idProfe = Integer.parseInt(request.getParameter("cmbProfesor"));
        String fechaExamen = request.getParameter("dtpFechaExamen");
        String obs = request.getParameter("txtObservaciones");
        int idGenero = Integer.parseInt(request.getParameter("txtIdGenero"));

        Examen e = new Examen(tipoExamen, fechaExamen, idProfe, idAluno, obs, idCateg);

        AccesoBaseDatos gestor = new AccesoBaseDatos();

        //Verefico que los campos del formulario tengan dato.
        //Si trajo datos: lo incluyo para el promedio final
        //busco el puntaje correspondiente en MatrizResultados para cada prueba
        //Añado el detalle de examen de cada prueba en el atributo ArrayList de Examen
        double timeCarrera = 0;
        if (!request.getParameter("txtCarrera 3K").trim().equals("")) {
            timeCarrera = Double.parseDouble(request.getParameter("txtCarrera 3K"));
        } else {
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaCarrera 3K"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, timeCarrera);

            DetalleExamen de = new DetalleExamen(idPrueba, fechaExamen, timeCarrera, puntaje, idProfe, obs);
            e.agregarDetalles(de);
//            gestor.registrarDetalleExamen(de);
        }
        if (timeCarrera != 0) {
            //necesito todos los datos para hacer el insert en detalleExamen.
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaCarrera 3K"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, timeCarrera);

            puntajeAcum += puntaje;
            cantPruebasRendidas++;

            DetalleExamen de = new DetalleExamen(idPrueba, fechaExamen, timeCarrera, puntaje, idProfe, obs);
            e.agregarDetalles(de);

//            gestor.registrarDetalleExamen(de);
        }

        double cantFlex = 0;
        String txtFlexiones = request.getParameter("txtFlexiones").trim();
        if (!txtFlexiones.trim().equals("")) {
            cantFlex = Double.parseDouble(request.getParameter("txtFlexiones"));
        } else {
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaFlexiones"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, cantFlex);

            DetalleExamen de = new DetalleExamen(idPrueba, fechaExamen, cantFlex, puntaje, idProfe, obs);
            e.agregarDetalles(de);
//            gestor.registrarDetalleExamen(de);
        }
        if (cantFlex != 0) {
            //necesito todos los datos para hacer el insert en detalleExamen.
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaFlexiones"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, cantFlex);

            puntajeAcum += puntaje;
            cantPruebasRendidas++;

            DetalleExamen de = new DetalleExamen(idPrueba, fechaExamen, cantFlex, puntaje, idProfe, obs);
            e.agregarDetalles(de);
//            gestor.registrarDetalleExamen(de);

        }

        double cantBarras = 0;
        if (!request.getParameter("txtBarras").trim().equals("")) {
            cantBarras = Double.parseDouble(request.getParameter("txtBarras"));
        } else {
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaBarras"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, cantBarras);

            DetalleExamen de = new DetalleExamen(idPrueba, fechaExamen, cantBarras, puntaje, idProfe, obs);
            e.agregarDetalles(de);
//            gestor.registrarDetalleExamen(de);
        }

        if (cantBarras != 0) {
            //necesito todos los datos para hacer el insert en detalleExamen.
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaBarras"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, cantBarras);

            puntajeAcum += puntaje;
            cantPruebasRendidas++;

            DetalleExamen de = new DetalleExamen(idPrueba, fechaExamen, cantBarras, puntaje, idProfe, obs);
            e.agregarDetalles(de);
//            gestor.registrarDetalleExamen(de);

        }

        double cantAbs = 0;
        if (!request.getParameter("txtAbdominales").trim().equals("")) {
            cantAbs = Double.parseDouble(request.getParameter("txtAbdominales"));
        } else {
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaAbdominales"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, cantAbs);

            DetalleExamen de = new DetalleExamen(idPrueba, fechaExamen, cantAbs, puntaje, idProfe, obs);
            e.agregarDetalles(de);
//            gestor.registrarDetalleExamen(de);
        }

        if (cantAbs != 0) {
            //necesito todos los datos para hacer el insert en detalleExamen.
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaAbdominales"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, cantAbs);

            puntajeAcum += puntaje;
            cantPruebasRendidas++;

            DetalleExamen de = new DetalleExamen(idPrueba, fechaExamen, cantAbs, puntaje, idProfe, obs);
            e.agregarDetalles(de);
//            gestor.registrarDetalleExamen(de);

        }

        double timeCaminata = 0;
        String txtCaminata = request.getParameter("txtCaminata").trim();
        if (!txtCaminata.equals("")) {
            timeCaminata = Double.parseDouble(request.getParameter("txtCaminata"));
        } else {
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaCaminata"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, timeCaminata);

            DetalleExamen de = new DetalleExamen(idPrueba, fechaExamen, timeCaminata, puntaje, idProfe, obs);
            e.agregarDetalles(de);
//            gestor.registrarDetalleExamen(de);
        }

        if (timeCaminata != 0) {
            //necesito todos los datos para hacer el insert en detalleExamen.
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaCaminata"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, timeCaminata);

            puntajeAcum += puntaje;
            cantPruebasRendidas++;

            DetalleExamen de = new DetalleExamen(idPrueba, fechaExamen, timeCaminata, puntaje, idProfe, obs);
            e.agregarDetalles(de);
//            gestor.registrarDetalleExamen(de);

        }

        //Al final, para garantizar la atomicidad de la transaccion se hacen todos
        //los inserts a la BD en un mismo metodo en 'AccesoBaseDatos'
        gestor.registrarExamen(e);

        String msj = "Registró correctamente el examen";
        request.setAttribute("msj", msj);

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
