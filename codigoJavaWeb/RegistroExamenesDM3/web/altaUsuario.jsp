<%-- 
    Document   : altaAlumno
    Created on : May 13, 2021, 11:06:03 AM
    Author     : Emiliano Barat
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="gestor" scope="request" class="controller.AccesoBaseDatos"/>


<!DOCTYPE html>
<html>
    <head>
        <%@include file="estilos.jsp" %>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>-->
        <title>Alta Usuario</title>

    </head>
    <body style="">
        <%@include file="header.jsp" %>
        <div class="container">
            <h1>Formulario Alta Usuario</h1>
            <!--<c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario <= 1}" >
                <a style="color: darkblue" href="menuAdmin.jsp">Volver al men&uacute; principal</a>
            </c:if>
            <c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario >= 2}">
                <a style="color: darkblue" href="menuUsuario.jsp">Volver al men&uacute; principal</a>
            </c:if>-->
            <div class="col-2"></div>
            <div class="col-4">

                <form action="AltaUsuario" name="formAltaUsusario" method="POST">

                    <label for="nombreUsuario" class="form-label">Nombre del nuevo usuario</label>
                    <input type="text" class="form-control" id="nombreUsuario" name="txtNombreUsuario" required="required">
                    

                    <label for="password" class="form-label">Asignele un password</label>
                    <input type="text" class="form-control" name="txtPassword" id="password" required="required">

                    
                    
                    <label class="form-label" for="tipoUsuario">Que rol le va a asignar??</label>
                    <select class="form-control" id="tipoUsuario" name="cmbTipoUsuario" required="required">
                        <option selected disabled="true">Seleccione una opcion</option>
                        <c:forEach items="${ gestor.listarTiposUsuarios()}" var="item">
                            <option value="${ item.getIdTipoUsuario() }">${ item.tipoUsuario }</option>                            
                        </c:forEach>
                    </select>

                    
                    <label for="preguntaSecreta" class="form-label">Pregunta secreta (recuperacion de password)</label>
                    <input type="text" class="form-control" id="preguntaSecreta" name="txtPreguntaSecreta" required="required">

                    
                    
                    <label for="respuestaSecreta" class="form-label">Respuesta secreta</label>
                    <input type="text" class="form-control" name="txtRespuestaSecreta" id="respuestaSecreta">
                    <br/>
                    <button type="submit" class="btn btn-primary" >Enviar</button>


                </form>
            </div>
        </div>
    </body>
</html>
