package rhss_server.rhss_server.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import rhss_server.rhss_server.DTOs.PersonalDto;
import rhss_server.rhss_server.Interfaces.IPersonalRepo;
import rhss_server.rhss_server.Tables.PersonalTable;

@Service
public class PersonalService {
    
    @Autowired
    private IPersonalRepo PersonalRepo;

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

    public PersonalTable getByNovedad (long novedad) {
        Optional<PersonalTable> personal = PersonalRepo.findByNovedad(novedad);
        if(personal.isPresent()){
            return personal.get();
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Ausente no encontrado.");
        }
    }

        public PersonalTable getById(long id) {
        Optional<PersonalTable> personal = PersonalRepo.findById(id);
        if(personal.isPresent()){
            return personal.get();
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Ausente no encontrado.");
        }
    }

}
