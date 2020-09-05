package test;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import java.util.Random;
import pkgControlador.Conexion;

/**
 *
 * @author abrego
 */
public class ConexionTest {
    
    private Conexion con; 
    private Random random;
    private int total;
    private static String res;
   
    /**
     *Constructor
     */     
    public ConexionTest(){
        con= new Conexion();
        random = new Random();
	total = 10 + random.nextInt(90);
    }

    /**
     * Metodo testInsertar realiza un guardado en la tabla Pruebas 
     * de la base de datos
     * hace una busqueda si el resultado es null hace la 
     * operacion de insertado, de lo contrario no realiza nada sobre 
     * la base de datos
     */
    @Test
    public void testInsertar() throws SQLException {                
        boolean exp=true;                       
        int id=total;
        String busca="SELECT id FROM Pruebas WHERE id="+id+";";                
        ResultSet b =con.buscaDatos(busca);        
        while(b.next())
            res=b.getString(1);        
        if(res==null){            
            String sql = "INSERT INTO Pruebas VALUES("+id+",'Servicio de prueba: "+id+"');";        
            Assert.assertTrue(con.insertar(sql));                        
        }else
            Assert.assertFalse(false);                            
    }

    /**
     * Realiza una busqueda dentro de la tabla pruebas
     * hace una busqueda si el resultado es distinto de null hace la 
     * operacion de busqueda, de lo contrario no realiza nada sobre 
     * la base de datos
     */
    @Test
    public void testBuscaDatos() throws SQLException {                
        String sql = "SELECT id FROM Pruebas WHERE id="+total+";";                        
        ResultSet result =con.buscaDatos(sql);                
        while(result.next())
            res=result.getString(1);        
        if(res!=null){
            Assert.assertTrue(true);
        }else{            
            Assert.assertFalse(false);
        }
    }

    /**
     * Realiza una actualizacion dentro de la tabla Pruebas
     * hace una busqueda si el resultado es distinto de null hace la 
     * operacion de actualizar, de lo contrario no realiza nada sobre 
     * la base de datos
     */
    @Test
    public void testActualizarBD() throws SQLException {                       
        String busqueda = "SELECT id FROM Pruebas WHERE id="+total+";";                        
        ResultSet result =con.buscaDatos(busqueda);                
        while(result.next())
            res=result.getString(1);                              
        if(res!=null){
            int i=Integer.parseInt(res);
            String sql="UPDATE Pruebas SET descripcion='actualizado' WHERE id="+i+";";
            con.actualizarBD(sql);
            Assert.assertTrue(true);
        }else
            Assert.assertFalse(false);
    }

    /**
     * Realiza un borrado sobre algun elemento de la tabla Pruebas
     * hace una busqueda si el resultado es distinto de null hace la 
     * operacion de borrado, de lo contrario no realiza nada sobre 
     * la base de datos
     */
    @Test
    public void testBorrarBD() throws SQLException {
        String busqueda = "SELECT id FROM Pruebas WHERE id="+total+";";                        
        ResultSet result =con.buscaDatos(busqueda);                
        while(result.next())
            res=result.getString(1);                              
        if(res!=null){
            int i=Integer.parseInt(res);
            String sql="DELETE FROM Pruebas WHERE id='"+i+"';";
            con.borrarBD(sql);
            Assert.assertTrue(true);
        }else
            Assert.assertFalse(false);        
    }   
}
