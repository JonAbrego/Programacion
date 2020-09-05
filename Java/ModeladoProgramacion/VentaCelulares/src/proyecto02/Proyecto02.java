package proyecto02;

import pkgVista.Principal;

/**
 *
 * @author abrego
 */
public class Proyecto02 {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }
    
}
