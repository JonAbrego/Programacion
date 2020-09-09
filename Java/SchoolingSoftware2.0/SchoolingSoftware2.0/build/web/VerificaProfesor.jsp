<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="es">
    <head>
        <title>Verificar Contraseña</title>
        <link rel="shortcut icon" href="IMG/flama.png"/>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" href="CSS/miEstilo.css" type="text/css" />
        <script>
            function validar(f) {
                
                if (f.pass.value  != f.cPass.value) {
                    alert ('Contraseña Incorrecta'); 
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
                    <li class="nivel1"><a href="HomeProfesor.jsp" class="nivel1">Home</a></li>
                </ul>
            </nav>
            <section id="principal">
                <form name="formProfesor" id="formProfesor" action="AdministrarDatosProfesor.jsp" method="post" onsubmit="return validar(this)">
                <table name="formInicio" id="formInicio">
                    <tr>
                        <td></td><td><input type="hidden" name="pass" maxlength="10" value="<jsp:getProperty name="beanIniciarProfesor" property="pass"></jsp:getProperty>"/></td>
                    </tr>
                    <tr>
                        <td>Escribe tu Contraseña: </td><td><input type="password" name="cPass" maxlength="10" autofocus/></td>
                    </tr>
                    <tr>
                        <td></td><td><input type="submit" id="enviar" value="Ingresar"/></td>
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