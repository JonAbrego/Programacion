package pkgModelo;

import java.sql.SQLException;

/**
 * Clase usada para modelar las acciones que puede realizar 
 * empresa dentro de la base de datos 
 * @author abrego
 */
public class OPGEmpresa extends Empresa {
    pkgControlador.Conexion objConecta;

    /**
     * Constructor
     */
    public OPGEmpresa() {
        objConecta = new pkgControlador.Conexion();
    }
    
    /**
     * Metodo encargado de insertar una nueva empresa en 
     * la base de datos, obteniendo los datos de la empresa con 
     * ayuda de los getters de la clase padre Empresa
     * @return <b>True</b> si se pudo guardar la empresa la nueva empresa en la 
     * base de datos 
     * @throws SQLException si ocurre algo durante el guardado
     */
    public boolean insertar() throws SQLException{       
       String sql="INSERT INTO Empresa VALUES('"+getRfc()+"','"+getNombre()+"','"+getCiudad()+"','"+getColonia()
               +"','"+getCalle()+"','"+getNumero()+"','"+getCodigoPostal()+"','"+getUrl()+"');";
       if(objConecta.insertar(sql)==true){
           objConecta.desconectar();
           return true;
       }
       return false;
    }
    
    /**
     * Metodo encargado de guardar el telefono de la nueva emrpesa en
     * en la base de datos
     * @param tel Cadena que representa el telefono de la nueva empresa creada 
     * @return <t>True</t> si se pudo guardar el telefono de la nueva empresa
     * @throws SQLException si ocurre algo durante el guardado
     */
    public boolean insertartTel(String tel) throws SQLException{
        String sql="Insert into telefonos_empresa values('"+getRfc()+"','"+tel+"');";
        if(objConecta.insertar(sql)==true){
           return true;
       }
       return false;
    }
    
    /**
     * Metodo encargado de guardar el email de la nueva emrpesa en
     * en la base de datos
     * @param ema Cadena que representa el email de la nueva empresa creada 
     * @return <t>True</t> si se pudo guardar el email de la nueva empresa
     * @throws SQLException si ocurre algo durante el guardado
     */
    public boolean insertartEmail(String ema) throws SQLException{
        String sql="Insert into email_empresa values('"+getRfc()+"','"+ema+"');";
        if(objConecta.insertar(sql)==true){
           objConecta.desconectar();
           return true;
       }
       return false;
    }

    /**
     * Metodo encargado de borrar alguna empresa que se encuentre en nuestra base de datos
     * este borrado se hace apartir del rfc de la empresa, el cual es obtenido por medio 
     * del getRfc() del la clase padre
     * @return <b>True</b> si se pudo borrar la empresa seleccionada de la base de datos 
     * @throws SQLException si ocurre algo durante el borrado de la empresa
     */    
    public boolean deleteEmpresa() throws SQLException{
        if(objConecta.borrarBD("DELETE FROM Empresa WHERE rfc='"+getRfc()+"';")){
            objConecta.desconectar();
            return true;
        }
        return false;        
    }
    
    /**
     * Metodo encargado de actualizar algunos de los datos particulares de alguna 
     * empresa en nuestra base de datos 
     * @throws SQLException si ocurre algo durante la actualizacion de los datos de la empresa 
     */
    public void upDate()throws SQLException{
        String sql="UPDATE  Empresa SET Ciudad='"+getCiudad()+"',"
                + "Nombre='"+getNombre()+"',Colonia='"+getColonia()+"',calle='"+getCalle()+"'"
                + ",numero='"+getNumero()+"',codigo_postal='"+getCodigoPostal()+
                "' where rfc='"+getRfc()+"';";
        objConecta.actualizarBD(sql);         
        objConecta.desconectar();
    }        
}
