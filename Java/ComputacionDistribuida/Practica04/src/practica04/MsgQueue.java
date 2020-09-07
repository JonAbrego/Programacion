/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica04;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author jonathan
 */
public class MsgQueue {

    private static MsgQueue instance;

    public Map<Integer, Message> getMsgs() {
        return msgs;
    }

    public void setMsgs(Map<Integer, Message> msgs) {
        System.out.println("Ha cambiado el hash de mensajes");
        this.msgs = msgs;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        System.out.println("Cambio el random");
        this.rand = rand;
    }

    public static void setInstance(MsgQueue instance) {
        System.out.println("Ha cambiado la instancia de los mensajes");
        MsgQueue.instance = instance;
    }

    private Map<Integer, Message> msgs;
    private Random rand;

    public static synchronized MsgQueue getInstance() {
        if (instance == null) {
            instance = new MsgQueue();
            instance.msgs = new HashMap<Integer, Message>();
            instance.rand = new Random();
        }
        return instance;
    }

    public synchronized boolean send(Message msg, Integer destUID) {
        if (msgs.containsKey(destUID)) {
            return false;
        }
        msgs.put(destUID, msg);
        //System.out.println("	msg sent to " + destUID);
        return true;
    }

    public synchronized Message receive(Integer destUID) {
        if (!msgs.containsKey(destUID)) {
            return null;
        }
        //System.out.println("	msg received by " + destUID);
        return msgs.remove(destUID);
    }

    public synchronized Integer getRandom(Integer max) {
        return rand.nextInt(max);
    }
}
