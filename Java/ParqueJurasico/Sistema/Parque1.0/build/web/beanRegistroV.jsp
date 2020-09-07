<%-- 
    Document   : beanRegistroV
    Created on : 10/06/2014, 05:38:10 PM
    Author     : abrego
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Escuela</title>
        <link rel="stylesheet" href="css/estilo.css" type="text/css">
    </head>
    <body>
        <div id="agrupar">
         <header id="cabecera">
                <h1> Registro Escuela</h1>
         </header>
            <nav id="menu">
                <ul>
                   <li class="nivel1"><a href="index.html" class="nivel1">Inicio</a></li>
                </ul>
            </nav>
        <section id="principal">
            <jsp:useBean id="beanEscuela" class="pkgModelo.RegistrarEscuela">
                <jsp:setProperty name="beanEscuela" property="*"/>
                <%
                    String clave=request.getParameter("clave");
                    if (beanEscuela.insertarEscuelaVieja(clave) == true){
                        out.print("<h1>¡Felicidades, tu nueva reservacion esta hecha</h1>\n");
                        out.print("<table name=\"datosAlumno\" id=\"datosAlumno\">\n");
                        out.print("<tr><td>Clave: </td><td>" + request.getParameter("clave") + "</td></tr>\n");
                        out.print("<tr><td>Grado: </td><td>" + request.getParameter("grado") + "</td></tr>\n");
                        out.print("<tr><td>Hora: </td><td>" + request.getParameter("hora") + "</td></tr>\n");
                        out.print("<tr><td>TipoVisita: </td><td>" + request.getParameter("tipoVisita") + "</td></tr>\n");
                        out.print("<tr><td>Fecha: </td><td>" + request.getParameter("fecha") + "</td></tr>\n");
                        out.print("</table>\n");
                        out.print("<table name=\"datosAlumno\" id=\"datosAlumno\">\n");
                        out.print("<form action=\"RegistraZonas.html\" method=\"post\">");
                        out.println("<tr>");
                        out.println("<td></td>");
                        out.println("<td>");
                        out.println("<input type=\"submit\" id=\"enviar\" value=\"Siguiente Paso\"/>");
                        out.println("</td>");
                        out.println("</tr>");
                        out.println("</table>");
                        out.println("</form>");                        
                    } else {
                        out.print("<h1>Falló la inserción</h1>\n");
                    }
                %>
                </jsp:useBean>
            </section>
        </div>
    </body>
</html>
