package pkgModelo;

import java.sql.SQLException;

/**
 * Clase usada para obtener los datos 
 * a guardar en la entidad empleado y sus 
 * relaciones correspondientes
 * @author abrego
 */
public class OPGEmpleado extends Persona {
     pkgControlador.Conexion objConecta;

    /**
     * Constructor 
     */
    public OPGEmpleado() {
        objConecta = new pkgControlador.Conexion();
    }
     
    /**
     * Metodo usado para guardar a un nuevo empleado     
     * @param turno cadena que sera el turno en el que trabajara el empleado
     * @param sueldo entero que respresenta el sueldo del nuevo empleado
     * @return <b>true</b> si se pudo guardar el nuevo empleado
     * de lo contrario retorna <b>false</b>
     * @throws SQLException 
     */
    public boolean insertarEmpleado(String turno,int sueldo) throws SQLException{
        String sql="INSERT INTO Empleado VALUES('"+getRfc()+"',"+sueldo+",'"+turno+"');";
        if(objConecta.insertar(sql)==true){     
            return true;
        }        
        return false;
    }
    
    /**
     * Metodo usado para insertar un nuevo vendedor en la base 
     * de datos 
     * @return <b>true</b> si se pudo guardar el nuevo vendedor
     * de lo contrario retorna <b>false</b>
     * @throws SQLException 
     */
    public boolean insertarV() throws SQLException{
        String sql="INSERT INTO Vendedor VALUES('"+getRfc()+"');";
        if(objConecta.insertar(sql)==true){
            objConecta.desconectar();
            return true;
        }        
        return false;
    }
    
    /**
     * Metodo usado para guardar un nuevo trabajador interno 
     * en la base de datos 
     * @return <b>true</b> si se pudo guardar el nuevo interno
     * de lo contrario retorna <b>false</b>
     * @throws SQLException 
     */
    public boolean insertarIn() throws SQLException{
        String sql="INSERT INTO Trabajador_Interno VALUES('"+getRfc()+"');";
        if(objConecta.insertar(sql)==true){
            objConecta.desconectar();
            return true;
        }        
        return false;
    }
    
    /**
     * Metodo usado para asignar la empresa correspondiente del 
     * nuevo trabajador agregado a la base de datos 
     * @param rfcEmpresa cadena que respresenta el rfc de la empresa donde trabajara el nuevo 
     * empleado ingresado
     * @param date cadena que representara la fecha en que fue contratado el empelado
     * @return <b>true</b> si se pudo guardar guardar la nueva relacion
     * de lo contrario retorna <b>false</b>
     * @throws SQLException 
     */
    public boolean insertarTrabajar(String rfcEmpresa, String date)  throws SQLException{
        String sql="INSERT INTO Trabajar VALUES('"+rfcEmpresa+"','"+getRfc()+"','"+date+"');";
        if(objConecta.insertar(sql)==true){
            return true;
        }        
        return false;
    }
    
    /**
     * Metodo encargado de actualizar ciertos datos particulares de un empleado almacenado en la base de 
     * datos 
     * @throws SQLException si ocurrre algo al intentar guardar o durante el proceso de conexion
     */
    public void upDatePersona(String turno,int sueldo)throws SQLException{
        String sql="UPDATE Empleado SET turno='"+turno+"',sueldo="+sueldo+" WHERE rfc='"+getRfc()+"';";
        objConecta.actualizarBD(sql);         
        objConecta.desconectar();
    }
}
