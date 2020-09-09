/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pkgModelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import pkgControl.Conexion;

/**
 *
 * @author adriansoto
 */
public class RecuperarContrasena {
    String correo;
    pkgControl.Conexion objConecta;
    private final Properties propiedades = new Properties();
    private Session sessionMail;

    public RecuperarContrasena() {
        objConecta = new pkgControl.Conexion();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    
    public String buscaUsuario (String busqueda) {
        //super.setUsuario(busqueda);
        String sql = "SELECT *\n" +
                     "FROM Persona\n" +
                     "WHERE correo = '" + getCorreo() + "'";
        //System.out.println(sql);
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next()) {
                return rs.getString(1);
            } else {
                return "Fallo la busqueda de datos";
            }
        } catch (SQLException ex) {
            System.out.println("Algo malo paso!!");
        }
        return "Fallo la busqueda de datos";
        
    }
    
    public String buscaPass (String busqueda) {
        //super.setUsuario(busqueda);
        String sql = "SELECT *\n" +
                     "FROM Persona\n" +
                     "WHERE correo = '" + getCorreo() + "'";
        //System.out.println(sql);
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next()) {
                return rs.getString(6);
            } else {
                return "Fallo la busqueda de datos";
            }
        } catch (SQLException ex) {
            System.out.println("Algo malo paso!!");
        }
        return "Fallo la busqueda de datos";   
    }
    private void initMail() {
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.port", 587);
        propiedades.put("mail.smtp.mail.sender", "info.harmonhell@gmail.com");
        propiedades.put("mail.smtp.user", "info.harmonhell@gmail.com");
        propiedades.put("mail.smtp.auth", "true");

        sessionMail = Session.getDefaultInstance(propiedades);
        sessionMail.setDebug(true);
    }

    private void sendEmail() {
        try {
            MimeMessage message = new MimeMessage(sessionMail);
            message.setFrom(new InternetAddress((String) propiedades.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            message.setSubject("Recuperacion de Cuenta");
            message.setText("Estimado usuario de Harmon Hell\n\n"
                    + "Tu usuario es: " + buscaUsuario(correo)
                    + "\nTu contraseña es: " + buscaPass(correo)
                    + "\n\nGracias!");
            Transport t = sessionMail.getTransport("smtp");
            t.connect((String) propiedades.get("mail.smtp.user"), "1234harmonhell");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException ex) {
            Logger.getLogger(RecuperarContrasena.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void enviarCorreo() {
        initMail();
        sendEmail();
    }
}