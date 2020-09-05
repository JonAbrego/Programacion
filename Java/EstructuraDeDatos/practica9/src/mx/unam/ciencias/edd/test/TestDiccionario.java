package mx.unam.ciencias.edd.test;

import java.util.NoSuchElementException;
import java.util.Random;
import mx.unam.ciencias.edd.Arreglos;
import mx.unam.ciencias.edd.Diccionario;
import mx.unam.ciencias.edd.Lista;
import org.junit.Assert;
import org.junit.Test;

/**
 * Clase para pruebas unitarias de la clase {@link Diccionario}.
 */
public class TestDiccionario {

    private int total;
    private Random random;
    private Diccionario<String, String> diccionario;

    /**
     * Crea un diccionario para cada prueba.
     */
    public TestDiccionario() {
        random = new Random();
        total = 64 + random.nextInt(64);
        diccionario = new Diccionario<String, String>(total);
    }

    /**
     * Prueba unitaria para {@link Diccionario#Diccionario}.
     */
    @Test public void testConstructor() {
        Assert.assertTrue(diccionario.getNumeroDeElementos() == 0);
        Assert.assertTrue(diccionario.carga() == 0.0);
        Assert.assertTrue(diccionario.colisiones() == 0);
    }

    /**
     * Prueba unitaria para {@link Diccionario#agrega}.
     */
    @Test public void testAgrega() {
        int ini = random.nextInt(10000);
        for (int i = 0; i < total * 2; i++) {
            String s = String.format("%x", ini + i * 1000);
            diccionario.agrega(s, s);
            Assert.assertTrue(diccionario.getNumeroDeElementos() == i+1);
            Assert.assertTrue(diccionario.contiene(s));
            Assert.assertTrue(diccionario.get(s).equals(s));
        }
        String k = String.format("%x", ini);
        String v = String.format("%x", ini+1);
        diccionario.agrega(k, v);
        Assert.assertTrue(diccionario.getNumeroDeElementos() == total*2);
        Assert.assertTrue(diccionario.contiene(k));
        Assert.assertTrue(diccionario.get(k).equals(v));
        Assert.assertTrue(diccionario.carga() < 0.72);
    }

    /**
     * Prueba unitaria para {@link Diccionario#get}.
     */
    @Test public void testGet() {
        int ini = random.nextInt(10000);
        for (int i = 0; i < total; i++) {
            String s = String.format("%x", ini + i * 1000);
            diccionario.agrega(s, s);
            Assert.assertTrue(diccionario.get(s).equals(s));
        }
        try {
            diccionario.get("00000");
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
    }

    /**
     * Prueba unitaria para {@link Diccionario#contiene}.
     */
    @Test public void testContiene() {
        int ini = random.nextInt(10000);
        for (int i = 0; i < total; i++) {
            String s = String.format("%x", ini + i * 1000);
            diccionario.agrega(s, s);
            Assert.assertTrue(diccionario.contiene(s));
        }
        Assert.assertFalse(diccionario.contiene("00000"));
    }

    /**
     * Prueba unitaria para {@link Diccionario#elimina}.
     */
    @Test public void testElimina() {
        String[] arreglo = new String[total];
        int ini = random.nextInt(10000);
        for (int i = 0; i < total; i++) {
            arreglo[i] = String.format("%x", ini + i * 1000);
            diccionario.agrega(arreglo[i], arreglo[i]);
        }
        for (int i = 0; i < total; i++) {
            Assert.assertTrue(diccionario.contiene(arreglo[i]));
            diccionario.elimina(arreglo[i]);
            Assert.assertFalse(diccionario.contiene(arreglo[i]));
            Assert.assertTrue(diccionario.getNumeroDeElementos() ==
                              total - (i+1));
            try {
                diccionario.get(arreglo[i]);
                Assert.fail();
            } catch (NoSuchElementException nsee) {}
        }
    }

    /**
     * Prueba unitaria para {@link Diccionario#llaves}.
     */
    @Test public void testLlaves() {
        String[] arreglo = new String[total];
        int ini = random.nextInt(10000);
        for (int i = 0; i < total; i++) {
            arreglo[i] = String.format("%x", ini + i * 1000);
            diccionario.agrega(arreglo[i], arreglo[i]);
        }
        Lista<String> llaves = diccionario.llaves();
        Assert.assertTrue(llaves.getLongitud() == total);
        llaves = Lista.mergeSort(llaves);
        Arreglos.quickSort(arreglo);
        int i = 0;
        for (String k : llaves)
            Assert.assertTrue(k.equals(arreglo[i++]));
    }

    /**
     * Prueba unitaria para {@link Diccionario#elementos}.
     */
    @Test public void testElementos() {
        String[] arreglo = new String[total];
        int ini = random.nextInt(10000);
        for (int i = 0; i < total; i++) {
            arreglo[i] = String.format("%x", ini + i * 1000);
            diccionario.agrega(arreglo[i], arreglo[i]);
        }
        Lista<String> elementos = diccionario.elementos();
        Assert.assertTrue(elementos.getLongitud() == total);
        elementos = Lista.mergeSort(elementos);
        Arreglos.quickSort(arreglo);
        int i = 0;
        for (String k : elementos)
            Assert.assertTrue(k.equals(arreglo[i++]));
    }

    /**
     * Prueba unitaria para {@link Diccionario#colisionMaxima}.
     */
    @Test public void testColisionMaxima() {
        int r = random.nextInt(10000);
        String s = String.format("%x", r);
        diccionario.agrega(s, s);
        Assert.assertTrue(diccionario.colisionMaxima() == 0);
        Assert.assertTrue(diccionario.colisiones() == 0);
    }

    /**
     * Prueba unitaria para {@link Diccionario#carga}.
     */
    @Test public void testCarga() {
        int ini = random.nextInt(10000);
        double c = 0.0;
        for (int i = 0; i < total; i++) {
            String s = String.format("%x", ini + i * 1000);
            diccionario.agrega(s, s);
            Assert.assertTrue(diccionario.carga() > c);
            c = diccionario.carga();
            Assert.assertTrue(diccionario.carga() < 0.72);
        }
    }

    /**
     * Prueba unitaria para {@link Diccionario#getNumeroDeElementos}.
     */
    @Test public void testGetNumeroDeElementos() {
        int ini = random.nextInt(10000);
        for (int i = 0; i < total; i++) {
            String s = String.format("%x", ini + i * 1000);
            diccionario.agrega(s, s);
            Assert.assertTrue(diccionario.getNumeroDeElementos() == i+1);
        }
    }
}
