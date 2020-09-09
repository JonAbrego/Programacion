<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="es">
    <head>
        <title>Registro de Profesor</title>
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
                    <li class="nivel1"><a href="index.html" class="nivel1">Inicio</a></li>
                    <li class="nivel1"><a href="IniciarSesionProfesor.jsp" class="nivel1">Iniciar Sesion</a></li>
                </ul>
            </nav>
            <section id="principal">
                <jsp:useBean id="beanIProfesor" class="pkgModelo.InsercionProfesor">
                <jsp:setProperty name="beanIProfesor" property="*"/>
                <%
                    if (beanIProfesor.insertar() == false){
                        out.print("<h1>¡Felicidades, te has registrado como profesor!</h1>\n");
                        out.print("<table name=\"datosProfesor\" id=\"datosProfesor\">\n");
                        out.print("<tr><td>Tu nombre de usuario es: </td><td>" + request.getParameter("usuario") + "</td></tr>\n");
                        out.print("<tr><td >Nombre(s): </td><td>" + request.getParameter("nombres") + "</td></tr>\n");
                        out.print("<tr><td>Apellido Paterno: </td><td>" + request.getParameter("aPat") + "</td></tr>\n");
                        out.print("<tr><td>Apellido Materno: </td><td>" + request.getParameter("aMat") + "</td></tr>\n");
                        out.print("<tr><td>Correo Electrónico: </td><td>" + request.getParameter("correo") + "</td></tr>\n");
                        out.print("</table>\n");
                    } else {
                        out.print("<h1>Falló la inserción</h1>\n");
                    }
                %>
                </jsp:useBean>
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
