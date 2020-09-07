/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica04;

/**
 *
 * @author jonathan
 */
public class Message {

    private Integer finalDestUID = null;
    private Integer TTL = 10;
    private Integer origin = null;
    private Integer pant;
    private Integer evnt;

    public Message(Integer finalDestUID, Integer origin, Integer evnt) {
        this.finalDestUID = finalDestUID;
        this.origin = origin;
        this.evnt = evnt;
    }

    public Integer getFinalDestUID() {
        return finalDestUID;
    }

    public Integer getOrigin() {
        return origin;
    }

    public void setFinalDestUID(Integer finalDestUID) {
        System.out.println("Ha cambiado el identificador del mensaje");
        this.finalDestUID = finalDestUID;
    }

    public Integer getTTL() {
        return TTL;
    }

    public void setTTL(Integer tTL) {
        System.out.println("Han cambiado los intentos restantes para morir");
        TTL = tTL;
    }

    public Integer getEvnt() {
        return evnt;
    }

    public void setEvnt(Integer evnt) {
        this.evnt = evnt;
    }
}
