<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="es">
    <head>
        <title>Cambios</title>
        <link rel="shortcut icon" href="IMG/flama.png"/>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" href="CSS/miEstilo.css" type="text/css" />
    </head>
    <body>
        <div id="agrupar">
            <header id="cabecera">
                <img src="IMG/hh.png" height="40%" width="40%"/>
            </header>
            <nav id="menu">
                <ul>
                    <li class="nivel1"><a href="HomeProfesor.jsp" class="nivel1">Home</a></li>
                </ul>
            </nav>
            <section id="principal">
                <jsp:useBean id="beanCambioProfesor" class="pkgModelo.AdministrarCuentaP">  
                <jsp:setProperty name="beanCambioProfesor" property="*"/>
                <%
                    beanCambioProfesor.guardar();
                    out.print("<h1>Cambios con Exito</h1>");                
                %>
                </jsp:useBean>
                <jsp:setProperty name="beanIniciarProfesor" property="usuario" value="<%=beanCambioProfesor.getUsuario()%>"></jsp:setProperty>
                <jsp:setProperty name="beanIniciarProfesor" property="nombres" value="<%=beanCambioProfesor.getNombres()%>"></jsp:setProperty>
                <jsp:setProperty name="beanIniciarProfesor" property="aPat" value="<%=beanCambioProfesor.getaPat()%>"></jsp:setProperty>
                <jsp:setProperty name="beanIniciarProfesor" property="aMat" value="<%=beanCambioProfesor.getaMat()%>"></jsp:setProperty>
                <jsp:setProperty name="beanIniciarProfesor" property="correo" value="<%=beanCambioProfesor.getCorreo()%>"></jsp:setProperty>
                <jsp:setProperty name="beanIniciarProfesor" property="constancia" value="<%=beanCambioProfesor.getConstancia()%>"></jsp:setProperty>
                <jsp:setProperty name="beanIniciarProfesor" property="video" value="<%=beanCambioProfesor.getVideo()%>"></jsp:setProperty>
                <jsp:setProperty name="beanIniciarProfesor" property="pass" value="<%=beanCambioProfesor.getPass()%>"></jsp:setProperty>
                <jsp:setProperty name="beanIniciarProfesor" property="cPass" value="<%=beanCambioProfesor.getcPass()%>"></jsp:setProperty>
                <table name="datosProfesor" id="datosProfesor">
                    <tr><td>Tu nombre de usuario es: </td><td><jsp:getProperty name="beanIniciarProfesor" property="usuario"/></td></tr>
                    <tr><td >Nombre(s): </td><td><jsp:getProperty name="beanIniciarProfesor" property="nombres"/></td></tr>
                    <tr><td>Apellido Paterno: </td><td><jsp:getProperty name="beanIniciarProfesor" property="aPat"/></td></tr>
                    <tr><td>Apellido Materno: </td><td><jsp:getProperty name="beanIniciarProfesor" property="aMat"/></td></tr>
                    <tr><td>Correo Electronico: </td><td><jsp:getProperty name="beanIniciarProfesor" property="correo"/></td></tr>
                    <tr><td>Constancia: </td><td><jsp:getProperty name="beanIniciarProfesor" property="constancia"/></td></tr>
                    <tr><td>Video: </td><td><jsp:getProperty name="beanIniciarProfesor" property="video"/></td></tr>
                </table>
            </section>
            <footer id="pie">
                Derechos reservados &copy; 2014
                <table id="follow">
                    <tr>
                        <td><a>Siguenos: </a></td>
                        <td><a href="https://www.facebook.com/harmonhellschool"><img src="IMG/ico_fb.gif"></a></td>
                        <td><a href="#"><img src="IMG/ico_tw.gif"></a></td>
                        <td><a href="#"><img src="IMG/ico_yt.gif"></a></td>
                        <td><a href="#"><img src="IMG/ico_gp.gif"></a></td>
                    </tr>
                </table>
            </footer>
        </div>
    </body>
</html>
