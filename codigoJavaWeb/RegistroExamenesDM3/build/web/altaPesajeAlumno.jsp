<%-- 
    Document   : altaPesajeAlumno
    Created on : Jun 22, 2021, 12:32:57 PM
    Author     : Emiliano Barat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="estilos.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <h1>Formulario Pesaje</h1>
            <!--<c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario <= 1}" >
                <a style="color: darkblue" href="menuAdmin.jsp">Volver al men&uacute; principal</a>
            </c:if>
            <c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario >= 2}">
                <a style="color: darkblue" href="menuUsuario.jsp">Volver al men&uacute; principal</a>
            </c:if>
            <div class="row">

    <div class="col-2"></div>-->
            <div class="col-4">
                <form action="AltaPesajeAlumno" name="formAltaPesajeALumno" method="POST">

                    <label for="peso" class="form-label">Alumno</label>
                    <input type="text" class="form-control" name="txtNombreApellidoAlumno" id="peso" disabled value="${alumno.nombres.trim()} ${alumno.apellidos.trim()}">

                    <input type="hidden" class="form-control" id="idAlumno" name="txtIdAlumno" value="${alumno.idAlumno}">
                    <input type="hidden" class="form-control" id="talla" name="txtTalla" value="${alumno.tallaEnMetros}">


                    <label for="peso" class="form-label">Peso (en kilos)</label>
                    <input type="number" class="form-control" name="txtPeso" id="peso" requiered>

                    <label for="fechaPesaje" class="form-label">Fecha Pesaje</label>
                    <input min="1950-01-01" max="${LocalDate}" autocomplete="${LocalDate}" type="date" name="dtpFechaPesaje" class="form-control" id="fechaPesaje" required="required">
                    <br>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" value="1" id="enTratamieto" name="chkTratamiento">
                        <label class="form-check-label" for="enTratamieto">
                            En Tratamiento?
                        </label>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="observaciones">Observaciones</label>
                        <textarea class="form-control" id="observaciones" rows="3" name="txtObservaciones"></textarea>
                    </div>

                    <button type="submit" class="btn btn-success" >Cargar</button>


                </form>
            </div>
            <div class="col-6"></div>
        </div>
    </div>
</body>
</html>
