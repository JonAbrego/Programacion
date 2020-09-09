/*
 * Clase que exiende a <b>Profesor</b> del paquete <b>pkgModelo</b>.
 * Inserta un usuario a la base de datos, primero en la tabla persona y despues
 * en la tabla profesor.
 */

package pkgModelo;

import java.sql.SQLException;

/**
 *
 * @author adriansoto
 */
public class InsercionProfesor extends Profesor{
    
    /** 
     * Variable tipo conecta, para realizar la conexion a la base de datos.
     */
    pkgControl.Conexion objConecta;

    /** 
     * Constructor de la clase, inicializa la variable de conexion. 
     */
    public InsercionProfesor() {
        objConecta = new pkgControl.Conexion();
    }
    
    /** 
     * Metodo que inserta un usuario en la base de datos. 
     * @return <b>false</b> si la consulta se realizó con éxito, <b>true</b> si falla la consulta.
     */
    public boolean insertar() {
        boolean r;
        String sql = "INSERT INTO persona(nombredeusuario, nombres, ap_pat, ap_mat, correo, \"contraseña\") VALUES ('" 
                + super.getUsuario() + "', '" + super.getNombres() + "', '" + super.getaPat() + "', '" + super.getaMat() 
                + "', '" + super.getCorreo() + "', '" + super.getPass() + "');\n"
                + "INSERT INTO profesor(nombredeusuario, constancia, video) VALUES ('" + super.getUsuario() + "', '" + super.getConstancia() + "', '" + super.getVideo() + "');";
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
