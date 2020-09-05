package mx.unam.ciencias.edd.test;

import java.util.NoSuchElementException;
import java.util.Random;
import mx.unam.ciencias.edd.ComparableIndexable;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.MonticuloMinimo;
import org.junit.Assert;
import org.junit.Test;

/**
 * Clase para pruebas unitarias de la clase {@link MonticuloMinimo}.
 */
public class TestMonticuloMinimo {

    private Random random;
    private int total;
    private MonticuloMinimo<Indexable<String>> monticulo;

    /* Clase interna privada que implementa ComparableIndexable para
     * poder probar a MonticuloMinimo. */
    private class Indexable<T> implements ComparableIndexable<Indexable<T>> {
        public T elemento;
        public double valor;
        public int indice;

        /* Crea un indexable con el elemento elemento y el valor
         * valor. */
        public Indexable(T elemento, double valor) {
            this.elemento = elemento;
            indice = -1;
            this.valor = valor;
        }

        /* Regresa el índice del indexable. */
        public void setIndice(int indice) {
            this.indice = indice;
        }

        /* Define el índice del indexable. */
        public int getIndice() {
            return indice;
        }

        /* Compara dos indexables. */
        public int compareTo(Indexable<T> indexable ) {
            if (valor - indexable.valor > 0.0)
                return 1;
            if (valor - indexable.valor < 0.0)
                return -1;
            return 0;
        }
    }

    /* Método auxiliar recursivo. */
    private static <T extends ComparableIndexable<T>> void
                      verificaMonticuloMinimo(MonticuloMinimo<T> monticulo,
                                              int i, int n) {
        if (i > n)
            return;
        int izq = 2 * i + 1;
        int der = 2 * i + 2;
        T elemento = monticulo.get(i);
        if (izq < n) {
            T izquierdo = monticulo.get(izq);
            Assert.assertTrue(elemento.compareTo(izquierdo) <= 0);
            verificaMonticuloMinimo(monticulo, izq, n);
        }
        if (der < n) {
            T derecho = monticulo.get(der);
            Assert.assertTrue(elemento.compareTo(derecho) <= 0);
            verificaMonticuloMinimo(monticulo, der, n);
        }
    }

    /* Método que verifica que un montículo mínimo cumpla con sus
     * propiedades. */
    private static <T extends ComparableIndexable<T>> void
                      verificaMonticuloMinimo(MonticuloMinimo<T> monticulo) {
        int n = monticulo.getNumeroDeElementos();
        if (n == 0)
            return;
        for (int i = 0; i < n; i++)
            Assert.assertTrue(monticulo.get(i).getIndice() == i);
        verificaMonticuloMinimo(monticulo, 0, n);
    }

    /**
     * Crea un montículo mínimo para cada prueba.
     */
    public TestMonticuloMinimo() {
        random = new Random();
        total = 10 + random.nextInt(90);
        Lista<Indexable<String>> l = new Lista<Indexable<String>>();
        for (int i = 0; i < total; i++) {
            String s = Integer.toString(random.nextInt());
            double p = random.nextDouble();
            Indexable<String> idx = new Indexable<String>(s, p);
            l.agregaFinal(idx);
        }
        monticulo = new MonticuloMinimo<Indexable<String>>(l);
        verificaMonticuloMinimo(monticulo);
    }

    /**
     * Prueba unitaria para {@link MonticuloMinimo#agrega}.
     */
    @Test public void testAgrega() {
        for (int i = 0; i < total * 4; i++) {
            String s = Integer.toString(random.nextInt());
            double p = random.nextDouble();
            Indexable<String> idx = new Indexable<String>(s, p);
            monticulo.agrega(idx);
            verificaMonticuloMinimo(monticulo);
            Assert.assertTrue(monticulo.getNumeroDeElementos() == total + i + 1);
        }
    }

    /**
     * Prueba unitaria para {@link MonticuloMinimo#elimina}.
     */
    @Test public void testElimina() {
        while (!monticulo.esVacio()) {
            Indexable<String> a = monticulo.elimina();
            for (int i = 0; i < monticulo.getNumeroDeElementos(); i++) {
                Indexable<String> b = monticulo.get(i);
                Assert.assertTrue(a.valor <= b.valor);
            }
            verificaMonticuloMinimo(monticulo);
            Assert.assertTrue(monticulo.getNumeroDeElementos() == --total);
        }
        try {
            monticulo.elimina();
            Assert.fail();
        } catch (IllegalStateException ise) {}
    }

    /**
     * Prueba unitaria para {@link MonticuloMinimo#esVacio}.
     */
    @Test public void testEsVacio() {
        monticulo = new MonticuloMinimo<Indexable<String>>();
        Assert.assertTrue(monticulo.esVacio());
        String s = Integer.toString(random.nextInt());
        double p = random.nextDouble();
        Indexable<String> idx = new Indexable<String>(s, p);
        monticulo.agrega(idx);
        Assert.assertFalse(monticulo.esVacio());
        idx = monticulo.elimina();
        Assert.assertTrue(monticulo.esVacio());
    }

    /**
     * Prueba unitaria para {@link MonticuloMinimo#reordena}.
     */
    @Test public void testReordena() {
        int n = monticulo.getNumeroDeElementos();
        for (int i = 0; i < n; i++) {
            verificaMonticuloMinimo(monticulo);
            Indexable<String> idx = monticulo.get(random.nextInt(n));
            idx.valor = idx.valor / 2.0;
            monticulo.reordena(idx);
            verificaMonticuloMinimo(monticulo);
        }
    }

    /**
     * Prueba unitaria para {@link MonticuloMinimo#getNumeroDeElementos}.
     */
    @Test public void testGetNumeroDeElementos() {
        try {
            monticulo.get(-1);
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
        try {
            monticulo.get(total);
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
    }

    /**
     * Prueba unitaria para {@link MonticuloMinimo#get}.
     */
    @Test public void testGet() {
        try {
            monticulo.get(-1);
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
        try {
            monticulo.get(total);
            Assert.fail();
        } catch (NoSuchElementException nsee) {}
        monticulo = new MonticuloMinimo<Indexable<String>>();
        for (int i = 0; i < total; i++) {
            String s = Integer.toString(random.nextInt());
            double p = random.nextDouble();
            Indexable<String> idx = new Indexable<String>(s, p);
            monticulo.agrega(idx);
            Assert.assertTrue(monticulo.getNumeroDeElementos() == i + 1);
            Assert.assertTrue(monticulo.get(idx.getIndice()) == idx);
        }
    }
}
