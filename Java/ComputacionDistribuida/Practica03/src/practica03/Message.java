/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica03;

/**
 *
 * @author Jonathan
 */
public class Message {

    public Integer finalDestUID = null;
    public Integer TTL = 10;

    public Message(Integer finalDestUID) {
        this.finalDestUID = finalDestUID;
    }
    
    public Integer getFinalDestUID() {
		return finalDestUID;
    }
    
    public void setFinalDestUID(Integer finalDestUID) {
		System.out.println("ha cambiado el identificador del mensaje");
		this.finalDestUID = finalDestUID;
    }
    
    public Integer getTTL() {
		return TTL;
    }

    public void setTTL(Integer tTL) {
		System.out.println("han cambiado los intentos restantes para morir");
		TTL = tTL;
    }
}