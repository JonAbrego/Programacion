package pkgModelo;

/**
 * Clase usada para modelar las caracteristicas 
 * de la entidad empresa 
 * @author abrego
 */
public class Empresa {
    private String rfc;
    private String nombre;
    private String ciudad;
    private String colonia;
    private String calle;
    private String numero;
    private String codigoPostal;
    private String url;
    

    /**
     * Constructor por defecto
     */
    public Empresa() {
    }

    /**
     * Constructor de copia
     * @param rfc 
     * @param nombre
     * @param ciudad
     * @param colonia
     * @param calle
     * @param numero
     * @param codigoPostal 
     */
    public Empresa(String rfc, String nombre, String ciudad, String colonia, String calle, String numero, String codigoPostal) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
    }  

    /**
     * Metodos usado para obtener el rfc de la empresa
     * @return el rfc de la empresa 
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Metodos usado para modificar el rfc de la empresa
     * @param rfc cadena que sera el nuevo rfc de la empresa
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * Metodos usado para obtener el nombre de la empresa 
     * @return el nombres de la empresa
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodos usado para modificar el nombre de la empresa
     * @param nombre cadena que sera el nuevo nombre de la empresa
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodos usado para obtener la ciudad donde
     * habita la empresa     
     * @return la ciudad donde esta ubicada la empresa
     */
    public String getCiudad() {
        return ciudad;
    }
    
    /**
     * Metodos usado para modificar la ciudad de la empresa
     * @param ciudad cadena que sera la nueva ciudad de la empresa
     */   
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Metodos usado para obtener la colonia  donde esta ubicada 
     * la empresa 
     * @return la colonia donde esta ubicada la empresa
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Metodos usado para modificar la colonia de la empresa
     * @param colonia cadena que sera la nueva colonia de la empresa
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * Metodos usado para obtener la calle donde esta ubicada 
     * la empresa
     * @return la calle donde se encuentra la empresa
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Metodos usado para modificar la calle de la empresa
     * @param calle cadena que sera la nueva calle de la empresa
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Metodos usado para obtener el numero donde esta ubicada 
     * la empresa
     * @return el numero (que es parte de la direccion) de la empresa
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Metodos usado para modificar el numero de la empresa
     * @param numero cadena que sera el nuevo numero (parte de la 
     * direccion) de la empresa
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Metodos usado para obtener el código postal de la 
     * empresa
     * @return el codigo postal de la empresa
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }
    
    /**
     * Metodos usado para modificar el codigo postal de la empresa
     * @param codigoPostal cadena que sera en nuevo 
     * codigo postal de la empresa
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    /**
     * Metodos usado para obtener la url imagen de la 
     * empresa
     * @return la url en cadena de nuestra ubicacion de la imagen
     */
    public String getUrl() {
        return url;
    }

    /**
     * Metodos usado para modificar la url de nuestra imagen de la empresa
     * @param url Cadena que sera nuestra direccion de la imagen 
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    
}
