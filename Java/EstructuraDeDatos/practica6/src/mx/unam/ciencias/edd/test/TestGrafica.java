package mx.unam.ciencias.edd.test;

import java.util.NoSuchElementException;
import java.util.Random;
import mx.unam.ciencias.edd.AccionVerticeGrafica;
import mx.unam.ciencias.edd.Color;
import mx.unam.ciencias.edd.Grafica;
import mx.unam.ciencias.edd.Lista;
import mx.unam.ciencias.edd.VerticeGrafica;
import org.junit.Assert;
import org.junit.Test;

/**
 * Clase para pruebas unitarias de la clase {@link Grafica}.
 */
public class TestGrafica {

    private int total;
    private Random random;
    private Grafica<Integer> grafica;

    /**
     * Crea una gráfica para cada prueba.
     */
    public TestGrafica() {
        random = new Random();
        total = 2 + random.nextInt(100);
        grafica = new Grafica<Integer>();
    }

    /**
     * Prueba unitaria para {@link Grafica#Grafica}.
     */
    @Test public void testConstructor() {
        Assert.assertTrue(grafica.getCardinalidadVertices() == 0);
        Assert.assertTrue(grafica.getCardinalidadAristas() == 0);
    }

    /**
     * Prueba unitaria para {@link Grafica#getCardinalidadVertices}.
     */
    @Test public void testGetCardinalidadVertices() {
        for (int i = 0; i < total; i++) {
            grafica.agrega(i);
            Assert.assertTrue(grafica.getCardinalidadVertices() == i+1);
        }
    }

    /**
     * Prueba unitaria para {@link Grafica#getCardinalidadAristas}.
     */
    @Test public void testGetCardinalidadAristas() {
        for (int i = 0; i < total; i++)
            grafica.agrega(i);
        int cont = 0;
        for (int i = 0; i < total; i++) {
            for (int j = i+1; j < total; j++) {
                grafica.conecta(i, j);
                cont++;
                Assert.assertTrue(grafica.getCardinalidadAristas() == cont);
            }
        }
    }

    /**
     * Prueba unitaria para {@link Grafica#agrega}.
     */
    @Test public void testAgrega() {
        for (int i = 0; i < total; i++) {
            grafica.agrega(i);
            Assert.assertTrue(grafica.contiene(i));
        }
        try {
            grafica.agrega(total/2);
        } catch (IllegalArgumentException iae) {
            return;
        }
        Assert.fail();
    }

    /**
     * Prueba unitaria para {@link Grafica#conecta}.
     */
    @Test public void testConecta() {
        for (int i = 0; i < total; i++)
            grafica.agrega(i);
        for (int i = 0; i < total; i++) {
            for (int j = i+1; j < total; j++) {
                Assert.assertFalse(grafica.sonVecinos(i, j));
                grafica.conecta(i, j);
                Assert.assertTrue(grafica.sonVecinos(i, j));
            }
        }
        boolean excepcion = false;
        try {
            grafica.conecta(0, 0);
        } catch (IllegalArgumentException iae) {
            excepcion = true;
        }
        Assert.assertTrue(excepcion);
        excepcion = false;
        try {
            grafica.conecta(-1, -2);
        } catch (NoSuchElementException nsee) {
            excepcion = true;
        }
        Assert.assertTrue(excepcion);
        excepcion = false;
        try {
            grafica.conecta(0, 1);
        } catch (IllegalArgumentException iae) {
            excepcion = true;
        }
        Assert.assertTrue(excepcion);
    }

    /**
     * Prueba unitaria para {@link Grafica#desconecta}.
     */
    @Test public void testDesconecta() {
        for (int i = 0; i < total; i++)
            grafica.agrega(i);
        for (int i = 0; i < total; i++)
            for (int j = i+1; j < total; j++)
                grafica.conecta(i, j);
        int c = (total * (total-1)) / 2;
        for (int i = 0; i < total; i++) {
            for (int j = i+1; j < total; j++) {
                Assert.assertTrue(grafica.getCardinalidadAristas() == c--);
                Assert.assertTrue(grafica.sonVecinos(i, j));
                grafica.desconecta(i, j);
                Assert.assertFalse(grafica.sonVecinos(i, j));
            }
        }
        boolean excepcion = false;
        try {
            grafica.desconecta(0, 0);
        } catch (IllegalArgumentException iae) {
            excepcion = true;
        }
        Assert.assertTrue(excepcion);
        excepcion = false;
        try {
            grafica.desconecta(-1, -2);
        } catch (NoSuchElementException nsee) {
            excepcion = true;
        }
        Assert.assertTrue(excepcion);
    }

    /**
     * Prueba unitaria para {@link Grafica#contiene}.
     */
    @Test public void testContiene() {
        for (int i = 0; i < total; i++) {
            grafica.agrega(i);
            Assert.assertTrue(grafica.contiene(i));
        }
        Assert.assertFalse(grafica.contiene(-1));
    }

