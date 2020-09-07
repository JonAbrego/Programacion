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
 * @author abrego
 */
public class Conexion {
    Connection con;
    Statement st;
    ResultSet rs;
    String url = "jdbc:postgresql://localhost:5432/Parque";
    String usuario = "postgres";
    String pass = "Aguilas09";

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
    
    public void actualizarBD(String sentencia) throws SQLException {
        st.executeUpdate(sentencia);
        System.out.println("Base Actualizada!");
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


