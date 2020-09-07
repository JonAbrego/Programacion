<%-- 
    Document   : beanIniciar
    Created on : 8/06/2014, 05:29:08 AM
    Author     : abrego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sesion</title>
        <link rel="stylesheet" href="css/estilo.css" type="text/css">
    </head>
    <body>
        <div id="agrupar">
         <header id="cabecera">
                <h1> Sesion Iniciada</h1>
         </header>
            <nav id="menu">
                <ul>
                      <li class="nivel1"><a href="Cerrar.jsp" class="nivel1">Salir</a></li>
                </ul>
            </nav>
        <section id="principal">
         <jsp:useBean id="beanIniciar" class="pkgModelo.Inicia" scope="session">
                <jsp:setProperty name="beanIniciar" property="usuario"/>
                <jsp:setProperty name="beanIniciar" property="cont"/>
                <%
                    if(beanIniciar.buscaUsuario("usuario").equals(request.getParameter("usuario")) &&
                    beanIniciar.buscaCont("cont").equals(request.getParameter("cont"))) {
                        out.print("<h1>Inició sesión como: " + request.getParameter("usuario") + "</h1>\n");
                        out.print("<table name=\"datosAlumno\" id=\"datosAlumno\">\n");
                        out.print("<form action=\"ConsReserva.jsp\" method=\"post\">");
                        out.println("<tr>");
                        out.println("<td></td>");
                        out.println("<td>");
                        out.println("<input type=\"submit\" id=\"enviar\" value=\"Ingresar al sistema\"/>");
                        out.println("</td>");
                        out.println("</tr>");
                        out.println("</table>");
                        out.println("</form>");                        
                    } else {
                        out.print("<h1>Contraseña o Usuario Incorrecto</h1>\n");
                        session.invalidate();
                    }
                %>
                </jsp:useBean>
            </section>
        </div>
    </body>
</html>
