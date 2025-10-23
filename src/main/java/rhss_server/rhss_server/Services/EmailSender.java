package rhss_server.rhss_server.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.resend.*;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.Attachment;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.CreateEmailResponse;


@Service
public class EmailSender {
    private final String noReplay = "\nNo responder este correo.";
    @Value("${EMAIL_RRHH}")
    private String emailRRHH;
    @Value("${EMAIL_USERNAME}")
    private String emailSys;
    @Value("${FRONT_URL}")
    private String frontUrl;
    @Value("${EMAIL_RRHH_TUICHA}")
    private String emailTuicha;
    @Value("${API_KEY_RESEND}")
    private String API_KEY;
    @Value("${TUICHA_ID}")
    private int tuichaId;

    public void sendEmailNewNovedad(String to, String numero, String cate,
     int legajo, String causa, long novedad_id, List<MultipartFile> adjuntos,byte empresa) {
        final String novedadLink = frontUrl+"/Novedad/"+novedad_id;
        Resend resend = new Resend(API_KEY);
        List<Attachment> adjuntosMail = new ArrayList<Attachment>();
        try {
            if(adjuntos != null){
                System.out.println("Archivos existen");
                for (MultipartFile archivo : adjuntos) {
                    final String name = archivo.getOriginalFilename();
                    if(name != null && !name.isBlank()){
                        String base64Ar = Base64.getEncoder().encodeToString(archivo.getBytes());
                        Attachment att = Attachment.builder().fileName(name).content(base64Ar).build();
                        adjuntosMail.add(att);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        CreateEmailOptions params = CreateEmailOptions.builder()
        .from(emailSys).to(to).subject("Novedad "+cate+" - "+numero).text("Nueva novedad creada en el dia "+LocalDate.now()+". De categoria "+cate+" vinculado al legajo "+legajo+".\nCausa o Descripcion:\n"+causa+noReplay)
        .attachments(adjuntosMail.size() > 0 ? adjuntosMail : null ).build();
        
        CreateEmailOptions params2;
        if(empresa == tuichaId) {
            params2 = CreateEmailOptions.builder()
            .from(emailSys).to(emailRRHH).subject("Novedad "+cate+" - "+numero).text("Nueva novedad creada en el dia "+LocalDate.now()+". De categoria "+cate+" vinculado al legajo "+legajo+".\nCausa o Descripcion:\n"+causa+"\nLink: "+novedadLink+noReplay).build();
        }
        else {
            params2 = CreateEmailOptions.builder()
            .from(emailSys).to(emailRRHH).addCc(emailTuicha).subject("Novedad "+cate+" - "+numero).text("Nueva novedad creada en el dia "+LocalDate.now()+". De categoria "+cate+" vinculado al legajo "+legajo+".\nCausa o Descripcion:\n"+causa+"\nLink: "+novedadLink+noReplay).build();
        }
        try {
            CreateEmailResponse data = resend.emails().send(params);
            CreateEmailResponse data2 = resend.emails().send(params2);
            System.out.println(data.getId());
            System.out.println(data2.getId());
        } catch (ResendException e) {
            e.printStackTrace();
        }

    }

    @Async
    public void sendEmailCloseNovedad(String to, String numero,String cate,LocalDate createdDate, byte empresa) {
        Resend resend = new Resend(API_KEY);
        CreateEmailOptions params = CreateEmailOptions.builder()
        .from(emailSys).to(to).subject("Novedad "+cate+" - "+numero).text("La novedad creada en la fecha "+createdDate+" fue cerrada el "+LocalDate.now()+"."+noReplay).build();
        
        CreateEmailOptions params2;
        if(empresa == tuichaId) {
            params2 = CreateEmailOptions.builder()
            .from(emailSys).to(emailRRHH).subject("Novedad "+cate+" - "+numero).text("La novedad creada en la fecha "+createdDate+" fue cerrada el "+LocalDate.now()+"."+noReplay).build();
        }
        else {
            params2 = CreateEmailOptions.builder()
            .from(emailSys).to(emailRRHH).addCc(emailTuicha).subject("Novedad "+cate+" - "+numero).text("La novedad creada en la fecha "+createdDate+" fue cerrada el "+LocalDate.now()+"."+noReplay).build();
        }
        try {
            CreateEmailResponse data = resend.emails().send(params);
            CreateEmailResponse data2 = resend.emails().send(params2);
            System.out.println(data.getId());
            System.out.println(data2.getId());
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void sendEmailReopenNovedad(String to, String numero,String cate,LocalDate createdDate, byte empresa) {
        System.out.println("Novedad reabierta");
    }
    @Async
    public void sendEmailActionNovedad(String to, String numero,String cate,LocalDate createdDate, String accion, String info, long novedad_id, byte empresa) {
        Resend resend = new Resend(API_KEY);
        final String novedadLink = frontUrl+"/Novedad/"+novedad_id;
        CreateEmailOptions params = CreateEmailOptions.builder()
        .from(emailSys).to(to).subject("Novedad "+cate+" - "+numero).text("La novedad creada en la fecha "+createdDate+" fue cerrada el "+LocalDate.now()+"."+noReplay).build();
        
        CreateEmailOptions params2;
        if(empresa == tuichaId) {
            params2 = CreateEmailOptions.builder()
            .from(emailSys).to(emailRRHH).subject("Novedad "+cate+" - "+numero).text("Se tomo una nueva accion "+accion+" en la novedad creada el "+createdDate+".\nInformacion Adicional:\n"+info+noReplay).build();
        }
        else {
            params2 = CreateEmailOptions.builder()
            .from(emailSys).to(emailRRHH).addCc(emailTuicha).subject("Novedad "+cate+" - "+numero).text("Se tomo una nueva accion "+accion+" en la novedad creada el "+createdDate+".\nInformacion Adicional:\n"+info+"\nLink: "+novedadLink+noReplay).build();
        }
        try {
            CreateEmailResponse data = resend.emails().send(params);
            CreateEmailResponse data2 = resend.emails().send(params2);
            System.out.println(data.getId());
            System.out.println(data2.getId());
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }
    @Async
    public void sendEmailRegister(String to, LocalDate createdDate, String username, String pass) {
        Resend resend = new Resend(API_KEY);
        final String loginLink = frontUrl+"/login";
        CreateEmailOptions params = CreateEmailOptions.builder()
        .from(emailSys).to(to).subject("Usuario Registrado para el Sistema Gestor de Novedades (SGN)").text("Se creo un nuevo usuario vinculado con este correo electronico:\nNombre de usuario: "+username+"\nContraseña: "+pass+"\nFecha de registracion: "+createdDate+"\nPuedes acceder al sistema desde el siguiente link: "+loginLink+"\n"+noReplay).build();
        try {
            CreateEmailResponse data = resend.emails().send(params);
            System.out.println(data.getId());
        } catch (ResendException e) {
            e.printStackTrace();
        }
    }
}
