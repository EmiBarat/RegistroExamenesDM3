<%-- 
    Document   : visualGraficos
    Created on : Jun 22, 2021, 10:49:17 AM
    Author     : Emiliano Barat
--%>

<%@page import="org.jfree.chart.ChartUtilities"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="estilos.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="gestor" scope="request" class="controller.AccesoBaseDatos" />
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body style="">
        <%@include file="header.jsp" %>
        <div style="margin-bottom: 20px" class="container">
            <h1>Distibución de peso del personal</h1>
            

        </div>
        <div class="container">
            <div class="row">                
                <div class="col-auto">
                    <img src="/RegistroExamenesDM3/GraficoPiePesoTodos"></img>
                </div>                
            </div>
        </div>
    </div>

</body>
</html>
