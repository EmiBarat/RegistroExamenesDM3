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
import java.util.ArrayList;
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
@WebServlet(name = "ListarExamenesPorAlumno", urlPatterns = {"/ListarExamenesPorAlumno"})
public class ListarExamenesPorAlumno extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
            int idAlumno = Integer.parseInt(request.getParameter("idAlumno"));

            AccesoBaseDatos gestor = new AccesoBaseDatos();

            ArrayList lista = gestor.buscarExamenPorAlumno(idAlumno, "");
            request.setAttribute("examenes", lista);

            ListadoAlumnosDTO alumno = gestor.buscarAlumno(idAlumno);
            request.setAttribute("alumno", alumno);

            RequestDispatcher rd = request.getRequestDispatcher("listadoExamenesPorAlumno.jsp");
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

        AccesoBaseDatos gestor = new AccesoBaseDatos();

        int idAlumno = Integer.parseInt(request.getParameter("hiddenIdAlumno"));
        String fechaDesde = request.getParameter("dtpFechaDesdeExamen");
        String fechaHasta = request.getParameter("dtpFechaHastaExamen");
        int tipoExamen = Integer.parseInt(request.getParameter("cmbTipoExamen"));
        int condicion = Integer.parseInt(request.getParameter("cmbTipoCondicion"));

        String sqlWhereTxt = "";

//      Al venir los campos vacios o con valor 0 no los incluyo en el parametro del select.
        if (!fechaDesde.equals("") && !fechaHasta.equals("")) {
            sqlWhereTxt += "and e.fechaExamen between '" + fechaDesde + "' and '" + fechaHasta + "' ";
        }
        if (tipoExamen != 0) {
            sqlWhereTxt += "and te.idTipoExamen = " + tipoExamen + " ";
        }
        if (condicion == 1) {
            sqlWhereTxt += "and e.notaFinal >= 60";
        }
        if (condicion == 2) {
            sqlWhereTxt += "and e.notaFinal < 60";
        }

        ArrayList listaFiltrada = gestor.buscarExamenPorAlumno(idAlumno, sqlWhereTxt);
        request.setAttribute("examenes", listaFiltrada);

        ListadoAlumnosDTO alumno = gestor.buscarAlumno(idAlumno);
        request.setAttribute("alumno", alumno);

//      
        RequestDispatcher rd = request.getRequestDispatcher("listadoExamenesPorAlumno.jsp");
        rd.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
