/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.AccesoBaseDatos;
import dtoModel.PruebasPromedioDTO;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Emiliano Barat
 */
@WebServlet(name = "GraficoExamenFiltrado", urlPatterns = {"/GraficoExamenFiltrado"})
public class GraficoExamenFiltrado extends HttpServlet {

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
        processRequest(request, response);
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
        HttpSession session = request.getSession();

        //Agregar los campos nuevos de filtro
//        String txtNombreAlumno = request.getParameter("txtNombreAlumno");
//        String txtApellidoAlumno = request.getParameter("txtApellidoAlumno");
        int idCategoriaSelected = Integer.parseInt(request.getParameter("cboCategorias"));

//        int idAlumno = Integer.parseInt(request.getParameter("hiddenIdAlumno"));
        String fechaDesde = request.getParameter("dtpFechaDesdeExamen");
        String fechaHasta = request.getParameter("dtpFechaHastaExamen");
        int tipoExamen = Integer.parseInt(request.getParameter("cmbTipoExamen"));
        int condicion = Integer.parseInt(request.getParameter("cmbTipoCondicion"));

        String sqlWhereTxt = "";

        //Agragar las condiciones de filtrado nuevas
//        if (!txtNombreAlumno.equals("")) {
//            sqlWhereTxt += "and a.nombres like '" + txtNombreAlumno + "%' ";
//        }
//
//        if (!txtApellidoAlumno.equals("")) {
//            sqlWhereTxt += "and a.apellido like '" + txtApellidoAlumno + "%' ";
//        }
        if (idCategoriaSelected != 0) {
            sqlWhereTxt += "and e.idCategoria = " + idCategoriaSelected + " ";
        }

//      Al venir los campos vacios o con valor 0 no los incluyo en el parametro del select.
        if (!fechaDesde.equals("") && !fechaHasta.equals("")) {
            sqlWhereTxt += "and e.fechaExamen between '" + fechaDesde + "' and '" + fechaHasta + "' ";
        }
        if (tipoExamen != 0) {
            sqlWhereTxt += "and e.idTipoExamen = " + tipoExamen + " ";
        }
        if (condicion == 1) {
            sqlWhereTxt += "and e.notaFinal >= 60";
        }
        if (condicion == 2) {
            sqlWhereTxt += "and e.notaFinal < 60";
        }

        ArrayList<PruebasPromedioDTO> listaFiltrada = gestor.contarPruebaPromedioFiltrado(sqlWhereTxt);
        session.setAttribute("listaExamenesGraficos", listaFiltrada);

        

//        String path = request.getContextPath();
//        response.sendRedirect(path + "/vistaGraficoBarras.jsp");

        RequestDispatcher rd = request.getRequestDispatcher("vistaGraficoBarras.jsp");
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
