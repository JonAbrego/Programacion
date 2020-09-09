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
public class InscribirAlumno {
    String alumno;
    String curso;
    pkgControl.Conexion objConecta;
    private final Properties propiedades = new Properties();
    private Session sessionMail;

    public InscribirAlumno() {
        objConecta = new pkgControl.Conexion();
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    public void inscribirAlumno () {
        String sql = "UPDATE curso SET usr_alumno = '" + alumno + "' WHERE id_c = '" + curso + "';\n"
                + "UPDATE curso SET elegido = 'si' WHERE id_c = '" + curso + "';\n"
                + "UPDATE curso SET alumno = '" + nombreCompleto(alumno) + "' WHERE id_c = '" + curso + "'\n";
        //System.out.println(sql);
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            return;
        } catch (SQLException ex) {
            Logger.getLogger(InscribirAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Error!!");
    }
    
    public String recuperarCorreo (String alumno) {
        String sql = "SELECT * FROM persona, alumno WHERE persona.nombredeusuario = alumno.nombredeusuario AND alumno.nombredeusuario = '" + alumno + "';";
        //System.out.println(sql);
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next())
                return rs.getString(5);
        } catch (SQLException ex) {
            Logger.getLogger(InscribirAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "error!";
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
        String correo = recuperarCorreo(alumno);
        try {
            MimeMessage message = new MimeMessage(sessionMail);
            message.setFrom(new InternetAddress((String) propiedades.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            message.setSubject("Confirmacion de Inscripcion");
            message.setText("Estimado Alumno " + alumno + " de Harmon Hell\n\n"
                    + "Ha sido inscrito en el curso del profesor " + obtenerProfesor(curso) + ", con horario " + obtenerHorario(curso)
                    + ", y nivel " + obtenerNivel(curso) + "." + "\n\nGracias!");
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
    
    public String obtenerProfesor (String user) {
        String r = "";
        String sql = "SELECT profesor FROM curso WHERE id_c = '" + user + "';";
        System.out.println(sql);
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next()) {
                //System.out.println(rs.getString(5));
                return rs.getString(1);
            } else {
                return "Fallo la busqueda de datos";
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitarCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    public String obtenerHorario (String user) {
        String r = "";
        String sql = "SELECT horario FROM curso WHERE id_c = '" + user + "';";
        System.out.println(sql);
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next()) {
                //System.out.println(rs.getString(5));
                return rs.getString(1);
            } else {
                return "Fallo la busqueda de datos";
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitarCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    public String obtenerNivel (String user) {
        String r = "";
        String sql = "SELECT nivel FROM curso WHERE id_c = '" + user + "';";
        //System.out.println(sql);
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next()) {
                //System.out.println(rs.getString(5));
                return rs.getString(1);
            } else {
                return "Fallo la busqueda de datos";
            }
        } catch (SQLException ex) {
            Logger.getLogger(SolicitarCurso.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    public String nombreCompleto (String user) {
        String r = "";
        String sql = "SELECT nombres, ap_pat FROM persona WHERE nombredeusuario = '" + user + "'";
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next())
                return rs.getString(1) + " " + rs.getString(2);
        } catch (SQLException ex) {
            Logger.getLogger(InscribirAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
}
