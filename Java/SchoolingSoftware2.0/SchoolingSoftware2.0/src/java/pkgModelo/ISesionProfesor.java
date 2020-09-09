/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgModelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adriansoto
 */
public class ISesionProfesor extends Profesor{
    
    pkgControl.Conexion objConecta;

    public ISesionProfesor() {
        objConecta = new pkgControl.Conexion();
    }
    
    public String buscaUsuario (String busqueda) {
        //super.setUsuario(busqueda);
        String sql = "SELECT Profesor.*, Persona.*\n" +
                     "FROM Persona\n" +
                     "INNER JOIN Profesor ON Persona.nombreDeUsuario = Profesor.nombreDeUsuario\n" +
                     "WHERE Persona.nombredeusuario = '" + super.getUsuario() + "'";
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
        String sql = "SELECT Profesor.*, Persona.*\n" +
                     "FROM Persona\n" +
                     "INNER JOIN Profesor ON Persona.nombreDeUsuario = Profesor.nombreDeUsuario\n" +
                     "WHERE Persona.nombredeusuario = '" + super.getUsuario() + "'";
        //System.out.println(sql);
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next()) {
                return rs.getString(10);
            } else {
                return "Fallo la busqueda de datos";
            }
        } catch (SQLException ex) {
            System.out.println("Algo malo paso!!");
        }
        return "Fallo la busqueda de datos";   
    }
    
    public void llenaProfesor () {
        
         String sql = "SELECT Profesor.*, Persona.*\n" +
                     "FROM Persona\n" +
                     "INNER JOIN Profesor ON Persona.nombreDeUsuario = Profesor.nombreDeUsuario\n" +
                     "WHERE Profesor.nombredeusuario = '" + super.getUsuario() + "'";
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next()) {
                super.setNombres(rs.getString(6));
                super.setaPat(rs.getString(7));
                super.setaMat(rs.getString(8));
                super.setCorreo(rs.getString(9));
                super.setConstancia(rs.getString(3));
                super.setVideo(rs.getString(4));
                super.setcPass(rs.getString(10));
            } else {
            }
        } catch (SQLException ex) {
            System.out.println("Algo malo paso!!");
        }
    }
    public String obtenerCursos () {
        String r = "";
        String sql = "SELECT *\n" +
                     "FROM Curso\n" +
                     "WHERE profesor = '" + getNombres() + " " + getaPat() + "'";
        //System.out.println(sql);
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            while(rs.next()) {
                r = r + "<tr>\n" + "<td>";
                r = r + rs.getString(2) + "\n" + "</td>\n" + "<td>\n";
                r = r + rs.getString(3) + "\n" + "</td>\n" + "<td>\n";
                r = r + rs.getString(4) + "\n" + "</td>\n";
                r = r + "</tr>\n";
            }
            r = r + "</table>";
            //System.out.println(r);
            return r;
        } catch (SQLException ex) {
            System.out.println("Algo malo paso!");
        }
        return "Fallo la busqueda de datos";
    }
    
    public String obtenerCursosInscribir () {
        String r;
        if (cursosInscribibles()){
            r = "<br><h1>No hay grupos que inscribir</h1>";
            return r;
        }
        r = "<table name=\"formRegistro\" id=\"formRegistro\">\n" +
"                        <tr>\n" +
"                            <td>\n" +
"                                <label for=\"alumno\">Alumno: </label>\n" +
"                            </td>\n" +
"                            <td>\n" +
"                                <input type=\"text\" id=\"alumno\" name=\"alumno\" placeholder=\"User del Alumno\" autofocus required>\n" +
"                            </td>\n" +
"                            <td>\n" +
"                                <label for=\"nivel\">Curso: </label>\n" +
"                            </td>\n" +
"                            <td>\n" +
"                                <select id=\"curso\" name=\"curso\">";
        String sql = "SELECT * FROM Curso WHERE usr_prof = '" + super.getUsuario() + "'";
        
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            while(rs.next()) {
                if(rs.getString(6).equals("no"))
                    r = r + "<option value=\"" + rs.getString(1) + "\">" + rs.getString(2) + " " + rs.getString(3) + "</option>";
            }
            r = r + "</select>\n" +
"                            </td>\n" +
"                            <td>\n" +
"                                <input type=\"submit\" id=\"enviar\" value=\"Inscribir\"/>\n" +
"                            </td>\n" +
"                        </tr>\n" +
"                    </table>";
            //System.out.println(r);
            return r;
        } catch (SQLException ex) {
            System.out.println("Algo malo paso!");
        }
        return "Fallo la busqueda de datos";
    }
    
    public String obtenerCursosEvaluar () {
        String r;
        if (cursosEvaluables()){
            r = "<br><h1>No hay grupos que evaluar</h1>";
            return r;
        }  
        r = "<table name=\"formRegistro\" id=\"formRegistro\">\n" +
"                        <tr>\n" +
"                            <td>\n" +
"                                <label for=\"calificacion\">Calificacion: </label>\n" +
"                            </td>\n" +
"                            <td>\n" +
"                                <input type=\"text\" id=\"calificacion\" name=\"calificacion\" placeholder=\"Calificacion del curso\" pattern=\"\\d{1,2}\" title=\"Ingrese un número del 0 al 10\" maxlength=\"2\" autofocus>\n" +
"                            </td>\n" +
"                            <td>\n" +
"                                <label for=\"nivel\">Curso: </label>\n" +
"                            </td>\n" +
"                            <td>\n" +
"                                <select id=\"curso\" name=\"curso\">";
        String sql = "SELECT * FROM Curso WHERE usr_prof = '" + super.getUsuario() + "'";
        //System.out.println(sql);
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            while(rs.next()) {
                if(rs.getString(6).equals("si") && rs.getString(9).equals("No evaluado"))
                    r = r + "<option value=\"" + rs.getString(1) + "\">" + rs.getString(4) + " Curso: " + rs.getString(2) + "</option>";
            }
            r = r + "</select>\n" +
"                            </td>\n" +
"                            <td>\n" +
"                                <input type=\"submit\" id=\"enviar\" value=\"Evaluar\"/>\n" +
"                            </td>\n" +
"                        </tr>\n" +
"                    </table>";
            return r;
        } catch (SQLException ex) {
            System.out.println("Algo malo paso!");
        }
        return "Fallo la busqueda de datos";
    }
    
    public boolean cursosEvaluables () {
        String sql = "SELECT * FROM curso WHERE elegido = 'si' AND calificacion = 'No evaluado' AND usr_prof = '" + super.getUsuario() + "'";
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next())
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(EvaluarAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean cursosInscribibles () {
        String sql = "SELECT * FROM curso WHERE elegido = 'no' AND usr_prof = '" + super.getUsuario() + "'";
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next())
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(EvaluarAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
