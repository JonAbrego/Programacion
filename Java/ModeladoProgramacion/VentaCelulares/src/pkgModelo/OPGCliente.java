package pkgModelo;

import java.sql.SQLException;

/**
 * Clase que se encarga de las operaciones (insertar,actualizar, borrar)
 * basicas de la entidad Cliente
 * @author abrego
 */
public class OPGCliente extends Persona{
    pkgControlador.Conexion objConecta;

    /**
     * Constructor 
     */
    public OPGCliente() {
        objConecta = new pkgControlador.Conexion();
    }
    
    /**
     * Metodos usado para insertar un nuevo cliente en 
     * la base de datos      
     * @return <b>true</b> si se pudo guardar el nuevo cliente 
     * de lo contrario retorna <b>false</b>
     * @throws SQLException si sucede algo durante 
     * el guardado del nuevo cliente
     */
    public boolean insertarCliente() throws SQLException{
        String sql="INSERT INTO Cliente VALUES('"+getRfc()+"');";
        if(objConecta.insertar(sql)==true){     
            return true;
        }        
        return false;
    }
    
    /**
     * Metodo usado para guardar la informacion en relacion <b>comprar</b> en la base de datos 
     * respecto a la venta de un celular 
     * @param imei cadena que representa el imei del celular comprado por el cliente
     * @param serv cadena que sera el servicio elegido por el cliente
     * @param fecha cadena que representa la fecha de compra del cliente
     * @param sueldo entero que representara el monto a pagar por el cliente
     * @return <b>true</b> si se pudo guardar la compra
     * de lo contrario retorna <b>false</b>
     * @throws SQLException 
     */
    public boolean insertarCompra(String imei,String serv,String fecha,int sueldo) throws SQLException{
        String sql="INSERT INTO Comprar VALUES('"+imei+"','"+getRfc()+"','"+serv+"','"+fecha+"',"+sueldo+");";
        if(objConecta.insertar(sql)==true){     
            return true;
        }        
        return false;
    } 
    
    /**
     * Metodos usado para guardar la informacion en la relacion
     * <b>Vender</b>, de la venta hecha por 
     * el vendedor del celular comprado por el cliente
     * @param imei cadena que representa el imei vendido por el vendedor 
     * @param rfcV cadena que representa el rfc del vendedor que hizo la venta 
     * @param comision flotante que sera la comision que recibe el vendedor por la venta hecha 
     * @return <b>true</b> si se pudo guardar la venta realizada
     * de lo contrario retorna <b>false</b>
     * @throws SQLException 
     */
    public boolean insertarVender(String imei,String rfcV,float comision) throws SQLException{
        String sql = "INSERT INTO Vender VALUES('"+imei+"','"+rfcV+"',"+comision+");";
        if(objConecta.insertar(sql)==true){     
           objConecta.desconectar();
            return true;
        }        
        return false;
    }
}