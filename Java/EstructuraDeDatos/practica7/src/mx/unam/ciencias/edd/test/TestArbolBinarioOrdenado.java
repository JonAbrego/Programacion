package mx.unam.ciencias.edd.test;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import mx.unam.ciencias.edd.ArbolBinarioOrdenado;
import mx.unam.ciencias.edd.IteradorArbolBinario;
import org.junit.Assert;
import org.junit.Test;

/**
 * Clase para pruebas unitarias de la clase {@link
 * ArbolBinarioOrdenado}.
 */
public class TestArbolBinarioOrdenado {

    private int total;
    private Random random;
    private ArbolBinarioOrdenado<Integer> arbol;

    /* Valida el iterador de un árbol ordenado, y recursivamente
     * revisa sus hijos. */
    private static <T extends Comparable<T>> void
                      iteradorOrdenadoValido(IteradorArbolBinario<T> it) {
        T elemento = it.dame();
        try {
            if (it.hayIzquierdo()) {
                it.izquierdo();
                T izquierdo = it.dame();
                Assert.assertTrue(izquierdo.compareTo(elemento) <= 0);
                iteradorOrdenadoValido(it);
                it.padre();
            }
            if (it.hayDerecho()) {
                it.derecho();
                T derecho = it.dame();
                Assert.assertTrue(elemento.compareTo(derecho) <= 0);
                iteradorOrdenadoValido(it);
                it.padre();
            }
        } catch (NoSuchElementException sdee) {
            Assert.fail();
        }
    }

    /**
     * Valida un árbol ordenado. Comprueba que para todo nodo A se
     * cumpla que si A tiene como hijo izquierdo a B, entonces B ≤
     * A, y si A tiene como hijo derecho a C, entonces A ≤ C.
     * @param arbol el árbol a revisar.
     */
    public static <T extends Comparable<T>> void
                     arbolOrdenadoValido(ArbolBinarioOrdenado<T> arbol) {
        if (arbol.getNumeroDeElementos() == 0)
            return;
        iteradorOrdenadoValido(arbol.iterador());
    }

    /**
     * Crea un árbol binario para cada prueba.
     */
    public TestArbolBinarioOrdenado() {
        random = new Random();
        arbol = new ArbolBinarioOrdenado<Integer>();
        total = 1 + random.nextInt(100);
    }

    /**
     * Prueba unitaria para {@link ArbolBinarioOrdenado#agrega}.
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
            arbolOrdenadoValido(arbol);
        }
    }

    /* Llena el árbol con elementos no repetidos. */
    private void llenaConNoRepetidos(int[] a) {
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
    }

