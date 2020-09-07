/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2;

import java.util.List;

/**
 *
 * @author Jonathan
 */
public class App2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        VisGraph vi = new VisGraph();
        List<NodeProcess> procs = vi.showWindow();

        //Run distributed system
        for(NodeProcess proc : procs) {
            proc.run();
        }

//        Wait until all processes are finished
        boolean isAlive = true;
        while (isAlive) {
            isAlive = false;

            for (NodeProcess proc : procs) {
                isAlive = isAlive ? true : proc.isAlive();
            }
        }

//      Print finished states
        for(NodeProcess proc : procs) {
            System.out.println("Proc " + proc.getUid() + " finished with code " + proc.getExitState());
        }

    }

}
