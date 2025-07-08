package rhss_server.rhss_server.Services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rhss_server.rhss_server.DTOs.SancionDto;
import rhss_server.rhss_server.Interfaces.INovedadesRepo;
import rhss_server.rhss_server.Interfaces.ISancionRepo;
import rhss_server.rhss_server.Interfaces.IUsuarioRepo;
import rhss_server.rhss_server.Tables.NovedadesModel;
import rhss_server.rhss_server.Tables.SancionModel;
import rhss_server.rhss_server.Tables.UsuarioModel;

@Service
public class SancionService {
    @Autowired
    private ISancionRepo SancionRepo;
    @Autowired
    private INovedadesRepo NovedadRepo;
    @Autowired
    private IUsuarioRepo UsuarioRepo;
    @Autowired
    private EmailSender emailSender;

    public String postSancion (SancionDto data) {
        String info = "Apercibimiento \n"+data.causa;
        final LocalDate current = LocalDate.now();
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
        if(data.fecha_final != null && data.fecha_inicio != null) {
            info = "Suspension del "+data.fecha_inicio+" al "+data.fecha_final+"\n"+data.causa;
        }
        SancionRepo.save(sancion);
        NovedadesModel novedad = NovedadRepo.findById(data.novedad_id).get();
        UsuarioModel usuario = UsuarioRepo.findById(novedad.getUsuario_id()).get();
        emailSender.sendEmailActionNovedad(usuario.getEmail(), novedad.getNumero(),
            novedad.getCategoria(), novedad.getFecha(), data.tipo, info, novedad.getNovedad_id(), novedad.getEmpresa_id());
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
        SancionModel sancione = SancionRepo.findById(id).get();
        return sancione;
    }
    public String[] getCategories() {
        String[] cat = {"SUSPENCION", "APERCIBIMIENTO"};
        return cat;
    }


}
