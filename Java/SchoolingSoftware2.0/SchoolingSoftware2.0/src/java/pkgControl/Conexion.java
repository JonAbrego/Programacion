 /*
 * Clase que realiza una conexion con la base de datos.
 */

package pkgControl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author adriansoto
 */
public class Conexion {
    Connection con;
    Statement st;
    ResultSet rs;
    String url = "jdbc:postgresql://localhost:5432/Escuela";
    String usuario = "postgres";
    String pass = "root";

    /**
     * Constructor de la clase, que realiza al conexion con la base de datos,
     * inicializa la variable <b>con</b> y usa <b>url</b>, <b>usuario</b> y
     * <b>pass</b> para enlazar con la base de datos.
     */
    public Conexion() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Cargo el Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Problemas con el Driver");
        }
        try {
            if (con == null)
                con = DriverManager.getConnection(url, usuario, pass);
            System.out.println("Se conectó al motor");
        } catch (SQLException ex) {
            System.out.println("Fallo la conexion al motor");
        }
    }
    
    /**
     * 
     * @param usuario
     * @param nombre
     * @param aPat
     * @param aMat
     * @param correo
     * @param contraseña
     * @param constancia
     * @param video
     * @return 
     */
    public ResultSet cambiarP(String usuario, String nombre ,String aPat, String aMat, String correo , String contraseña, String constancia, String video){
        rs = null;
        st = null;
        try{
            System.out.print("Abri Conexion");
            st = con.createStatement();
            System.out.print("Pase el Stament\n" + "UPDATE persona" + 
                     "\nSET nombres=\'" + nombre + "\', ap_pat=\'" + aPat +"\', ap_mat= \'" + aMat + "\' , correo=\'" + correo + "\', \"contraseña\"=\'" + contraseña + "\'" +
                                " \nWHERE nombredeusuario = '" + usuario +"';"+ "\nUPDATE profesor" + "\nSET video = \'" + video +  "\', constancia=\'" + constancia + "\'" + "\' \nWHERE nombredeusuario = \'" + usuario +"\';");
            rs = st.executeQuery("UPDATE persona" + 
                     "\nSET nombres=\'" + nombre + "\', ap_pat=\'" + aPat +"\', ap_mat= \'" + aMat + 
                    "\' , correo=\'" + correo + "\', \"contraseña\"=\'" + contraseña + "\'" +
                    
                                " \nWHERE nombredeusuario = '" + usuario +"';"
            + "\nUPDATE profesor" + "\nSET video = \'" + video +  "\', constancia=\'" + constancia + "\'" + "\' \nWHERE nombredeusuario = \'" + usuario +"\';");
            System.out.print("Actualice Segun");
            st.close();
            desconectar();
        }
        catch (SQLException e){
            System.out.println("Problema al consultar la base de datos.");
        }
        return rs;
    }
    
    /**
     * 
     * @param usuario
     * @param nombre
     * @param aPat
     * @param aMat
     * @param telefono
     * @param correo
     * @param contraseña
     * @return 
     */
    public ResultSet cambiarA(String usuario, String nombre ,String aPat, String aMat, String telefono, String correo , String contraseña){
        rs = null;
        st = null;
        try{
            System.out.print("Abri Conexion");
            st = con.createStatement();
            System.out.print("Pase el Stament\n" + "UPDATE persona" + 
                     "\nSET nombres = \'" + nombre + "\', ap_pat = \'" + aPat +"\', ap_mat = \'" + aMat + "\' , correo = \'" 
                    + correo + "\', \"contraseña\" = \'" + contraseña + "\'" 
                    + " \nWHERE nombredeusuario = \'" + usuario +"\';"
                    + "\nUPDATE alumno" + "\nSET telefono = \'" + telefono + "\' \nWHERE nombredeusuario = \'" + usuario +"\';");
            rs = st.executeQuery("UPDATE persona" + 
                     "\nSET nombres = \'" + nombre + "\', ap_pat = \'" + aPat +"\', ap_mat = \'" + aMat + "\' , correo = \'" 
                    + correo + "\', \"contraseña\" = \'" + contraseña + "\'" 
                    + " \nWHERE nombredeusuario = \'" + usuario +"\';"
                    + "\nUPDATE alumno" + "\nSET telefono = \'" + telefono + "\' \nWHERE nombredeusuario = \'" + usuario +"\';");
            System.out.print("Actualice Segun");
            st.close();
            desconectar();
        }
        catch (SQLException e){
            System.out.println("Problema al consultar la base de datos.");
        }
        return rs;
    }
    
    /**
     * Inserta un usuario a la base de datos.
     * @param sql la sentencia para insertar, en leguaje SQL
     * @return <b>boolean</b> <b>false</b> si la consulta se realiza con éxito
     * y <b>true</b> si algo falló.
     * @throws SQLException 
     */
    public boolean insertar(String sql) throws SQLException {
       st = con.createStatement(); // Se conecta con la base de datos
       boolean r = st.execute(sql);
       return r;
    }
    
    public ResultSet buscaDatos (String sql) throws SQLException {
        st = con.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }
    
    /**
     * Desconecta al sistema de la base de datos. 
     */
    public void desconectar() {
        try {
            st.close();
            System.out.println("Se cerró el statement");
        } catch (SQLException ex) {
            System.out.println("No se pudo cerrar el statement");
        }
        try {
            con.close();
            System.out.println("Se cerró la conexion");
        } catch (SQLException ex) {
            System.out.println("No se pudo cerrar la conexion");
        }
    }
}