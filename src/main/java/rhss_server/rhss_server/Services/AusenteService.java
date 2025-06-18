package rhss_server.rhss_server.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import rhss_server.rhss_server.DTOs.AusenteDto;
import rhss_server.rhss_server.Interfaces.IAusenteRepo;
import rhss_server.rhss_server.Tables.AusenteModel;

@Service
public class AusenteService {

    @Autowired
    private IAusenteRepo AusenteRepo;

    public String postAusente (AusenteDto data) {
        LocalDate current = LocalDate.now();
        AusenteModel ausente = new AusenteModel();
        ausente.setCausa(data.causa);
        ausente.setFecha(current);
        ausente.setFecha_ausentada(data.fecha_ausente);
        ausente.setJustificado(data.justificado);
        ausente.setLegajo(data.legajo);
        ausente.setNovedad_id(data.novedad_id);
        AusenteRepo.save(ausente);
        return "Ausente creado en legajo "+data.legajo; 
    }
    
    public List<AusenteModel> allAusentesByLegajo (int legajo) {
        List<AusenteModel> ausentes = AusenteRepo.findByLegajo(legajo);
        return ausentes;
    }

    public List<AusenteModel> allAusentesByNovedad (int novedad_id) {
        List<AusenteModel> ausentes = AusenteRepo.findByNovedad(novedad_id);
        return ausentes;
    }

    public String justifyAusente(long ausente_id) {
        Optional<AusenteModel> ausente = AusenteRepo.findById(ausente_id);
        if(ausente.isPresent()) {
            ausente.get().setJustificado(true);
            AusenteRepo.save(ausente.get());
            return "Ausente justificado.";
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Ausente no encontrado.");
        }
    }
    
}
