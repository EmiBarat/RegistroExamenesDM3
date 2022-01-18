<%-- 
    Document   : errorCarga
    Created on : May 14, 2021, 1:30:00 PM
    Author     : Emiliano Barat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="estilos.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="">
        <%@include file="header.jsp" %>
        <div class="container">
            <h1>Se ha producido un error</h1>
            <h3>Regrese al menu principal y reintente la acción</h3>
            <h4>si el error persiste comuniquese con un administrador o contacte con el servicio técnico</h3>
            <p>${msj}</p>
            <!--<c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario <= 1}" >
                <a class="btn btn-primary" style="" href="menuAdmin.jsp">Volver al menu</a>
            </c:if>
            <c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario >= 2}">
                <a class="btn btn-primary" style=""  href="menuUsuario.jsp">Volver al menu</a>
            </c:if>-->
                <a class="btn btn-primary" style="margin-left: " href="contactoFormulario.jsp">Contacto</a>
            
        </div>
    </body>
</html>
