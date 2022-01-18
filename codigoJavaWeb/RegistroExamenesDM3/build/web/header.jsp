<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@include file="estilos.jsp" %>--%>
<div class="header">

    <div class="container">
        <header class="d-flex align-items-center justify-content-center justify-content-md-between py-3 mb-4">

            <c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario <= 1}" >
                <a href="menuAdmin.jsp" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
                    <img width="100px" src="img/gendarmeriaEmblem.png">
                </a>
            </c:if>
            <c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario >= 2}">
                <a href="menuUsuario.jsp" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
                    <img width="100px" src="img/gendarmeriaEmblem.png"> <span style="color: white; padding-left: 5px">HOME</span>
                </a>
            </c:if>
            <!--            <a href="" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
                            <img width="100px" src="img/gendarmeriaEmblem.png">
                        </a>-->

            <ul class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">

                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownAlumnos" role="button" data-toggle="dropdown">Alumnos</a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown" >
                        <li><a class="dropdown-item" href="AltaAlumno">Alta alumno</a></li>
                        <li><a class="dropdown-item" href="ListarAlumnos">Listado</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownExamenes" role="button" data-toggle="dropdown">Examenes</a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="AltaExamen">Alta examen</a></li>
                        <li><a class="dropdown-item" href="ListarExamenes">Listado</a></li>
                    </ul>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownPesajes" role="button" data-toggle="dropdown">Pesajes</a>
                    <ul class="dropdown-menu">
                        <li><a href="ListarAlumnosPeso" class="dropdown-item">Alta pesaje</a></li>
                        <li><a class="dropdown-item" href="ListarPesosTodos">Listado</a></li>
                        <!--<li><a class="dropdown-item" href="ReportePeso">Reporte</a></li>-->
                    </ul>
                </li>

                

                <c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario <= 1}" >
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownExamenes" role="button" data-toggle="dropdown">Profesores</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="AltaProfesor">Alta profesor</a></li>
                            <li><a class="dropdown-item" href="ListarProfesores">Listado profesores</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownExamenes" role="button" data-toggle="dropdown">Usuarios</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="AltaUsuario">Alta usuario</a></li>
                            <li><a class="dropdown-item" href="ControlUsuarios">Listado usuarios</a></li>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownExamenes" role="button" data-toggle="dropdown">Gráficos Estadísticos</a>
                        <ul class="dropdown-menu">
                            <li><a class="dropdown-item" href="filtroGraficosExamen.jsp">de Exámenes</a></li>
                            <li><a class="dropdown-item" href="visualGraficos.jsp">de Pesajes</a></li>
                        </ul>
                    </li>
                    <!--                    <li><a href="#" class="nav-link px-2 link-dark">Profesores</a></li>
                                        <li><a href="#" class="nav-link px-2 link-dark">Usuarios</a></li>-->
                    <!--<li><a href="visualGraficos.jsp" class="nav-link px-2 link-dark">Graficos estadisticos</a></li>-->
                    <!--<li><a href="visualGraficos.jsp" class="nav-link px-2 link-dark">Graficos estadisticos</a></li>-->
                    </c:if>


                <li class="nav-item dropdown login-usuario">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown">${usuario.nombreUsuario.trim()}</a>
                    <ul class="dropdown-menu" aria-labelledby="navbarDropdown" >
                        <li><a class="dropdown-item" href="LogOut">Salir</a></li>

                    </ul>
                </li>

            </ul>


        </header>
    </div>
</div>