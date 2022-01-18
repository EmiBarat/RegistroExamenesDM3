<%-- 
    Document   : confirmarEliminacion
    Created on : Jun 28, 2021, 10:08:16 AM
    Author     : Emiliano Barat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="estilos.jsp"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body style="">
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="row" >
                <div class="col-3"><img style="box-sizing: border-box" width="100%" src="img/alert.png"></div>
                <div class="col-4">

                    <h1 class="">Confirma la eliminación???</h1>
                    <h3 class="">Posible recuperacion, no habrá.</h3>
                    <form name="confirmarEliminacion" action="EstadoProfesor" method="post">
                        <input type="hidden" value="1" name="confirmacion">
                        <input type="hidden" value="${idProfe}" name="txtIdProfe">
                        <input type="submit" class="btn btn-danger" value="Eliminar">
                        <a href="ListarProfesores" class="btn btn-primary">Cancelar</a>
                    </form>

                </div>
                <div class="col-3"></div>
            </div>
        </div>
    </body>
</html>