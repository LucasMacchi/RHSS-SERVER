package rhss_server.rhss_server.Services;

import java.time.LocalDate;
import java.util.List;
import rhss_server.rhss_server.Utils.SessionCheck;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpSession;
import rhss_server.rhss_server.DTOs.LoginDto;
import rhss_server.rhss_server.DTOs.RegisterUserDto;
import rhss_server.rhss_server.Interfaces.IEmpresasRepo;
import rhss_server.rhss_server.Interfaces.IUsuarioRepo;
import rhss_server.rhss_server.Tables.EmpresaModel;
import rhss_server.rhss_server.Tables.UsuarioModel;

@Service
public class UserService {

    @Autowired
    private IUsuarioRepo UsuarioRepo;
    @Autowired
    private IEmpresasRepo EmpresaRepo;

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
        user.setPassword(data.password);
        UsuarioRepo.save(user);
        return "Usuario "+data.username+" creado";
    }

    public String activateUser (String id) {
        UsuarioModel user = UsuarioRepo.findById(Long.parseLong(id)).get();
        user.setActivado(true);
        UsuarioRepo.save(user);
        return "Usuario Activado";
    }

    public String deactivateUser (String id) {
        UsuarioModel user = UsuarioRepo.findById(Long.parseLong(id)).get();
        user.setActivado(false);
        UsuarioRepo.save(user);
        return "Usuario Desactivado";
    }

    public UsuarioModel getUniqUser (String id) {
        UsuarioModel user = UsuarioRepo.findById(Long.parseLong(id)).get();
        return user;
    }
    
    public String loginSession (LoginDto data, HttpSession session) {
        UsuarioModel user = UsuarioRepo.findByUsername(data.username).get();
        EmpresaModel empresas = EmpresaRepo.findById(user.getEmpresa_id()).get();
        System.out.println(empresas.getNombre());
        if(user.getActivado() && user.getUsername().equals(data.username) &&
        user.getPassword().equals(data.password)) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("admin", user.getAdmin());
            session.setAttribute("empresa", empresas.getNombre());
            session.setAttribute("administrativo", user.getAdministrativo());
            String sessionId = session.getId();
            return sessionId;
        }
        else {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Usuario no encontrado.");
        }
    }

    public SessionCheck getSession (HttpSession session) {
        try {
            SessionCheck newSession = new SessionCheck(session);
            return newSession;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"Usuario sin sesion.");
        }

    }

    public String logOutSession (HttpSession session) {
        session.invalidate();
        return "Logout Complete";
    }
}
