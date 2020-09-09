/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgModelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import pkgModelo.Profesor;
import pkgModelo.Profesor;

public class AdministrarCuentaA extends Alumno{

    pkgControl.Conexion objConecta = new pkgControl.Conexion();
    
    public boolean guardar(){
    
        return objConecta.cambiarA(usuario, nombres, aPat, aMat, telefono, correo, pass) != null;

    }
    
}
        

