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
import com.itextpdf.text.BadElementException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;


/**
 *
 * @author adriansoto
 */
public class EvaluarAlumno {
    String calificacion;
    String curso;
    pkgControl.Conexion objConecta;
    private final Properties propiedades = new Properties();
    private Session sessionMail;
    
    public EvaluarAlumno() {
        objConecta = new pkgControl.Conexion();
    }

    public String getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(String calificacion) {
        this.calificacion = calificacion;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
    
    public void calificar () {
        String sql = "UPDATE curso SET calificacion = '" + calificacion + "' WHERE id_c = '" + curso + "';";
        System.out.println(sql);
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            return;
        } catch (SQLException ex) {
            Logger.getLogger(InscribirAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Error!!");
    }
    
    public String recuperaUsuario (String id) {
        String r = "";
        String sql = "SELECT * FROM curso WHERE id_c = '" + id + "';";
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next())
                return rs.getString(8);
        } catch (SQLException ex) {
            Logger.getLogger(InscribirAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    public String recuperaNombre (String id) {
        String r = "";
        String sql = "SELECT * FROM curso WHERE id_c = '" + id + "';";
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next())
                return rs.getString(4);
        } catch (SQLException ex) {
            Logger.getLogger(InscribirAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
     
    public String recuperaProfe (String id) {
        String r = "";
        String sql = "SELECT * FROM curso WHERE id_c = '" + id + "';";
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next())
                return rs.getString(5);
        } catch (SQLException ex) {
            Logger.getLogger(InscribirAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
    }
    
    public String recuperaNivel (String id) {
        String r = "";
        String sql = "SELECT * FROM curso WHERE id_c = '" + id + "';";
        try {
            ResultSet rs = objConecta.buscaDatos(sql);
            if(rs.next())
                return rs.getString(2);
        } catch (SQLException ex) {
            Logger.getLogger(InscribirAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return r;
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
    
	private void generaConstancia() throws FileNotFoundException, DocumentException, BadElementException, IOException
	{
		FileOutputStream archivo = new FileOutputStream("C:\\Users\\Armando\\Desktop\\constancia.pdf");
		Document documento = new Document();
                String nombre = recuperaNombre(curso);
                String calif = calificacion;
                String nivel = recuperaNivel(curso);
                String profe = recuperaProfe(curso);
                Paragraph titulo, parrafo, parrafo2, parrafo3;
                Image logo = Image.getInstance("C:\\Users\\Armando\\Desktop\\img2.png");
		PdfWriter.getInstance(documento, archivo);
                titulo = new Paragraph("¡Certificacion!\n\n\n");
                titulo.setAlignment(Element.ALIGN_CENTER);
                parrafo = new Paragraph("Por medio de la presente y a solicitud de parte interesada, hacemos constar que el usuario " + nombre + " ha acreditado " +
                        "con calificacion de " + calif + " en el nivel: " + nivel + " impartido por el profesor: " + profe + "\n\n\n");
                parrafo2 = new Paragraph("La Escuela Harmon Hell se enorgullece de constatar este gran logro.");
		documento.open();
                documento.add(logo);
                documento.add(titulo);
		documento.add(parrafo);
		documento.add(parrafo2);
		documento.close();
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

    private void sendEmail(){
        String correo = recuperarCorreo(recuperaUsuario(curso));
        try {
            generaConstancia();
            MimeMessage message = new MimeMessage(sessionMail);
            message.setFrom(new InternetAddress((String) propiedades.get("mail.smtp.mail.sender")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            message.setSubject("Calificacion y Certificado");
            Transport t = sessionMail.getTransport("smtp");
            t.connect((String) propiedades.get("mail.smtp.user"), "1234harmonhell");
            BodyPart texto = new MimeBodyPart();   
            BodyPart adjunto = new MimeBodyPart();
            MimeMultipart multiParte = new MimeMultipart();
            if(Integer.parseInt(calificacion) > 5){
                texto.setText("Estimado " + recuperaUsuario(curso) + " tu actual profesor te ha evaluado" + 
                    " con calificacion de " + calificacion + ". Aqui mismo puedes descargar tu constancia del curso." );
                adjunto.setDataHandler(new DataHandler(new FileDataSource("C:\\Users\\Armando\\Desktop\\constancia.pdf")));
                adjunto.setFileName("Constancia.pdf");
                multiParte.addBodyPart(adjunto);
            }else{
                texto.setText("Estimado " + recuperaUsuario(curso) + " no has aprobado el curso.");
            }
            multiParte.addBodyPart(texto);
            message.setContent(multiParte);
            t.sendMessage(message, message.getAllRecipients());
            t.close();
        } catch (MessagingException ex) {
            Logger.getLogger(RecuperarContrasena.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EvaluarAlumno.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(EvaluarAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public void enviarCorreo() {
        initMail();
        sendEmail();
    }
}
