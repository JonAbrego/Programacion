package mx.unam.ciencias.edd.test;

import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Iterator;
import org.junit.Test;
import org.junit.Assert;
import mx.unam.ciencias.edd.IteradorArbolBinario;
import mx.unam.ciencias.edd.ArbolBinario;


/**
 * Clase para pruebas unitarias de la clase {@link ArbolBinario}.
 */
public class TestArbolBinario {

    /* Clase interna privada para probar árboles binarios. */
    private class ArbolBinarioSimple<T> extends ArbolBinario<T> {

        @Override public void elimina(T elemento) {
            IteradorArbolBinario<T> it = busca(elemento);
            if (it == null)
                return;
            numeroDeElementos--;
            Nodo<T> nodo = nodoDeIterador(it);
            if (raiz == nodo) {
                raiz = nodo.derecho;
                raiz.padre = null;
                return;
            }
            nodo.padre.derecho = nodo.derecho;
            if (nodo.derecho != null)
                nodo.derecho.padre = nodo.padre;
        }

        /* Implementación trivial del método agrega, que siempre
         * agrega a la derecha. */
        @Override public IteradorArbolBinario<T> agrega(T elemento) {
            if (raiz != null)
                return agrega(raiz, elemento);
            raiz = new Nodo<T>(elemento);
            numeroDeElementos++;
            return iterador(raiz);
        }

        /* Método auxiliar recursivo que agrega siempre a la derecha. */
        private IteradorArbolBinario<T> agrega(Nodo<T> nodo, T elemento) {
            if (nodo.derecho != null)
                return agrega(nodo.derecho, elemento);
            nodo.derecho = new Nodo<T>(elemento);
            nodo.derecho.padre = nodo;
            numeroDeElementos++;
            return iterador(nodo);
        }
    }

    /* Valida un iterador, y recursivamente valida sus hijos. */
    private static void iteradorValido(IteradorArbolBinario<?> it) {
        try {
            if (it.hayIzquierdo()) {
                it.izquierdo();
                Assert.assertTrue(it.hayPadre());
                iteradorValido(it);
                it.padre();
            }
            if (it.hayDerecho()) {
                it.derecho();
                Assert.assertTrue(it.hayPadre());
                iteradorValido(it);
                it.padre();
            }
        } catch (NoSuchElementException sdee) {
            Assert.fail();
        }
    }

    /**
     * Valida un árbol. Comprueba que si un nodo A tiene como hijo
     * al nodo B, entonces el nodo B tiene al nodo A como padre.
     * @param arbol el árbol a validar.
     */
    public static void arbolValido(ArbolBinario<?> arbol) {
        if (arbol.getNumeroDeElementos() == 0)
            return;
        IteradorArbolBinario<?> it = arbol.iterador();
        Assert.assertFalse(it.hayPadre());
        iteradorValido(it);
    }

    private Random random;
    private int total;
    private ArbolBinarioSimple<Integer> arbol;

    /**
     * Crea un árbol binario para cada prueba.
     */
    public TestArbolBinario() {
        random = new Random();
        total = 3 + random.nextInt(100);
        arbol = new ArbolBinarioSimple<Integer>();
    }

    /**
     * Prueba unitaria para {@link ArbolBinario#profundidad}.
     */
    @Test public void testProfundidad() {
        for (int i = 0; i < total; i++) {
            arbol.agrega(random.nextInt(total));
            arbolValido(arbol);
            Assert.assertTrue(arbol.profundidad() == i);
        }
    }

    /**
     * Prueba unitaria para {@link ArbolBinario#numeroDeElementos}.
     */
    @Test public void testNumeroDeElementos() {
        for (int i = 0; i < total; i++) {
            arbol.agrega(random.nextInt(total));
            arbolValido(arbol);
            Assert.assertTrue(arbol.getNumeroDeElementos() == i+1);
        }
    }

    /**
     * Prueba unitaria para {@link ArbolBinario#numeroDeElementos}.
     */
    @Test public void testBusca() {
        int[] a = new int[total];
        int ini = random.nextInt(total);
        for (int i = 0; i < total; i++) {
            a[i] = ini + i;
            arbolValido(arbol);
            arbol.agrega(a[i]);
        }
        for (int i = 0; i < total; i++)
            Assert.assertTrue(arbol.busca(a[i]) != null);
        Assert.assertTrue(arbol.busca(ini - total) == null);
        Assert.assertTrue(arbol.busca(ini + total*2) == null);
    }

    /**
     * Prueba unitaria para {@link ArbolBinario#iterador}.
     */
    @Test public void testIterador() {
        for (int i = 0; i < total; i++)
            arbol.agrega(random.nextInt(total));
        IteradorArbolBinario<Integer> iterador = arbol.iterador();
        Assert.assertTrue(iterador.hayDerecho());
        iterador.derecho();
        while (iterador.hayDerecho()) {
            Assert.assertTrue(iterador.hayPadre());
            Assert.assertFalse(iterador.hayIzquierdo());
            Assert.assertTrue(iterador.hayDerecho());
            iterador.derecho();
        }
    }

    /**
     * Prueba unitaria para {@link ArbolBinario#toString}.
     */
    @Test public void testToString() {
        /* Estoy dispuesto a aceptar una mejor prueba. */
        Assert.assertTrue(arbol.toString() != null &&
                          arbol.toString().equals(""));
        for (int i = 0; i < total; i++) {
            arbol.agrega(random.nextInt(total));
            arbolValido(arbol);
            Assert.assertTrue(arbol.toString() != null &&
                              !arbol.toString().equals(""));
        }
        String cadena =
            "1\n" +
            "└─»2\n" +
            "   └─»3\n" +
            "      └─»4\n" +
            "         └─»5";
        arbol = new ArbolBinarioSimple<Integer>();
        for (int i = 1; i <= 5; i++)
            arbol.agrega(i);
        Assert.assertTrue(arbol.toString().equals(cadena));
    }
}
