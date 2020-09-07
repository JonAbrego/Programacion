<%-- 
    Document   : ConsReserva
    Created on : 8/06/2014, 10:33:44 PM
    Author     : abrego
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
        <title>Sistema<jsp:getProperty name="beanIniciar" property="usuario"></jsp:getProperty> </title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" href="css/estilo.css" type="text/css">
    </head>
    <body>
        <div id="agrupar">
            <header id="cabecera">
                <h1>Pagina Reservaciones</h1>
            </header>
            <nav id="menu">
                <ul>
                    <li class="nivel1"><a href="Cerrar.jsp" class="nivel1">Cerrar</a></li>
                    <li class="nivel1"><a href="ListaReservaciones.jsp" class="nivel1">Lista Reservaciones</a></li>
                    <li class="nivel1"><a href="ListaProfesores.jsp" class="nivel1">Lista Profesores</a></li>
                    <li class="nivel1"><a href="ListaAlumnos.jsp" class="nivel1">Lista Alumnos</a></li>
                </ul>
            </nav>
            <section id="principal2">
                <%
                    out.println("<h1>Bienvenido al sistema de consultas</h1>");
                %>
            </section>
        </div>
    </body>
</html>