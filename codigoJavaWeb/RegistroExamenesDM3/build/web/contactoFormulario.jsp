<%-- 
    Document   : contactoFormulario
    Created on : Jul 3, 2021, 5:10:05 PM
    Author     : Emiliano Barat
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="estilos.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Contacto</title>

    </head>
    <body style="">
        <%@include file="header.jsp" %>
    <center>
        <div class="container">
            <h1>Formulario de contacto</h1>
            
            <form action="https://formspree.io/f/xbjqkybd" autocomplete="off" method="POST" onsubmit="return validar()">
                <div class="form-group ">
                    <label for="mail" class="form-label"></label>
                    Su direccion de e-mail:
                    <input class="form-control" type="email" id="mail" name="_replyto" required="" >

                    <label for="txtArea" class="form-label"></label>
                    Escriba su mensaje:
                    <textarea id="txtArea" class="form-control" style="height: 100" rows="4" maxlength="140" name="message" required=""></textarea>
                    </br>                    
                    <button class="btn btn-primary"type="submit">Enviar</button>
                    <c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario <= 1}" >
                        <a class="btn btn-primary" href="menuAdmin.jsp">Cancelar</a>
                    </c:if>
                    <c:if test="${sessionScope.usuario.tipoUsuario.idTipoUsuario >= 2}">
                        <a class="btn btn-primary" href="menuUsuario.jsp">Cancelar</a>
                    </c:if>
                </div>
            </form>
        </div>
    </center>
<!--    <script>
        const clearFields = function () {
            document.getElementById("mail").value = "";
            document.getElementById("txtArea").value = "";
        }
        window.onload = clearFields(), console.log('windows')
    </script>-->
    <script>


        const validar = function () {
            const mail = document.getElementById("mail").value;
            const txtArea = document.getElementById("txtArea").value;
            let valida = true
            if (mail == '' && txtArea == '') {
                valida = false
            }
            const expresion = /^[a-z][\w.-]+@\w[\w.-]+\.[\w.-]*[a-z][a-z]$/i;

            if (expresion.test(mail)) {
                valida = false;
            }
            if (!valida) {
                alert("Controle los campos")
            }
            console.log(valida);
            mail.value = ""
            txtArea.value = ""
            return valida
        }



    </script>
</body>
</html>
