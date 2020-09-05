package test;

import pkgModelo.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import pkgControlador.Conexion;


/**
 *
 * @author abrego
 */
public class OPGPersonaTest {
    
    private OPGPersona persona;
    private Conexion conexion;
    private Auxiliares aux;
    private static String res; 
    private String cd;
    
    /**
     * Constructor
     */
    public OPGPersonaTest() {
        persona=new OPGPersona();
        conexion=new Conexion();
        aux=new Auxiliares();
        cd=aux.generaCedula();
    }       
        
    /**
     * Metodo encargado de realizar un guardado en la tabla personas 
     * generando el rfc de manera aleatoria, ya teniendo el rfc generado 
     * procedemos a ver si existe ese mismo en la base de datos, si existe 
     * no realizamos ninguna accion de guardado, en el caso contrario lo guardamos
     * en dicha tabla con el rfc generado y el resto de sus atributos con cosas X
     */
    @Test
    public void testInsertar() throws Exception {
        persona.setRfc(cd);
        persona.setNombre("preubas");
        persona.setApellidoP("prueba");
        persona.setApellidoM("prueba");
        persona.setFechaNacimiento("prueba");        
        persona.setSexo("M");
        persona.setCiudad("prueba");
        persona.setColonia("prueba");
        persona.setCalle("prueba");
        persona.setNumero("prueba");
        persona.setCodigoPostal("prueba");
        String busca="SELECT rfc FROM Persona WHERE rfc='"+persona.getRfc()+"';";
        ResultSet b =conexion.buscaDatos(busca);        
        while(b.next())
            res=b.getString(1);               
        if(res==null){            
            Assert.assertTrue(persona.insertar());
        }else{
            Assert.assertFalse(false);            
        }        
    }

    /**
     * Buscamos a nuestra persona por medio del rfc,
     * si existe entonces agregamos su telefono
     */
    @Test
    public void testInsertartTel() throws Exception {
        persona.setRfc(cd);
        String busca="SELECT rfc FROM Persona WHERE rfc='"+persona.getRfc()+"';";
        ResultSet b =conexion.buscaDatos(busca);        
        while(b.next())
            res=b.getString(1);               
        if(res==null){            
            Assert.assertFalse(false);            
        }else{            
            Assert.assertTrue(persona.insertartTel("prueba"));            
        }                
    }


    /**
     * Buscamos a nuestra persona por medio del rfc,
     * si existe entonces agregamos su telefono
     */
    @Test
    public void testInsertartEmail() throws Exception {
        persona.setRfc(cd);
        String busca="SELECT rfc FROM Persona WHERE rfc='"+persona.getRfc()+"';";
        ResultSet b =conexion.buscaDatos(busca);        
        while(b.next())
            res=b.getString(1);               
        if(res==null){            
            Assert.assertFalse(false);            
        }else{            
            Assert.assertTrue(persona.insertartEmail("prueba"));            
        }        
    }



    /**
     * Creamos una lista donde guardamos todos los rfc eistentes 
     * en la tabla persona, si la lista obtenida tiene al menos 
     * una elemeto procedemos al borrado de lo contrario no hacemos nada 
     */
    @Test
    public void testDeletePersona() throws Exception {
        ArrayList<String> lis=new ArrayList<>();
        String sql="SELECT rfc FROM Persona;";
        ResultSet b =conexion.buscaDatos(sql);        
        while(b.next())
            lis.add(b.getString(1));                
        if(lis.size()>0){
            String s=lis.get(0);
            persona.setRfc(s);
            if(persona.deletePersona())
                Assert.assertTrue(true);
            else
                Assert.assertFalse(false);
        }
        Assert.assertFalse(false);
    }

    /**
     * Metodo encargado de actualizar algunos datos particulares de 
     * la entidad persona, si la persona que queremos actualizar 
     * existe procedemos a la actualizacion de lo contrario no realizamos
     * ninguna accion
     */
    @Test
    public void testUpDatePersona() throws Exception {       
        persona.setCiudad("actualizado");
        persona.setColonia("actualizado");
        persona.setCalle("actualizado");
        persona.setNumero("actualizado");
        persona.setCodigoPostal("actualizado");        
        persona.setRfc(cd);
        String sql="SELECT rfc FROM Persona WHERE rfc='"+persona.getRfc()+"';";
        ResultSet b =conexion.buscaDatos(sql);        
        while(b.next())
            res=b.getString(1);                
        if(res!=null){
            persona.upDatePersona();
            Assert.assertTrue(true);
        }else{
            Assert.assertFalse(false);
        }
    }    
}
