package test;

import pkgModelo.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Assert;
import org.junit.Test;
import pkgControlador.Conexion;


/**
 *
 * @author abrego
 */
public class OPGClienteTest {
    
    private Auxiliares aux;
    private OPGCliente cliente;
    private Conexion conexion;       
    private String cd;
    private static String res;
    
    public OPGClienteTest() {
        aux=new Auxiliares();
        cliente=new OPGCliente();        
        conexion=new Conexion();
        cd=aux.generaCedula();
    }
    
    /**
     * Metodo encargado de insertar un cliente en la base de datos
     * hace una busqueda en persona del nuevo cliente a agregar, si no esta en 
     * la tabla persona, no realizamos nada, si es el caso contrario hacemos 
     * el guardado
     */
    @Test
    public void testInsertarCliente() throws SQLException {        
        cliente.setRfc(cd);
        String sql="SELECT rfc FROM Persona WHERE rfc='"+cliente.getRfc()+"';";
        ResultSet b =conexion.buscaDatos(sql);        
        while(b.next())
            res=b.getString(1);        
        if(res!=null){
            String insert="SELECT rfc FROM Cliente WHERE rfc='"+cliente.getRfc()+"';";
            ResultSet b1=conexion.buscaDatos(insert);
            while(b1.next())
                res=b1.getString(1);
            if(res==null){
                cliente.insertarCliente();
                Assert.assertTrue(true);
            }else
                Assert.assertFalse(false);
        }
        Assert.assertFalse(false);
    }
    

    /**
     * Creamos un insertado X para ver si realiza 
     * el guardado en la relacion, haciendo una busqueda del cliente
     * en la tabla persona si existe procedemos, de lo contrario 
     * no realizamos ningun guardado
     */
    @Test
    public void testInsertarCompra() throws Exception {
        cliente.setRfc(cd);
        String sql="SELECT rfc FROM Persona WHERE rfc='"+cliente.getRfc()+"';";
        ResultSet b =conexion.buscaDatos(sql);        
        while(b.next())
            res=b.getString(1);        
        if(res!=null){
            cliente.insertarCompra("01","4","1992-01-01",1);
            Assert.assertTrue(true);
        }else{
            Assert.assertFalse(false);
        }                    
    }

    /**
     * Realizamos un guardado de alguna venta hecha
     */
    @Test
    public void testInsertarVender() throws Exception {
        float com=0.5f;
        Assert.assertTrue(cliente.insertarVender("01", cd,com));
    }
    
}
