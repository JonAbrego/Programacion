package pkgControlador;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Calse encargada de hacer la comunicación con
 * la base de datos en SQLite
 * @author abrego
 */
public class Conexion {
    Connection con;
    Statement st;
    ResultSet rs;
    String url="jdbc:sqlite:Proyecto02.db";
    
    
    /**
     * Constructor de la clase, que realiza al conexion con la base de datos,
     * inicializa la variable <b>con</b> y usa <b>url</b>, 
     * para enlazar con la base de datos.
     */
    public Conexion(){
        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Cargo el Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Problemas con el Driver");
        }
        try {
            if (con == null)
                con = DriverManager.getConnection(url);
            System.out.println("Se conectó al motor");
        } catch (SQLException ex) {
            System.out.println("Fallo la conexion al motor");
        }
    }
    
    /**
     * Inserta una tupla la base de datos.
     * @param sql la sentencia para insertar, en leguaje SQL
     * @return <b>boolean</b> <b>true</b> si la consulta se realiza con éxito
     * y <b>false</b> si algo falló.
     * @throws SQLException si hay algún problema con el insertado
     */
    public boolean insertar(String sql) throws SQLException {
        st = con.createStatement(); // Se conecta con la base de datos
        boolean r = st.execute(sql);
        return !r;
    }
    
    /**
     * Realiza una consulta dentro de la base de datos
     * @param sql la sentencia para buscar, en lenguaje SQL
     * @return la busqueda realizada 
     * @throws SQLException si no fue hecha de manera adecauda la consulta <br>
     * o si ocurre algo extraño
     */
    public ResultSet buscaDatos (String sql) throws SQLException {
        st = con.createStatement();
        rs = st.executeQuery(sql);
        return rs;
    }
    
    /**
     * Realiza una actualizacion dentro de la base de datos
     * @param sql la sentencia para buscar, en lenguaje SQL
     * @throws SQLException si la actualizacion no fue hecha adecuadamente
     */
    public void actualizarBD(String sql) throws SQLException {
        st=con.createStatement();
        st.executeUpdate(sql);
        System.out.println("Base Actualizada!");
    }
    
    /**
     * Realiza una sentecia de borrado sobre la base de datos
     * @param sql la senetencia para borrar en lenguaje SQL
     * @return <b>boolean</b> <b>true</b> si el borrado fue hecho exitosamente
     * <b>false</b> en otro caso
     * @throws SQLException 
     */
    public boolean borrarBD(String sql) throws SQLException {
        st = con.createStatement(); // Se conecta con la base de datos
        boolean r = st.execute(sql);
        return !r;
    }
    
    /**
     * Cierra el Statement y cierra la conexion hecha con la base de datos
     */
    public void desconectar() {
        try {
            st.close();
            System.out.println("Se cerró el statement");
        } catch (SQLException ex) {
            System.out.println("No se pudo cerrar el statement");
        }
    }    
}
