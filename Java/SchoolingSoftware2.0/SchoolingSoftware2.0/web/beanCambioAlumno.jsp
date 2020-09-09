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
                    <li class="nivel1"><a href="HomeAlumno.jsp" class="nivel1">Home</a></li>
                </ul>
            </nav>
            <section id="principal">
                <jsp:useBean id="beanCambioAlumno" class="pkgModelo.AdministrarCuentaA">  
                <jsp:setProperty name="beanCambioAlumno" property="*"/>
                <%
                    beanCambioAlumno.guardar();
                    out.print("<h1>Cambios con Exito</h1>");                
                %>
                </jsp:useBean>
                <jsp:setProperty name="beanIniciarAlumno" property="usuario" value="<%=beanCambioAlumno.getUsuario()%>"></jsp:setProperty>
                <jsp:setProperty name="beanIniciarAlumno" property="nombres" value="<%=beanCambioAlumno.getNombres()%>"></jsp:setProperty>
                <jsp:setProperty name="beanIniciarAlumno" property="aPat" value="<%=beanCambioAlumno.getaPat()%>"></jsp:setProperty>
                <jsp:setProperty name="beanIniciarAlumno" property="aMat" value="<%=beanCambioAlumno.getaMat()%>"></jsp:setProperty>
                <jsp:setProperty name="beanIniciarAlumno" property="correo" value="<%=beanCambioAlumno.getCorreo()%>"></jsp:setProperty>
                <jsp:setProperty name="beanIniciarAlumno" property="telefono" value="<%=beanCambioAlumno.getTelefono()%>"></jsp:setProperty>
                <jsp:setProperty name="beanIniciarAlumno" property="pass" value="<%=beanCambioAlumno.getPass()%>"></jsp:setProperty>
                <jsp:setProperty name="beanIniciarAlumno" property="cPass" value="<%=beanCambioAlumno.getcPass()%>"></jsp:setProperty>
                <table name="datosAlumno" id="datosAlumno">
                    <tr><td>Tu nombre de usuario es: </td><td><jsp:getProperty name="beanIniciarAlumno" property="usuario"/></td></tr>
                    <tr><td >Nombre(s): </td><td><jsp:getProperty name="beanIniciarAlumno" property="nombres"/></td></tr>
                    <tr><td>Apellido Paterno: </td><td><jsp:getProperty name="beanIniciarAlumno" property="aPat"/></td></tr>
                    <tr><td>Apellido Materno: </td><td><jsp:getProperty name="beanIniciarAlumno" property="aMat"/></td></tr>
                    <tr><td>Correo Electrónico: </td><td><jsp:getProperty name="beanIniciarAlumno" property="correo"/></td></tr>
                    <tr><td>Teléfono: </td><td><jsp:getProperty name="beanIniciarAlumno" property="telefono"/></td></tr>
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
