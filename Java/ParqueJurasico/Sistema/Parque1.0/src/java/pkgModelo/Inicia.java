/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgModelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author abrego
 */
public class Inicia extends Gerente{
     pkgControl.Conexion objConecta;
     private Statement stmt;
   
     /**
      * Constructor por defecto
      */
     public Inicia(){
          objConecta = new pkgControl.Conexion();
     }
     
     /**
      * Metodo usado para buscar un usuario 
      * @param busqueda
      * @return el usuario que estamos buscando
      */
     public String buscaUsuario (String busqueda) {
        String sql = "SELECT * FROM Administrador Where usuario = '" + super.getUsuario() + "'";
        System.out.println(sql);
    try {
        ResultSet rs = objConecta.buscaDatos(sql);
        if(rs.next()) {
            return rs.getString(1);
        } else {
            return "Fallo la busqueda de datos";
        }
    } catch (SQLException ex) {
        System.out.println("Algo malo paso!!");
    }
        return "Fallo la busqueda de datos";       
    }
     
     /**
      * Metodo usado para buscar la congtrasena 
      * @param busqueda
      * @return la contrasena  
      */
     public String buscaCont (String busqueda) {
    //super.setUsuario(busqueda);
        String sql = "SELECT * FROM Administrador Where contrasena = '" + super.getCont() + "'";
        System.out.println(sql);
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
}
