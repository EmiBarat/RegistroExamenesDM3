<%-- 
    Document   : listarPesos
    Created on : Jun 24, 2021, 11:28:37 AM
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
            <h1>Registro de peso del alumno ${alumno.nombre} ${alumno.apellido} </h1>
            <!--<a style="margin-right: 50px; color: darkblue" class="" href="ListarAlumnosPeso">Volver a listado de alumnos</a>
            <c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario <= 1}" >
                <a style="color: darkblue" href="menuAdmin.jsp">Volver al men&uacute; principal</a>
            </c:if>
            <c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario >= 2}">
                <a style="color: darkblue" href="menuUsuario.jsp">Volver al men&uacute; principal</a>
            </c:if>-->
        </div>

        <div class="container">
            <table class="table table-centered table-striped table-bordered">
                <thead >
                    <tr  class="">
                        <th >Fecha</th>                
                        <th >Peso</th>
                        <th >IMC</th>
                        <th >Esatado</th>
                        <th >En tratamiento?</th>
                        <th >Observaciones</th>
                        <th colspan="2">Acciones</th>
                        <!--                        <th colspan="2">Caminata</th>
                                                <th style="vertical-align: middle" rowspan="2">Nota Final</th>-->
                    </tr>

                </thead>
                <tbody>

                    <c:forEach items="${listaPeso}" var="item">
                        <tr>
                            <td>${item.fechaPesaje}</td>
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
