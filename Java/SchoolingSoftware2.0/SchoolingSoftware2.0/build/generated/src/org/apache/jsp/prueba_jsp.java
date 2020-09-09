package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class prueba_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html>\n");
      out.write("<!--\n");
      out.write("To change this license header, choose License Headers in Project Properties.\n");
      out.write("To change this template file, choose Tools | Templates\n");
      out.write("and open the template in the editor.\n");
      out.write("-->\n");
      out.write("<html lang=\"es\">\n");
      out.write("    <head>\n");
      out.write("        <title>Harmon Hell</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"CSS/miEstilo.css\" type=\"text/css\" />\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div id=\"agrupar\">\n");
      out.write("            <header id=\"cabecera\">\n");
      out.write("                <img src=\"IMG/hh.png\" height=\"40%\" width=\"40%\"/>\n");
      out.write("            </header>\n");
      out.write("            <nav id=\"menu\">\n");
      out.write("                <ul>\n");
      out.write("                    <li class=\"nivel1\"><a href=\"index.html\" id=\"boton\">Inicio</a></li>\n");
      out.write("                    <li class=\"nivel1\"><a href=\"RegistroProfesor.jsp\" id=\"boton\">Regresar</a></li>\n");
      out.write("                </ul>\n");
      out.write("            </nav>\n");
      out.write("            <section id=\"principal\">\n");
      out.write("                <input type=\"file\" accept=\"application/pdf\">\n");
      out.write("            </section>\n");
      out.write("            <footer id=\"pie\">\n");
      out.write("                Derechos reservados &copy; 2014\n");
      out.write("                <table id=\"follow\">\n");
      out.write("                    <tr>\n");
      out.write("                        <td><a>Siguenos: </a></td>\n");
      out.write("                        <td><a href=\"https://www.facebook.com/harmonhellschool\"><img src=\"IMG/ico_fb.gif\"></a></td>\n");
      out.write("                        <td><a href=\"#\"><img src=\"IMG/ico_tw.gif\"></a></td>\n");
      out.write("                        <td><a href=\"#\"><img src=\"IMG/ico_yt.gif\"></a></td>\n");
      out.write("                        <td><a href=\"#\"><img src=\"IMG/ico_gp.gif\"></a></td>\n");
      out.write("                    </tr>\n");
      out.write("                </table>\n");
      out.write("            </footer>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
