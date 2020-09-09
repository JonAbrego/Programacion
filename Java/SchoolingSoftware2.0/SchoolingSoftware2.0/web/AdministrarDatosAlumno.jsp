<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="es">
    <head>
        <title>Administrar Datos</title>
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
                <form name="formAlumno" id="formAlumno" action="beanCambioAlumno.jsp" method="post">
                <table name="formRegistro" id="formRegistro">
                    <tr>
                        <td><label for="usuario">Usuario: </label></td>
                        <td><input type="text" id="usuario" name="usuario" value="<jsp:getProperty name="beanIniciarAlumno" property="usuario"></jsp:getProperty>"maxlength="10" required readonly></td>
                    </tr>
                    <tr>
                        <td><label for="nombres">Nombre(s): </label></td>
                        <td><input type="text" id="nombres" name="nombres" value="<jsp:getProperty name="beanIniciarAlumno" property="nombres"></jsp:getProperty>" pattern="[A-Z]{1}[a-z]{2,}(\s{1}[A-Z]{1}[a-z]{2,})*" title="Primera letra en mayúscula, las demás minúsculas, sólo letras" required autofocus></td>
                    </tr>
                    <tr>
                        <td><label for="aPat">Apellido Paterno: </label></td>
                        <td><input type="text" id="aPat" name="aPat" value="<jsp:getProperty name="beanIniciarAlumno" property="aPat"></jsp:getProperty>" pattern="[A-Z]{1}[a-z]{1,}" title="Primera letra en mayúscula, las demás minúsculas, sólo letras" required></td>
                    </tr>
                    <tr>
                        <td><label for="aMat">Apellido Materno: </label></td>
                        <td><input type="mail" id="aMat" name="aMat" value="<jsp:getProperty name="beanIniciarAlumno" property="aMat"></jsp:getProperty>" pattern="[A-Z]{1}[a-z]{1,}" title="Primera letra en mayúscula, las demás minúsculas, sólo letras" required></td>
                    </tr>
                    <tr>
                        <td><label for="correo">Correo Electrónico: </label></td>
                        <td><input type="email" id="correo" name="correo" value="<jsp:getProperty name="beanIniciarAlumno" property="correo"></jsp:getProperty>" required></td>
                    </tr>
                    <tr>
                        <td><label for="correo">Teléfono: </label></td>
                        <td><input type="tel" id="telefono" name="telefono" value="<jsp:getProperty name="beanIniciarAlumno" property="telefono"></jsp:getProperty>" pattern="\d{10}" title="Escribe los 10 números de tu teléfono, solo números" min="10" maxlength="10" required></td>
                    </tr>
                    <tr>
                        <td><label for="pass">Contraseña: </label></td>
                        <td><input type="password" id="pass" name="pass" value="<jsp:getProperty name="beanIniciarAlumno" property="pass"></jsp:getProperty>" maxlength="10" required></td>
                    </tr>
                    <tr>
                        <td><label for="cPass">Confirmar Contraseña: </label></td>
                        <td><input type="password" id="cPass" name="cPass" value="<jsp:getProperty name="beanIniciarAlumno" property="cPass"></jsp:getProperty>" maxlength="10" required></td>
                    </tr>
                    <tr>
                        <td></td><td><input type="submit" id="enviar" value="Cambiar"/></td>
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