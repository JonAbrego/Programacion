/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgModelo;

import java.sql.SQLException;

/**
 *
 * @author adriansoto
 */
public class InsercionAlumno extends Alumno{
    pkgControl.Conexion objConecta;

    public InsercionAlumno() {
        objConecta = new pkgControl.Conexion();
    }
    
    public boolean insertar() {
        boolean r;
        String sql = "INSERT INTO persona(nombredeusuario, nombres, ap_pat, ap_mat, correo, \"contraseña\") VALUES ('" 
                + super.getUsuario() + "', '" + super.getNombres() + "', '" + super.getaPat() + "', '" + super.getaMat() 
                + "', '" + super.getCorreo() + "', '" + super.getPass() + "');\n"
                + "INSERT INTO alumno(nombredeusuario, telefono) VALUES ('" + super.getUsuario() + "', " + super.getTelefono() + ");";
        //System.out.println(sql);
        try {
            r = objConecta.insertar(sql);
            objConecta.desconectar();
            return r;
        } catch (SQLException ex) {
            objConecta.desconectar();
            return true;
        }
    }
}
