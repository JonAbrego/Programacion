<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html lang="es">
    <head>
        <title>Harmon Hell</title>
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
                    <li class="nivel1"><a href="index.html" id="boton">Inicio</a></li>
                    <li class="nivel1"><a href="RegistroProfesor.jsp" id="boton">Regresar</a></li>
                </ul>
            </nav>
            <section id="principal">
                <!--<input type="file" name="video" id="video" accept="video/*"><br>-->
                <input type="file" name="const" id="const" accept="application/pdf">
                <video width="320" height="240" controls>
                    <source src="Videos/video.mp4" type="video/mp4">
                    <source src="movie.ogg" type="video/ogg">
                    Your browser does not support the video tag.
                </video>
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
<input type="url" id="constancia" name="constancia" placeholder="Escribe la URL de tu constancia" required>
<input type="url" id="video" name="video" placeholder="Escribe la URL de tu video" required>