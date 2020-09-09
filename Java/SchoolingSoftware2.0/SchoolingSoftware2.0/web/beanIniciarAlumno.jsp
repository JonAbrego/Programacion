<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="es">
    <head>
        <title>Inicio de Alumno</title>
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
                <jsp:useBean id="beanIniciarAlumno" class="pkgModelo.ISesionAlumno" scope="session">
                <jsp:setProperty name="beanIniciarAlumno" property="usuario"/>
                <jsp:setProperty name="beanIniciarAlumno" property="pass"/>
                <%
                    if(beanIniciarAlumno.buscaUsuario("usuario").equals(request.getParameter("usuario")) &&
                    beanIniciarAlumno.buscaPass("pass").equals(request.getParameter("pass"))) {
                        beanIniciarAlumno.llenaAlumno();
                        out.print("<nav id=\"menu\">\n" + "<ul>"
                        + "<li class=\"nivel1\"><a href=\"HomeAlumno.jsp\" id=\"boton\">Home</a></li>" + "</ul>"
                        + "</nav>" + "<section id=\"principal\">");
                        out.print("<h1>Inició sesion como: " + request.getParameter("usuario") + "</h1>\n");
                    } else {
                        out.print("<nav id=\"menu\">\n" + "<ul>"
                        + "<li class=\"nivel1\"><a href=\"index.html\" id=\"boton\">Inicio</a></li>" 
                        + "<li class=\"nivel1\"><a href=\"IniciarSesionAlumno.jsp\" id=\"boton\">Regresar</a></li>" + "</ul>"
                        + "</nav>" + "<section id=\"principal\">");
                        if (!beanIniciarAlumno.buscaUsuario("usuario").equals(request.getParameter("usuario")))
                            out.print("<h1>Usuario Incorrecto</h1>\n");
                        else
                            out.print("<h1>Contraseña Incorrecta</h1>\n");
                        session.invalidate();
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