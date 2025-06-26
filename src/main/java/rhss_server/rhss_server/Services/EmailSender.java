package rhss_server.rhss_server.Services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

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
     int legajo, String causa, long novedad_id) {
        final String novedadLink = frontUrl+"/Novedad/"+novedad_id;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailSys);
        message.setTo(to);
        message.setSubject("Novedad "+cate+" - "+numero);
        message.setText("Nueva novedad creada en el dia "+LocalDate.now()+". De categoria "+cate+" vinculado al legajo "+legajo+".\nCausa o Descripcion:\n"+causa+noReplay);
        mailSender.send(message);
        SimpleMailMessage message2 = new SimpleMailMessage();
        message2.setFrom(emailSys);
        message2.setTo(emailRRHH);
        message2.setSubject("Novedad "+cate+" - "+numero);
        message2.setText("Nueva novedad creada en el dia "+LocalDate.now()+". De categoria "+cate+" vinculado al legajo "+legajo+".\nCausa o Descripcion:\n"+causa+"\nLink: "+novedadLink+noReplay);
        mailSender.send(message2);
    }

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
}
