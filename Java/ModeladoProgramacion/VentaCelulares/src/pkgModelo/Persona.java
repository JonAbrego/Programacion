package pkgModelo;

/**
 * Clase usada para obtener los atributos de la entidad persona 
 * atraves de los getters y setters
 * @author abrego
 */
public class Persona {
    private String rfc;
    private String nombre;
    private String apellidoP;
    private String apellidoM;
    private String fechaNacimiento;
    private String sexo;
    private String ciudad;
    private String colonia;
    private String calle;
    private String numero;
    private String codigoPostal;

    /**
     * Constructor 
     */
    public Persona() {
    }

    /**
     * Constructor de copia
     * @param rfc Cadena que representa el rfc de la nueva persona creada 
     * @param nombre Cadena que representa el nombre de la nueva persona creada 
     * @param apellidoP Cadena que representa el apellido paterno de la nueva persona creada 
     * @param apellidoM Cadena que representa el apellido materno de la nueva persona creada 
     * @param fechaNacimiento Cadena que representa la fehca de nacimiento de la nueva persona creada 
     * @param sexo Cadena que representa el sexo de la nueva persona creada 
     * @param ciudad Cadena que representa la ciudad de la nueva persona creada 
     * @param colonia Cadena que representa la colonia de la nueva persona creada 
     * @param calle Cadena que representa la calle de la nueva persona creada 
     * @param numero Cadena que representa el numero  de la nueva persona creada 
     * @param codigoPostal Cadena que representa el codigo postal de la nueva persona creada 
     */
    public Persona(String rfc, String nombre, String apellidoP, String apellidoM, String fechaNacimiento, String sexo, String ciudad, String colonia, String calle, String numero, String codigoPostal) {
        this.rfc = rfc;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.apellidoM = apellidoM;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.ciudad = ciudad;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
        this.codigoPostal = codigoPostal;
    }

    /**
     * Metodo encargado de obtener el rfc de la persona 
     * @return String que es el rfc de la persona
     */
    public String getRfc() {
        return rfc;
    }

    /**
     * Metodo encargado de modificar el rfc de la persona
     * @param rfc Cadena que sera el nuevo valor para rfc 
     */
    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    /**
     * Metodo encardado de obtener el rfc de la persona
     * @return String que es el nombre de la persona
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo usado para modificar el valor de la variablo nombre 
     * @param nombre cadena que sera el nuevo valor para nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Metodo encargado de obtener el valor de apellido paterno de la persona
     * @return String que es el apellido paterno de la persona
     */
    public String getApellidoP() {
        return apellidoP;
    }

    /**
     * Metodo usado para modificar el valor de la variablo apellidoP
     * @param apellidoP cadena que sera el nuevo valor de apellidoP
     */
    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    /**
     * Metodo encargado de obtener el valor de la variable apellidoM
     * @return String que es el apellido materno de la persona
     */
    public String getApellidoM() {
        return apellidoM;
    }

    /**
     * Metodo usado para modificar el valor de la variablo apellidoM
     * @param apellidoM cadena que sera el nuevo valor de apellidoM
     */
    public void setApellidoM(String apellidoM) {
        this.apellidoM = apellidoM;
    }

    /**
     * Metodo encargado de obtener el valor de la variable fechaNacieminto
     * @return String que es la fecha de nacimiento de la persona
     */
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     *  Metodo usado para modificar el valor de la variablo fechaNaciemiento
     * @param fechaNacimiento cadena que sera el nuevo valor de fechaNaciemiento
     */
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Metodo encargado de obtener el valor de la variable sexo
     * @return String que respresenta el sexo de la persona
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * Metodo usado para modificar el valor de la variable sexo
     * @param sexo Cadena que sera el nuevo valor de sexo
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    /**
     * Metodo usado para obtener el valo de la variable ciudad 
     * @return String que es el valor de la variable ciudad 
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Metodo encargado de modificar el valor de la variable ciudad 
     * @param ciudad Cadena que sera el nuevo valor para la variable ciduad 
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * Metodo encargado de obtener le valor de la variables colonia
     * @return String que es el valor de la variable colonia 
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * Metodo encargado de modificar el valor de la variable colonia
     * @param colonia Cadean que sera el nuevo valor de la variable colonia
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * Metodo encargado de obtener el valor de la variable calle
     * @return String que es el valor de la variable calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * Metodo encargado de modificar el valor de la variables calle
     * @param calle Cadena que tendra el nuevo valor de la variable calle
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * Metodo encargado de obtener el valor de la variable numero
     * @return String que es el valor de la variabloe numero 
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Metodo encargado de modificar le valor de la variable nuemro
     * @param numero Cadena que representara el nuevo valor para la variables numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Metodo encargado de devolver el nuevo valor de la variable codigoPostal
     * @return String que es el valor de la variable codigo postal 
     */
    public String getCodigoPostal() {
        return codigoPostal;
    }

    /**
     * Metodo encargado de modificar el valor de la variable codigoPostal
     * @param codigoPostal Cadena que contendra el nuevo valor asignado a la variable codigoPostal
     */
    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }
}
