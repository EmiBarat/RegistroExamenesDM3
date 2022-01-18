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
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Alumno;
import model.Categoria;
import model.DetalleExamen;
import model.Examen;
import model.Usuario;

/**
 *
 * @author Emiliano Barat
 */
@WebServlet(name = "EditarExamen", urlPatterns = {"/EditarExamen"})
public class EditarExamen extends HttpServlet {

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
//      Obtengo idExamen desde el parametro de la URL

//      verifico que haya usuario logueado
        HttpSession session = request.getSession();
        Usuario user = (Usuario) session.getAttribute("usuario");

        int idExamen = 0;
        if (user != null && !user.getNombreUsuario().equals("")) {
//            verifico que el usuario tenga los permisos de administrador
            if (user.getTipoUsuario().getIdTipoUsuario() == 1) {
                
                try {
                    idExamen = Integer.parseInt(request.getParameter("idExamen"));
//              busco el examenDto por ID para llenar el form en el JSP

                    ExamenDto examenDto = gestor.buscarExamen(idExamen);
                    Examen examen = gestor.buscarExamenModel(idExamen);
                    request.setAttribute("examenDto", examenDto);
                    request.setAttribute("examenModel", examen);
                    
                    RequestDispatcher rd = request.getRequestDispatcher("editarExamen.jsp");
                    rd.forward(request, response);
                } catch (Exception e) {
                    RequestDispatcher rd = request.getRequestDispatcher("ListarExamenes");
                    rd.forward(request, response);
                }
                


            } else {
//                cuando no tiene los permisos de edicion de examen lo redirijo al listado de examenes con un mensaje
                String msj = "No tiene permisos para editar un examen. Comunicarse con un administrador";
                request.setAttribute("msj", msj);
                RequestDispatcher rd = request.getRequestDispatcher("listadoExamenes.jsp");
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

//        creo un accesoDB
        AccesoBaseDatos gestor = new AccesoBaseDatos();

//        recupero los datos del JSP editarExamen
        int tipoExamen = Integer.parseInt(request.getParameter("cmbTipoExamen"));
        int idExamen = Integer.parseInt(request.getParameter("txtIdExamen"));
        int idProfe = Integer.parseInt(request.getParameter("cmbProfesor"));
        String fechaExamen = request.getParameter("dtpFechaExamen");
        int idAluno = Integer.parseInt(request.getParameter("txtIdAlumno"));
        String obs = request.getParameter("txtObservaciones");

        int idCateg = Integer.parseInt(request.getParameter("txtIdCategoria"));
        int idGenero = gestor.buscarGeneroDeAlumno(idAluno);

//        llamo a metodo de AccesoBaseDatos que hace el update con los parametros a modificar
        gestor.editarDatosExamen(idExamen, tipoExamen, idProfe, fechaExamen, obs);

//        ahora tengo que hacer el update de los resultados de las pruebas y consecuentemente de las notas parciales y finales
        int puntajeAcum = 0;
        int cantPruebasRendidas = 0;

//        hago el update en el detalle de examen correspondiente y voy preparando el promedio para el update final a Examen
        double timeCarrera = 0;
        if (!request.getParameter("txtCarrera 3K").trim().equals("")) {
            timeCarrera = Double.parseDouble(request.getParameter("txtCarrera 3K"));
        } else {
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaCarrera 3K"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, timeCarrera);

            DetalleExamen de = new DetalleExamen(idExamen, idPrueba, fechaExamen, timeCarrera, puntaje, idProfe, obs);

            gestor.editarDetalleExamen(de);
        }
        if (timeCarrera != 0) {
            //necesito todos los datos para hacer el insert en detalleExamen.
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaCarrera 3K"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, timeCarrera);

            puntajeAcum += puntaje;
            cantPruebasRendidas++;

            DetalleExamen de = new DetalleExamen(idExamen, idPrueba, fechaExamen, timeCarrera, puntaje, idProfe, obs);

            gestor.editarDetalleExamen(de);

        }

        double cantFlex = 0;
        String txtFlexiones = request.getParameter("txtFlexiones").trim();
        if (!txtFlexiones.trim().equals("")) {
            cantFlex = Double.parseDouble(request.getParameter("txtFlexiones"));
        } else {
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaFlexiones"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, cantFlex);

            DetalleExamen de = new DetalleExamen(idExamen, idPrueba, fechaExamen, cantFlex, puntaje, idProfe, obs);

            gestor.editarDetalleExamen(de);
        }
        if (cantFlex != 0) {
            //necesito todos los datos para hacer el insert en detalleExamen.
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaFlexiones"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, cantFlex);

            puntajeAcum += puntaje;
            cantPruebasRendidas++;

            DetalleExamen de = new DetalleExamen(idExamen, idPrueba, fechaExamen, cantFlex, puntaje, idProfe, obs);

            gestor.editarDetalleExamen(de);

        }

        double cantBarras = 0;
        if (!request.getParameter("txtBarras").trim().equals("")) {
            cantBarras = Double.parseDouble(request.getParameter("txtBarras"));
        } else {
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaBarras"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, cantBarras);

            DetalleExamen de = new DetalleExamen(idExamen, idPrueba, fechaExamen, cantBarras, puntaje, idProfe, obs);

            gestor.editarDetalleExamen(de);
        }

        if (cantBarras != 0) {
            //necesito todos los datos para hacer el insert en detalleExamen.
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaBarras"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, cantBarras);

            puntajeAcum += puntaje;
            cantPruebasRendidas++;

            DetalleExamen de = new DetalleExamen(idExamen, idPrueba, fechaExamen, cantBarras, puntaje, idProfe, obs);

            gestor.editarDetalleExamen(de);

        }

        double cantAbs = 0;
        if (!request.getParameter("txtAbdominales").trim().equals("")) {
            cantAbs = Double.parseDouble(request.getParameter("txtAbdominales"));
        } else {
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaAbdominales"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, cantAbs);

            DetalleExamen de = new DetalleExamen(idExamen, idPrueba, fechaExamen, cantAbs, puntaje, idProfe, obs);

            gestor.editarDetalleExamen(de);
        }

        if (cantAbs != 0) {
            //necesito todos los datos para hacer el insert en detalleExamen.
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaAbdominales"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, cantAbs);

            puntajeAcum += puntaje;
            cantPruebasRendidas++;

            DetalleExamen de = new DetalleExamen(idExamen, idPrueba, fechaExamen, cantAbs, puntaje, idProfe, obs);

            gestor.editarDetalleExamen(de);

        }

        double timeCaminata = 0;
        String txtCaminata = request.getParameter("txtCaminata").trim();
        if (!txtCaminata.equals("")) {
            timeCaminata = Double.parseDouble(request.getParameter("txtCaminata"));
        } else {
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaCaminata"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, timeCaminata);

            DetalleExamen de = new DetalleExamen(idExamen, idPrueba, fechaExamen, timeCaminata, puntaje, idProfe, obs);

            gestor.editarDetalleExamen(de);
        }

        if (timeCaminata != 0) {
            //necesito todos los datos para hacer el insert en detalleExamen.
            int idPrueba = Integer.parseInt(request.getParameter("txtIdTipoPruebaCaminata"));
            int puntaje = gestor.obtenerResultado(idCateg, idPrueba, idGenero, timeCaminata);

            puntajeAcum += puntaje;
            cantPruebasRendidas++;

            DetalleExamen de = new DetalleExamen(idExamen, idPrueba, fechaExamen, timeCaminata, puntaje, idProfe, obs);

            gestor.editarDetalleExamen(de);

        }

        double promedio = puntajeAcum / cantPruebasRendidas;

        //update de la nota final que habia sido cargada en cero
        gestor.updateNotaExam(promedio, idExamen);

        String msj = "Editó correctamente el examen nro: " + idExamen;
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
