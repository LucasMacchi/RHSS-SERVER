package rhss_server.rhss_server.Controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import rhss_server.rhss_server.DTOs.RegisterUserDto;
import rhss_server.rhss_server.Services.UserService;
import rhss_server.rhss_server.Tables.UsuarioModel;
import rhss_server.rhss_server.Utils.SessionCheck;


@RestController
@RequestMapping("/usuario")
@Validated
public class UserControllers {

    @Autowired
    private UserService service;

    @PostMapping("/login/{username}")
    public String login(@PathVariable String username, HttpSession session) {
        return this.service.loginSession(username, session);
    }
    @PostMapping("/session")
    public SessionCheck getSession(HttpSession session) {
        return this.service.getSession(session);
    }
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        return this.service.logOutSession(session);
    }
    @GetMapping("/ping")
    public String getUsuarioPing () {
        LocalDateTime current = LocalDateTime.now();
        return "User Route pinged at "+current;
    }
    @GetMapping("/all/usuarios")
    public List<UsuarioModel> getAllUsuarios () {
        return this.service.getAllUsuarios();
    }
    @GetMapping("/uniq/{id}")
    public UsuarioModel getUser (@PathVariable String id) {
        return this.service.getUniqUser(id);
    }
    @PostMapping("/registrar")
    public String registerUser (@Valid @RequestBody RegisterUserDto data) {
        return this.service.registerUser(data);
    }
    @PatchMapping("/activate/{id}")
    public String actiaveUser (@PathVariable String id) {
        return this.service.activateUser(id);
    }
    @PatchMapping("/deactivate/{id}")
    public String deactiaveUser (@PathVariable String id) {
        return this.service.deactivateUser(id);
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}