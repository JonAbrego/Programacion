/**
 * Clase base recibe n&uacute;meros representados en una determinada base(en est&eacute; caso 
 * sera una cadena de d&iacute;gitos) e imprime su valor en base 10(representaci&oacute;n decimal)
 * <BR><BR>
 * Por ejemplo:
 * <BR><BR>
 * base 2 10101
 * <BR><BR>
 * 21
 * <BR><BR>
 * base 8 31302102
 * <BR><BR>
 * 6652994
 * <BR><BR>
 * base 3 101
 * <BR><BR>
 * 10
 * @author Abrego &Aacute;lvarez Jonathan
 * @version 1.0
 */
import java.util.Scanner;
import java.lang.*;

public class Base{
    private String numero;//Cadena que representara el numero ingresado 
    private int base;//Entero que representara la base
    private Scanner ent=new Scanner(System.in);

    /**
     * Constructor por omision
     */
    public Base(){
		base=0;
		numero="";
    }
    /**
     * Constructor que recibe los datos necesarios para la conversi&oacute;n
     * @param bas Entero que representa la base del n&uacute;mero dado
     * @param num Cadena que representa el n&uacute;mero en una base dada
     */
    public Base(int bas, String num){
		numero=num;
		base=bas;
		int i=Integer.parseInt(num,bas);
		System.out.println(i);
    }
    /**
     * Constructor de copia
     * @param b Un objeto de la misma clase Base
     */
    public Base(Base b){
		base=b.base;
		numero=b.numero;
    }
    
    /**
     * Obtien el valor de la base del numero que se ingresara 
     * @return el valor de la base
     */
    public int obtenerBase(){
		return base;
    }
    
    /**
     * Obtien el numero que ingrese el usuario con respecto a la base 
     * @return el numero en una base
     */
    public String obtenerNumero(){
		return numero;
    }
    
    /**
     * Modifica el valor de la base 
     * @return El nuevo valor de la base
     */
    public int modificaBase(){
		base=ent.nextInt();
		return base;
    }
    
    /**
     * Modifica el valor del numero
     * @return El nuevo valor del numero
     */
    public String modificaNumero(){
		numero=ent.next();
		return numero;
    }

    /**
     * Metodo que evalua un numero dado en una base y lo imprime de forma decimal
     * cabe mencionar que recibe dos parametros donde el primero es una cadena y el segundo es una base
     * <BR><BR>
     * Modo de uso en ejecusi&oacute;n
     * <BR><BR>
     * Primero se ingresa la base
     * <BR><BR>
     * Despues dejando un espacio se ingresa el numero
     * <BR><BR>
     * N&uacute;mero en base decimal
     * <BR><BR>
     * Ejemplo:
     * <BR><BR>
     * 2 101
     * <BR><BR>
     * 5
     * @return El valor decimal del numero ingresado
     */
    public int evaluarADecimal(){
		System.out.println("Bienvenido.Este programa evaluara de acuerdo a la base(de 2 hasta 10) y un numero(en esa base)dados por usted");
		System.out.println("Ingrese la base despues, deje un espacio e ingrese el numero ");
       	System.out.print("base:");
		modificaBase();
		modificaNumero();
		int valDec=Integer.parseInt(obtenerNumero(),obtenerBase());
		return valDec;
    }
     
    //Metodo main para probar el/los metodos 
    public static void main(String args[]){
    	try{
    		Base c1=new Base(Integer.parseInt(args[0]),args[1]);	
    	}catch(NumberFormatException e){
    		System.out.println("Verifique los datos ingresados");
    	}
    }    			
}