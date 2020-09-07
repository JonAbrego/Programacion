/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Jonathan
 */
public class App {

    private static String getRandomRGB() {
        return "rgb(" + randomNumber() + ", " + randomNumber() + ", " + randomNumber() + ")";
    }

    private static int randomNumber() {
        Random rand = new Random();
        return rand.nextInt(256);
    }

    static void setCSS(Graph graph) {
        int n = graph.getNodeCount();
        String css = "node {fill-color:grey;}";
        ArrayList<String> rgb = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String s = getRandomRGB();

            for (String str : rgb) {
                while (s.equals(str)) {
                    s = getRandomRGB();
                }
            }
            rgb.add(s);
            css += "node.important" + i + "{fill-color: " + getRandomRGB() + "; size: 15px;}";
            graph.addAttribute("ui.label", i);

        }
        graph.addAttribute("ui.stylesheet", css);
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
    }

    public String toString(Node nodo) {
        return "El nodo: " + nodo;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Generating a random graph
        Graph graph = new SingleGraph("Random");
        Generator gen = new RandomGenerator(2);
        Node nodo;

        gen.addSink(graph);
        gen.begin();
        for (int i = 0; i < 50; i++) {
            gen.nextEvents();
        }
        gen.end();
        setCSS(graph);
        Iterator<? extends Node> nodos = graph.getNodeIterator();
        ArrayList<Node> lista = new ArrayList<>();
        int contador = 0;

        while (nodos.hasNext()) {
            nodo = nodos.next();
            if (!lista.contains(nodo)) {
                Iterator<? extends Node> vecinos = nodo.getDepthFirstIterator();
                contador++;

                nodo.addAttribute("ui.class", "important" + contador);
                nodo.addAttribute("ui.label", nodo.getId());
                lista.add(nodo);
                while (vecinos.hasNext()) {
                    Node vecino = vecinos.next();
                    vecino.addAttribute("ui.class", "important" + contador);
                    vecino.addAttribute("ui.label", vecino.getId());
                    lista.add(vecino);
                }
            }
        }
        graph.display();
    }
}
