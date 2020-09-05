package test;


import pkgModelo.*;
import junit.framework.Assert;
import org.junit.Test;


/**
 * Test de la clase Auxiliar
 * @author abrego
 */
public class AuxiliaresTest {
    private Auxiliares aux;
    
    /**
     *Constructor
     */
    public AuxiliaresTest() {
        aux=new Auxiliares();
    }

    /**
     * Test del metodo consultas, el cual devuelve una consulta
     */
    @Test
    public void testConsultas() {
        int t=1;
        int f=6;
        if(aux.consultas(1)!=null)
            Assert.assertTrue(true);
        if(aux.consultas(6)==null)
            Assert.assertFalse(false);
    }

    /**
     * Test del metodo palabraDrop , el cual verifica si una cadena dada es igual a DROP
     */
    @Test
    public void testPalabraDrop() {
        String pal="drop";        
        Assert.assertTrue(aux.palabraDrop(pal));
    }

//    /**
//     * Test del metodo chechaPrecio
//     */
//    @Test
//    public void testChecaPrecio() {
//        
//    }
//
//    /**
//     * Test del metodo chechaNombre
//     */
//    @Test
//    public void testChechaNombreC() {
//    }
//
//    /**
//     * Test del metodo chechaRFC
//     */
//    @Test
//    public void testChecaRFC() {
//    }
//
//    /**
//     * Test del metodo checaCorreo
//     */
//    @Test
//    public void testChecaCorreo() {
//    }
//
//    /**
//     * Test del metodo checaTelefono
//     */
//    @Test
//    public void testChecaTelefono() {
//    }
//
//    /**
//     * Test del metodo chechaIMEI
//     */
//    @Test
//    public void testChecaIMEI() {
//    }
//
//
//    /**
//     * Test del metodo chechaServicio
//     */
//    @Test
//    public void testSeleccionaServicio() {
//    }
//
//    /**
//     * Test del metodo generaCedula
//     */
//    @Test
//    public void testGeneraCedula() {
//    }
//
//    /**
//     * Test del metodo generMarca
//     */
//    @Test
//    public void testGeneraMarca() {
//    }
//    
}
