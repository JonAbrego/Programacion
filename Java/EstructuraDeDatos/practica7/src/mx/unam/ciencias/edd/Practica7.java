package mx.unam.ciencias.edd;

import java.util.Random;

/**
 * Práctica 7: Trayectoria mínima y algoritmo de Dijkstra.
 */
public class Practica7 {

    private static String cadena;

    public static void main(String[] args) {
        /*       3
         *    b─────d
         *  1╱│╲    │╲1
         *  ╱ │ ╲1  │ ╲
         * a 2│  ╲  │2 f
         *  ╲ │   ╲ │ ╱
         *  2╲│    ╲│╱1
         *    c─────e
         *       1           */
        Grafica<String> g = new Grafica<String>();
        g.agrega("a");
        g.agrega("b");
        g.agrega("c");
        g.agrega("d");
        g.agrega("e");
        g.agrega("f");

        g.conecta("a", "b", 1);
        g.conecta("a", "c", 2);
        g.conecta("b", "c", 2);
        g.conecta("b", "d", 3);
        g.conecta("b", "e", 1);
        g.conecta("c", "e", 1);
        g.conecta("d", "e", 2);
        g.conecta("d", "f", 1);
        g.conecta("e", "f", 1);

        /* BFS */
        g.paraCadaVertice(new AccionVerticeGrafica<String>() {
                public void actua(VerticeGrafica<String> vertice) {
                    vertice.setColor(Color.ROJO);
                }
            });
        Cola<VerticeGrafica<String>> c = new Cola<VerticeGrafica<String>>();
        VerticeGrafica<String> it = g.vertice("a");
        it.setColor(Color.NEGRO);
        c.mete(it);
        cadena = "BFS 1: ";
        while (!c.esVacia()) {
            it = c.saca();
            cadena += it.getElemento() + ", ";
            while (it.hayVecino()) {
                VerticeGrafica<String> vec = it.vecino();
                if (vec.getColor() == Color.ROJO) {
                    vec.setColor(Color.NEGRO);
                    c.mete(vec);
                }
            }
        }
        System.out.println(cadena);

        /* BFS de la clase */
        cadena = "BFS 2: ";
        g.bfs("a", new AccionVerticeGrafica<String>() {
                public void actua(VerticeGrafica<String> vertice) {
                    cadena += vertice.getElemento() + ", ";
                }
            });
        System.out.println(cadena);

        /* DFS */
        g.paraCadaVertice(new AccionVerticeGrafica<String>() {
                public void actua(VerticeGrafica<String> vertice) {
                    vertice.setColor(Color.ROJO);
                }
            });
        Pila<VerticeGrafica<String>> p = new Pila<VerticeGrafica<String>>();
        it = g.vertice("a");
        it.setColor(Color.NEGRO);
        p.mete(it);
        cadena = "DFS 1: ";
        while (!p.esVacia()) {
            it = p.saca();
            cadena += it.getElemento() + ", ";
            while (it.hayVecino()) {
                VerticeGrafica<String> vec = it.vecino();
                if (vec.getColor() == Color.ROJO) {
                    vec.setColor(Color.NEGRO);
                    p.mete(vec);
                }
            }
        }
        System.out.println(cadena);

        /* DFS de la clase */
        cadena = "DFS 2: ";
        g.dfs("a", new AccionVerticeGrafica<String>() {
                public void actua(VerticeGrafica<String> vertice) {
                    cadena += vertice.getElemento() + ", ";
                }
            });
        System.out.println(cadena);

        /* Trayectoria mínima */
        Lista<VerticeGrafica<String>> trayectoria = g.trayectoriaMinima("a", "f");
        String s = "Trayectoría mínima: ";
        for (VerticeGrafica<String> v : trayectoria)
            s += v.getElemento() + ", ";
        System.out.println(s);

        /* Dijkstra */
        Lista<VerticeGrafica<String>> dijkstra = g.dijkstra("a", "f");
        s = "Dijkstra: ";
        for (VerticeGrafica<String> v : dijkstra)
            s += v.getElemento() + ", ";
        System.out.println(s);
    }
}
