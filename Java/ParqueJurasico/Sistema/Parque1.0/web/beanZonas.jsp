<%-- 
    Document   : beanZonas
    Created on : 10/06/2014, 02:22:27 AM
    Author     : abrego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Zonas</title>
        <link rel="stylesheet" href="css/estilo.css" type="text/css">
        <link rel="stylesheet" href="css/Listas.css" type="text/css">
    </head>
    <body>
        <div id="agrupar">
         <header id="cabecera">
                <h1> Registo zonas</h1>
         </header>
            <nav id="menu">
                <ul>
                   <li class="nivel1"><a href="index.html" class="nivel1">cancelar</a></li>
                </ul>
            </nav>
        <section>
            <jsp:useBean id="beanZonas" class="pkgModelo.MuestraReservacion">
                <%
                    int id=beanZonas.regresaID();
                    beanZonas.IngresaZonas(id, request.getParameter("zona1"));
                    beanZonas.IngresaZonas(id, request.getParameter("zona2") );
                    beanZonas.IngresaZonas(id, request.getParameter("zona3"));
                    beanZonas.IngresaZonas(id, request.getParameter("zona4"));
                    beanZonas.IngresaZonas(id, request.getParameter("zona5"));
                    beanZonas.IngresaZonas(id, request.getParameter("zona6"));
                    beanZonas.IngresaZonas(id, request.getParameter("zona7"));
                    beanZonas.IngresaZonas(id, request.getParameter("zona8"));
                    beanZonas.IngresaZonas(id, request.getParameter("zona9"));
                    beanZonas.IngresaZonas(id, request.getParameter("zona10"));
                    beanZonas.IngresaZonas(id, request.getParameter("zona11"));
                    beanZonas.desco();
                        out.print("<table name=\"datosAlumno\" id=\"datosAlumno\">\n");
                        out.print("<form action=\"index.html\" method=\"post\">");
                        out.println("<tr>");
                        out.println("<td>");
                        out.println("<input type=\"submit\" id=\"enviar\" value=\"Finalizar\"/>");
                        out.println("</td>");
                        out.println("</tr>");
                        out.println("</table>");
                        out.println("</form>");
                %>
                </jsp:useBean>
            </section>
        </div>
    </body>
</html>
