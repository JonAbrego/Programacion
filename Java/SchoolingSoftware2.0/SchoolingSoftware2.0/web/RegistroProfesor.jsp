<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="es">
    <head>
        <title>Registro Profesor</title>
        <link rel="shortcut icon" href="IMG/flama.png"/>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" href="CSS/miEstilo.css" type="text/css" />
        <script>
            function validar(f) {
                if (f.pass.value  != f.cPass.value) {
                    alert ('Las Contraseñas no coinciden'); 
                    f.pass.focus();
                    return false;
                }
                return true;
            }
        </script>
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
                <h1>Registro de Profesor</h1>
                <form name="formProfesor" id="formProfesor" action="beanRegistroProfesor.jsp" method="post" onsubmit="return validar(this)">
                    <table name="formRegistro" id="formRegistro">
                        <tr>
                            <td><label for="nombres">Nombre(s): </label></td>
                            <td><input type="text" id="nombres" name="nombres" placeholder="Escribe tu nombre" pattern="[A-Z]{1}[a-z]{2,}(\s{1}[A-Z]{1}[a-z]{2,})*" title="Primera letra en mayúscula, las demás minúsculas, sólo letras" required autofocus></td>
                        </tr>
                        <tr>
                            <td><label for="aPat">Apellido Paterno: </label></td>
                            <td><input type="text" id="aPat" name="aPat" placeholder="Escribe tu apellido paterno" pattern="[A-Z]{1}[a-z]{1,}" title="Primera letra en mayúscula, las demás minúsculas" required></td>
                        </tr>
                        <tr>
                            <td><label for="aMat">Apellido Materno: </label></td>
                            <td><input type="text" id="aMat" name="aMat" placeholder="Escribe tu apellido materno" pattern="[A-Z]{1}[a-z]{1,}" title="Primera letra en mayúscula, las demás minúsculas" required></td>
                        </tr>
                        <tr>
                            <td><label for="correo">Correo Electronico: </label></td>
                            <td><input type="email" id="correo" name="correo" placeholder="Escribe tu correo" required></td>
                        </tr>
                        <tr>
                            <td><label for="usuario">Nombre de Usuario: </label></td>
                            <td><input type="text" id="usuario" name="usuario" placeholder="Escribe tu nombre de usuario" maxlength="10" required></td>
                        </tr>
                        <tr>
                            <td><label for="constancia">Constancia de Acreditación: </label></td>
                            <td><input type="file" name="constancia" id="constancia" accept="application/pdf"></td>
                        </tr>
                        <tr>
                            <td><label for="video">Video de Introducción: </label></td>
                            <td><input type="file" name="video" id="video" accept="video/*"></td>
                        </tr>
                        <tr>
                            <td><label for="pass">Contraseña: </label></td>
                            <td><input type="password" id="pass" name="pass" placeholder="Escribe tu contraseña" maxlength="10" required></td>
                        </tr>
                        <tr>
                            <td><label for="cPass">Confirmar Contraseña: </label></td>
                            <td><input type="password" id="cPass" name="cPass" placeholder="Confirma tu contraseña" maxlength="10" required></td>
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
