package pkgModelo;

/**
 * Clase que representa una entidad en la 
 * base de datos, donde se definene 
 * los atributos que contiene dicha entidad 
 * @author abrego
 */
public class Celular {
    private String imei;
    private String marca;
    private String modelo;
    private String descripcion;
    
    /**
     * Constructor por defecto
     */
    public Celular() {
    }

    /**
     * Constructor
     * @param imei Cadena que representa el imei de un celular en particular
     * @param marca Cadena que representa la marca del nuevo celular 
     * @param modelo Cadena que representa el modelo del nuevo celular 
     * @param descripcion Cadena que representa la descripcion(caracteristicas) 
     * del nuevo celular
     */
    public Celular(String imei, String marca, String modelo, String descripcion) {
        this.imei = imei;
        this.marca = marca;
        this.modelo = modelo;
        this.descripcion = descripcion;
    }

    /**
     * Metodo que regresa el imei del celular
     * @return imei del celular 
     */
    public String getImei() {
        return imei;
    }

    /**
     * Metodo usado para modificar el imei del celular
     * @param imei cadena que representara el nuevo imei del celular
     */
    public void setImei(String imei) {
        this.imei = imei;
    }

    /**
     * Metodo usado para obtener la marca del celular
     * @return la marca a la que pertenece el celular
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Metodo usado para modificar la marca del celular
     * @param marca 
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Metodo usado para obtener el modelo del celular
     * @return el modelo del celular 
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * Metodo usado para modificar el modelo del celular
     * @param modelo del celular 
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * Metodo usado para obtener la descripcion del celular
     * @return la descripcion del celular
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Metodo usado para modificar la descripcion del celular
     * @param descripcion la nueva descripcion del celular
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }    
}