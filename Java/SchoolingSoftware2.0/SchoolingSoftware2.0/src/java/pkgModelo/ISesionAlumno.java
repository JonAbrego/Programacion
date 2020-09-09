/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgModelo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author adriansoto
 */
public class ISesionAlumno extends Alumno{
    
    pkgControl.Conexion objConecta;

    public ISesionAlumno () {
        objConecta = new pkgControl.Conexion();
    }
    
    public String buscaUsuario (String busqueda) {
    //super.setUsuario(busqueda);
    String sql = "SELECT Alumno.*, Persona.*\n" +
                 "FROM Persona\n" +
                 "INNER JOIN Alumno ON Persona.nombreDeUsuario = Alumno.nombreDeUsuario\n" +
                 "WHERE Alumno.nombredeusuario = '" + super.getUsuario() + "'";
    //System.out.println(sql);
    try {
        ResultSet rs = objConecta.buscaDatos(sql);
        if(rs.next()) {
            return rs.getString(2);
        } else {
            return "Fallo la busqueda de datos";
        }
    } catch (SQLException ex) {
        System.out.println("Algo malo paso!!");
    }
        return "Fallo la busqueda de datos";       
    }
    
    public String buscaPass (String busqueda) {
        //super.setUsuario(busqueda);
        String sql = "SELECT Alumno.*, Persona.*\n" +
                     "FROM Persona\n" +
                     "INNER JOIN Alumno ON Persona.nombreDeUsuario = Alumno.nombreDeUsuario\n" +
                     "WHERE Alumno.nombredeusuario = '" + super.getUsuario() + "'";
        //System.out.println(sql);
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next()) {
                return rs.getString(9);
            } else {
                return "Fallo la busqueda de datos";
            }
        } catch (SQLException ex) {
            System.out.println("Algo malo paso!!");
        }
        return "Fallo la busqueda de datos";   
    }
    
    public void llenaAlumno () {
        
        String sql = "SELECT Alumno.*, Persona.*\n" +
                     "FROM Persona\n" +
                     "INNER JOIN Alumno ON Persona.nombreDeUsuario = Alumno.nombreDeUsuario\n" +
                     "WHERE Alumno.nombredeusuario = '" + super.getUsuario() + "'";
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next()) {
                super.setNombres(rs.getString(5));
                super.setaPat(rs.getString(6));
                super.setaMat(rs.getString(7));
                super.setCorreo(rs.getString(8));
                super.setTelefono(rs.getString(3));
                super.setcPass(rs.getString(9));
            } else {
            }
        } catch (SQLException ex) {
            System.out.println("Algo malo paso!!");
        }
    }
    public String obtenerCursos () {
        String r = "";
        String sql = "SELECT * FROM Curso WHERE elegido = 'no' ORDER BY horario";
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            while(rs.next()) {
                r = r + "<form name=\"formCurso\" id=\"formCurso\" action=\"beanSolicitarCurso.jsp\" method=\"post\">\n";
                r = r + "<tr>\n" + "<td>\n";
                r = r + "<input type=\"hidden\" id=\"nivel\" name=\"nivel\" value=\"" + rs.getString(2) + "\"/>\n";
                r = r + "<a href=\"#\">" + rs.getString(2) + "</a>\n" + "</td>\n" + "<td>\n";
                r = r + "<input type=\"hidden\" id=\"horario\" name=\"horario\" value=\"" + rs.getString(3) + "\"/>\n";
                r = r + "" + rs.getString(3) + "\n" + "</td>\n" + "<td>\n";
                r = r + "<input type=\"hidden\" id=\"profesor\" name=\"profesor\" value=\"" + rs.getString(5) + "\"/>\n";
                r = r + "<a href=\"#\">" + rs.getString(5) + "</a>\n" + "</td>\n" + "<td>\n";
                r = r + "<input type=\"submit\" id=\"enviar\" value=\"Solicitar\"/>\n" + "</td>\n";
                r = r + "</tr>\n";
                r = r + "</form>\n";
            }
            //System.out.println(r);
            return r;
        } catch (SQLException ex) {
            System.out.println("Algo malo paso!");
        }
        return "Fallo la busqueda de datos";
    }
    
    public String misCursos () {
        String r = "";
        String sql = "SELECT * FROM Curso WHERE usr_alumno = '" + super.getUsuario() + "' ORDER BY horario";
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            while(rs.next()) {
                r = r + "<tr>\n" + "<td>\n";
                r = r + rs.getString(2) + "</td>\n" + "<td>\n";
                r = r + rs.getString(3) + "</td>\n" + "<td>\n";
                r = r + rs.getString(5) + "</td>\n";
                r = r + "</tr>\n";
            }
            //System.out.println(r);
            return r;
        } catch (SQLException ex) {
            System.out.println("Algo malo paso!");
        }
        return "Fallo la busqueda de datos";
    }
}
