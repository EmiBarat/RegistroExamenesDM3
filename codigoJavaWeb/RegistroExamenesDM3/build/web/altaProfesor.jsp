<%-- 
    Document   : altaAlumno
    Created on : May 13, 2021, 11:06:03 AM
    Author     : Emiliano Barat
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="gestor" scope="request" class="controller.AccesoBaseDatos"/>
<%@include file="estilos.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>-->
        <title>AltaUsuario</title>

    </head>
    <body style="">
        <%@include file="header.jsp" %>
        <div class="container">
            <h1>Formulario Alta Profesor</h1>
            
            <div class="col-2"></div>
            <div class="col-4">

                <form action="AltaProfesor" name="formAltaProfesor" method="POST">

                    <label for="nombreProfesor" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="nombreProfesor" name="txtNombreProfesor" required="required">
                    

                    <label for="apellidoProfesor" class="form-label">Apellido</label>
                    <input type="text" class="form-control" name="txtApellidoProfesor" id="apellidoProfesor" required="required">
                    
                    <label for="codEstadistico" class="form-label">CE</label>
                    <input type="text" class="form-control" name="txtCodEstadistico" id="codEstadistico" required="required">

                    <label for="dni" class="form-label">DNI</label>
                    <input type="text" class="form-control" name="txtDni" id="dni" required="required">
                    
                    
                    
                    <label class="form-label" for="grado">Grado</label>
                    <select class="form-control" id="grado" name="cmbGrado" required="required">
                        <option selected disabled="true">Seleccione una opcion</option>
                        <c:forEach items="${ gestor.listarGrados()}" var="item">
                            <option value="${ item.getIdGrado() }">${ item.nombreGrado }</option>                            
                        </c:forEach>
                    </select>
                    <br>
                    
                    <button type="submit" class="btn btn-success" >Enviar</button>


                </form>
            </div>
        </div>
    </body>
</html>
