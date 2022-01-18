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
@WebServlet(name = "ListarPesosTodos", urlPatterns = {"/ListarPesosTodos"})
public class ListarPesosTodos extends HttpServlet {

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
            try {

                AccesoBaseDatos gestor = new AccesoBaseDatos();

                ArrayList lista = gestor.listarPesoTodos();
                request.setAttribute("listaPesoTodos", lista);
                session.setAttribute("listaReporte", lista);

                RequestDispatcher rd = request.getRequestDispatcher("listarPesosTodos.jsp");
                rd.forward(request, response);
            } catch (Exception e) {
                String path = request.getContextPath();
                response.sendRedirect(path + "/errorCarga.jsp");
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

        //Agregar los campos nuevos de filtro
        String txtNombreAlumno = request.getParameter("txtNombreAlumno");
        String txtApellidoAlumno = request.getParameter("txtApellidoAlumno");
        String fechaDesde = request.getParameter("dtpFechaDesdeExamen");
        String fechaHasta = request.getParameter("dtpFechaHastaExamen");
        int genero = Integer.parseInt(request.getParameter("cmbTipoGenero"));
        int idSituacionSelected = Integer.parseInt(request.getParameter("cboSituacion"));
        int idGrado = Integer.parseInt(request.getParameter("cmbTipoGrado"));
        

        String sqlWhereTxt = "where 1 = 1 ";

        //Agragar las condiciones de filtrado nuevas
        if (!txtNombreAlumno.equals("")) {
            sqlWhereTxt += "and a.nombres like '" + txtNombreAlumno + "%' ";
        }

        if (!txtApellidoAlumno.equals("")) {
            sqlWhereTxt += "and a.apellido like '" + txtApellidoAlumno + "%' ";
        }

        
        
        if (idSituacionSelected != 0) {
            sqlWhereTxt += "and p.idEstadoPeso = " + idSituacionSelected + " ";
        }
        
        if (idGrado != 0) {
            sqlWhereTxt += "and a.idGrado = " + idGrado + " ";
        }

//      Al venir los campos vacios o con valor 0 no los incluyo en el parametro del select.
        if (!fechaDesde.equals("") && !fechaHasta.equals("")) {
            sqlWhereTxt += "and p.fechaPesaje between '" + fechaDesde + "' and '" + fechaHasta + "' ";
        }
        if (genero != 0) {
            sqlWhereTxt += "and a.idGenero = " + genero + " ";
        }
        

        ArrayList listaFiltrada = gestor.listarPesajesFiltrados(sqlWhereTxt);
        request.setAttribute("listaPesoTodos", listaFiltrada);
        
        HttpSession session = request.getSession();
        session.setAttribute("listaReporte", listaFiltrada);

//        ListadoAlumnosDTO alumno = gestor.buscarAlumno(idAlumno);
//        request.setAttribute("alumno", alumno);    
        RequestDispatcher rd = request.getRequestDispatcher("listarPesosTodos.jsp");
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
