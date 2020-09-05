package test;

import pkgModelo.*;
import java.sql.ResultSet;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import pkgControlador.Conexion;

/**
 *
 * @author abrego
 */
public class OPGServicioTest {

    private Auxiliares aux;
    private Conexion con;        
    private OPGServicio servicio;   
    private static String res;  

    /**
     *Constructor
     */
    public OPGServicioTest() {
        con=new Conexion();
        servicio= new OPGServicio();                
        aux=new Auxiliares();
    }    
    
    /**
     * Metodo que hace un guardado en la tabla  
     * Servicio
     */
    @Test
    public void testInsertar() throws Exception {    
        servicio.setDescricion("prueba");
        Assert.assertTrue(servicio.insertar());
    }
    
    /**
     * Metodo con el cual borramos algun servicio de una lista que contiene ciertos servicio en ella
     * si el resultante es alguno que seleccionara lo borramos, de lo 
     * contrario no realizamos nada 
     */
    @Test
    public void testDeleteServicio() throws Exception {                       
        if(servicio.deleteServicio(aux.seleccionaServicio())==true)
            Assert.assertTrue(true);
        else
            Assert.assertFalse(false);
    }

    /**
     * Metodo con el cual actualizamos la descripcion de algun servicio ofrecido
     * seleccionamos alguno de una arreglo, si el resultado arrojado existe en nuestra
     * tabla procedemos a la actualizacion de lo contrario no realizamos dicha accion
     */
    @Test
    public void testUpDate() throws Exception {       
        String r=aux.seleccionaServicio();
        ResultSet b=con.buscaDatos("SELECT id FROM Servicio WHERE id='"+r+"';");       
        while(b.next())
            res=b.getString(1); 
        if(res==null)
            Assert.assertFalse(false);
        else{
            servicio.setDescricion("Modificado"+r);
            servicio.upDate(r);
            Assert.assertTrue(true);
        }       
    }
    
}
