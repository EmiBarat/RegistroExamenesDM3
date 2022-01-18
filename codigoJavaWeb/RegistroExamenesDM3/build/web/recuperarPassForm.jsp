<%-- 
    Document   : login
    Created on : Jun 9, 2021, 12:28:44 PM
    Author     : Emiliano Barat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="estilos.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="">
        
        <div class="container">
            <div style="height: 150px" class="row align-items-center">
                <div class="col-auto">
                    <h1>Recuperacion de contraseña</h1>
                    <h4>Para recuperar su contraseña indique su usuario</h4>

                    <a style="color: #464F41" href="login.jsp">Volver al login</a>

                </div>
            </div>
            <div style="margin-top: 20px" class="row justify-content-center align-items-center">
                <div class="col-3"></div>

                <div class="col-4">
                    <form action="ObtenerPreguntaSecreta" method="post" class="form">

                        <div>
                            <label style="font-size: larger" class="form-label" for="user">Usuario:</label>
                            <input class="form-control" required="" type="text" name="txtUser" id="user">
<!--                            <p class="text-danger">${usuarioIncorrecta}</p>
                            <p class="text-danger">${usuarioBaja}</p>-->
                        </div>
                        <!--                        <div>
                                                    <label class="form-label" for="pass">Password:</label>
                                                    <input class="form-control" required="required" type="password" name="txtPass" id="pass">
                                                    <p class="text-danger">${contrasenaIncorrecta}</p>
                                                </div>-->
                        <br>
                        <input class="form-control btn btn-primary" type="submit" value="Continuar">
                    </form>


                </div>
                <div class="col-3"></div>
            </div>

        </div>   
    </body>
</html>

