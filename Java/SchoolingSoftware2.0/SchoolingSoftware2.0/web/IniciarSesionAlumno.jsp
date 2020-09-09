<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="es">
    <head>
        <title>Iniciar Sesión Alumno</title>
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
                </ul>
            </nav>
            <section id="principal">
                <h1>Iniciar sesión Alumno</h1>
                <form name="formAlumno" id="formAlumno" action="beanIniciarAlumno.jsp" method="post">
                    <table name="formInicio" id="formInicio">
                        <tr>
                            <td><label for="usuario">Nombre de Usuario: </label></td>
                            <td><input type="text" id="usuario" name="usuario" placeholder="Escribe tu nombre de usuario" maxlength="10" required autofocus></td>
                        </tr>
                        <tr>
                            <td><label for="pass">Contraseña: </label></td>
                            <td><input type="password" id="pass" name="pass" placeholder="Escribe tu contraseña" maxlength="10" required autofocus></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><a href="RecuperarContrasena.jsp">¿Olvidaste tu contraseña?</a></td>
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
