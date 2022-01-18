<%-- 
    Document   : menuAdmin
    Created on : Jun 10, 2021, 11:45:25 AM
    Author     : Emiliano Barat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="estilos.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="">
        <%@include file="header.jsp" %>
        <div class="container">
            <h1>Sección de Acondicionamiento Psicofísico Profesional</h1>
            <br/>
            <div class="row d-flex">
                <div class="col-6">            
                    <h2>Destacamento movil 3 - Sarg Ayudante Ramón Gumersindo Acosta</h2>
                    <br/>
                    <h3>Sistema de gestion de alumnos, exámenes y control de peso</h3>
                </div>
                <div class="col-6">            
                    <img src="img/Escuela-de-Oficiales-de-Gendarmeria-Nacional-3.jpg">
                </div>
            </div>
        </div>
        <footer class="footer footer-principal fixed-bottom mt-auto py-3">
            <div class="container d-flex justify-content-md-between">

                <a class="btn link-footer " href="terminosCondiciones.jsp">Terminos y condiciones</a>
                <a class="btn link-footer" href="./contactoFormulario.jsp">Contacto</a>
                <a class="btn link-footer" href="faq.jsp">FAQ</a>
            </div>
        </footer>
    </body>
    <!--    <body style="">
            <div style="padding-top: 10px" class="container-fluid">
                <div style="background-color:darkgreen; color: azure; height: 150px" class="row d-flex align-items-center justify-content-between">
    
                    <div style="padding-left: 40px">
                        <h2 >Menu de Administracion</h2>
                        <h3>Hola ${usuario.nombreUsuario}</h3>            
                    </div>
                    <div><img height="120px" src="./img/Escudo_gendarmeria1.jpg"></div>
                    <div style="align-self: start; margin: 10px" class="align-items-start">
                        <a href="LogOut"  class="btn btn-primary ">Cerrar Sesion</a>
                    </div>
                </div>
                <div class="row">
                    <div class="col-1"></div>
                    <div style="margin: 30px; " class="col-10">
                        <div style="padding: 15px" class="row d-block ">
                            <h3>Tareas con examenes o alumnos</h3>
                            <p>Desde los listados podrá editar o eliminar un elemento</p>
                            <p>Para registar un examen el alumno causante debe estar previamente dado de alta</p>
    
                            <ul class="nav nav-pills justify-content-around">
                                <li class="nav-item">
                                    <a class="btn btn-primary" href="AltaAlumno" >Alta de Alumno</a>
                                </li>
                                <li class="nav-item">
                                    <a class="btn btn-primary" href="ListarAlumnos">Listado de alumnos</a>
                                </li>
                                <li class="nav-item">
                                    <a class="btn btn-primary" href="ListarAlumnos" >Registrar examen</a>
                                </li>
                                <li class="nav-item">
                                    <a class="btn btn-primary" href="ListarExamenes" >Ver exámenes</a>
                                </li>
                                <li class="nav-item">
                                    <a class="btn btn-primary" href="ListarAlumnosPeso">Registro de peso</a>
                                </li>
                            </ul>
                        </div>
                        <hr>
                        <div class="row d-block">
                            <h3>Tareas de administracion del sistema </h3>
                            <p>Desde los listados podrá editar o eliminar un elemento</p>
                            <ul class="nav nav-pills justify-content-around">
                                <li class="nav-item">
                                    <a class="btn btn-primary" href="AltaProfesor" >Alta de profesor</a>
                                </li>
                                <li class="nav-item">                            
                                    <a class="btn btn-primary" href="ListarProfesores">Listado de profesores</a>
                                </li>
                                <li class="nav-item">
                                    <a class="btn btn-primary" href="AltaUsuario" >Alta de Usuario</a>
                                </li>
                                <li class="nav-item">
                                    <a class="btn btn-primary" href="ControlUsuarios" >Control de Usuarios</a>
                                </li>
                            </ul>
                        </div>
                        <hr>
                        <div class="row d-block">
                            <h3>Gráficos y estadísticas</h3>
                            <ul class="nav nav-pills">
                                <li class="nav-item">
                                    <a href="visualGraficos.jsp" class="btn btn-primary">Ver gráficos y datos estadísticos</a>
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-2"></div>
                    <img src="/RegistroExamenesDM3/GraficoTorta?"></img>
                </div>
                <div style="background-color: darkgreen; height: 150px" class="row d-flex align-items-center justify-content-around">
                    <a class="btn btn-primary" href="#">Terminos y condiciones</a>
                    <a class="btn btn-primary" href="./contactoFormulario.jsp">Contacto</a>
                    <a class="btn btn-primary" href="#">FAQ</a>
                </div>
            </div>
    
        </body>-->
</html>
