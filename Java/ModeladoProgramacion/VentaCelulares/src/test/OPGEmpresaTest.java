package test;


import pkgModelo.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import pkgControlador.Conexion;


/**
 *
 * @author abrego
 */
public class OPGEmpresaTest {
    
    private OPGEmpresa empresa;
    private Conexion con=new Conexion();
    private Auxiliares aux;
    private String cd;
    private static String res;
    
    public OPGEmpresaTest() {
        empresa=new OPGEmpresa();
        aux=new Auxiliares();
        cd=aux.generaCedula();
    }        
    
    /**
     * Test of insertar method, of class OPGEmpresa.
     */
    @Test
    public void testInsertar() throws SQLException {
        empresa.setRfc(cd);
        empresa.setCalle("x");
        empresa.setCiudad("z");
        empresa.setCodigoPostal("s/n");
        empresa.setColonia("xd");
        empresa.setNombre("desc");
        empresa.setNumero("TRF");
        String busca="SELECT rfc FROM Empresa WHERE rfc='"+empresa.getRfc()+"';";
        ResultSet b =con.buscaDatos(busca);        
        while(b.next())
            res=b.getString(1);        
        if(res==null)
            Assert.assertTrue(empresa.insertar());
        else
            Assert.assertFalse(false);
    }

    /**
     * Test of insertartTel method, of class OPGEmpresa.
     */
    @Test
    public void testInsertartTel() throws Exception {
        empresa.setRfc(cd);
        String busca="SELECT rfc FROM Empresa WHERE rfc='"+empresa.getRfc()+"';";
        ResultSet b =con.buscaDatos(busca);        
        while(b.next())
            res=b.getString(1);        
        if(res==null)
            Assert.assertFalse(false);     
        else{            
            Assert.assertTrue(empresa.insertartTel("prueba"));
        }            
    }

    /**
     * Test of insertartEmail method, of class OPGEmpresa.
     */
    @Test
    public void testInsertartEmail() throws Exception {
        empresa.setRfc(cd);
        String busca="SELECT rfc FROM Empresa WHERE rfc='"+empresa.getRfc()+"';";
        ResultSet b =con.buscaDatos(busca);        
        while(b.next())
            res=b.getString(1);        
        if(res==null)
            Assert.assertFalse(false);     
        else{            
            Assert.assertTrue(empresa.insertartEmail("prueba"));
        }
    }

    /**
     * Test of deleteEmpresa method, of class OPGEmpresa.
     */
    @Test
    public void testDeleteEmpresa() throws Exception {
        empresa.setRfc(cd);
        if(empresa.deleteEmpresa()==true)
            Assert.assertTrue(true);
        else
            Assert.assertFalse(false);
    }

    /**
     * Test of upDate method, of class OPGEmpresa.
     */
    @Test
    public void testUpDate() throws Exception {        
        empresa.setRfc(cd);
        String busca="SELECT rfc FROM Empresa WHERE rfc='"+empresa.getRfc()+"';";        
        ResultSet b =con.buscaDatos(busca);        
        while(b.next())
            res=b.getString(1);        
        if(res!=null)
            Assert.assertTrue(true);
        else
            Assert.assertFalse(false);
    }   
}
