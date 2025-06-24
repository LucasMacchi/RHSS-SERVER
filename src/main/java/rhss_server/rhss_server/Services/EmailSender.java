package rhss_server.rhss_server.Services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
    @Value("${EMAIL_RRHH}")
    private String emailRRHH;
    @Value("${EMAIL_USERNAME}")
    private String emailSys;
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmailNewNovedad(String to, String numero, String cate,
     int legajo, String causa) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailSys);
        message.setTo(to);
        message.setSubject("Novedad "+cate+" - "+numero);
        message.setText("Nueva novedad creada en el dia "+LocalDate.now()+". De categoria "+cate+" vinculado al legajo "+legajo+".\nCausa o Descripcion:\n"+causa);
        message.setCc(emailRRHH);
        mailSender.send(message);
    }

    public void sendEmailCloseNovedad(String to, String numero,String cate,LocalDate createdDate) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailSys);
        message.setTo(to);
        message.setSubject("Novedad "+cate+" - "+numero);
        message.setText("La novedad creada en la fecha "+createdDate+" fue cerrada el "+LocalDate.now()+".");
        message.setCc(emailRRHH);
        mailSender.send(message);
    }

    public void sendEmailReopenNovedad(String to, String numero,String cate,LocalDate createdDate) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailSys);
        message.setTo(to);
        message.setSubject("Novedad "+cate+" - "+numero);
        message.setText("La novedad creada en la fecha "+createdDate+" fue reabierta el "+LocalDate.now()+".");
        message.setCc(emailRRHH);
        mailSender.send(message);
    }
        public void sendEmailActionNovedad(String to, String numero,String cate,LocalDate createdDate, String accion, String info) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailSys);
        message.setTo(to);
        message.setSubject("Novedad "+cate+" - "+numero);
        message.setText("Se tomo una nueva accion "+accion+" en la novedad creada el "+createdDate+".\nInformacion Adicional:\n"+info);
        mailSender.send(message);
    }
}
