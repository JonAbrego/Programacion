/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aparece_N_veces;

/**
 *
 * @author jonathan
 * @version 1.0
 */
public class Aparece_N_Veces {

    public Aparece_N_Veces() {
    }
    
    /**
     * Metodo que busca si una número dentro del arreglo aparece al menos 
     * cierta cantidad de veces, de cumplirse la condicion 
     * regresa un valor de verdad 
     * 
     * @param a arreglo de número enteros 
     * @param numero entero que deseamos buscar 
     * @param apariciones las veces que esperamos se repita el entero en el
     * arreglo
     * @return 
     */
    public boolean contiene(int[] a,int numero, int apariciones){
        int j=0; //entero empleado para llevar las veces que aparece el numero
        for(int i=0; i < a.length;i++){
            if(a[i] == numero){
                j++;//si el numero aparece 
            }
        }
        if(apariciones <= j){
            return true;
        }else
            return false;        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        int[] a = new int[]{1,2,3,4,5,1,1,3,4};
        Aparece_N_Veces p = new Aparece_N_Veces();
        System.out.println(p.contiene(a, 1, 7));
    }    
}
