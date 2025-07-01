package rhss_server.rhss_server.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rhss_server.rhss_server.DTOs.PersonalDto;
import rhss_server.rhss_server.Interfaces.INovedadesRepo;
import rhss_server.rhss_server.Interfaces.IPersonalRepo;
import rhss_server.rhss_server.Interfaces.IUsuarioRepo;
import rhss_server.rhss_server.Tables.NovedadesModel;
import rhss_server.rhss_server.Tables.PersonalTable;
import rhss_server.rhss_server.Tables.UsuarioModel;

@Service
public class PersonalService {
    
    @Autowired
    private IPersonalRepo PersonalRepo;
    @Autowired
    private INovedadesRepo NovedadRepo;
    @Autowired
    private IUsuarioRepo UsuarioRepo;
    @Autowired
    private EmailSender emailSender;

    public String postPersonal (PersonalDto data) {
        LocalDate current = LocalDate.now();
        PersonalTable personal = new PersonalTable();
        System.out.println(data);
        personal.setCategoria(data.categoria);
        personal.setCausa(data.causa);
        personal.setFecha(current);
        personal.setFecha_ocurrido(data.fecha_ocurrido);
        personal.setLegajo(data.legajo);
        personal.setNovedad_id(data.novedad_id);
        PersonalRepo.save(personal);
        NovedadesModel novedad = NovedadRepo.findById(data.novedad_id).get();
        UsuarioModel usuario = UsuarioRepo.findById(novedad.getUsuario_id()).get();
        emailSender.sendEmailActionNovedad(usuario.getEmail(), novedad.getNumero(),
            novedad.getCategoria(), novedad.getFecha(), data.categoria, data.causa, novedad.getNovedad_id());
        return "Informe de personal creado";
    }

    public List<PersonalTable> getAllPersonal () {
        List<PersonalTable> informes = PersonalRepo.findAll();
        return informes;
    }

    public List<PersonalTable> getByLegajo (int legajo) {
        List<PersonalTable> informes = PersonalRepo.findByLegajo(legajo);
        return informes;
    }

    public List<PersonalTable> getByNovedad (long novedad) {
        List<PersonalTable> personal = PersonalRepo.findByNovedad(novedad);
        return personal;
    }

    public PersonalTable getById(long id) {
        PersonalTable personal = PersonalRepo.findById(id).get();
        return personal;    
    }

    public String[] getCategories() {
        String[] cat = {"DESPIDO", "DESPIDO UOCRA", "DESPIDO EN PERIODO DE PRUEBA", "SOLICITUD DE CERTIFICADO DE TRABAJO", "ENTREGA DE INDUMENTARIA"};
        return cat;
    }

}
