package mx.unam.ciencias.edd.test;

import java.util.Random;
import java.util.Iterator;
import org.junit.Test;
import org.junit.Assert;
import mx.unam.ciencias.edd.ArbolBinarioNiveles;
import mx.unam.ciencias.edd.Cola;
import mx.unam.ciencias.edd.IteradorArbolBinario;


/**
 * Clase para pruebas unitarias de la clase {@link ArbolBinarioNiveles}.
 */
public class TestArbolBinarioNiveles {

    private int total;
    private Random random;
    private ArbolBinarioNiveles<Integer> arbol;

    /**
     * Valida un árbol binario por niveles. Comprueba que los nodos
     * del árbol estén acomodados por niveles.
     * @param arbol el árbol a revisar.
     */
    public static <T extends Comparable<T>> void
                     arbolBinarioNivelesValido(ArbolBinarioNiveles<T> arbol) {
        if (arbol.getNumeroDeElementos() == 0)
            return;
        IteradorArbolBinario<T> it = arbol.iterador();
        Assert.assertTrue(arbol.profundidad() ==
                          (int)(Math.floor(Math.log(arbol.getNumeroDeElementos()) /
                                           Math.log(2))));
    }

    /**
     * Crea un árbol binario por niveles para cada prueba.
     */
    public TestArbolBinarioNiveles() {
        random = new Random();
        arbol = new ArbolBinarioNiveles<Integer>();
        total = random.nextInt(100);
    }

    /**
     * Prueba unitaria para {@link ArbolBinarioNiveles#agrega}.
     */
    @Test public void testAgrega() {
        for (int i = 0; i < total; i++) {
            int n = random.nextInt(100);
            arbol.agrega(n);
            TestArbolBinario.arbolValido(arbol);
	    Assert.assertTrue(arbol.getNumeroDeElementos() == i+1);
            IteradorArbolBinario<Integer> it = arbol.busca(n);
            Assert.assertTrue(it != null);
            Assert.assertTrue(it.dame() == n);
            arbolBinarioNivelesValido(arbol);
        }
    }

    /**
     * Prueba unitaria para {@link ArbolBinarioNiveles#elimina}.
     */
    @Test public void testElimina() {
        int[] a = new int[total];
        for (int i = 0; i < total; i++) {
            int r;
            boolean repetido = false;
            do {
                r = random.nextInt(1000);
                repetido = false;
                for (int j = 0; j < i; j++)
                    if (r == a[j])
                        repetido = true;
            } while (repetido);
            a[i] = r;
            arbol.agrega(a[i]);
        }
        for (int i : a)
            Assert.assertTrue(arbol.busca(i) != null);
        int n = total;
        while (arbol.getNumeroDeElementos() > 0) {
            Assert.assertTrue(arbol.getNumeroDeElementos() == n);
            int i = random.nextInt(total);
            if (a[i] == -1)
                continue;
            int e = a[i];
            IteradorArbolBinario<Integer> it = arbol.busca(e);
            Assert.assertTrue(it != null);
            Assert.assertTrue(it.dame() == e);
            arbol.elimina(e);
            it = arbol.busca(e);
            Assert.assertTrue(it == null);
            Assert.assertTrue(arbol.getNumeroDeElementos() == --n);
            TestArbolBinario.arbolValido(arbol);
            arbolBinarioNivelesValido(arbol);
            a[i] = -1;
        }
    }
}
