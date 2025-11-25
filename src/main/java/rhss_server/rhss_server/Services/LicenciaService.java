package rhss_server.rhss_server.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rhss_server.rhss_server.DTOs.LicenciaDto;
import rhss_server.rhss_server.Interfaces.ILicenciaRepo;
import rhss_server.rhss_server.Interfaces.INovedadesRepo;
import rhss_server.rhss_server.Interfaces.IUsuarioRepo;
import rhss_server.rhss_server.Tables.LicenciaTable;
import rhss_server.rhss_server.Tables.NovedadesModel;
import rhss_server.rhss_server.Tables.UsuarioModel;

@Service
public class LicenciaService {
    @Autowired
    private ILicenciaRepo LicenciaRepo;
    @Autowired
    private INovedadesRepo NovedadRepo;
    @Autowired
    private IUsuarioRepo UsuarioRepo;
    @Autowired
    private EmailSender emailSender;

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
        final String info = "Licencia desde "+data.fecha_entrada+" al "+data.fecha_salida+".\n"+data.causa;
        NovedadesModel novedad = NovedadRepo.findById(data.novedad).get();
        UsuarioModel usuario = UsuarioRepo.findById(novedad.getUsuario_id()).get();
        emailSender.sendEmailActionNovedad(usuario.getEmail(), novedad.getNumero(),
         novedad.getCategoria(), novedad.getFecha(), data.categoria,info,novedad.getNovedad_id(), novedad.getEmpresa_id());
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
        LicenciaTable lic = LicenciaRepo.findById(id).get();
        return lic;
    }

    public String[] getCategories() {
        String[] cat = {"ART", "LICENCIA POR ENFERMEDAD", "LICENCIA POR EMBARAZO", "LICENCIA DE VACACIONES", "ATENCION FAMILIAR", "LINCENCIA DE MATRIMONIO"};
        return cat;
    }
}
