<%-- 
    Document   : listadoExamenesPorAlumno
    Created on : Jun 1, 2021, 5:31:13 PM
    Author     : Emiliano Barat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="estilos.jsp" %>
<jsp:useBean id="gestor" scope="request" class="controller.AccesoBaseDatos"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="">
        <%@include file="header.jsp" %>
        <div class="container">
            <h1>Examenes de ${alumno.nombre} ${alumno.apellido} </h1>
            <!-- <a  style="color: darkblue; margin-right: 50px " class="" href="ListarAlumnos">Volver a listado de alumnos</a>
            <c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario <= 1}" >
                <a style="color: darkblue" href="menuAdmin.jsp">Volver al men&uacute; principal</a>
            </c:if>
            <c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario >= 2}">
                <a href="menuUsuario.jsp">Volver al men&uacute; principal</a>
            </c:if> -->

            <div>
                <h2>Filtrar examenes</h2>
                <form class="form-group d-block " action="ListarExamenesPorAlumno" method="post">
                    <input name="hiddenIdAlumno" type="hidden" value="${alumno.idAlumno}"/>

                    <!--aca pongo los controles para filtrar la lista, se las mando a un servlet
                    y le devuelvo al JSP los mismos PARAMETERS 'alumno' y 'examenes'
                    Empecemos con filtros: FECHA, TIPO DE EXAMEN, APROBADO/REPROBADO-->

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
                            <label class="form-label d-block" for="tipoExamen">Tipo de Examen</label>
                            <select class="form-control d-inline" id="tipoExamen" name="cmbTipoExamen">
                                <c:forEach items="${ gestor.listarTiposExamen()}" var="item">
                                    <option value="${ item.idTipoExamen }">${ item.descripcion }</option>
                                </c:forEach>
                                <option selected value="0">Todos los tipos</option>
                            </select>
                        </div>
                        <div style="padding-right:1px " class="col-auto">
                            <label class="form-label d-block" for="tipoCondicion">Condición</label>
                            <select  class="form-control d-inline" id="tipoCondicion" name="cmbTipoCondicion">

                                <option value="1">Aprobado</option>
                                <option value="2">Reprobado</option>                            
                                <option selected value="0">Todos</option>                            

                            </select>
                        </div>
                    </div>
                    <br/>
                    <div class="row">
                        <div class="col-auto">
                            <input class=" form-control btn btn-primary mb-2" type="submit" value="Aplicar filtros"/>
                        </div>
                    </div>
                </form>

            </div>
        </div>
        <div >
            <table style="width: 100%" class="table table-centered table-striped table-bordered">
                <thead style="background-color: darkgray" >
                    <tr  class="">
                        <th colspan="3">Examen</th>                
                        <!--<th style="vertical-align: middle" rowspan="2">Profesor</th>-->
                        <th style="vertical-align: middle" rowspan="2">Categoria</th>
                        <th colspan="2">Carrera</th>
                        <th colspan="2">Flexiones</th>
                        <th colspan="2">Barras</th>
                        <th colspan="2">Abdominales</th>
                        <th colspan="2">Caminata</th>
                        <th style="vertical-align: middle" rowspan="2">Nota Final</th>
                        <th style="vertical-align: middle" rowspan="2" colspan="2">Acciones</th>
                    </tr>
                    <tr>
                        <th>Nro</th>
                        <th>Tipo</th>
                        <th>Fecha</th>
                        <th>Tiempo</th>
                        <th>Nota</th>
                        <th>Rep</th>
                        <th>Nota</th>
                        <th>Rep</th>
                        <th>Nota</th>
                        <th>Rep</th>
                        <th>Nota</th>                        
                        <th>Tiempo</th>
                        <th>Nota</th>                        
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <c:forEach items="${examenes}" var="item">
                        <tr>
                            <td>${item.idExamen}</td>
                            <td>${item.tipoExamen.trim()}</td>
                            <td>${item.fechaExamen}</td>
                            <!--<td>${item.apellidoProfe}</td>-->
                            <td>${item.categoria}</td>

                            <!--HAGO UN FOREEACH CON UN DTO QUE TRAIGA LAS PRUEBAS RENDIDAS EN ESE EXAMEN
                            Y LO RECORRO Y LLENO LA TABLA SEGUN TRAJO O NO DATOS... IF(RESULTADO)DATOS ELSE SIN REGISTRO-->

                            <c:forEach items="${gestor.listarPruebasRendidasXIdExamen(item.idExamen)}" var="elemento">

                                <c:if  test="${elemento.resultado != 0}" >
                                    <td>${elemento.resultado}</td>
                                    <td>${elemento.puntaje}</td>
                                </c:if>
                                <!--ESTO VA AFUNCIONAR CUANDO A LOS DETALLE DE EXAMEN LES PONGA RESULTADO 0 CUANDO NO RINDEN LA PRUEBA-->
                                <c:if test="${elemento.resultado == 0}" >
                                    <td>sin dato</td>
                                    <td>sin dato</td>
                                </c:if>

                            </c:forEach>

                            <td>${item.notaFinal}</td>
                            <td><a class="btn btn-primary" href="EditarExamen?idExamen=${item.idExamen}">Editar</a></td>
                            <!--<td><a class="btn btn-primary" href="EliminarExamen?idExamen=${item.idExamen}">Eliminar</a></td>-->


                        </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>

    </body>
</html>
