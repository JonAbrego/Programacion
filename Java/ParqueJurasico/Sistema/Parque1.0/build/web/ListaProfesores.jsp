<%-- 
    Document   : ListaProfesores
    Created on : 9/06/2014, 06:16:25 AM
    Author     : abrego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="css/Listas.css" type="text/css">
        <link rel="stylesheet" href="css/estilo.css" type="text/css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Profesores</title>
    </head>
    <body>
        <div id="agrupar">
            <header id="cabecera">
                <h1>Lista Profesores</h1>
            </header>
            <nav id="menu">
                <ul>
                    <li class="nivel1"><a href="Cerrar.jsp" class="nivel1">Cerrar</a></li>
                    <li class="nivel1"><a href="ListaReservaciones.jsp" class="nivel1">Lista Reservaciones</a></li>
                    <li class="nivel1"><a href="#" class="nivel1">Lista Profesores</a></li>
                    <li class="nivel1"><a href="ListaAlumnos.jsp" class="nivel1">Lista Alumnos</a></li>
                </ul>
            </nav>
            <section>
                <jsp:useBean id="beanRes" class="pkgModelo.MuestraReservacion">
                <%  
                   out.println(beanRes.verProfesores());
                %>
                </jsp:useBean>
            </section>
        </div>
    </body>
</html>
