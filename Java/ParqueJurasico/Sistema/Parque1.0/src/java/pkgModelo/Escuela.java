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
public class Escuela {
  String clave;
  String nombre;
  String entidad; 
  String localidad; 
  String calle; 
  String numero;  
  String hora;
  String tipoVisita;
  String fecha;
  int grado;
  
  public Escuela(){}
  
    /**
     * 
     * @return 
     */
    public int getGrado() {
        return grado;
    }

    /**
     * 
     * @param grado 
     */
    public void setGrado(int grado) {
        this.grado = grado;
    }
  
    /**
     * 
     * @return 
     */
    public String getClave() {
        return clave;
    }

    /**
     * 
     * @param clave 
     */
    public void setClave(String clave) {
      this.clave = clave;
    }

    /**
     * 
     * @return 
     */
    public String getNombre() {
       return nombre;
    }

    /**
     * 
     * @param nombre 
     */
    public void setNombre(String nombre) {
      this.nombre = nombre;
    }

    /**
     * 
     * @return 
     */
    public String getEntidad() {
      return entidad;
    }

    /**
     * 
     * @param entidad 
     */
    public void setEntidad(String entidad) {
      this.entidad = entidad;
    }

    /**
     * 
     * @return 
     */
    public String getLocalidad() {
      return localidad;
    }

    /**
     * 
     * @param localidad 
     */
    public void setLocalidad(String localidad) {
      this.localidad = localidad;
    }

    /**
     * 
     * @return 
     */
    public String getCalle() {
      return calle;
    }

    /**
     * 
     * @param calle 
     */
    public void setCalle(String calle) {
      this.calle = calle;
    }

    /**
     * 
     * @return 
     */
    public String getNumero() {
      return numero;
    }

    /**
     * 
     * @param numero 
     */
    public void setNumero(String numero) {
      this.numero = numero;
    }

    /**
     * 
     * @return 
     */
    public String getHora() {
        return hora;
    }

    /**
     * 
     * @param hora 
     */
    public void setHora(String hora) {
        this.hora = hora;
    }
   
    /**
     * 
     * @return 
     */
    public String getTipoVisita() {
        return tipoVisita;
    }

    /**
     * 
     * @param tipoVisita 
     */
    public void setTipoVisita(String tipoVisita) {
        this.tipoVisita = tipoVisita;
    }

    /**
     * 
     * @return 
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * 
     * @param fecha 
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
