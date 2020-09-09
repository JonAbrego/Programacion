<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="es">
    <head>
        <title>Home: <jsp:getProperty name="beanIniciarProfesor" property="usuario"></jsp:getProperty> </title>
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
                    <li class="nivel1"><a href="InscribirAlumno.jsp" class="nivel1">Inscribir</a></li>
                    <li class="nivel1"><a href="EvaluarCruso.jsp" class="nivel1">Evaluar</a></li>
                    <li class="nivel1"><a href="CerrarSesion.jsp" class="nivel1">Cerrar Sesión</a></li>
                </ul>
            </nav>
            <section id="principal">
                <h1>Cursos Registrados</h1>
                <table name="datosCurso" id="datosCurso">
                    <tr>
                        <td>
                            Nivel
                        </td>
                        <td>
                            Horario
                        </td>
                        <td>
                            Alumno
                        </td>
                    </tr>
                    <jsp:useBean id="beanIniciarProfesor" type="pkgModelo.ISesionProfesor" scope="session">
                    </jsp:useBean>
                    <%
                        String r = beanIniciarProfesor.obtenerCursos();
                        out.print(r);
                    %>
                </table>
                <h1>Registrar Nuevo Curso</h1>
                <form name="formProfesor" id="formProfesor" action="beanRegistroCurso.jsp" method="post">
                    <table name="formRegistro" id="formRegistro">
                        <tr>
                            <td></td><td><input type="hidden" id="profesor" name="profesor" value="<jsp:getProperty name="beanIniciarProfesor" property="nombres"></jsp:getProperty> <jsp:getProperty name="beanIniciarProfesor" property="aPat"></jsp:getProperty>"/></td>
                            <td></td><td><input type="hidden" id="usuario" name="usuario" value="<jsp:getProperty name="beanIniciarProfesor" property="usuario"></jsp:getProperty>"/></td>
                        </tr>
                        <tr>
                            <td><label for="nivel">Nivel: </label></td>
                            <td>
                                <select id="nivel" name="nivel">
                                    <option value="Principiante">Principiante</option>
                                    <option value="Intermedio">Intermedio</option>
                                    <option value="Avanzado">Avanzado</option>
                                    <option value="Conversacion">Conversacion</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><label for="horario">Horario: </label></td>
                            <td>
                                <select id="horario" name="horario">
                                    <option value="09:00-10:00">09:00-10:00</option>
                                    <option value="10:00-11:00">10:00-11:00</option>
                                    <option value="11:00-12:00">11:00-12:00</option>
                                    <option value="12:00-13:00">12:00-13:00</option>
                                    <option value="13:00-14:00">13:00-14:00</option>
                                    <option value="14:00-15:00">14:00-15:00</option>
                                    <option value="15:00-16:00">15:00-16:00</option>
                                    <option value="16:00-17:00">16:00-17:00</option>
                                    <option value="17:00-18:00">17:00-18:00</option>
                                    <option value="18:00-19:00">18:00-19:00</option>
                                    <option value="19:00-20:00">19:00-20:00</option>
                                    <option value="20:00-21:00">20:00-21:00</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td></td><td><input type="submit" id="enviar" value="Registrar"/></td>
                        </tr>
                    </table>
                </form>
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
