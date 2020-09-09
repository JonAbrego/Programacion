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
public class LlenarCursos{
     /** 
     * Variable tipo conecta, para realizar la conexion a la base de datos.
     */
    pkgControl.Conexion objConecta;
    String profesor;
    
    /** 
     * Constructor de la clase, inicializa la variable de conexion. 
     */
    public LlenarCursos() {
        objConecta = new pkgControl.Conexion();
    }
    
    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
    
    public String obtenerCursos () {
        String r = "<table name=\"datosCurso\" id=\"datosCurso\">\n" +"<tr>\n" + "<td>\n" + "Nivel\n" + "</td>\n" +
                "<td>\n" + "Horario\n" + "</td>\n" + "<td>\n" + "Profesor\n" + "</td>\n" + "</tr>\n";
        String sql = "SELECT * FROM Curso ORDER BY horario";
        //System.out.println(sql);
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            while(rs.next()) {
                if(rs.getString(6).equals("no")) {
                    r = r + "<tr>\n" + "<td>";
                    r = r + rs.getString(2) + "\n" + "</td>\n" + "<td>\n";
                    r = r + rs.getString(3) + "\n" + "</td>\n" + "<td>\n";
                    r = r + rs.getString(5) + "\n" + "</td>\n";
                    r = r + "</tr>\n";
                }
            }
            r = r + "</table>";
            //System.out.println(r);
            return r;
        } catch (SQLException ex) {
            System.out.println("Algo malo paso!");
        }
        return "Fallo la busqueda de datos";
    }
    
    public String obtenerCursos (String profesor) {
        String r = "<table name=\"datosCurso\" id=\"datosCurso\">\n" +"<tr>\n" + "<td>\n" + "Nivel\n" + "</td>\n" +
                "<td>\n" + "Horario\n" + "</td>\n" + "<td>\n" + "Alumno\n" + "</td>\n" + "</tr>\n";
        String sql = "SELECT *\n" +
                     "FROM Curso\n" +
                     "WHERE profesor = '" + getProfesor() + "'";
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
}
