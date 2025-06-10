package rhss_server.rhss_server.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import rhss_server.rhss_server.DTOs.LicenciaDto;
import rhss_server.rhss_server.Interfaces.ILicenciaRepo;
import rhss_server.rhss_server.Tables.LicenciaTable;

@Service
public class LicenciaService {
    @Autowired
    private ILicenciaRepo LicenciaRepo;

    public String postLicencia (LicenciaDto data) {
        LocalDate current = LocalDate.now();
        LicenciaTable licencia = new LicenciaTable();
        licencia.setCategoria(data.categoria);
        licencia.setCausa(data.causa);
        licencia.setFecha(current);
        licencia.setFecha_entrada(data.fecha_entrada);
        licencia.setFecha_salida(data.fecha_salida);
        licencia.setLegajo(data.legajo);
        licencia.setNovedad(data.novedad);
        LicenciaRepo.save(licencia);
        return "Licencia Creada";
    }

    public List<LicenciaTable> getAllLicencias () {
        List<LicenciaTable> licencias = LicenciaRepo.findAll();
        return licencias;
    }

    public List<LicenciaTable> getAllByLegajo (int legajo) {
        List<LicenciaTable> licencias = LicenciaRepo.findByLegajo(legajo);
        return licencias;
    }

    public List<LicenciaTable> getAllByNov(long novedad) {
        List<LicenciaTable> licencias = LicenciaRepo.findByNovedad(novedad);
        return licencias;
    }

    public LicenciaTable getById (long id) {
        Optional<LicenciaTable> lic = LicenciaRepo.findById(id);
        if(lic.isPresent()) return lic.get();
        else{
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Licencia no encontrada.");
        }
    }

    public String[] getCategories() {
        String[] cat = {"ART", "LICENCIA POR ENFERMEDAD", "LICENCIA POR EMBARAZO", "LICENCIA DE VACACIONES"};
        return cat;
    }
}
