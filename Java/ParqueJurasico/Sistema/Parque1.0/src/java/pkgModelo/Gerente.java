/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgModelo;

/**
 *
 * @author abrego
 */
public class Gerente {

    String usuario;
    String cont;

    /**
     * Constructor de copia 
     * @param usuario
     * @param cont 
     */
    public Gerente(String usuario, String cont) {
        this.usuario = usuario;
        this.cont = cont;
    }
    
    /**
     * Constructor por defecto
     */
    public Gerente() {
    }
    
    /**
     * Metodo para obtener el ususario 
     * @return una cadena que representa el usuario
     */
    public String getUsuario() {
        return usuario;
    }
    
    /**
     * Metodo para modificar la contrasena
     * @param usuario 
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    /**
     * Metodo para obtener la contrasea
     * @return cadena que representara la contra
     */
    public String getCont() {
        return cont;
    }

    /**
     * Metodo para modificar la contraseña
     * @param cont 
     */
    public void setCont(String cont) {
        this.cont = cont;
    }

}
