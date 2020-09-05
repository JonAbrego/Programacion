package pkgModelo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Calse con el proposito de tener metodos auxiliares para usar 
 * en otras clases
 * @author abrego
 */
public class Auxiliares {    
    
    Random rnd=new Random();
    
    /**
     * Métodos que nos ayudara para realizar las consultas no triviales 
     * en lenguaje SQL
     * @param i la consulta elegida 
     * @return una cadena que sera la consulta seleccionada 
     */
    public String consultas(int i){
        switch (i){
           case 0:
	       return "SELECT RFC_Vendedor,max(Ventas)Ventas,Num_ventas FROM\n" +
                       "(SELECT t1.rfc_vendedor,count(rfc_vendedor)Num_Ventas,sum(Pago)Ventas \n" +
                       "FROM Comprar,\n" +
                       "(SELECT imei, rfc_vendedor FROM Vender)AS t1\n" +
                       "WHERE Comprar.imei=t1.imei\n" +
                       "GROUP BY rfc_vendedor)AS t2;";
            case 1:
                return "SELECT Marca,max(Numero_ventas)Ventas FROM\n" +
                       "(SELECT count(Marca)Numero_ventas,Marca \n" +
                       "FROM Celular\n" +
                       "GROUP BY Marca)AS T1;";       
            case 2:
                return "SELECT rfc_empresa,max(num_ventas) FROM \n" +
                       "(SELECT rfc_empresa,(count(rfc_empresa)*ventas)num_ventas FROM Trabajar,\n" +
                       "(SELECT rfc_vendedor,count(rfc_vendedor)ventas \n" +
                       "FROM Vender\n" +
                       "GROUP BY rfc_vendedor) AS t1\n" +
                       "WHERE t1.rfc_vendedor=Trabajar.rfc_empleado\n" +
                       "GROUP BY rfc_empresa)AS t2";
	    case 3:
		return "SELECT imei,T1.rfc,pago FROM Comprar,\n"+
                        "(SELECT rfc FROM Cliente\n"+
                        "EXCEPT SELECT rfc FROM Persona) AS T1\n"+
                        "WHERE Comprar.rfc=T1.rfc;";
	    case 4:		
		return "SELECT Persona.rfc,nombre,apellido_p,apellido_m,MaximoSueldo,rfc_empresa FROM Persona,\n"+
                        "(SELECT rfc, max(Sueldo)MaximoSueldo,rfc_empresa FROM Empleado,\n"+
                        "(SELECT rfc_empleado,rfc_empresa FROM Trabajar) AS T1\n"+
                        "WHERE Empleado.rfc=T1.rfc_empleado\n"+
                        "GROUP BY rfc_empresa) AS T1\n"+
		        "WHERE Persona.rfc=T1.rfc;";
            default:
                return " ";
                
        }    
    }
    
    /**
     * Metodo para identificar si una cadena contiene la palabra 
     * DROP
     * @param sql cadena en lenguaje SQL
     * @return <b>true</b> si la cadena que le pasamos contiene la palabra DROP
     */
    public boolean palabraDrop(String sql){
        Pattern pat = Pattern.compile(".*[dD][rR][oO][pP].*");
        Matcher mat = pat.matcher(sql);
        if (mat.matches()) 
             return true;
        return false;
    }
    
    /**
     * Metodo usado para verificar si el precio 
     * ingresado corresponde a una secuencia de numeros
     * enteros
     * @param pago cadena ingresada 
     * @return <b>true</b> si la cadena es valida en el patron
     */
    public boolean checaPrecio(String pago){
        Pattern pat=Pattern.compile("\\d*");
        Matcher mat = pat.matcher(pago);
        if(mat.matches())
            return true;
        return false;
    }
    
    /**
     * Metodos usador para verificar si una cadena 
     * tiene la forma esperada con la que se escribe 
     * con los nombres 
     * @param nombreC 
     * @return <b>true</b> si la cadena inicia con una mayuscula, 
     * seguida de una secuencia de letra
     */
    public boolean  chechaNombreC(String nombreC){
        Pattern pat=Pattern.compile("[A-Z][a-z]+//s?");
        Matcher mat = pat.matcher(nombreC);
        if(mat.matches())
            return true;
        return false;
    }
    
