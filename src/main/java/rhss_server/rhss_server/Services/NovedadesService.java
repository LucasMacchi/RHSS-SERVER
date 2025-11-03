package rhss_server.rhss_server.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import rhss_server.rhss_server.DTOs.NovedadDto;
import rhss_server.rhss_server.DTOs.NovedadFilterDto;
import rhss_server.rhss_server.Interfaces.IAltaRepo;
import rhss_server.rhss_server.Interfaces.IArchivoRepo;
import rhss_server.rhss_server.Interfaces.IAusenteRepo;
import rhss_server.rhss_server.Interfaces.IEmpresasRepo;
import rhss_server.rhss_server.Interfaces.ILegajoRepo;
import rhss_server.rhss_server.Interfaces.ILicenciaRepo;
import rhss_server.rhss_server.Interfaces.INovedadesRepo;
import rhss_server.rhss_server.Interfaces.IPersonalRepo;
import rhss_server.rhss_server.Interfaces.ISancionRepo;
import rhss_server.rhss_server.Interfaces.IUsuarioRepo;
import rhss_server.rhss_server.Tables.AltaTable;
import rhss_server.rhss_server.Tables.ArchivoModel;
import rhss_server.rhss_server.Tables.AusenteModel;
import rhss_server.rhss_server.Tables.EmpresaModel;
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
    @Autowired
    private IAltaRepo AltaRepo;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private IEmpresasRepo EmpresaRepo;

    public String postNovedad (NovedadDto data, List<MultipartFile> adjuntos ) {
        LocalDate current = LocalDate.now();
        NovedadesModel novedad = new NovedadesModel();
        UsuarioModel user = UsuarioRepo.findByUsername(data.solicitante).get();
        novedad.setCausa(data.causa);
        if(data.empresa_id == 0) {
            novedad.setEmpresa_id(user.getEmpresa_id());
        }
        else {
            novedad.setEmpresa_id(data.empresa_id);
        }
        novedad.setFecha_creacion(current);
        if(!data.categoria.equals("ALTA DE LEGAJO")) novedad.setLegajo(data.legajo);
        novedad.setSolicitante(data.solicitante);
        novedad.setUsuario_id(user.getUsuario_id());
        novedad.setCategoria(data.categoria);
        novedad.setCerrado(false);
        novedad.setTelefono(data.telefono);
        novedad.setEmail(data.email);
        NovedadRepo.save(novedad);
        emailSender.sendEmailNewNovedad(user.getEmail(), novedad.getNumero(), data.categoria, data.legajo, data.causa,novedad.getNovedad_id(), adjuntos, novedad.getEmpresa_id());
        return "Novedad creada, numero "+novedad.getNumero();

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
    public List<NovedadesModel> getAllNovSup (NovedadFilterDto data) {
        List<NovedadesModel> novedades = NovedadRepo.findAll();
        List<NovedadesModel> novedadesF = new ArrayList<>();
        Iterator<NovedadesModel> ite = novedades.iterator();
        while (ite.hasNext()) {
            NovedadesModel nov = ite.next();
            if(!data.categoria.isBlank() && !nov.getCategoria().equals(data.categoria)){
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
        NovedadesModel novedad = NovedadRepo.findById(novedad_id).get();
        List<SancionModel> sanciones = SancionRepo.findByNovedad(novedad_id);
        List<PersonalTable> personal = PersonalRepo.findByNovedad(novedad_id);
        List<LicenciaTable> licencias = LicenciaRepo.findByNovedad(novedad_id);
        List<AusenteModel> ausentes = AusenteRepo.findByNovedad(novedad_id);
        List<ArchivoModel> archivos = ArchivoRepo.findByNovedad(novedad_id);
        List<AltaTable> altas = AltaRepo.findByNovedad(novedad_id);
        if(!novedad.getCategoria().equals("ALTA DE LEGAJO")) {
            EmpresaModel empresa = EmpresaRepo.findById(novedad.getEmpresa_id()).get();
            LegajosTable leg = LegajoRepo.findByLegajoAndEmpresa(novedad.getLegajo(),empresa.getNombre()).get(0);
            NovLegajo novleg = new NovLegajo(leg, novedad,ausentes,sanciones,personal,licencias, archivos,altas);
            return novleg;
        }
        else {
            LegajosTable legajo = new LegajosTable();
            legajo.setCuil(0);
            legajo.setDireccion("");
            legajo.setFecha_egreso(LocalDate.now());
            legajo.setFullname("");
            legajo.setSector("");
            NovLegajo novleg = new NovLegajo(legajo, novedad,ausentes,sanciones,personal,licencias, archivos,altas);
            return novleg;
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
            NovedadesModel novedad = NovedadRepo.findById(id).get();
            UsuarioModel usuario = UsuarioRepo.findById(novedad.getUsuario_id()).get();
            if(novedad.getCerrado()) {
                novedad.setCerrado(false);
                NovedadRepo.save(novedad);
                emailSender.sendEmailReopenNovedad(usuario.getEmail(), 
                novedad.getNumero(),novedad.getCategoria(),novedad.getFecha(), novedad.getEmpresa_id());
            }
            else {
                novedad.setCerrado(true);
                NovedadRepo.save(novedad);
                emailSender.sendEmailCloseNovedad(usuario.getEmail(), novedad.getNumero(),
                novedad.getCategoria(),novedad.getFecha(), novedad.getEmpresa_id());
            }
            return "Estado cambiado.";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Novedad no encontrada.");
        }
    }


    public String[] getCategories () {
        String[] cat = {"SUSPENSION", "APERCIBIMIENTO", "ART", "LICENCIA POR ENFERMEDAD", 
        "LICENCIA POR EMBARAZO", "LICENCIA DE VACACIONES","DESPIDO", "DESPIDO UOCRA", "DESPIDO EN PERIODO DE PRUEBA", 
        "SOLICITUD DE CERTIFICADO DE TRABAJO", "ENTREGA DE INDUMENTARIA", "CAMBIO DE LUGAR DE TRABAJO","CAMBIO DE SERVICIO",
        "CAMBIO DE JORNADA","CAMBIO DE CATEGORIA","AUSENTE", "TARDANZA","ALTA DE LEGAJO", "RENUNCIA","ATENCION FAMILIAR"};
        return cat;
    }

}
