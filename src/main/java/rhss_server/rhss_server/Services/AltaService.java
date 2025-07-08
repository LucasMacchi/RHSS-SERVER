package rhss_server.rhss_server.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rhss_server.rhss_server.DTOs.AltaDto;
import rhss_server.rhss_server.Interfaces.IAltaRepo;
import rhss_server.rhss_server.Interfaces.INovedadesRepo;
import rhss_server.rhss_server.Interfaces.IUsuarioRepo;
import rhss_server.rhss_server.Tables.AltaTable;
import rhss_server.rhss_server.Tables.NovedadesModel;
import rhss_server.rhss_server.Tables.UsuarioModel;

@Service
public class AltaService {
    @Autowired
    private IAltaRepo AltaRepo;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private INovedadesRepo NovedadRepo;
    @Autowired
    private IUsuarioRepo UsuarioRepo;

    public String postAlta (AltaDto data) {
        NovedadesModel novedad = NovedadRepo.findById(data.novedad).get();
        UsuarioModel usuario = UsuarioRepo.findById(novedad.getUsuario_id()).get();
        AltaTable alta = new AltaTable();
        alta.setCuit(data.cuit);
        alta.setDireccion(data.direccion);
        alta.setFecha_ingreso(data.fecha_ingreso);
        alta.setJornada(data.jornada);
        alta.setLugar(data.lugar);
        alta.setNacimiento(data.nacimiento);
        alta.setNovedad(data.novedad);
        alta.setCategoria(data.categoria);
        AltaRepo.save(alta);
        emailSender.sendEmailActionNovedad(usuario.getEmail(), 
        novedad.getNumero(), novedad.getCategoria(), alta.getFecha(),
        "Alta de Legajo", "Legajo nuevo creado.",novedad.getNovedad_id(), novedad.getEmpresa_id());
        return "Alta creada";
    }
}
