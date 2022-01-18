<%-- 
    Document   : reportPeso
    Created on : Jul 12, 2021, 7:46:28 PM
    Author     : Emiliano Barat
--%>

<%@page import="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controller.AccesoBaseDatos"%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte</title>
    </head>
    <body>
        
        <%
            AccesoBaseDatos gestor = new AccesoBaseDatos();
            ArrayList lista = gestor.listarTiposExamen();
//            HttpSession session = request.getSession();
            Connection cn;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn = DriverManager.getConnection("jdbc:sqlserver://LAPTOP-AORGGV7T\\SQLEXPRESS:1433;databaseName=DM3_Examenes_EduFis", "sa", "administrador");
            File reportFile = new File(session.getServletContext().getRealPath("reportTipoExamen.jasper"));
            Map parameters = new HashMap();

//            parameters.put("nombre", "Emma");
//            parameters.put("idEstadoPeso", 2);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista, true);
            byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, cn);
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream outputStream = response.getOutputStream();
            outputStream.write(bytes, 0, bytes.length);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        %>
        
    </body>
</html>
