package rhss_server.rhss_server.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSender {
    private final String noReplay = "\nNo responder este correo.";
    @Value("${EMAIL_RRHH}")
    private String emailRRHH;
    @Value("${EMAIL_USERNAME}")
    private String emailSys;
    @Value("${FRONT_URL}")
    private String frontUrl;
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailNewNovedad(String to, String numero, String cate,
     int legajo, String causa, long novedad_id, List<MultipartFile> adjuntos) {
        final String novedadLink = frontUrl+"/Novedad/"+novedad_id;
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessage mimeMessage2 = mailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            MimeMessageHelper message2 = new MimeMessageHelper(mimeMessage2, true, "UTF-8");
            message.setFrom(emailSys);
            message.setTo(to);
            message.setSubject("Novedad "+cate+" - "+numero);
            message.setText("Nueva novedad creada en el dia "+LocalDate.now()+". De categoria "+cate+" vinculado al legajo "+legajo+".\nCausa o Descripcion:\n"+causa+noReplay);
            mailSender.send(mimeMessage);
            if(adjuntos != null){
                System.err.println("Archivos existen");
                for (MultipartFile archivo : adjuntos) {
                    final String name = archivo.getOriginalFilename();
                    if(name != null && !name.isBlank()){
                        message2.addAttachment(name, archivo);
                    }

                }
            }
            message2.setFrom(emailSys);
            message2.setTo(emailRRHH);
            message2.setSubject("Novedad "+cate+" - "+numero);
            message2.setText("Nueva novedad creada en el dia "+LocalDate.now()+". De categoria "+cate+" vinculado al legajo "+legajo+".\nCausa o Descripcion:\n"+causa+"\nLink: "+novedadLink+noReplay);
            mailSender.send(mimeMessage2);
            
        } catch (Exception e) {
            System.err.println("No se pudo enviar el mail: " + e.getMessage());
        }

    }

    @Async
    public void sendEmailCloseNovedad(String to, String numero,String cate,LocalDate createdDate) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailSys);
        message.setTo(to);
        message.setSubject("Novedad "+cate+" - "+numero);
        message.setText("La novedad creada en la fecha "+createdDate+" fue cerrada el "+LocalDate.now()+"."+noReplay);
        mailSender.send(message);
        SimpleMailMessage message2 = new SimpleMailMessage();
        message2.setFrom(emailSys);
        message2.setTo(emailRRHH);
        message2.setSubject("Novedad "+cate+" - "+numero);
        message2.setText("La novedad creada en la fecha "+createdDate+" fue cerrada el "+LocalDate.now()+"."+noReplay);
        mailSender.send(message2);
    }

    @Async
    public void sendEmailReopenNovedad(String to, String numero,String cate,LocalDate createdDate) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailSys);
        message.setTo(to);
        message.setSubject("Novedad "+cate+" - "+numero);
        message.setText("La novedad creada en la fecha "+createdDate+" fue reabierta el "+LocalDate.now()+"."+noReplay);
        message.setCc(emailRRHH);
        mailSender.send(message);
        SimpleMailMessage message2 = new SimpleMailMessage();
        message2.setFrom(emailSys);
        message2.setTo(emailRRHH);
        message2.setSubject("Novedad "+cate+" - "+numero);
        message2.setText("La novedad creada en la fecha "+createdDate+" fue reabierta el "+LocalDate.now()+"."+noReplay);
        mailSender.send(message2);
    }
    @Async
    public void sendEmailActionNovedad(String to, String numero,String cate,LocalDate createdDate, String accion, String info, long novedad_id) {
        final String novedadLink = frontUrl+"/Novedad/"+novedad_id;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailSys);
        message.setTo(to);
        message.setSubject("Novedad "+cate+" - "+numero);
        message.setText("Se tomo una nueva accion "+accion+" en la novedad creada el "+createdDate+".\nInformacion Adicional:\n"+info+noReplay);
        mailSender.send(message);
        SimpleMailMessage message2 = new SimpleMailMessage();
        message2.setFrom(emailSys);
        message2.setTo(emailRRHH);
        message2.setSubject("Novedad "+cate+" - "+numero);
        message2.setText("Se tomo una nueva accion "+accion+" en la novedad creada el "+createdDate+".\nInformacion Adicional:\n"+info+"\nLink: "+novedadLink+noReplay);
        mailSender.send(message2);
    }
    @Async
    public void sendEmailRegister(String to, LocalDate createdDate, String username, String pass) {
        final String loginLink = frontUrl+"/login";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailSys);
        message.setTo(to);
        message.setSubject("Usuario Registrado para el Sistema Gestor de Novedades (SGN)");
        message.setText("Se creo un nuevo usuario vinculado con este correo electronico:\nNombre de usuario: "+username+"\nContraseña: "+pass+"\nFecha de registracion: "+createdDate+"\nPuedes acceder al sistema desde el siguiente link: "+loginLink+"\n"+noReplay);
        mailSender.send(message);
    }
}
