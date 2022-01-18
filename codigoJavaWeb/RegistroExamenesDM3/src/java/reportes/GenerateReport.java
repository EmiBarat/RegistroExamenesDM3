// /*
// * Esto fue una prueba, parte de la investigacion para hace los repotes
// */
//package reportes;
//
//import controller.AccesoBaseDatos;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
//
///**
// *
// * @author Emiliano Barat
// */
//@WebServlet(name = "GenerateReport", urlPatterns = {"/GenerateReport"})
//public class GenerateReport extends HttpServlet {
//
//    /**
//     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
//     * methods.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            AccesoBaseDatos gestor = new AccesoBaseDatos();
//            
//            response.setContentType("application/PDF");
//            response.setHeader("Content-disposition", "attachment; filename=\"my_documents.pdf\"");
//            
//            JasperReport jasperReport = JasperCompileManager.compileReport(new FileInputStream(new File("D:\\PracticaSupervisadaUTN\\codigoJavaWeb\\RegistroExamenesDM3\\src\\java\\reportes\\name_report.jrxml")));
//            
//            
//            
//            List<PesajeBean> lista = new ArrayList<PesajeBean>();
//            
//            PesajeBean pesaje;
//            
//            for (int i = 0; i < 10; i++) {
//                pesaje = new PesajeBean();
//                pesaje.setNombreAlumno("Nombre " + (i +1));
//                pesaje.setFechaPesaje("2021-07-" + (i +1));
//                pesaje.setSituacion("normal " + (i +1));
//                pesaje.setTratamiento("Si " + (i +1));
//                pesaje.setPesoKg(12 * (i+1 ));
//                pesaje.setImc(0.1 * (i+1 ));
//                lista.add(pesaje);
//            }
//            
//            Map<String, Object> parameters = new HashMap<String, Object>();
////            parameters.put("company_name", "El Dorado"); 
////            parameters.put("company_address", "Canimo Verde Km 233, Misiones");        
////            
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//            
//            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
//            
//            response.getOutputStream().close();
//            parameters.clear();
//            dataSource.getData().clear();
//    } 
//        catch (Exception e) {
//            System.out.println(e);
//        }
//    }
//
//    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
//    /**
//     * Handles the HTTP <code>GET</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
