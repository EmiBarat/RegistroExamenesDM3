<%-- 
    Document   : listadoExamenes
    Created on : Jun 7, 2021, 8:22:27 PM
    Author     : Emiliano Barat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="estilos.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="gestor" scope="request" class="controller.AccesoBaseDatos" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="">
        <%@include file="header.jsp" %>
        <div class="container">
            <h1>Listado de Examenes</h1>
            <h3>${msj}</h3>

            <!--<c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario <= 1}" >
                <a style="color: darkblue" href="menuAdmin.jsp">Volver al men&uacute; principal</a>
            </c:if>
            <c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario >= 2}">
                <a style="color: darkblue" href="menuUsuario.jsp">Volver al men&uacute; principal</a>
            </c:if>-->
            <h2>Filtrar examenes...</h2>
            <form class="form d-block " action="ListarExamenes" method="post">
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
                        <label class="form-label" for="categorias">Categoria</label>
                        <select class="form-control" id="categorias" name="cboCategorias">
                            <c:forEach items="${gestor.listarCategorias()}" var="item">
                                <option value="${item.idCategoria}">${item.descripcion}</option>
                            </c:forEach>
                            <option value="0" selected=""></option>
                        </select>
                    </div>


                </div>


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
                        <label class="form-label d-inline" for="tipoExamen">Tipo de Examen</label>
                        <select class="form-control d-inline" id="tipoExamen" name="cmbTipoExamen">
                            <c:forEach items="${ gestor.listarTiposExamen()}" var="item">
                                <option value="${ item.idTipoExamen }">${ item.descripcion }</option>
                            </c:forEach>
                            <option selected value="0">Todos los tipos</option>
                        </select>
                    </div>
                    <div style="padding-right:1px " class="col-auto">
                        <label class="form-label d-inline" for="tipoCondicion">Condición</label>
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
                    <div class="col-auto">
                        <a href="ListarExamenes" class="btn btn-primary mb-2">Deshacer los filtros</a>
                    </div>
                    <div class="col-auto">
                        <a href="ReporteExamenes" class="btn btn-primary mb-2">Generara Reporte</a>
                    </div>
                </div>
            </form>

        </div>

        <div class="container">
            <!--EN ESTA TABLA MUESTRO LOS RESULTADOS.-->
            <table class="table table-centered table-striped table-bordered">
                <thead>
                    <tr>
                        <th style="vertical-align: middle" rowspan="2"  class="">Nro Examen</th>
                        <th colspan="2" class="">Alumno</th>
                        <th style="vertical-align: middle" rowspan="2" >Categoria</th>
                        <th colspan="2" class="">Examen</th>
                        <th rowspan="2" style="vertical-align: middle" >Profesor</th>
                        <th rowspan="2" style="vertical-align: middle" >Nota</th>
                        <th colspan="" rowspan="2" style="vertical-align: middle; text-align: center " >Acciones</th>
                    </tr>
                    <tr>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Tipo</th>
                        <th>Fecha</th>
                    </tr>
                </thead>
                <tbody>

                <script type="text/javascript">
                    if (${listaExamenes})
                        alert("No hay resultados para esos filtros");
                </script>

                <c:forEach items="${listaExamenes}" var="item">
                    <tr>                            
                        <td style="vertical-align: middle; text-align: center">${item.idExamen}</td>
                        <td>${item.nombreAlumno}</td>
                        <td>${item.apellidoAlumno}</td>
                        <td>${item.categoria}</td>
                        <td>${item.tipoExamen}</td>
                        <td>${item.fechaExamen}</td>
                        <td>${item.apellidoProfe}</td>
                        <td>${item.notaFinal}</td>

                        <td style="vertical-align: middle; text-align: center"><a class="btn btn-primary" href="EditarExamen?idExamen=${item.idExamen}">Ver/Editar pruebas</a></td>
<!--                        <td><a class="btn btn-primary" href="EliminarExamen?idExamen=${item.idExamen}">Eliminar Examen</a></td>-->

                    </tr>
                </c:forEach>


                </tbody>
            </table>
        </div>
    </div>
</body>
</html>
