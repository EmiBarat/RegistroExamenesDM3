/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import controller.AccesoBaseDatos;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Pesaje;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import reportes.PesajeBean;
import reportes.ReportRoutine;
import reportes.Reporte;
import reportes.dtos.PesajeDto;

/**
 *
 * @author Emiliano Barat
 */
@WebServlet(name = "ReportePeso", urlPatterns = {"/ReportePeso"})
public class ReportePeso extends HttpServlet {

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
        
        try {
            //recupero la lista filtrada de pesajes desde el servlet
            //para llenar el reporte
            HttpSession session = request.getSession();
            ArrayList<Pesaje> listaReporte = (ArrayList<Pesaje>) session.getAttribute("listaReporte");
            
            response.setContentType("application/PDF");
            response.setHeader("Content-disposition", "attachment; filename=\"my_documents.pdf\"");
            
            JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream(new File("D:\\PracticaSupervisadaUTN\\codigoJavaWeb\\RegistroExamenesDM3\\src\\java\\reportes\\name_report.jrxml")));
            
            
            
            List<PesajeBean> lista = new ArrayList<PesajeBean>();
            
            PesajeBean pesaje;
            
            for (Pesaje item : listaReporte) {
                pesaje = new PesajeBean();
                pesaje.setNombreAlumno(item.getAlumno().getNombres().trim() + " " + item.getAlumno().getApellidos().trim());
                pesaje.setFechaPesaje(item.getFechaPesaje());
                pesaje.setSituacion(item.getEstadoPeso().getDescripcion().trim());
                pesaje.setTratamiento(item.getObservaciones().trim());
                pesaje.setPesoKg(item.getPesoEnKilos());
                pesaje.setImc(item.getIndiceMasaCorporal());
                lista.add(pesaje);
            }
            
//            for (int i = 0; i < 10; i++) {
//                
//            }
            
            Map<String, Object> parameters = new HashMap<String, Object>();
                        
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            
            response.getOutputStream().close();
            parameters.clear();
            dataSource.getData().clear();
    } 
        catch (Exception e) {
            System.out.println(e);
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
