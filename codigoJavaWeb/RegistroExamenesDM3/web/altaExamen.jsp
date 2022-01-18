<%-- 
    Document   : altaExamen
    Created on : May 18, 2021, 4:18:01 PM
    Author     : Emiliano Barat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="estilos.jsp" %>
<jsp:useBean id="gestor" scope="request" class="controller.AccesoBaseDatos"/>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="">
        <%@include file="header.jsp" %>
        <div class="container">
            <h1 class="">Registro de examen</h1>


            <table class="table table-striped table-center table-centered text-center table-bordered">

                <thead>
                    <tr>
                        <th>Grado</th>
                        <th>Apellido</th>
                        <th>Nombre</th>
                        <th>DNI</th>
                        <th>CE</th>
                        <th>Género</th>
                        <th>Fecha Nacimiento</th>
                        <th>Categoria</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>${alumnoDto.grado}</td>
                        <td>${alumnoDto.apellido}</td>
                        <td>${alumnoDto.nombre}</td>
                        <td>${alumnoDto.dni}</td>
                        <td>${alumnoDto.codigoEst}</td>
                        <td>${alumnoDto.genero}</td>
                        <td>${alumnoDto.fechaNac}</td>
                        <td>${categoria}</td>




                    </tr>
                </tbody>
            </table>  
        </div>
        <div class="container">
            <h2 class="">Ingrese los datos del examen</h2>
            <form action="AltaExamen" method="POST" name="formAltaExamen">

                <!--paso el idAlumno y el idCategoria para poder completar los campos de examen.-->
                <input type="hidden" value="${alumnoDto.idAlumno}" name="txtIdAlumno">
                <input type="hidden" value="${idCategoria}" name="txtIdCategoria">
                <input type="hidden" value="${alumnoDto.idGenero}" name="txtIdGenero">

                <div class="row">

                    <div style="height: 100px" class="col-4">
                        <label class="form-label d-block" for="tipoExamen">Tipo de Examen</label>
                        <select class="form-control d-block" id="tipoExamen" name="cmbTipoExamen">
                            <c:forEach items="${ gestor.listarTiposExamen()}" var="item">
                                <option value="${ item.idTipoExamen }">${ item.descripcion }</option>
                            </c:forEach>
                        </select>

                    </div>
                    <div style="height: 100px" class="col-4 ">
                        <div class="form-group ">
                            <label class="form-label d-block" for="profesor">Profesor</label>
                            <select class="form-control d-block" id="profesor" name="cmbProfesor">
                                <c:forEach items="${ gestor.listarProfesores()}" var="item">
                                    <option value="${ item.idProfesor }">${ item.nombre } ${item.apellido}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div style="height: 100px" class="col-4">
                        <label class="form-label d-block" for="fechaExamen">Fecha del Examen</label>
                        <input type="date" autocomplete="true" name="dtpFechaExamen" class="form-control" min="2000-01-01" max="${fechaActual}" value="" > 
                        <!--SERIA INTERESANTE DEJAR LA ULTIMA FECHA QUE INGRESO PARA LA PROXIMA CARGA. (DEJARLA EN SESSION???)-->
                    </div>

                </div>

                </br>
                <!--////////////busco las pruebas de la DB y las pongo como inputs-->       
                <h4>Ingrese los resultados de las pruebas</h4>
                <div class="row">

                    <div class="col">
                        <div class="form-group row">
                            <c:forEach items="${gestor.listarTiposDePrueba()}" var="item">
                                <div class="form-check">
                                    <input type="hidden" name="txtIdTipoPrueba${item.descripcion.trim()}" value="${item.tipoPrueba}">
                                    <label for="${item.descripcion.trim()}" class="col-auto col-form-label">${item.descripcion.trim()}</label>
                                    <div class="col-auto">
                                        <input type="number" min="0" step="0.01" class="form-control" id="${item.descripcion.trim()}" name="txt${item.descripcion.trim()}" placeholder="">
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>

                </div>

                <div class="form-group">
                    <label for="observaciones">Observaciones</label>
                    <textarea class="form-control" id="observaciones" rows="3" name="txtObservaciones"></textarea>
                </div>

                <!--////////////btn sumit////////////-->
                <div class="form-group row">
                    <input  class=" form-control btn btn-primary mb-2" type="submit" value="Registrar">
                </div>
            </form>

    </body>
</html>
