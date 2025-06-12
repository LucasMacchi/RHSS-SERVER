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
        novedad.setCategoria(data.categoria);
        NovedadRepo.save(novedad);
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

    public NovedadesModel getNov (long novedad_id) {
        Optional<NovedadesModel> novedad = NovedadRepo.findById(novedad_id);
        if(novedad.isPresent()) return novedad.get();
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

    public String[] getCategories () {
        String[] cat = {"SUSPENCION", "APERCIBIMIENTO", "ART", "LICENCIA POR ENFERMEDAD", 
        "LICENCIA POR EMBARAZO", "LICENCIA DE VACACIONES","DESPIDO", "DESPIDO UOCRA", "DESPIDO EN PERIODO DE PRUEBA", 
        "SOLICITUD DE CERTIFICADO DE TRABAJO", "ENTREGA DE INDUMENTARIA"};
        return cat;
    }

}
