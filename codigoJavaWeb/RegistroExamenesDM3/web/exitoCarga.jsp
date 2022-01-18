<%-- 
    Document   : exitoCarga
    Created on : May 24, 2021, 11:57:30 AM
    Author     : Emiliano Barat
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean  id="gestor" scope="request" class="controller.AccesoBaseDatos"/>

<%@include file="estilos.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Examenes eduFis</title>
    </head>
    <body style="">
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="row d-flex">
                <div class="col-6">
                    <h1>Acci&oacute;n realizada con &eacute;xito</h1>
                    
                </div>
                <div class="col-6">
                    <img width="400px" src="img/saludo1.png">
                    
                </div>
            </div>

<!--<h3>${msj}</h3>-->


            <!--            <br>
                        <div style="margin-bottom: 50px">
                            <a style="margin-right: 50px" href="ListarAlumnos" class="btn btn-primary">Volver a listado de Alumnos</a>
                            <a style="margin-right: 50px" href="ListarAlumnos" class="btn btn-primary">Registrar nuevo examen</a>
                            <a style="margin-right: 50px" href="ListarExamenes" class="btn btn-primary">Volver a listado de examenes</a>
                        </div>
                        <div>
                            <a style="margin-right: 50px" href="ListarAlumnosPeso" class="btn btn-primary">Volver al registro de peso</a>            
            <c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario <= 1}" >
                <a class="btn btn-primary" href="menuAdmin.jsp">Volver al men&uacute; principal</a>
            </c:if>
            <c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario >= 2}">
                <a class="btn btn-primary" href="menuUsuario.jsp">Volver al men&uacute; principal</a>
            </c:if>
        </div>-->




            <!--DEBERIA HABER UNA FORMA DE EDITAR ANTES DE MANDAR A LA BASE DE DATOS... SE PODRIA HACER EL VISUALIZAR COMO UNA VISTA PREVIA-->

        </div>
    </body>
</html>
