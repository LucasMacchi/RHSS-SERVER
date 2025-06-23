package rhss_server.rhss_server.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import rhss_server.rhss_server.DTOs.NovedadDto;
import rhss_server.rhss_server.DTOs.NovedadFilterDto;
import rhss_server.rhss_server.Interfaces.IArchivoRepo;
import rhss_server.rhss_server.Interfaces.IAusenteRepo;
import rhss_server.rhss_server.Interfaces.ILegajoRepo;
import rhss_server.rhss_server.Interfaces.ILicenciaRepo;
import rhss_server.rhss_server.Interfaces.INovedadesRepo;
import rhss_server.rhss_server.Interfaces.IPersonalRepo;
import rhss_server.rhss_server.Interfaces.ISancionRepo;
import rhss_server.rhss_server.Interfaces.IUsuarioRepo;
import rhss_server.rhss_server.Tables.ArchivoModel;
import rhss_server.rhss_server.Tables.AusenteModel;
import rhss_server.rhss_server.Tables.LegajosTable;
import rhss_server.rhss_server.Tables.LicenciaTable;
import rhss_server.rhss_server.Tables.NovedadesModel;
import rhss_server.rhss_server.Tables.PersonalTable;
import rhss_server.rhss_server.Tables.SancionModel;
import rhss_server.rhss_server.Tables.UsuarioModel;
import rhss_server.rhss_server.Utils.NovLegajo;

@Service
public class NovedadesService {
    
    @Autowired
    private INovedadesRepo NovedadRepo;
    @Autowired
    private IUsuarioRepo UsuarioRepo;
    @Autowired
    private ILegajoRepo LegajoRepo;
    @Autowired
    private ISancionRepo SancionRepo;
    @Autowired
    private IAusenteRepo AusenteRepo;
    @Autowired
    private ILicenciaRepo LicenciaRepo;
    @Autowired
    private IPersonalRepo PersonalRepo;
    @Autowired
    private IArchivoRepo ArchivoRepo;

    public String postNovedad (NovedadDto data) {
        LocalDate current = LocalDate.now();
        NovedadesModel novedad = new NovedadesModel();
        Optional<UsuarioModel> user = UsuarioRepo.findByUsername(data.solicitante);
        if(user.isPresent()) {
            novedad.setCausa(data.causa);
            if(data.empresa_id == 0) {
                novedad.setEmpresa_id(user.get().getEmpresa_id());
            }
            else {
                novedad.setEmpresa_id(data.empresa_id);
            }
            novedad.setFecha_creacion(current);
            novedad.setLegajo(data.legajo);
            novedad.setSolicitante(data.solicitante);
            novedad.setUsuario_id(user.get().getUsuario_id());
            novedad.setCategoria(data.categoria);
            novedad.setCerrado(false);
            novedad.setTelefono(data.telefono);
            novedad.setEmail(data.email);
            NovedadRepo.save(novedad);
            return "Novedad creada, numero "+novedad.getNumero();
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Usuario no encontrada.");
        }

    }

    public List<NovedadesModel> getAllNov (NovedadFilterDto data) {
        List<NovedadesModel> novedades = NovedadRepo.findAll();
        List<NovedadesModel> novedadesF = new ArrayList<>();
        Iterator<NovedadesModel> ite = novedades.iterator();
        while (ite.hasNext()) {
            NovedadesModel nov = ite.next();
            if(!data.categoria.isBlank() && !nov.getCategoria().equals(data.categoria)){
                continue;
            }
            if(data.empresa_id == 0 || nov.getEmpresa_id() != data.empresa_id) {
                continue;
            }
            if(!data.solicitante.isBlank() && !nov.getSolicitante().equals(data.solicitante)){
                continue;
            }
            if(nov.getFecha().isBefore(data.fecha_inicio) || nov.getFecha().isAfter(data.fecha_final)){
                continue;
            }

            novedadesF.add(nov);
        }
        
        return novedadesF;
    }

    public NovLegajo getNov (long novedad_id) {
        Optional<NovedadesModel> novedad = NovedadRepo.findById(novedad_id);
        List<SancionModel> sanciones = SancionRepo.findByNovedad(novedad_id);
        List<PersonalTable> personal = PersonalRepo.findByNovedad(novedad_id);
        List<LicenciaTable> licencias = LicenciaRepo.findByNovedad(novedad_id);
        List<AusenteModel> ausentes = AusenteRepo.findByNovedad(novedad_id);
        List<ArchivoModel> archivos = ArchivoRepo.findByNovedad(novedad_id);
        if(novedad.isPresent()) {
            Optional<LegajosTable> leg = LegajoRepo.findById(novedad.get().getLegajo());
            NovLegajo novleg = new NovLegajo(leg.get(), novedad.get(),ausentes,sanciones,personal,licencias, archivos);
            return novleg;
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Novedad no encontrada.");
        }
    }

    public List<NovedadesModel> getTodayNov () {
        LocalDate current = LocalDate.now();
        List<NovedadesModel> novedades = NovedadRepo.findByFecha(current);
        return novedades;
    }

    public List<NovedadesModel> getNroNov (String nro) {
        List<NovedadesModel> novedades = NovedadRepo.findByNumero(nro);
        return novedades;
    }


    public List<NovedadesModel> getLegNov (Long legajo) {
        List<NovedadesModel> novedades = NovedadRepo.findByLegajo(legajo);
        return novedades;
    }

    public String changeStateNov (long id) {
        try {
            Optional<NovedadesModel> novedad = NovedadRepo.findById(id);
            if(novedad.isPresent()) {
                if(novedad.get().getCerrado()) {
                    novedad.get().setCerrado(false);
                    NovedadRepo.save(novedad.get());
                }
                else {
                    novedad.get().setCerrado(true);
                    NovedadRepo.save(novedad.get());
                }
                return "Estado cambiado.";
            }
            else {
                throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Novedad no encontrada.");
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Novedad no encontrada.");
        }
    }


    public String[] getCategories () {
        String[] cat = {"SUSPENCION", "APERCIBIMIENTO", "ART", "LICENCIA POR ENFERMEDAD", 
        "LICENCIA POR EMBARAZO", "LICENCIA DE VACACIONES","DESPIDO", "DESPIDO UOCRA", "DESPIDO EN PERIODO DE PRUEBA", 
        "SOLICITUD DE CERTIFICADO DE TRABAJO", "ENTREGA DE INDUMENTARIA","AUSENTE"};
        return cat;
    }

}
