package pkgModelo;

import java.sql.SQLException;

/**
 * Clase que se encarga de las operaciones (insertar,actualizar, borrar)
 * basicas de la entidad celular
 * @author abrego
 */
public class OPGCelular extends Celular {
    private pkgControlador.Conexion objConecta;

    /**
     * Constructor donde se realiza la conexion a la base 
     * de datos
     */
    public OPGCelular() {
        objConecta=new pkgControlador.Conexion();
    }
    
    /**
     * Metodo usado para insertar una nueva tupla en la 
     * base de datos en la tabla <b>Celular</b>
     * @return <b>true</b> si se pudo insertar la tupla 
     * correspondiente en la base de datos
     * @throws SQLException si no se pudo insertar 
     * la tupla requerida
     */
    public boolean insertar() throws SQLException{
       String sql="INSERT INTO Celular VALUES('"+getImei()+"','"+getMarca()+"','"+getModelo()+"','"+getDescripcion()+"');";
       if(objConecta.insertar(sql)==true){
           objConecta.desconectar();
           return true;
       }
       return false;
    }
    
    /**
     * Metodo usado para borrar alguna tupla 
     * de de la tabla celualr 
     * @return <b>true</b> si se pudo borrar la tupla 
     * elegida de la tabla correspondiente 
     * @throws SQLException si ocurre algo malo al intentar 
     * borrar la tupla seleccionada
     */
    public boolean deleteCelular() throws SQLException{
        String sql="DELETE FROM Celular WHERE imei='"+getImei()+"';";
        if(objConecta.borrarBD(sql)){
            objConecta.desconectar();
            return true;
        }
        return false;        
    }
    
    /**
     * Metodo usado para realizar alguna actualizacion respecto 
     * a la descripcion del celular elegido 
     * @throws SQLException si sucede algo malo durante la 
     * actualizacion del objeto(celular)
     */
    public void upDate()throws SQLException{
        String sql="UPDATE Celular SET marca='"+getMarca()+""
                + "',modelo='"+getModelo()+"',descripcion='"+getDescripcion()+"'"
                + "WHERE imei='"+getImei()+"';";
        objConecta.actualizarBD(sql);         
        objConecta.desconectar();
    }        
    
}
