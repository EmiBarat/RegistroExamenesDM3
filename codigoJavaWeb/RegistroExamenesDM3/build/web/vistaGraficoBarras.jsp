<%-- 
    Document   : graficadorExamenesFiltrados
    Created on : Jul 19, 2021, 7:51:50 PM
    Author     : Emiliano Barat
--%>

<%@page import="java.io.OutputStream"%>
<%@page import="org.jfree.chart.ChartUtilities"%>
<%@page import="org.jfree.chart.plot.PlotOrientation"%>
<%@page import="org.jfree.chart.JFreeChart"%>
<%@page import="org.jfree.chart.ChartFactory"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dtoModel.PruebasPromedioDTO"%>
<%@page import="org.jfree.data.category.DefaultCategoryDataset"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="estilos.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="gestor" scope="request" class="controller.AccesoBaseDatos" />

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            
            <div class="row">
                <div class="col-6">
                    <h1>Su gráfico</h1>
                    <img src="/RegistroExamenesDM3/CreadorGraficoBarras">
                </div>
                <div class="col-6">
                    <br>
                    <br>
                    <br>
                    <h2>Seleccione los filtros para generar un nuevo gráfico</h2>
                    <form class="form d-block " action="GraficoExamenFiltrado" method="post">
                        <div class="form-group row">
                            <div class="col-auto">
                                <label class="form-label" for="categorias">Categoria</label>
                                <select class="form-control" id="categorias" name="cboCategorias">
                                    <c:forEach items="${gestor.listarCategorias()}" var="item">
                                        <option value="${item.idCategoria}">${item.descripcion}</option>
                                    </c:forEach>
                                    <option value="0" selected="">Todas</option>
                                </select>
                            </div>
                            <div style="padding-right:1px " class="col-auto">
                                <label class="form-label " for="tipoCondicion">Condición</label>
                                <select  class="form-control " id="tipoCondicion" name="cmbTipoCondicion">

                                    <option value="1">Aprobado</option>
                                    <option value="2">Reprobado</option>                            
                                    <option selected value="0">Todos</option>                            

                                </select>
                            </div>
                        </div>
                        <div class="form-group row">                            
                            <div style="padding-right:1px" class="col-auto">
                                <label class="form-label d-block" for="fechaDesdeExamen">Fecha desde...</label>
                                <input class="form-control d-block" type="date" name="dtpFechaDesdeExamen" id="fechaDesdeExamen"/>
                            </div>
                            <div style="padding-right:1px" class="col-auto">
                                <label class="form-label d-block" for="fechaHastaExamen">Fecha hasta...</label>
                                <input class="form-control d-block" type="date" name="dtpFechaHastaExamen" id="fechaHastaExamen"/>
                            </div>
                        </div>

                        <!--aca pongo los controles para filtrar la lista, se las mando a un servlet
                        y le devuelvo al JSP los mismos PARAMETERS 'alumno' y 'examenes'
                        Empecemos con filtros: FECHA, TIPO DE EXAMEN, APROBADO/REPROBADO-->
                        <div style="align-items: flex-end " class="row">

                            <div style="padding-right:1px" class="col-auto">
                                <label class="form-label d-inline" for="tipoExamen">Tipo de Examen</label>
                                <select class="form-control d-inline" id="tipoExamen" name="cmbTipoExamen">
                                    <c:forEach items="${ gestor.listarTiposExamen()}" var="item">
                                        <option value="${ item.idTipoExamen }">${ item.descripcion }</option>
                                    </c:forEach>
                                    <option selected value="0">Todos los tipos</option>
                                </select>
                            </div>
                            
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-auto">
                                <input class=" form-control btn btn-primary mb-2" type="submit" value="Aplicar filtros"/>

                            </div>
                            <div class="col-auto">
                                <a href="#" class="btn btn-primary mb-2">Deshacer los filtros</a>
                            </div>

                        </div>
                    </form>
                </div>
                
            </div>
        </div>        
    </body>
</html>
