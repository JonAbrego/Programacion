package pkgModelo;

import java.sql.SQLException;

/**
 * Clase encargada de las operaciones elementales en una base de 
 * datos para un servicio
 * @author abrego
 */
public class OPGServicio extends Servicio {
    pkgControlador.Conexion objConecta;

    /**
     * Constructor  
     */
    public OPGServicio() {
        objConecta = new pkgControlador.Conexion();
    }
    
    /**
     * Metodo encargado de guardar en la base de datos un nuevo servicio, donde lo unico 
     * que se pide es la descripcion del servicio nuevo a almacenar
     * @return <b>True</b> si se pudo guardar el nuevo servicio en la base de datos
     * en otro caso False
     * @throws SQLException si ocurrre algo al intentar guardar o durante el proceso de conexion
     */
    public boolean insertar() throws SQLException{    
        String sql="INSERT INTO Servicio('descripcion') VALUES('"+getDescricion()+"');";
        if(objConecta.insertar(sql)==true){
           objConecta.desconectar();
           return true;
       }
       return false;
    }
    
    /**
     * Metodo encargado o usado para borra algun servicio de la base de datos 
     * @return <b>True</b> si se pudo borrar el servicio seleccionado de la  base de datos
     * en otro caso False
     * @throws SQLException si ocurrre algo al intentar borrar o durante el proceso de conexion
     */
    public boolean deleteServicio(String id) throws SQLException{
        if(objConecta.borrarBD("DELETE FROM Servicio WHERE id='"+id+"';")){
            objConecta.desconectar();
            return true;
        }
        return false;        
    }
    
    /**
     * Metodo usado para actualiza la informacion de un nuevo servicio almacenado 
     * en la base de datos
     * @throws SQLException si ocurrre algo al intentar guardaractualizar o durante el proceso de conexion
     */
    public void upDate(String id)throws SQLException{
        String sql="UPDATE Servicio SET descripcion='"+getDescricion()+"'"
                + "WHERE id='"+id+"';";
        objConecta.actualizarBD(sql);         
        objConecta.desconectar();
    }        
}
