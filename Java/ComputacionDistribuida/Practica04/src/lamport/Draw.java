/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamport;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.List;

import javax.swing.JFrame;
import practica04.NodeProcess;

/**
 *
 * @author jonathan
 */
class Draw extends JFrame{
    private final int ARR_SIZE = 4;
    private List<NodeProcess> procs;
    private int dis;

    public Draw(List<NodeProcess> procs){
    	this.procs = procs;
    	this.dis = 650/procs.size();
    }
    
    void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
    	Graphics2D g = (Graphics2D) g1.create();
    		
    	double dx = x2 - x1, dy = y2 - y1;
    	double angle = Math.atan2(dy, dx);
    	int len = (int) Math.sqrt(dx*dx + dy*dy);
    	AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
    	at.concatenate(AffineTransform.getRotateInstance(angle));
    	g.transform(at);
    		
    	//Draw horizontal arrow starting in (0, 0)
    	g.drawLine(0, 0, len, 0);
    	g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len}, 
    				  new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }
    	
    	public void paintComponent(Graphics g) {
    		for (int x = 15; x < 200; x += 16)
    			drawArrow(g, x, x, x, 150);
    		drawArrow(g, 30, 300, 300, 190);
    	}
    	
    	public void paint(Graphics g){
    		Graphics2D go=(Graphics2D)g; 
    		go.setPaint(Color.black);
    		for(int i=1;i<=procs.size();i++){
    			go.drawLine(50,dis*i,1150,dis*i);
    		}
    		int i = 1;
    		int j = 1;
    		for(NodeProcess n : procs){
    			j = 1;
            	for(LamportClock v : n.getVector()){
            		System.out.println("Proceso "+i + " Evento "+ j+ " Proviene de "+ "Proceso "+ v.getNumP()+ " , Evento "+ v.getNumE());
					go.setPaint(Color.blue); 
					go.fillOval(60*j,dis*i-3,5,5);
					go.drawString("p"+i+"e"+j,60*j,dis*i-5);
            		if(v.getNumP() != (n.getUID()+1)){
    					go.setPaint(Color.red);
    					drawArrow(go,60*v.getNumE()+2,dis*v.getNumP(),60*j+2,dis*i);
            		}
            		j++;
        		}
            	i++;
    		}
    	}
 }