    /**
     * Metodo usado para verificar si el rfc ingresado 
     * contiene el numero adecuado de numeros y letras  
     * @param rfc cadena compuesta de letras y numeros
     * @return <b>true</b> si la cadena ingresada corresponde 
     * al patron designado
     */
    public boolean checaRFC(String rfc){
        Pattern pat=Pattern.compile("[A-Z]{4,4}[0-9]{6,6}[A-Z]{3,4}");
        Matcher mat = pat.matcher(rfc);
        if(mat.matches())
            return true;
        return false;
    }
    
    /**
     * Metodo encargado de verificar que el correo tenga el formato mas 
     * comun de los emails
     * @param email cadena 
     * @return <b>true</b> si la cadena ingresada corresponde 
     * al patron designado
     */
    public boolean checaCorreo(String email){
        Pattern pat=Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mat = pat.matcher(email);
        if(mat.matches())
            return true;
        return false;
    }
    
    /**
     * Metodo usado para verificar que la cadena tenga 
     * la longitud y formato adecuado
     * @param tel
     * @return <b>true</b> si la cadena ingresada corresponde 
     * al patron designado
     */
    public boolean checaTelefono(String tel){
        Pattern pat=Pattern.compile("[0-9]{8,10}");
        Matcher mat = pat.matcher(tel);
        if(mat.matches())
            return true;
        return false;
    }
    
    /**
     * Metodo usado para verificar que el imei de un
     * celular ingresado tenga la longitud y patron 
     * correspondiente
     * @param imei
     * @return <b>true</b> si la cadena ingresada corresponde 
     * al patron designado
     */
    public boolean checaIMEI(String imei){
        Pattern pat=Pattern.compile("[0-9]{1,16}");
        Matcher mat = pat.matcher(imei);
        if(mat.matches())
            return true;
        return false;
    }
    
    /**
     * Metodos usado para hacer un llenado de 
     * los box usados en las vistas apartir de la 
     * informacion extraida (de una consulta SQL) 
     * de la base de datos
     * @param box 
     * @param sql Sentencia en lenguaje SQL usada para hacer la consulta
     * @throws SQLException si la busqueda es hecha erroneamente
     */
    @SuppressWarnings("unchecked")
    public void llenaBox(javax.swing.JComboBox box,String sql) throws SQLException{
        pkgControlador.Conexion con=new pkgControlador.Conexion();
        ResultSet rs=con.buscaDatos(sql);
        while(rs.next())
            box.addItem(rs.getString(1));    
        con.desconectar();
    }
    
    /**
     * Metodo encargado de seleccionar algun sevicio de la lista de forma 
     * aleatoria
     * @return Cadena que sera el servicio seleccionado
     */
    public String seleccionaServicio(){
        String [] marca = {"1","2","3","4","5","6","7","8","9"};
        String cad = null;
            cad = marca[(int)(rnd.nextDouble()*(marca.length-1))];        
        return cad;
    }


    /**
     * Metodo encargado de generar un rfc a partir de la lista, de forma 
     * aleatoria
     * @return Cadena que sera el rfc generado
     */ 
    public String generaCedula(){
        String [] cara = {"A","B","C","D","E","F","G","H","I","J","K","L", "1",
            "2","3","4","5","6","7","8","9","0",
            "M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
        String cad = "";
        for(int i = 0; i < 10; i++){
            cad += cara[(int)(rnd.nextDouble()*(cara.length-1))];
        }
        return cad;
    }
    
   /**
     * Metodo encargado de seleccionar una marca a partir de la lista, de forma 
     * aleatoria
     * @return Cadena que sera la marca seleccionada
     */ 
    public String generaMarca(){
        String [] marca = {"Iphone","Sony","LG","ALCATEL","Nokia"};
        String cad = "";
            cad += marca[(int)(rnd.nextDouble()*(marca.length-1))];        
        return cad;
    }
}
