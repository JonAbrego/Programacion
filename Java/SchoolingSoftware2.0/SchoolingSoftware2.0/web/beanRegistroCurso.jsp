<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="es">
    <head>
        <title>Registro de Curso</title>
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
                    <li class="nivel1"><a href="AdministrarCursos.jsp" class="nivel1">Mis Cursos</a></li>
                </ul>
            </nav>
            <section id="principal">
                <jsp:useBean id="beanICurso" class="pkgModelo.InsercionCurso">
                    <jsp:setProperty name="beanICurso" property="horario"/>
                    <jsp:setProperty name="beanICurso" property="nivel"/>
                    <jsp:setProperty name="beanICurso" property="profesor"/>
                    <jsp:setProperty name="beanICurso" property="alumno" value="No seleccionado"/>
                    <jsp:setProperty name="beanICurso" property="elegido" value="no"/>
                    <jsp:setProperty name="beanICurso" property="usuario"/>
                    <jsp:setProperty name="beanICurso" property="usr_alumno" value="No seleccionado"/>
                    <jsp:setProperty name="beanICurso" property="calificacion" value="No evaluado"/>
                <%
                    if (beanICurso.insertar() == false){
                        out.print("<h1>¡Felicidades, has registrado un nuevo curso!</h1>\n");
                        out.print("<table name=\"datosProfesor\" id=\"datosProfesor\">\n");
                        out.print("<tr><td>Nivel: </td><td>" + request.getParameter("nivel") + "</td></tr>\n");
                        out.print("<tr><td >Horario: </td><td>" + request.getParameter("horario") + "</td></tr>\n");
                        out.print("</table>\n");
                    } else {
                        out.print("<h1>Ya tiene un grupo en ese horario, solicite otro por favor</h1>\n");
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
