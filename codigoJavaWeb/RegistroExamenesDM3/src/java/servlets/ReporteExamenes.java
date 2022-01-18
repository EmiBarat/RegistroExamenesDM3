/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dtoModel.ExamenDto;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Pesaje;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import reportes.PesajeBean;

/**
 *
 * @author Emiliano Barat
 */
@WebServlet(name = "ReporteExamenes", urlPatterns = {"/ReporteExamenes"})
public class ReporteExamenes extends HttpServlet {

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
            ArrayList<ExamenDto> listaReporte = (ArrayList<ExamenDto>) session.getAttribute("listaExamenesReportFiltrada");
            
            response.setContentType("application/PDF");
            response.setHeader("Content-disposition", "attachment; filename=\"ExamenReporte.pdf\"");
            
            JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream(new File("D:\\PracticaSupervisadaUTN\\codigoJavaWeb\\RegistroExamenesDM3\\src\\java\\reportes\\examenes_report.jrxml")));
            
            
            
            List<reportes.ExamenDto> lista = new ArrayList<reportes.ExamenDto>();
//            
            reportes.ExamenDto examen;
            
            for (ExamenDto item : listaReporte) {
                examen = new reportes.ExamenDto();
                examen.setApellidoAlumno(item.getApellidoAlumno().trim());
                examen.setFechaExamen(item.getFechaExamen().trim());
                examen.setCategoria(item.getCategoria().trim());
                examen.setNombreAlumno(item.getNombreAlumno().trim());
                examen.setIdExamen(item.getIdExamen());
                examen.setNotaFinal(item.getNotaFinal());
                examen.setTipoExamen(item.getTipoExamen());
                examen.setApellidoProfe(item.getApellidoProfe());
                lista.add(examen);
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
