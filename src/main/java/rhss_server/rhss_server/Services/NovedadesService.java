package rhss_server.rhss_server.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import rhss_server.rhss_server.DTOs.NovedadDto;
import rhss_server.rhss_server.Interfaces.INovedadesRepo;
import rhss_server.rhss_server.Tables.NovedadesModel;

@Service
public class NovedadesService {
    
    @Autowired
    private INovedadesRepo NovedadRepo;

    public String postNovedad (NovedadDto data) {
        LocalDate current = LocalDate.now();
        NovedadesModel novedad = new NovedadesModel();
        novedad.setCausa(data.causa);
        novedad.setEmpresa_id(data.empresa_id);
        novedad.setFecha_creacion(current);
        novedad.setLegajo(data.legajo);
        novedad.setSolicitante(data.solicitante);
        novedad.setUsuario_id(data.usuario_id);
        NovedadRepo.save(novedad);
        return "Novedad creada, numero "+novedad.getNumero();
    }

    public List<NovedadesModel> getAllNov () {
        List<NovedadesModel> novedades = NovedadRepo.findAll();
        return novedades;
    }

        public NovedadesModel getNov (long novedad_id) {
        Optional<NovedadesModel> novedad = NovedadRepo.findById(novedad_id);
        if(novedad.isPresent()) return novedad.get();
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Ausente no encontrado.");
        }
    }
}
