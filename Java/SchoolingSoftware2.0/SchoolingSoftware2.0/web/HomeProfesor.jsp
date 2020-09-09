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
                        <li class="nivel1"><a href="VerificaProfesor.jsp" class="nivel1">Cambiar Datos</a></li>
                        <li class="nivel1"><a href="AdministrarCursos.jsp" class="nivel1">Mis Cursos</a></li>
                        <li class="nivel1"><a href="CerrarSesion.jsp" class="nivel1">Cerrar Sesión</a></li>
                    </ul>
                </nav>
                <section id="principal">
                    <h1>Iniciado como: <jsp:getProperty name="beanIniciarProfesor" property="usuario"></jsp:getProperty></h1>
                    <table name="datosProfesor" id="datosProfesor">
                        <tr><td>Nombre(s): </td><td><jsp:getProperty name="beanIniciarProfesor" property="nombres"></jsp:getProperty></td></tr>
                        <tr><td>Apellido Paterno:</td><td><jsp:getProperty name="beanIniciarProfesor" property="aPat"></jsp:getProperty></td></tr>
                        <tr><td>Apellido Materno: </td><td><jsp:getProperty name="beanIniciarProfesor" property="aMat"></jsp:getProperty></td></tr>
                        <tr><td>Correo Electronico: </td><td><jsp:getProperty name="beanIniciarProfesor" property="correo"></jsp:getProperty></td></tr>
                        <tr><td>Video: </td><td><a href="#openVideo">Presentación</a></td></tr>
                        <tr><td>Constancia: </td><td><a href="#openConstancia">Constancia</a></td></tr>
                        <tr><td >Nombre de Usuario: </td><td><jsp:getProperty name="beanIniciarProfesor" property="usuario"></jsp:getProperty></td></tr>
                    </table>
                    <div id="openConstancia" class="modalDialog">
                        <div>
                            <a href="#close" title="Close" class="close">X</a>
                            <h2>Constancia de Acreditacion</h2>
                            <img width="300" height="300" src="<jsp:getProperty name="beanIniciarProfesor" property="constancia"></jsp:getProperty>">
                        </div>
                    </div>
                    <div id="openVideo" class="modalDialog">
                        <div>
                            <a href="#close" title="Close" class="close">X</a>
                            <h2>Video de Introduccion</h2>
                            <iframe width="420" height="345"
                            src="Videos/<jsp:getProperty name="beanIniciarProfesor" property="video"></jsp:getProperty>">
                            </iframe>
                        </div>
                    </div>
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