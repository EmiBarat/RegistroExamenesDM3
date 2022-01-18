<%-- 
    Document   : listarPesos
    Created on : Jun 24, 2021, 11:28:37 AM
    Author     : Emiliano Barat
--%>

<%@page import="model.Pesaje"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="estilos.jsp" %>
<jsp:useBean id="gestor" scope="request" class="controller.AccesoBaseDatos"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>lista pesajes</title>
    </head>
    <body style="">
        <%@include file="header.jsp" %>
        <div class="container">
            <h1>Listado de pesajes</h1>
            <h2>Filtrar por...</h2>
            
            <form class="form d-block " action="ListarPesosTodos" method="post">
<!--                <input name="hiddenIdAlumno" type="hidden" value="${alumno.idAlumno}"/>-->

                <div class="form-group row">
                    <div class="col-auto">
                        <label class="form-label" for="nombreAlumno">Por nombre</label>
                        <input class="form-control" id="nombreAlumno" name="txtNombreAlumno" type="text"/>
                    </div>
                    <div class="col-auto">
                        <label class="form-label" for="apellidoAlumno">Por apellido</label>
                        <input class="form-control" id="apellidoAlumno" name="txtApellidoAlumno" type="text"/>
                    </div>

                    <div class="col-auto">
                        <label class="form-label" for="categorias">Situacion de peso</label>
                        <select class="form-control" id="situacion" name="cboSituacion">
                            <c:forEach items="${gestor.listarEstadoPeso()}" var="item">
                                <option value="${item.idTipoEstadoPeso}">${item.descripcion}</option>
                            </c:forEach>
                            <option value="0" selected="">Tadas los estados</option>
                        </select>
                    </div>


                </div>

                <div style="align-items: flex-end " class="row">
                    <div style="padding-right:1px" class="col-auto">
                        <label class="form-label d-block" for="fechaDesdeExamen">Fecha desde...</label>
                        <input class="form-control d-block" type="date" name="dtpFechaDesdeExamen" id="fechaDesdeExamen"/>
                    </div>
                    <div style="padding-right:1px" class="col-auto">
                        <label class="form-label d-block" for="fechaHastaExamen">Fecha hasta...</label>
                        <input class="form-control d-block" type="date" name="dtpFechaHastaExamen" id="fechaHastaExamen"/>
                    </div>

                    <div style="padding-right:1px" class="col-auto">
                        <label class="form-label d-inline" for="tipoGenero">...por genero</label>
                        <select class="form-control d-inline" id="tipoGenero" name="cmbTipoGenero">
                            <c:forEach items="${ gestor.listarGeneros()}" var="item">
                                <option value="${ item.idGenero }">${ item.nombreGenero }</option>
                            </c:forEach>
                            <option selected value="0">Todos los tipos</option>
                        </select>
                    </div>
                    <div style="padding-right:1px " class="col-auto">
                        <label class="form-label d-inline" for="tipoGrado">...por grado</label>
                        <select  class="form-control d-inline" id="tipoGrado" name="cmbTipoGrado">
                            <c:forEach items="${ gestor.listarGrados()}" var="item">
                                <option value="${ item.idGrado }">${ item.nombreGrado }</option>
                            </c:forEach>
                            <option selected value="0">Todos los Grados</option>
                        </select>
                    </div>
                </div>
                <br/>
                <div class="container row">
                    <div class="col-auto">
                        <input class=" form-control btn btn-primary mb-2" type="submit" value="Aplicar filtros"/>

                    </div>
                    <div class="col-auto">
                        <a href="ListarPesosTodos" class="btn btn-primary mb-2">Deshacer los filtros</a>
                    </div>
                    <div class="col-auto">
                        <a href="ReportePeso" class="btn btn-primary mb-2">Generar Reporte</a>
                    </div>

                </div>
            </form>
        </div>

        <div class="container">
            <table class="table table-centered table-striped table-bordered">
                <thead >
                    <tr  class="">
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th >Fecha</th>                
                        <th >Peso</th>
                        <th >IMC</th>
                        <th >Esatado</th>
                        <th >En tratamiento?</th>
                        <th >Observaciones</th>
                        <th colspan="2">Acciones</th>                        
                    </tr>

                </thead>
                <tbody>

                    <c:forEach items="${listaPesoTodos}" var="item">
                        
                        <tr>
                            <td>${item.alumno.nombres}</td>
                            <td>${item.alumno.apellidos}</td>
                            <td style="white-space: nowrap">${item.fechaPesaje}</td>
                            <td>${item.pesoEnKilos}</td>
                            <td>${item.indiceMasaCorporal}</td>
                            <td>${item.estadoPeso.descripcion}</td>
                            <c:if test="${item.bajoSeguimiento}">
                                <td><input type="checkbox"   checked=""></td>
                                </c:if>
                                <c:if test="${!item.bajoSeguimiento}">
                                <td><input  type="checkbox"> </td>
                                </c:if>
                            <td>${item.observaciones}</td>
                            <td><a class="btn btn-primary" href="EditarPesaje?idPesaje=${item.idPesaje}">Editar</a></td>
                            <td><a class="btn btn-primary" href="EliminarPesaje?idPesaje=${item.idPesaje}">Eliminar</a></td>
                        </tr>

                    </c:forEach>

                </tbody>
            </table>
        </div>
    </body>
</html>
