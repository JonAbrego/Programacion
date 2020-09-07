/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lamport;

/**
 *
 * @author jonathan
 */
public class LamportClock {
	private int numProcess;
	private int numEvent;
	
	public LamportClock(int numProcess, int numEvent){
		this.numProcess = numProcess;
		this.numEvent = numEvent;
	}
	
	public int getNumP(){
		return this.numProcess;
	}
	
	public int getNumE(){
		return this.numEvent;
	}
}
