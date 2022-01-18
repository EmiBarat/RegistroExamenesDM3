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
        <title>EditarAlumno</title>

    </head>
    <body style="">
        <%@include file="header.jsp" %>
        <div class="container">
            <h1>Formulario Edición de Alumno</h1>
            <!--<c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario <= 1}" >
                <a style="color: darkblue" href="menuAdmin.jsp">Volver al men&uacute; principal</a>
            </c:if>
            <c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario >= 2}">
                <a style="color: darkblue" href="menuUsuario.jsp">Volver al men&uacute; principal</a>
            </c:if>-->


            <form action="EditarAlumno" name="formEditarALumno" method="POST">
                <div class="row d-flex">
                    <div class="col-5">
                        <input hidden name="txtIdAlumno" id="idAlumno" value="${alumno.idAlumno}">
                        <input hidden name="txtIdCategoria" id="idCategoria" value="${categoria.idCategoria}">

                        <label for="nombre" class="form-label">Nombre</label>
                        <input type="text" class="form-control" id="txtNombre" value="${alumno.nombres.trim()}" name="txtNombre" required="required">


                        <label for="apellido" class="form-label">Apellido</label>
                        <input type="text" class="form-control" value="${alumno.apellidos.trim()}" name="txtApellido" id="txtApellido" required="required">

                        <label for="codEstadistico" class="form-label">CE</label>
                        <input type="text" class="form-control" value="${alumno.codigoEstadistico}" id="numCodEstadistico" name="numCodEstadistico" required="required">

                        <label for="dni" class="form-label">DNI</label>
                        <input type="number" class="form-control" value="${alumno.matriculaIndividual}" id="numDni" name="numDni" required="required">

                        <label for="iosfa" class="form-label">Nro IOSFA</label>
                        <input type="number" class="form-control" value="${alumno.nroIOSFA}" name="numIosfa" id="numIosfa">

                        <label for="dateFechaNac" class="form-label">Fecha Nacimiento</label>
                        <input min="1950-01-01" max="${LocalDate}" value="${alumno.fechaNacimiento.trim()}" autocomplete="on" type="date" name="dateFechaNac" class="form-control" id="dateFechaNac" required="required">

                        <label class="form-label" for="cmbGeneros">Género</label>
                        <select class="form-control" id="cmbGeneros" name="cmbGeneros" required="required">
                            <option selected  value="${alumno.genero}">${gestor.buscarGenero(alumno.genero).nombreGenero}</option>
                            <c:forEach items="${ gestor.listarGeneros()}" var="item">
                                <option value="${ item.idGenero }">${ item.nombreGenero }</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-1"></div>
                    <div class="col-5">
                        <label class="form-label" for="cmbGrados">Grado</label>
                        <select class="form-control" id="cmbGrados" name="cmbGrados" required="required">
                            <option selected  value="${alumno.grado}">${gestor.buscarGrado(alumno.grado).nombreGrado.trim()}</option>
                            <c:forEach items="${ gestor.listarGrados()}" var="item">
                                <option value="${ item.idGrado }">${ item.nombreGrado }</option>
                            </c:forEach>
                        </select>

                        <label class="form-label" for="cmbGrSang">Grupo Sanguineo</label>
                        <select class="form-control" id="cmbGrSang" name="cmbGrSang">
                            <option selected  value="${alumno.grupoSanguineo}">${gestor.buscarGrupoSanguineo(alumno.grupoSanguineo).grupoSanguineo.trim()}</option>
                            <c:forEach items="${ gestor.listarGruposSanguineos()}" var="item">
                                <option value="${ item.idGrupoSanguineo }">${ item.grupoSanguineo }</option>
                            </c:forEach>
                        </select>

                        <label class="form-label" for="cmbFacSang">Factor Sanguineo</label>
                        <select class="form-control" id="cmbFacSang" name="cmbFacSang">
                            <option selected  value="${alumno.factorSanguineo}">${gestor.buscarFactorSanguineo(alumno.factorSanguineo).factorSanguineo.trim()}</option>
                            <c:forEach items="${ gestor.listarFactorSanguineos()}" var="item">
                                <option value="${ item.idFactorSanguineo }">${ item.factorSanguineo }</option>
                            </c:forEach>
                        </select>
                        <br>

                        <div class="form-floating">
                            <textarea class="form-control" placeholder="Observaciones Sanitarias" name="infoSanitaria" id="infoSanitaria" style="height: 100px">${alumno.obsSanitarias}</textarea>
                            <label for="infoSanitaria">Informacion Sanitaria</label>
                        </div>


                        <br>               

                        <div class="form-floating">
                            <textarea class="form-control" placeholder="Observaciones Generales" name="infoGeneral" id="infoGeneral" style="height: 100px">${alumno.obsGenerales}</textarea>
                            <label for="infoGeneral">Observaciones Generales</label>
                        </div>

                        <label for="txtAltura" class="form-label">Altura en metros</label>
                        <input type="number" class="form-control" value="${alumno.tallaEnMetros}" min="1.4" max="2.2" step="0.01" id="txtAltura" name="txtAltura" aria-describedby="alturaHelp">
                        <div id="alturaHelp" class="form-text">Ingrese solamente numeros entre 1,4 y 2,2</div>




                    </div>
                </div>
                <button type="submit" class="btn btn-success" >Editar</button>
                <a href="ListarAlumnos" class="btn btn-primary">Cancelar</a>
            </form>
        </div>


        <div class="col-2"></div>

    </div>
</body>
</html>
