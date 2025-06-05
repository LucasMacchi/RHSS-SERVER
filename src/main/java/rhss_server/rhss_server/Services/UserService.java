package rhss_server.rhss_server.Services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import rhss_server.rhss_server.Utils.SessionCheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpSession;
import rhss_server.rhss_server.DTOs.RegisterUserDto;
import rhss_server.rhss_server.Interfaces.IUsuarioRepo;
import rhss_server.rhss_server.Tables.UsuarioModel;

@Service
public class UserService {

    @Autowired
    private IUsuarioRepo UsuarioRepo;

    public List<UsuarioModel> getAllUsuarios () {
        List<UsuarioModel> usuarios = UsuarioRepo.findAll();
        return usuarios;
    }

    public String registerUser (RegisterUserDto data) {
        UsuarioModel user = new UsuarioModel();
        user.setAdmin(false);
        user.setAdministrativo(data.administrativo);
        user.setApellido(data.apellido);
        user.setNombre(data.nombre);
        user.setEmail(data.email);
        user.setEmpresa_id(data.empresa_id);
        user.setUsername(data.username);
        user.setFecha_creacion(LocalDate.now());
        user.setActivado(false);
        UsuarioRepo.save(user);
        return "Usuario "+data.username+" creado";
    }

    public String activateUser (String id) {
        Optional<UsuarioModel> user = UsuarioRepo.findById(Byte.parseByte(id));
        if(user.isPresent()) {
            user.get().setActivado(true);
            UsuarioRepo.save(user.get());
            return "Usuario Activado";
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Usuario no encontrado.");
        }
    }

    public String deactivateUser (String id) {
        Optional<UsuarioModel> user = UsuarioRepo.findById(Byte.parseByte(id));
        if(user.isPresent()) {
            user.get().setActivado(false);
            UsuarioRepo.save(user.get());
            return "Usuario Desactivado";
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Usuario no encontrado.");
        }
    }

    public UsuarioModel getUniqUser (String id) {
        Optional<UsuarioModel> user = UsuarioRepo.findById(Byte.parseByte(id));
        if(user.isPresent()) {
            return user.get();
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Usuario no encontrado.");
        }
    }
    
    public String loginSession (String username, HttpSession session) {
        Optional<UsuarioModel> user = UsuarioRepo.findByUsername(username);
        if(user.isPresent()) {
            session.setAttribute("username", user.get().getUsername());
            session.setAttribute("admin", user.get().getAdmin());
            session.setAttribute("administrativo", user.get().getAdministrativo());
            String sessionId = session.getId();
            return sessionId;

        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Usuario no encontrado.");
        }
    }

    public String getSession (HttpSession session) {
        SessionCheck newSession = new SessionCheck(session);
        return newSession.username + " " + newSession.admin + " " + newSession.administrativo;
        
    }

    public String logOutSession (HttpSession session) {
        session.invalidate();
        return "Logout Complete";
    }
}
