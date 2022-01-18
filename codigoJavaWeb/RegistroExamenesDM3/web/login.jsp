<%-- 
    Document   : login
    Created on : Jun 9, 2021, 12:28:44 PM
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
    <body class="text-center  login-inicial">
        <form class="form-signin" action="VerificarLogin" method="POST" autocomplete="off">
            <img width="200px" class="mb-4" src="img/gendarmeriaEmblem.png">
            <h1 class="h3 mb-3 font-weight-normal">Ingreso al sistema</h1>
            <h4>${msj}</h4>
            <label for="inputEmail" class="sr-only">Usuario</label>
            <input type="text" name="txtUser" id="inputEmail" class="form-control" placeholder="Usuario" required="" autofocus="" pb-role="username">
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" name="txtPass" id="inputPassword" class="form-control" placeholder="Password" required="" pb-role="password">
            <a style="color: #464F41 !important" class="" href="recuperarPassForm.jsp">Olvidó su contraseña??</a>
            <!--            
            <div class="checkbox mb-3">
                            <label>
                                <input type="checkbox" value="remember-me"> Remember me
                            </label>
                        </div>-->
            <br/>
           
            <button style="margin-top: 20px" class="btn btn-lg btn-primary btn-block" type="submit" pb-role="submit">Ingresar</button>
            <!--<p class="mt-5 mb-3 text-muted">©2021</p>-->

            <%--<%@include file="header.jsp" %>--%>
            <!--        <div class="container">
                        <div style="
                             height: 170px;
                             background-color: darkgreen;
                             color: cornsilk;" class="row align-items-center">
                            <div style="justify-content: flex-end; text-align: center" class="col-3 ">
                                <h4 class="" >Sección acondicionamiento psicofísico profesional</h4>
                            </div>
                            <div style="text-align: center " class="col-6">
                                <h1>Página de ingreso</h1>
                                <h3>Sistema de gestión de exámenes</h3>
                                <h3 style="color: yellow">${msj}</h5>
                            </div>
                            <div class="col-3" style="justify-content: flex-end; text-align: center">
                                <h4>GENDARMERIA NACIONAL</h4>
                            </div>
                        </div>
                        <div style="margin-top: 20px" class="row justify-content-center align-items-center">
                            <div class="col-4">
                                <img class="img-fluid"  src="img/Escuela-de-Oficiales-de-Gendarmeria-Nacional-3.jpg">
                            </div>
            
                            <div class="col-4">
                                <form action="VerificarLogin" method="post" class="form">
            
                                    <div>
                                        <label style="font-size: larger; color: azure" class="form-label" for="user">Usuario:</label>
                                        <input class="form-control" required="" type="text" name="txtUser" id="user">
                                        <p class="text-danger">${usuarioIncorrecta}</p>
                                        <p class="text-danger">${usuarioBaja}</p>
                                    </div>
                                    <div>
                                        <label style="font-size: larger; color: azure" class="form-label" for="pass">Password:</label>
                                        <input class="form-control" required="required" type="password" name="txtPass" id="pass">
                                        <p class="text-danger">${contrasenaIncorrecta}</p>
                                    </div>
                                    <br>
                                    <input class="form-control btn btn-primary" type="submit" value="Ingresar">
                                </form>
                                ESTE LINK TIENE QUE LLEVAR A UN LUGAR QUE HAGA LA PREGUNTA PARA RESPUESTA SECRETA
                                <a style="color: azure" class="" href="recuperarPassForm.jsp">Olvidó su contraseña??</a>
                            </div>
                            <div style="text-align: center" class="col-4">
                                <img src="./img/escudoDM3lg.jpg">
                            </div>
                        </div>
            
                    </div>   -->
            <!--        <footer class="footer footer-principal fixed-bottom mt-auto py-3">
                        <div class="container d-flex justify-content-md-between">
                            <a class="btn link-footer " href="#">Terminos y condiciones</a>
                            <a class="btn link-footer" href="./contactoFormulario.jsp">Contacto</a>
                            <a class="btn link-footer" href="#">FAQ</a>
                        </div>
                    </footer>-->
    </body>
</html>
