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
 * @author abrego
 */
public class RegistrarEscuela extends Escuela {
     pkgControl.Conexion objConecta;
     
     /**
      * Constructor por defecto
      */
     public RegistrarEscuela() {
         objConecta = new pkgControl.Conexion();
     }
     
     /**
      * Metodo que sirve para insertar una escuela a la base de datos 
      * @return un booleano que indica si se pudo agregar la escuela 
      */
     public boolean insertarEscuela() {
         boolean r;
         String sql = "INSERT INTO escuela(clave, nombre, entidad, localidad, calle,numero) VALUES ('" 
                       + super.getClave() + "', '" + super.getNombre() + "', '" + super.getEntidad() 
                       + "', '" + super.getLocalidad()+ "', '" + super.getCalle() + "', '" + super.getNumero() + "');\n"
                       + "INSERT INTO Reservacion(hora,tipo_visita, fecha) VALUES ('" 
                       + super.getHora() + "', '" + super.getTipoVisita()+ "', '" + super.getFecha()+ "');\n"
                       + "INSERT INTO Realiza(clave,grado) VALUES ('" 
                       + super.getClave()+"'," + super.getGrado()+");\n";                              
         try {        
             r = objConecta.insertar(sql);
             objConecta.desconectar();
             return r;
         } catch (SQLException ex) {
             objConecta.desconectar();
             return true;
         }
     } 
            
     /**
      * Metodos usado para ingresar una nueva resrvacion de alguna escuela ya hecha
      * @param clave String 
      * @return boolean si se pudo ingrear al sistema
      * @throws SQLException 
      */
         public boolean insertarEscuelaVieja(String clave) throws SQLException {
         boolean r;
         ResultSet rs=objConecta.buscaDatos("select * from Escuela where clave = '"
                                            +clave+"'");
         String sql;
         if(rs.next()){
               sql =  "INSERT INTO Reservacion(hora,tipo_visita, fecha) VALUES ('" 
                       + super.getHora() + "', '" + super.getTipoVisita()+ "', '" + super.getFecha()+ "');\n"
                       + "INSERT INTO Realiza(clave,grado) VALUES ('" 
                       + super.getClave()+"'," + super.getGrado()+");\n";
               r=objConecta.insertar(sql);
               return true;
            
         }
         return false;
     } 
}
