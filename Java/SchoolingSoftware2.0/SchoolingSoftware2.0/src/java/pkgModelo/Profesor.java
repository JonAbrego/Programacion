/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgModelo;

/**
 *
 * @author adriansoto
 */
public class Profesor {
    
    String nombres;
    String aPat;
    String aMat;
    String correo;
    String usuario;
    String constancia;
    String video;
    String pass;
    String cPass;
    

    public Profesor() {
    }

    public Profesor(String nombres, String aPat, String aMat, String correo, String usuario, String constancia, String video, String pass, String cPass) {
        this.nombres = nombres;
        this.aPat = aPat;
        this.aMat = aMat;
        this.correo = correo;
        this.usuario = usuario;
        this.constancia = constancia;
        this.video = video;
        this.pass = pass;
        this.cPass = cPass;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getaPat() {
        return aPat;
    }

    public void setaPat(String aPat) {
        this.aPat = aPat;
    }

    public String getaMat() {
        return aMat;
    }

    public void setaMat(String aMat) {
        this.aMat = aMat;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getConstancia() {
        return constancia;
    }

    public void setConstancia(String constancia) {
        this.constancia = constancia;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getcPass() {
        return cPass;
    }

    public void setcPass(String cPass) {
        this.cPass = cPass;
    }

    
}
