package pkgModelo;

import java.sql.SQLException;

/**
 * Clase que se encarga de las operaciones elementales(insertar,actualizar,borrar) relacionadas con la base de 
 * datos para una persona
 * @author abrego
 */
public class OPGPersona extends Persona{
    pkgControlador.Conexion objConecta;

    /**
     * Constructor
     */
    public OPGPersona() {
        objConecta = new pkgControlador.Conexion();
    }
    
    /**
     * Metodo encargado de guardar una nueva persona en la base de datos con ayuda de los 
     * los getters de la clase padre <b>Persona</b>
     * @return <b>True</b> si se pudo guardar la nueva persona en la base de datos 
     * en otro caso False
     * @throws SQLException si ocurrre algo al intentar guardar o durante el proceso de conexion
     */
    public boolean insertar() throws SQLException{
       String sql="INSERT INTO Persona VALUES('"+getRfc()+"','"+getNombre()+"','"+getApellidoP()+"'"
                                             + ",'"+getApellidoM()+"','"+getFechaNacimiento()+"'"
                                             + ",'"+getSexo()+"','"+getCiudad()+"','"+getColonia()+"'"
                                             + ",'"+getCalle()+"','"+getNumero()+"','"+getCodigoPostal()+"');";
       if(objConecta.insertar(sql)==true){
           objConecta.desconectar();
           return true;
       }
       return false;
    }

    /**
     * Metodo encargado de guardar el telefono de la nueva persona creada 
     * @param tel Cadena que representa el telefono de la persona creada
     * @return <b>True</b> si se pudo guardar el telefono de la nueva persona en la base de datos 
     * en otro caso False
     * @throws SQLException si ocurrre algo al intentar guardar o durante el proceso de conexion
     */
    public boolean insertartTel(String tel) throws SQLException{
        String sql="Insert into telefonos_persona values('"+getRfc()+"','"+tel+"');";
        if(objConecta.insertar(sql)==true){
           return true;
       }
       return false;
    }
    
    /**
     * Metodo encargado de guardar el email de la nueva persona creada 
     * @param email Cadena que representa el email de la persona creada
     * @return <b>True</b> si se pudo guardar el email de la nueva persona en la base de datos 
     * en otro caso False
     * @throws SQLException si ocurrre algo al intentar guardar o durante el proceso de conexion
     */
    public boolean insertartEmail(String email) throws SQLException{
        String sql="INSERT INTO email_persona VALUES('"+getRfc()+"','"+email+"');";
        if(objConecta.insertar(sql)==true){
           objConecta.desconectar();
           return true;
       }
       return false;
    }

    /**
     * Metodo encargado de borrar un persona de la base de datos a tarves del 
     * rfc que obtenemos con ayuda de getRfc de la clase padre 
     * @return <b>True</b> si se pudo borrar la persona seleccionada de la base de datos 
     * en otro caso False
     * @throws SQLException si ocurrre algo al intentar guardar o durante el proceso de conexion
     */    
    public boolean deletePersona() throws SQLException{
        if(objConecta.borrarBD("DELETE FROM PERSONA WHERE rfc='"+getRfc()+"';")){
            objConecta.desconectar();
            return true;
        }
        return false;        
    }
    

    /**
     * Metodo encargado de actualizar ciertos datos particulares de una persona almacenada en la base de 
     * datos 
     * @throws SQLException si ocurrre algo al intentar guardar o durante el proceso de conexion
     */
    public void upDatePersona()throws SQLException{
        String sql="UPDATE Persona SET ciudad='"+getCiudad()+"',"
                + "colonia='"+getColonia()+"',calle='"+getCalle()+"'"
                + ",numero='"+getNumero()+"',codigo_postal='"+getCodigoPostal()+"'"
                + " WHERE rfc='"+getRfc()+"';";
        objConecta.actualizarBD(sql);         
        objConecta.desconectar();
    }
            
}
