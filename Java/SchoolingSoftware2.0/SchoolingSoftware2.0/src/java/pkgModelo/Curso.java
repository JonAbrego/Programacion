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
public class Curso {
    String profesor;
    String alumno;
    String nivel;
    String horario;
    String elegido;
    String usuario;
    String usr_alumno;
    String calificacion;
    
    public Curso() {
    }

    public Curso(String profesor, String alumno, String nivel, String horario, String elegido, String usuario, String usr_alumno, String calificacion) {
        this.profesor = profesor;
        this.alumno = alumno;
        this.nivel = nivel;
        this.horario = horario;
        this.elegido = elegido;
        this.usuario = usuario;
        this.usr_alumno = usr_alumno;
        this.calificacion = calificacion;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getElegido() {
        return elegido;
    }

    public void setElegido(String elegido) {
        this.elegido = elegido;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getUsr_alumno() {
        return usr_alumno;
    }

    public void setUsr_alumno(String usr_alumno) {
        this.usr_alumno = usr_alumno;
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }
    
    
}
