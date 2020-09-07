/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.SingleGraph;

/**
 *
 * @author Jonathan
 */
public class VisGraph {

    private Node nodo;

    @SuppressWarnings("UnusedAssignment")
    public List<NodeProcess> startMsj( Graph graph) {
        List<NodeProcess> lst = new ArrayList<>();
        Set<Integer> recepients = new HashSet<>();
        Set<Integer> neighbors;
        Iterator<? extends Node> n = graph.getNodeIterator();
        
        while (n.hasNext()) {
            nodo = n.next();
            //creamos un conjunto nuevo para los vecinos del nodo que esta 
            neighbors= new HashSet<>();
            //Iterador para recorrer la grafica desde el nodo dado
            Iterator<? extends Node> vecinos = nodo.getDepthFirstIterator();
            //metemos todos los nodos en un conunto
            recepients.add(Integer.parseInt(nodo.getId()));
            //vemos los vecinos del nodo presente
            while (vecinos.hasNext()) {
                Node v = vecinos.next();
                //vemos si es vecino del nodo que estamos 
                //si lo es lo agregamos a su lista de vecinos
                if(v.hasEdgeFrom(nodo))
                    neighbors.add(Integer.parseInt(v.getId()));    
            }
            //cremos un NoceProcess por cada nodo, con sus repectivos vecinos
            lst.add(new NodeProcess(Integer.parseInt(nodo.getId()),new HashSet<>(neighbors), recepients));
            //limpiamos los vecinos para el siguiente nodo que entra
            neighbors=null;
        }
        return lst;
    }

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
            css += "node.important" + i + "{fill-color: " + getRandomRGB() + "; size: 13px;}";
            graph.addAttribute("ui.label", i);

        }
        graph.addAttribute("ui.stylesheet", css);
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
    }

    public String toString(Node nodo) {
        return "El nodo: " + nodo;
    }

    public List<NodeProcess> showWindow() {
        Graph graph = new SingleGraph("Random");
        Generator gen = new RandomGenerator(2);
        gen.addSink(graph);
        gen.begin();
        for (int i = 0; i < 6; i++) {
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
                    lista.add(nodo);
                }
            }
        }
        graph.display();
        return startMsj(graph);
    }
}