    /**
     * Prueba unitaria para {@link Grafica#elimina}.
     */
    @Test public void testElimina() {
        for (int i = 0; i < total; i++)
            grafica.agrega(i);
        for (int i = 0; i < total; i++)
            for (int j = i+1; j < total; j++)
                grafica.conecta(i, j);
        int vertices = total;
        int aristas = (total * (total - 1)) / 2;
        final int[] grado = { vertices - 1 };
        for (int i = 0; i < total; i++) {
            grafica.paraCadaVertice(new AccionVerticeGrafica<Integer>() {
                    public void actua(VerticeGrafica<Integer> vertice) {
                        Assert.assertTrue(vertice.getGrado() == grado[0]);
                    }
                });
            Assert.assertTrue(grafica.getCardinalidadVertices() == vertices);
            Assert.assertTrue(grafica.getCardinalidadAristas() == aristas);
            grafica.elimina(i);
            vertices--;
            aristas -= vertices;
            grado[0]--;
        }
        Assert.assertTrue(grafica.getCardinalidadVertices() == 0);
        Assert.assertTrue(grafica.getCardinalidadAristas() == 0);
    }

    /**
     * Prueba unitaria para {@link Grafica#sonVecinos}.
     */
    @Test public void testSonVecinos() {
        for (int i = 0; i < total; i++)
            grafica.agrega(i);
        for (int i = 0; i < total; i++) {
            for (int j = i+1; j < total; j++) {
                Assert.assertFalse(grafica.sonVecinos(i, j));
                grafica.conecta(i, j);
                Assert.assertTrue(grafica.sonVecinos(i, j));
            }
        }
        try {
            grafica.sonVecinos(-1, -2);
        } catch (NoSuchElementException nsee) {
            return;
        }
        Assert.fail();
    }

   /**
     * Prueba unitaria para {@link Grafica#vertice}.
     */
    @Test public void testVertice() {
        for (int i = 0; i < total; i++)
            grafica.agrega(i);
        for (int i = 0; i < total; i++)
            for (int j = i+1; j < total; j++)
                grafica.conecta(i, j);
        VerticeGrafica<Integer> vertice = grafica.vertice(0);
        Assert.assertTrue(vertice.getElemento() == 0);
        Assert.assertTrue(vertice.getGrado() == total - 1);
        Assert.assertTrue(vertice.getColor() == Color.NINGUNO);
        vertice.setColor(Color.ROJO);
        Assert.assertTrue(vertice.getColor() == Color.ROJO);
        int v = 1;
        while (vertice.hayVecino()) {
            VerticeGrafica<Integer> vecino = vertice.vecino();
            Assert.assertTrue(vecino.getElemento() == v++);
        }
    }

   /**
     * Prueba unitaria para {@link Grafica#paraCadaVertice}.
     */
    @Test public void testParaCadaVertice() {
        for (int i = 0; i < total; i++)
            grafica.agrega(i);
        for (int i = 0; i < total; i++)
            for (int j = i+1; j < total; j++)
                grafica.conecta(i, j);
        final int[] cont = { 0 };
        grafica.paraCadaVertice(new AccionVerticeGrafica<Integer>() {
                public void actua(VerticeGrafica<Integer> vertice) {
                    vertice.setColor(Color.ROJO);
                    cont[0]++;
                }
            });
        Assert.assertTrue(cont[0] == total);
        grafica.paraCadaVertice(new AccionVerticeGrafica<Integer>() {
                public void actua(VerticeGrafica<Integer> vertice) {
                    Assert.assertTrue(vertice.getColor() == Color.ROJO);
                }
            });
    }

   /**
     * Prueba unitaria para {@link Grafica#bfs}.
     */
    @Test public void testBfs() {
        for (int i = 0; i < total; i++)
            grafica.agrega(i);
        for (int i = 0; i < total; i++)
            for (int j = i+1; j < total; j++)
                grafica.conecta(i, j);
        final Lista<Integer> lista = new Lista<Integer>();
        grafica.bfs(0, new AccionVerticeGrafica<Integer>() {
                public void actua(VerticeGrafica<Integer> vertice) {
                    lista.agregaFinal(vertice.getElemento());
                }
            });
        Assert.assertTrue(lista.getLongitud() == total);
        int c = 0;
        for (Integer n : lista)
            Assert.assertTrue(n == c++);
    }

   /**
     * Prueba unitaria para {@link Grafica#dfs}.
     */
    @Test public void testDfs() {
        for (int i = 0; i < total; i++)
            grafica.agrega(i);
        for (int i = 0; i < total; i++)
            for (int j = i+1; j < total; j++)
                grafica.conecta(i, j);
        final Lista<Integer> lista = new Lista<Integer>();
        grafica.dfs(0, new AccionVerticeGrafica<Integer>() {
                public void actua(VerticeGrafica<Integer> vertice) {
                    lista.agregaFinal(vertice.getElemento());
                }
            });
        Assert.assertTrue(lista.getLongitud() == total);
        int c = 0;
        for (Integer n : lista) {
            if (c == 0)
                Assert.assertTrue(n == c++);
            else
                Assert.assertTrue(n == total - c++);
        }
    }
}
