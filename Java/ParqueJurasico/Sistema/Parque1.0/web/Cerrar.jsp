<%-- 
    Document   : Cerrar
    Created on : 8/06/2014, 08:12:31 PM
    Author     : abrego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/estilo.css" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Cerrar</title>
    </head>
   <body>     
        <div id="agrupar">
            <header id="cabecera">
                <h1> Parque  Jurásico</h1>
            </header>
            <nav id="menu">
                <ul>
                    <li class="nivel1"><a href="IniciarSesion.html" class="nivel1">Consultar Reservaciones</a></li>
                    <li class="nivel1"><a href="#" class="nivel1">Registrate</a>
                        <ul>
                            <li><a href="Telefono.html">Telefonica</a></li>
                            <li><a href="TipoRegistro.html">Correo electronico</a></li>
                        </ul> 
                    </li>
                    <li class="nivel1"><a href="Galeria.html" class="nivel1">Galería</a>
                    </li>
                    <li class="nivel1"><a href="MapaParque.html" class="nivel1">Mapa Del Parque</a></li>
                </ul>
            </nav>
            <section id="principal">  
                <h1>Hasta Pronto!!</h1>
                <%
                    session.invalidate();
                %>
            </section>
        </div>
    </body>
</html>
