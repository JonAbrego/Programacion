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
public class SolicitarCurso {
    String nivel;
    String horario;
    String profesor;
    String alumno;
    String usr_alumno;
    pkgControl.Conexion objConecta;
    private final Properties propiedades = new Properties();
    private Session sessionMail;

    public SolicitarCurso() {
        objConecta = new pkgControl.Conexion();
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getUsr_alumno() {
        return usr_alumno;
    }

    public void setUsr_alumno(String usr_alumno) {
        this.usr_alumno = usr_alumno;
    }
    
    
    
    public String recuperaUsuario (String profesor) {
        String r = "";
        String sql = "SELECT * FROM curso WHERE profesor = '" + profesor + "'";
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next()) {
                //System.out.println(rs.getString(7));
                return rs.getString(7);
            } else {
                return "Fallo la busqueda de datos";
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitarCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    public String recuperaCorreo (String usuario) {
        String r = "";
        String sql = "SELECT * FROM persona, profesor WHERE persona.nombredeusuario = profesor.nombredeusuario AND profesor.nombredeusuario = '" + usuario + "';";
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next()) {
                //System.out.println(rs.getString(5));
                return rs.getString(5);
            } else {
                return "Fallo la busqueda de datos";
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitarCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
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
        String correo = recuperaCorreo(recuperaUsuario(profesor));
        try {
            MimeMessage message = new MimeMessage(sessionMail);
            message.setFrom(new InternetAddress((String) propiedades.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            message.setSubject("Solcitud de curso");
            message.setText("Estimado Profesor " + profesor + " de Harmon Hell\n\n"
                    + "El alumno " + alumno + " " + obtenerAp(usr_alumno) + " con nombre de usuario " + usr_alumno
                    + " solicita inscripcion en su curso de nivel " + nivel +", con horario de "
                    + horario + "."
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
    
    public String obtenerAp (String user) {
        String r = "";
        String sql = "SELECT * FROM persona WHERE nombredeusuario = '" + user + "';";
        System.out.println(sql);
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next()) {
                //System.out.println(rs.getString(5));
                return rs.getString(3);
            } else {
                return "Fallo la busqueda de datos";
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitarCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
}
