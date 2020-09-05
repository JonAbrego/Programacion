package mx.unam.ciencias.edd.test;

import java.util.Random;
import java.util.NoSuchElementException;
import org.junit.Test;
import org.junit.Assert;
import mx.unam.ciencias.edd.Cola;

/**
 * Clase para pruebas unitarias de la clase {@link Cola}.
 */
public class TestCola {

    private Random random;
    private int total;
    private Cola<Integer> cola;

    /**
     * Crea un generador de números aleatorios para cada prueba, un
     * número total de elementos para nuestra cola, y una cola.
     */
    public TestCola() {
        random = new Random();
        total = 10 + random.nextInt(90);
        cola = new Cola<Integer>();
    }

    /**
     * Prueba unitaria para {@link Cola#saca}.
     */
    @Test public void testSaca() {
        int[] a = new int[total];
        for (int i = 0; i < total; i++) {
            a[i] = random.nextInt(total);
            cola.mete(a[i]);
        }
        int i = 0;
        while (!cola.esVacia()) {
            int n = cola.saca();
            Assert.assertTrue(n == a[i++]);
        }
    }

    /**
     * Prueba unitaria para {@link Cola#mira}.
     */
    @Test public void testMira() {
        int[] a = new int[total];
        for (int i = 0; i < total; i++) {
            a[i] = random.nextInt(total);
            cola.mete(a[i]);
            int n = cola.mira();
            Assert.assertTrue(n == a[0]);
        }
    }
}
