/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mayorDiferencia;

/**
 *
 * @author jonathan
 * @version 1.0
 */
public class MayorDiferencia {

    /**
     * Constructor por defecto
     */
    public MayorDiferencia() {
    }
    
    /**
     * Metodo que dado un arreglo localiza al numero mas chico
     * y al más grande, para despúes poder sacar la diferencia 
     * entre esos dos números 
     * 
     * @param a un arreglo de enteros 
     * @return la mayor diferencia entre el número más pequeño y el 
     * más grande, es decir la resta de dos números 
     */
    public int mayorDiferencia(int[] a){
        int menor = a[0];
        int mayor = a[0];        
        for(int i = 0; i < a.length; i++){
            if(menor > a[i]){
                menor = a[i];
            }
            if(mayor < a[i]){
                mayor = a[i];
            }
        }       
        return mayor-menor;                    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] a = new int[]{10,3,9,2,7,11,3,1};                
        MayorDiferencia p = new MayorDiferencia();
        System.out.println(p.mayorDiferencia(a));
    }
    
}
