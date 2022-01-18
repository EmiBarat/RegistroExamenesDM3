/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.AccesoBaseDatos;
import dtoModel.PruebasPromedioDTO;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Emiliano Barat
 */
@WebServlet(name = "GraficoBarrasNotasPorPruebaTodos", urlPatterns = {"/GraficoBarrasNotasPorPruebaTodos"})
public class GraficoBarrasNotasPorPruebaTodos extends HttpServlet {

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

        response.setContentType("image/PNG");
        OutputStream out = response.getOutputStream();
        ArrayList<PruebasPromedioDTO> pruebasPromedios = new ArrayList();
        int idCateg;
        try {
            AccesoBaseDatos gestor = new AccesoBaseDatos();
            try {
                idCateg = Integer.parseInt(request.getParameter("cboCategorias"));
                if (idCateg == 0) {
                    pruebasPromedios = gestor.contarPruebaPromedio();
                }else{
                    pruebasPromedios = gestor.contarPruebaPromedio(idCateg);
                }
                
            } catch (Exception e) {
                pruebasPromedios = gestor.contarPruebaPromedio();
            }
            
            DefaultCategoryDataset data = new DefaultCategoryDataset();
            for (PruebasPromedioDTO item : pruebasPromedios) {
                data.setValue(item.getPromedio(), item.getPrueba(), item.getPrueba());
            }
            JFreeChart chart = ChartFactory.createBarChart("PROMEDIO DE PUNTAJE POR PRUEBA DEL PERSONAL", "PRUEBAS", "NOTAS", data, PlotOrientation.VERTICAL, true, true, true);

            int ancho = 500;
            int alto = 450;

            ChartUtilities.writeChartAsPNG(out, chart, ancho, alto);

        } catch (Exception e) {

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
        processRequest(request, response);
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
