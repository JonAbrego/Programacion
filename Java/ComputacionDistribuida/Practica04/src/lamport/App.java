/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamport;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;

import org.graphstream.algorithm.generator.Generator;
import org.graphstream.algorithm.generator.RandomGenerator;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import practica04.NodeProcess;

/**
 *
 * @author jonathan
 */
public class App{	 
	
    static void ejecutar(List<String> lista, List<NodeProcess> procs,Graph grafica, Set<Integer> recepients, int np){
    	Iterator <?extends Node> nodesIt = grafica.getNodeIterator();

        while(nodesIt.hasNext()){
        	Node actual = nodesIt.next();
        	actual.addAttribute("ui.class","rojo");
        	actual.addAttribute("ui.label",actual.getId());
        	System.out.println("Los vecinos del nodo "+actual.getId()+" son:");
        	Iterator <?extends Node> neighbors = actual.getNeighborNodeIterator();     
        	List<Integer> vecinos = new ArrayList<>();
        	while(neighbors.hasNext()){
        		Node nodoComponente = neighbors.next();
        		System.out.println("El nodo: "+nodoComponente.getId());
        		vecinos.add(Integer.parseInt(nodoComponente.getId())); //llenamos la lista de vecinos
        	}
        	//por cada nodo hacemos un nuevo proceso (NodeProcess)
        	procs.add(new NodeProcess(Integer.parseInt(actual.getId()), new HashSet<>(vecinos), recepients, np));
        }
    	for(NodeProcess proc : procs) proc.start();
    	//esto ya es de la practica anterior
    	boolean isAlive = true;
    	while(isAlive){
    		isAlive = false;
    		for(NodeProcess proc : procs) isAlive = isAlive ? true : proc.isAlive();
    	}
    	
    	for(NodeProcess proc : procs)System.out.println("Proceso(" + proc.getUid() + ") ----> " + proc.getExitState());
    	grafica.display();
    }
    
    public static void main(String args[]) {    	
    	//numero de nodos que tendra la grafica
    	int total = 5;    	
    	//lista de NodeProcess
    	List<NodeProcess> procs = new ArrayList<NodeProcess>();
    	
    	List<Integer> recepientsList = new ArrayList<Integer>();
    	for(int i = 0; i < total; i++){
    		recepientsList.add(i);
    	}
    	
    	Set<Integer> recepients =  new HashSet<Integer>(recepientsList);
    	List<String> evento1 = new ArrayList<String>();
    	List<String> evento2 = new ArrayList<String>();
    	//nueva grafica
        Graph graph = new SingleGraph("Random");
        Generator gen = new RandomGenerator(2);
        gen.addSink(graph);
        gen.begin();
        for(int i = 0; i<total-3; i++)
            gen.nextEvents();
        gen.end();                
        
        ejecutar(evento1,procs,graph,recepients, total);
        for(NodeProcess n : procs){
        	for(LamportClock v : n.getVector()){
        		if(v.getNumP() != n.getUID())
        			System.out.println("Proceso "+ v.getNumP()+ " , Evento "+ v.getNumE());
    		}
        	for(String i : n.getLista()){
            	evento1.add(i);
            }
        }
        procs = new ArrayList<NodeProcess>();
        try{
        	ejecutar(evento2,procs,graph,recepients, total);
        	for(NodeProcess n : procs){
        		for(String i : n.getLista()){
        			evento2.add(i);
        		}
        	}
        }
        catch(NullPointerException e){
        	System.out.println();
        }
        System.out.println("Eventos primera ejecucion");
        for(int i = 0; i<evento1.size();i++){
        	System.out.println(evento1.get(i));	
        }
        System.out.println("Eventos segunda ejecucion");
        for(int i = 0; i<evento2.size();i++){
    		System.out.println(evento2.get(i));	
        }
        for(int i = 0; i<evento1.size();i++){
        	for(int j = 0; j < evento2.size(); j++){
        		if(evento1.get(i).equals(evento2.get(j))){
            		System.out.println("Evento repetido en ambas ejecuciones: "+evento1.get(i));
            	}
        	}
        }
        
        JFrame jf=new Draw(procs);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(1200,750);
		jf.setVisible(true);
    }
}