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
public class OPGCelularTest {
        
    private OPGCelular cel;
    private Conexion con;    
    private static String res;  
    private Auxiliares aux;
            
    public OPGCelularTest(){    
        cel=new OPGCelular();       
        aux=new Auxiliares();
        con=new Conexion();
    }    
        
    /**
     * Metodo que se encarda de insertar algun celular con un imei generado de 
     * manera aleatoria, realiza una busqueda en la base de datos si el celular
     * ya existe, es decir el imei ya existe en la tabla no realizamos ninguna accion
     * de lo contrario gurdamos
     */
    @Test
    public void testInsertar() throws SQLException {        
            cel.setImei(aux.generaCedula());
            cel.setMarca(aux.generaMarca());
            cel.setModelo(aux.generaMarca());
            cel.setDescripcion("xxx");
            String busca="SELECT imei FROM Celular WHERE imei='"+cel.getImei()+"';";             
            ResultSet b =con.buscaDatos(busca);        
            while(b.next())
                res=b.getString(1);        
            if(res==null){
                String sql="INSERT INTO Celular VALUES('"+cel.getImei()+"','"+cel.getMarca()+
                        "','"+cel.getModelo()+"','"+cel.getDescripcion()+"');";
                Assert.assertTrue(cel.insertar());
            }else{
                Assert.assertFalse(cel.insertar());
            }        
    }

    /**
     * Metodo que se encarga de borra algun celular, realizamos una busqueda 
     * del producto si el resultado de esa busqueda es null entonces no realizamos
     * ninguna accion, de lo contrario procedemos a realizar la accion
     */
    @Test
    public void testDeleteCelular() throws SQLException {
        cel.setImei(aux.generaCedula());
        cel.setMarca(aux.generaMarca());
        cel.setModelo(aux.generaMarca());
        cel.setDescripcion("xxx");
        String busca="SELECT imei FROM Celular WHERE imei='"+cel.getImei()+"';";             
        con=new Conexion();
        ResultSet b =con.buscaDatos(busca);        
        while(b.next())
            res=b.getString(1);        
        if(res!=null){                    
            Assert.assertTrue(cel.deleteCelular());
        }else{
            Assert.assertFalse(false);
        }
    }

    /**
     * Metodo que se encarga de actualizar algunos datos (que no es el imei)
     * de un celular que se encuentre en la tabla celular, realizamos la busqueda 
     * del producto si se encuentra procedemos con la actualizacion, de lo cobtrario no 
     * ejercemos ninguna accion
     */
    @Test
    public void testUpDate() throws Exception {
        cel.setImei(aux.generaCedula());
        cel.setMarca(aux.generaMarca());
        cel.setModelo(aux.generaMarca());
        cel.setDescripcion("xxx");
        String busca="SELECT imei FROM Celular WHERE imei='"+cel.getImei()+"';";                     
        con=new Conexion();
        ResultSet b =con.buscaDatos(busca);        
        while(b.next())
            res=b.getString(1);                
        if(res!=null){            
            cel.upDate();
            Assert.assertTrue(true);
        }
        else{            
            Assert.assertFalse(false);
        }
    }
    
}
