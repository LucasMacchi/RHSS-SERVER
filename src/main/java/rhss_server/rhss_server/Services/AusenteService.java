package rhss_server.rhss_server.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rhss_server.rhss_server.DTOs.AusenteDto;
import rhss_server.rhss_server.Interfaces.IAusenteRepo;
import rhss_server.rhss_server.Interfaces.INovedadesRepo;
import rhss_server.rhss_server.Interfaces.IUsuarioRepo;
import rhss_server.rhss_server.Tables.AusenteModel;
import rhss_server.rhss_server.Tables.NovedadesModel;
import rhss_server.rhss_server.Tables.UsuarioModel;

@Service
public class AusenteService {

    @Autowired
    private IAusenteRepo AusenteRepo;
    @Autowired
    private INovedadesRepo NovedadRepo;
    @Autowired
    private IUsuarioRepo UsuarioRepo;
    @Autowired
    private EmailSender emailSender;

    public String postAusente (AusenteDto data, String categoria) {
        LocalDate current = LocalDate.now();
        AusenteModel ausente = new AusenteModel();
        ausente.setCausa(data.causa);
        ausente.setFecha(current);
        ausente.setFecha_ausentada(data.fecha_ausente);
        ausente.setJustificado(data.justificado);
        ausente.setLegajo(data.legajo);
        ausente.setCategoria(categoria);
        ausente.setNovedad_id(data.novedad_id);
        AusenteRepo.save(ausente);
        NovedadesModel novedad = NovedadRepo.findById(data.novedad_id).get();
        UsuarioModel usuario = UsuarioRepo.findById(novedad.getUsuario_id()).get();
        emailSender.sendEmailActionNovedad(usuario.getEmail(), novedad.getNumero(),
        novedad.getCategoria(), novedad.getFecha(), categoria, data.causa,novedad.getNovedad_id(), novedad.getEmpresa_id());
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
        AusenteModel ausente = AusenteRepo.findById(ausente_id).get();
        ausente.setJustificado(true);
        AusenteRepo.save(ausente);
        return "Ausente justificado.";
    }
    
}
