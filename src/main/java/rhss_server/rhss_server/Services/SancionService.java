package rhss_server.rhss_server.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import rhss_server.rhss_server.DTOs.SancionDto;
import rhss_server.rhss_server.Interfaces.ISancionRepo;
import rhss_server.rhss_server.Tables.SancionModel;

@Service
public class SancionService {
    @Autowired
    private ISancionRepo SancionRepo;

    public String postSancion (SancionDto data) {
        LocalDate current = LocalDate.now();
        SancionModel sancion = new SancionModel();
        sancion.setCausa(data.causa);
        sancion.setFecha(current);
        sancion.setLegajo(data.legajo);
        sancion.setNovedad_id(data.novedad_id);
        sancion.setTipo(data.tipo);
        if(data.fecha_final != null) {
            sancion.setFecha_final(data.fecha_final);
        }
        if(data.fecha_inicio != null) {
            sancion.setFecha_inicio(data.fecha_inicio);
        }
        SancionRepo.save(sancion);
        return "Sancion creada";
    }

    public List<SancionModel> getAllSanciones () {
        List<SancionModel> sanciones = SancionRepo.findAll();
        return sanciones;
    }

    public List<SancionModel> getSancionLegajo (long legajo) {
        List<SancionModel> sanciones = SancionRepo.findByLegajo(legajo);
        return sanciones;
    }

    public List<SancionModel> getSancionNovedad (long novedad) {
        List<SancionModel> sanciones = SancionRepo.findByNovedad(novedad);
        return sanciones;
    }

    public SancionModel getSancionId(long id) {
        Optional<SancionModel> sancione = SancionRepo.findById(id);
        if(sancione.isPresent()) return sancione.get();
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Sancion no encontrada.");
        }
    }


}
