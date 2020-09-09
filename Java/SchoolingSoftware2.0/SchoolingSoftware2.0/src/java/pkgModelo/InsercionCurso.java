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
public class InsercionCurso extends Curso{
    /** 
     * Variable tipo conecta, para realizar la conexion a la base de datos.
     */
    pkgControl.Conexion objConecta;

    /** 
     * Constructor de la clase, inicializa la variable de conexion. 
     */
    public InsercionCurso() {
        objConecta = new pkgControl.Conexion();
    }
    
    /** 
     * Metodo que inserta un usuario en la base de datos. 
     * @return <b>false</b> si la consulta se realizó con éxito, <b>true</b> si falla la consulta.
     */
    public boolean insertar() {
        if (horarioValido(super.getHorario()))
            return true;
        boolean r;
        String sql = "INSERT INTO curso(nivel, horario, alumno, profesor, elegido, usr_prof, usr_alumno, calificacion) VALUES ('" + super.getNivel() + "', '"
                + super.getHorario() + "', '" + super.getAlumno() + "', '" + super.getProfesor() + "', '"
                + super.getElegido() + "', '" + super.getUsuario() + "', '" + super.getUsr_alumno() + "', '" + super.getCalificacion() + "');";
        System.out.println(sql);
        try {
            r = objConecta.insertar(sql);
            objConecta.desconectar();
            return r;
        } catch (SQLException ex) {
            objConecta.desconectar();
            return true;
        }
    }
    public boolean horarioValido(String horario) {
        String sql = "SELECT horario FROM curso WHERE horario = '" + super.getHorario() + "' AND profesor = '" + super.getProfesor() + "'";
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next())
                return true;
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(InsercionCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
