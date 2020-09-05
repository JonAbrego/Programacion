package pkgModelo;

/**
 * Clase uada para modelar las caracteristicas 
 * de la entidad empresa
 * @author abrego
 */
public class Servicio {
    String descricion;

    /**
     * Constructor por defecto
     */
    public Servicio() {
    }

    /**
     * Constructor de copia
     * @param descricion 
     */
    public Servicio(String descricion) {
        this.descricion = descricion;
    }

    /**
     * Metodos usado para obtener la descripcion del servicio
     * @return la descripcion del servicio
     */
    public String getDescricion() {
        return descricion;
    }

    /**
     * Metodo usado para modificar la descripcion del servicio
     * @param descricion una cadena que respresentara la nueva 
     * descripcion del servicio
     */
    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }
}
