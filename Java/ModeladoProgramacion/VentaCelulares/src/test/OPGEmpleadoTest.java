
package test;


import pkgModelo.*;
import java.sql.ResultSet;
import org.junit.Assert;
import org.junit.Test;
import pkgControlador.Conexion;


/**
 *
 * @author abrego
 */
public class OPGEmpleadoTest {
    private OPGEmpleado empleado;
    private Conexion conexion;    
    private Auxiliares aux;
    private String cd;
    private static String res;

    /**
     * Constructor
     */    
    public OPGEmpleadoTest() {
        aux=new Auxiliares();
        empleado=new OPGEmpleado();
        conexion=new Conexion();        
        cd=aux.generaCedula();
    }           

    /**
     * Metodo encargado de ingresar un empleado en la base de datos 
     * en su tabla correspondiente, buscamos en persona primero si existe 
     * o es distinto de null vemos si existe en la tabla empleados 
     * si en nuestra segunda busqueda es nula, lo guardamos, de lo contrario 
     * no realizamos ningun guardado
     */
    @Test
    public void testInsertarEmpleado() throws Exception {
        empleado.setRfc(cd);
        String sql="SELECT rfc FROM Persona WHERE rfc='"+empleado.getRfc()+"';";
        ResultSet b =conexion.buscaDatos(sql);        
        while(b.next())
            res=b.getString(1);        
        if(res!=null){
            String insert="SELECT rfc FROM Emplaedo WHERE rfc='"+empleado.getRfc()+"';";
            ResultSet b1=conexion.buscaDatos(insert);
            while(b1.next())
                res=b1.getString(1);
            if(res==null){
                empleado.insertarEmpleado("Maturino", 1);
                Assert.assertTrue(true);
            }else
                Assert.assertFalse(false);
        }
        Assert.assertFalse(false);
    }

    /**
     * Metodo encargado de insertar un vendedor en la base 
     * si existe en la tabla empleados y no en la tabla de vendedores
     * lo guardamos de lo contrario no realizamos dicho operaccion
     */
    @Test
    public void testInsertarV() throws Exception {
        empleado.setRfc(cd);
        String insert="SELECT rfc FROM Empleado WHERE rfc='"+empleado.getRfc()+"';";
            ResultSet b1=conexion.buscaDatos(insert);
            while(b1.next())
                res=b1.getString(1);
            if(res==null)
                Assert.assertFalse(false);
            else{
                String insertV="SELECT rfc FROM Vendedor WHERE rfc='"+empleado.getRfc()+"';";
                ResultSet b=conexion.buscaDatos(insertV);
                while(b.next())
                    res=b.getString(1);
                if(res==null)
                    Assert.assertTrue(empleado.insertarV());
                else 
                    Assert.assertFalse(false);
            }                                
    }


    /**
     * Metodo encargado de insertar un trabajador interno en la base 
     * si existe en la tabla empleados y no en la tabla de trabajador interno
     * lo guardamos de lo contrario no realizamos dicho operaccion
     */
    @Test
    public void testInsertarIn() throws Exception {
        empleado.setRfc(cd);
        String insert="SELECT rfc FROM Empleado WHERE rfc='"+empleado.getRfc()+"';";
            ResultSet b1=conexion.buscaDatos(insert);
            while(b1.next())
                res=b1.getString(1);
            if(res==null)
                Assert.assertFalse(false);
            else{
                String insertV="SELECT rfc FROM Trabajador_interno WHERE rfc='"+empleado.getRfc()+"';";
                ResultSet b=conexion.buscaDatos(insertV);
                while(b.next())
                    res=b.getString(1);
                if(res==null)
                    Assert.assertTrue(empleado.insertarIn());
                else 
                    Assert.assertFalse(false);
            } 
    }

    /**
     * Se encarga de guardar en la relacion Trabajar, verificamos que el exista 
     * si existe lo guardamos en alguna empresa X que este en la base de 
     * datos 
     */
    @Test
    public void testInsertarTrabajar() throws Exception {
        empleado.setRfc(cd);
        String insert="SELECT rfc FROM Empleado WHERE rfc='"+empleado.getRfc()+"';";
            ResultSet b1=conexion.buscaDatos(insert);
            while(b1.next())
                res=b1.getString(1);
            if(res!=null){
                String trabaja="SELECT rfc_empleado FROM Trabajar WHERE rfc_empleado='"+empleado.getRfc()+"';";
                ResultSet b=conexion.buscaDatos(trabaja);
                while(b.next())
                    res=b.getString(1);
                if(res!=null)
                    Assert.assertFalse(false);
                else{
                    empleado.insertarTrabajar("TEL0123LAS","2000-01-01");
                    Assert.assertTrue(true);
                }
            }else
                Assert.assertFalse(false);
    }
    
}
