/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.AccesoBaseDatos;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Emiliano Barat
 */
@WebServlet(name = "GraficoPiePesoTodos", urlPatterns = {"/GraficoPiePesoTodos"})
public class GraficoPiePesoTodos extends HttpServlet {

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
        
        try {
            
            AccesoBaseDatos gestor = new AccesoBaseDatos();
            ArrayList<Integer> cantGenero = gestor.contarEstadosPeso();
            
            int bajo = cantGenero.get(0);
            int normo = cantGenero.get(1);
            int sobre = cantGenero.get(2);
            int obe1 = cantGenero.get(3);
            int obe2 = cantGenero.get(4);
            int obe3 = cantGenero.get(5);
            
            DefaultPieDataset data = new DefaultPieDataset();            
            data.setValue("Peso normal: "+ String.valueOf(normo), normo);
            data.setValue("Bajo peso: "+ String.valueOf(bajo), bajo);
            data.setValue("Sobre peso: "+ String.valueOf(sobre), sobre);
            data.setValue("Obesidad I: "+ String.valueOf(obe1), obe1);
            data.setValue("Obesidad II: "+ String.valueOf(obe2), obe2);
            data.setValue("Obesidad III: "+ String.valueOf(obe3), obe3);
            
            JFreeChart chart = ChartFactory.createPieChart("SITUACION DE PESO DEL PERSONAL", data, true, true, true);
            
            
            int ancho = 500;
            int alto = 400;
            
            
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
