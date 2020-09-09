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

public class AdministrarCuentaP extends Profesor{

    pkgControl.Conexion objConecta = new pkgControl.Conexion();
    
    public boolean guardar(){
    
        return objConecta.cambiarP(usuario, nombres, aPat, aMat, correo, pass, constancia, video)!=null;

    }
    
}
        

