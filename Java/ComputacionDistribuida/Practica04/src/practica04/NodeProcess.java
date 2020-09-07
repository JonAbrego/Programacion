/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica04;

import lamport.LamportClock;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;

/**
 *
 * @author jonathan
 */
public class NodeProcess extends Thread {

    private Integer uid;
    private Set<Integer> neighbors;
    private MsgQueue queue;
    private Set<Integer> recepients;
    private ArrayList<String> lista;
    private ArrayList<LamportClock> lampVector;
    private int ne;
    private ExitState exitState = ExitState.EJECUTANDOSE; //0 - is running, 1 - received 5 messages, 2 - died by lifetime, 3 - died by an error 

    public enum ExitState {

        EJECUTANDOSE, FINALIZOCONEXITO, MURIO, ERROR
    }

    public Set<Integer> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Set<Integer> neighbors) {
        System.out.println("	cambiamos el conjunto de vecinos");
        this.neighbors = neighbors;
    }

    public MsgQueue getQueue() {
        return queue;
    }

    public void setQueue(MsgQueue queue) {
        System.out.println("	ha cambiado la cola de mensajes");
        this.queue = queue;
    }

    public Set<Integer> getRecepients() {
        return recepients;
    }

    public void setRecepients(Set<Integer> recepients) {
        System.out.println("	han cambiado los destinatarios");
        this.recepients = recepients;
    }

    public void setUid(Integer uid) {
        System.out.println("	ha cambiado el identificador del proceso");
        this.uid = uid;
    }

    public void setExitState(ExitState exitState) {
        System.out.println("	ha cambiado el estado de salida");
        this.exitState = exitState;
    }

    public NodeProcess(Integer uid, Set<Integer> neighbors, Set<Integer> recepients, int np) {
        this.uid = uid;
        this.neighbors = new HashSet<Integer>();
        this.neighbors.addAll(neighbors);

        this.recepients = new HashSet<Integer>();
        this.recepients.addAll(recepients);

        queue = MsgQueue.getInstance();

        this.lampVector = new ArrayList<LamportClock>();
        this.ne = 1;
    }

    public int getUID() {
        return uid;
    }

    public ArrayList<String> getLista() {
        return lista;
    }

    public ArrayList<LamportClock> getVector() {
        return this.lampVector;
    }

    public void run() {
        //Cambio de estado
        System.out.println("ESTADO: Process " + uid + " started");
        lista = new ArrayList<String>();
        //kill process if it has no neighbors 
        //Cambio de estado
        if (neighbors.isEmpty()) {
            System.out.println("ESTADO: Process " + uid + " nas no neighbors, stopped");
            exitState = ExitState.MURIO;
            return;
        }

        Integer received = 0;
        Integer lifetime = 100;

        try {
            while (true) {
                //**************** SEND MESSAGE *****************
                for (Integer finalDestUID : recepients) {
                    //evento
                    Message mensaje = new Message(finalDestUID, uid, ne);
                    //devuelvo el evento
                    int rndNeigh = getRandNeighbor();
                    if (queue.send(mensaje, rndNeigh)) {
                        System.out.println("Evento: envio de mensaje");
                        ++ne;
                        lampVector.add(new LamportClock(uid + 1, ne - 1));
                        System.out.println("Proceso " + (uid + 1) + " evento " + (ne - 1));
                        System.out.println("     " + (uid + 1) + " envio a " + (rndNeigh + 1));
                        lista.add("envio de " + mensaje.getOrigin() + " a " + mensaje.getFinalDestUID());
                        recepients.remove(finalDestUID);
                        break;
                    }
                }

                //**************** RECEIVE MESSAGE **************
                Message msg = queue.receive(uid);
                System.out.println("EVENTO: Mensaje recibido");
                ++ne;
                System.out.println("Proceso " + (uid + 1) + " evento " + (ne - 1));
                System.out.println("     " + (uid + 1) + " recibio de " + (msg.getOrigin() + 1));
                lampVector.add(new LamportClock(msg.getOrigin() + 1, msg.getEvnt()));
                lista.add("recibe " + uid + " de " + msg.getOrigin());

                if (msg != null) {
                    if (msg.getFinalDestUID() == uid) {
        				//evento
                        //msg reached its destination
                        System.out.println("EVENTO: Mensaje llego a destinatario");
                        received++;
                    } else {
                        if (msg.getTTL() > 0) {
        					//evento
                            //scale down message TTL
                            msg.setTTL(msg.getTTL() - 1);
                            //resend same msg to the randomly selected neighbor
                            lista.add("envio de " + msg.getOrigin() + " a vecino aleatorio " + msg.getFinalDestUID());
                            queue.send(msg, getRandNeighbor());
                            System.out.println("EVENTO: Mensaje enviado a vecino aleatorio");
                            System.out.println("Proceso " + (uid + 1) + " evento " + ne);
                        }
                        //if msg.TTL == 0 - the message has not reached its target within 10 tries and shell be forgotten
                    }
                }

        		//**************** CHECK RECEIVED ****************
                //evento
                if (received == 1) {
                    System.out.println("CAMBIO DE ESTADO: Process " + uid + " received all messages and finished");
                    exitState = ExitState.FINALIZOCONEXITO;
                    break;
                }

        		//**************** CHECK LIFETIME ****************
                //evento
                lifetime--;
                if (lifetime == 0) {
                    System.out.println("CAMBIO DE ESTADO: Process " + uid + " lifetime finished");
                    exitState = ExitState.MURIO;
                    break;
                }

                sleep(100);
            }
        } catch (Exception e) {
            System.err.println("CAMBIO DE ESTADO: Process " + uid + " died: " + e.getMessage());
            //evento
            exitState = ExitState.ERROR;
        }
    }

    private Integer getRandNeighbor() {

        Integer[] all = (Integer[]) neighbors.toArray(new Integer[0]);
        return all[queue.getRandom(neighbors.size())];
    }

    public ExitState getExitState() {
        return exitState;
    }

    public Integer getUid() {
        return uid;
    }
}