    /**
     * Prueba unitaria para {@link ArbolBinarioOrdenado#elimina}.
     */
    @Test public void testElimina() {
        int[] a = new int[total];
        llenaConNoRepetidos(a);
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
            arbolOrdenadoValido(arbol);
            a[i] = -1;
        }
    }

    /**
     * Prueba unitaria para {@link ArbolBinarioOrdenado#busca}.
     */
    @Test public void testBusca() {
        int[] a = new int[total];
        llenaConNoRepetidos(a);
        for (int i : a) {
            IteradorArbolBinario<Integer> it = arbol.busca(i);
            Assert.assertTrue(it != null);
            Assert.assertTrue(it.dame() == i);
        }
        int m = 1500 + random.nextInt(100);
        Assert.assertTrue(arbol.busca(m) == null);
    }

    /**
     * Prueba unitaria para {@link
     * ArbolBinarioOrdenado#giraDerecha}.
     */
    @Test public void testGiraDerecha() {
        int[] a = new int[total];
        llenaConNoRepetidos(a);
        int Q = -1;
        IteradorArbolBinario<Integer> it = null;
        do {
            Q = a[random.nextInt(total)];
            it = arbol.busca(Q);
            Assert.assertTrue(it != null);
            Assert.assertTrue(it.dame() == Q);
        } while (!it.hayIzquierdo());
        it.izquierdo();
        int P = it.dame();
        int A = -1, B = -1, C = -1;
        if (it.hayIzquierdo()) {
            it.izquierdo();
            A = it.dame();
            it.padre();
        }
        if (it.hayDerecho()) {
            it.derecho();
            B = it.dame();
            it.padre();
        }
        it.padre();
        if (it.hayDerecho()) {
            it.derecho();
            C = it.dame();
            it.padre();
        }
        arbol.giraDerecha(it);
        TestArbolBinario.arbolValido(arbol);
        Assert.assertTrue(arbol.getNumeroDeElementos() == total);
        arbolOrdenadoValido(arbol);
        for (int n : a)
            Assert.assertTrue(arbol.busca(n) != null);
        Assert.assertTrue(it.dame() == Q);
        Assert.assertTrue(it.hayPadre());
        it.padre();
        Assert.assertTrue(it.dame() == P);
        if (A != -1) {
            Assert.assertTrue(it.hayIzquierdo());
            it.izquierdo();
            Assert.assertTrue(it.dame() == A);
            it.padre();
        }
        Assert.assertTrue(it.hayDerecho());
        it.derecho();
        Assert.assertTrue(it.dame() == Q);
        if (B != -1) {
            Assert.assertTrue(it.hayIzquierdo());
            it.izquierdo();
            Assert.assertTrue(it.dame() == B);
            it.padre();
        }
        if (C != -1) {
            Assert.assertTrue(it.hayDerecho());
            it.derecho();
            Assert.assertTrue(it.dame() == C);
            it.padre();
        }
    }

    /**
     * Prueba unitaria para {@link
     * ArbolBinarioOrdenado#giraIzquierda}. Crea un árbol binario
     * ordenado, y le agrega dos elementos. Después gira el árbol a
     * la izquierda, y verifica que el giro haya sido efectuado
     * correctamente.
     */
    @Test public void testGiraIzquierda() {
        int[] a = new int[total];
        llenaConNoRepetidos(a);
        int P = -1;
        IteradorArbolBinario<Integer> it = null;
        do {
            P = a[random.nextInt(total)];
            it = arbol.busca(P);
            Assert.assertTrue(it != null);
            Assert.assertTrue(it.dame() == P);
        } while (!it.hayDerecho());
        it.derecho();
        int Q = it.dame();
        int A = -1, B = -1, C = -1;
        if (it.hayIzquierdo()) {
            it.izquierdo();
            B = it.dame();
            it.padre();
        }
        if (it.hayDerecho()) {
            it.derecho();
            C = it.dame();
            it.padre();
        }
        it.padre();
        if (it.hayIzquierdo()) {
            it.izquierdo();
            A = it.dame();
            it.padre();
        }
        arbol.giraIzquierda(it);
        TestArbolBinario.arbolValido(arbol);
        Assert.assertTrue(arbol.getNumeroDeElementos() == total);
        arbolOrdenadoValido(arbol);
        for (int n : a)
            Assert.assertTrue(arbol.busca(n) != null);
        Assert.assertTrue(it.dame() == P);
        Assert.assertTrue(it.hayPadre());
        it.padre();
        Assert.assertTrue(it.dame() == Q);
        if (C != -1) {
            Assert.assertTrue(it.hayDerecho());
            it.derecho();
            Assert.assertTrue(it.dame() == C);
            it.padre();
        }
        Assert.assertTrue(it.hayIzquierdo());
        it.izquierdo();
        Assert.assertTrue(it.dame() == P);
        if (A != -1) {
            Assert.assertTrue(it.hayIzquierdo());
            it.izquierdo();
            Assert.assertTrue(it.dame() == A);
            it.padre();
        }
        if (B != -1) {
            Assert.assertTrue(it.hayDerecho());
            it.derecho();
            Assert.assertTrue(it.dame() == B);
            it.padre();
        }
    }
}
