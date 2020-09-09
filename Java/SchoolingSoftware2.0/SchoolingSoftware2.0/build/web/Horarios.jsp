<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="es">
    <head>
        <title>Horarios</title>
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
                    <li class="nivel1"><a href="#" class="nivel1">Registrate</a>
                        <ul>
                            <li><a href="RegistroProfesor.jsp">Profesor</a></li>
                            <li><a href="RegistroAlumno.jsp">Alumno</a></li>
                        </ul> 
                    </li>
                    <li class="nivel1"><a href="#" class="nivel1">Ingresa</a>
                        <ul>
                            <li><a href="IniciarSesionProfesor.jsp">Profesor</a></li>
                            <li><a href="IniciarSesionAlumno.jsp">Alumno</a></li>
                        </ul>
                    </li>
                </ul>
            </nav>
            <section id="principal">
                <h1>Cursos Disponibles</h1>
                <jsp:useBean id="beanCurso" class="pkgModelo.LlenarCursos">
                    <%
                        String r = beanCurso.obtenerCursos();
                        out.print(r);
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