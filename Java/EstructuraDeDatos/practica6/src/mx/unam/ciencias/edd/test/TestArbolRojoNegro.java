package mx.unam.ciencias.edd.test;

import java.util.Iterator;
import java.util.Random;
import mx.unam.ciencias.edd.ArbolRojoNegro;
import mx.unam.ciencias.edd.Color;
import mx.unam.ciencias.edd.IteradorArbolBinario;
import org.junit.Assert;
import org.junit.Test;

/**
 * Clase para pruebas unitarias de la clase {@link ArbolRojoNegro}.
 */
public class TestArbolRojoNegro {

    private int total;
    private Random random;
    private ArbolRojoNegro<Integer> arbol;

    /* Valida el iterador de un árbol rojo-negro, y recursivamente
     * revisa sus hijos. */
    private static <T extends Comparable<T>> void
                      iteradorRojoNegroValido(IteradorArbolBinario<T> it) {
        switch (it.color()) {
        case NEGRO:
            if (it.hayIzquierdo()) {
                it.izquierdo();
                iteradorRojoNegroValido(it);
                it.padre();
            }
            if (it.hayDerecho()) {
                it.derecho();
                iteradorRojoNegroValido(it);
                it.padre();
            }
            break;
        case ROJO:
            if (it.hayIzquierdo()) {
                it.izquierdo();
                Assert.assertTrue(it.color() != Color.ROJO);
                iteradorRojoNegroValido(it);
                it.padre();
            }
            if (it.hayDerecho()) {
                it.derecho();
                Assert.assertTrue(it.color() != Color.ROJO);
                iteradorRojoNegroValido(it);
                it.padre();
            }
            break;
        default:
            Assert.fail();
        }
    }

    /* Valida que los caminos del iterador a sus hojas tengan todos
       el mismo número de nodos negros. */
    private static <T extends Comparable<T>> int
                      iteradorRojoNegroValidaCaminos(IteradorArbolBinario<T> it) {
        int ni = -1, nd = -1;
        if (it.hayIzquierdo()) {
            it.izquierdo();
            ni = iteradorRojoNegroValidaCaminos(it);
            it.padre();
        } else {
            ni = 1;
        }
        if (it.hayDerecho()) {
            it.derecho();
            nd = iteradorRojoNegroValidaCaminos(it);
            it.padre();
        } else {
            nd = 1;
        }
        Assert.assertTrue(ni == nd);
        switch (it.color()) {
        case NEGRO:
            return 1 + ni;
        case ROJO:
            return ni;
        default:
            Assert.fail();
        }
        // Inalcanzable.
        return -1;
    }

    /**
     * Valida un árbol rojo-negro. Comprueba que la raíz sea negra,
     * que las hojas sean negras, que un nodo rojo tenga dos hijos
     * negros, y que todo camino de la raíz a sus hojas tiene el
     * mismo número de nodos negros.
     * @param arbol el árbol a revisar.
     */
    public static <T extends Comparable<T>> void
                     arbolRojoNegroValido(ArbolRojoNegro<T> arbol) {
        if (arbol.getNumeroDeElementos() == 0)
            return;
        IteradorArbolBinario<T> it = arbol.iterador();
        Assert.assertTrue(it.color() == Color.NEGRO);
        iteradorRojoNegroValido(it);
        iteradorRojoNegroValidaCaminos(it);
    }

    /**
     * Crea un árbol rojo-ngro para cada prueba.
     */
    public TestArbolRojoNegro() {
        random = new Random();
        arbol = new ArbolRojoNegro<Integer>();
        total = random.nextInt(100);
    }

    /**
     * Prueba unitaria para {@link ArbolRojoNegro#agrega}.
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
            TestArbolBinarioOrdenado.arbolOrdenadoValido(arbol);
            arbolRojoNegroValido(arbol);
        }
    }

    /**
     * Prueba unitaria para {@link ArbolRojoNegro#elimina}.
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
            arbolRojoNegroValido(arbol);
            a[i] = -1;
        }
    }
}
