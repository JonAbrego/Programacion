<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="es">
    <head>
        <title>Horarios Disponibles</title>
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
                    <li class="nivel1"><a href="CerrarSesion.jsp" class="nivel1">Cerrar Sesión</a></li>
                </ul>
            </nav>
            <section id="principal">
                <h1>Cursos Disponibles</h1>
                <table name="datosCurso" id="datosCurso">
                    <tr>
                        <td>
                            Nivel
                        </td>
                        <td>
                            Horario
                        </td>
                        <td>
                            Profesor
                        </td>
                        <td>
                            Solicitar
                        </td>
                    </tr>
                    <jsp:useBean id="beanIniciarAlumno" type="pkgModelo.ISesionAlumno" scope="session">
                    </jsp:useBean>
                    <%
                        String r = beanIniciarAlumno.obtenerCursos();
                        out.print(r);
                    %>
                </table>
                <p>Al dar click en el nombre del profesor podra ver su video de presentacion, y al dar click en el nivel
                podra ver la constancia de acreditacion del mismo.</p>
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