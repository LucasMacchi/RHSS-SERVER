package rhss_server.rhss_server.Controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import rhss_server.rhss_server.DTOs.PersonalDto;
import rhss_server.rhss_server.Services.PersonalService;
import rhss_server.rhss_server.Tables.PersonalTable;
import rhss_server.rhss_server.Utils.SessionCheck;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/personal")
public class PersonalControllers {

    @Autowired
    private PersonalService service;

    @GetMapping("/ping")
    public String getUsuarioPing () {
        LocalDateTime current = LocalDateTime.now();
        return "Personal Route pinged at "+current;
    }

    @PostMapping("/create")
    public String createPersonal(@RequestBody PersonalDto data,HttpSession session) {
        new SessionCheck(session);
        return this.service.postPersonal(data);
    }

    @GetMapping("/all")
    public List<PersonalTable> getAllPers(HttpSession session) {
        new SessionCheck(session);
        return this.service.getAllPersonal();
    }

    @GetMapping("/legajo/{legajo}")
    public List<PersonalTable> getByLegajo(@PathVariable String legajo,HttpSession session) {
        new SessionCheck(session);
        return this.service.getByLegajo(Integer.parseInt(legajo));
    }

    @GetMapping("/novedad/{id}")
    public List<PersonalTable> getByNov(@PathVariable String id,HttpSession session) {
        new SessionCheck(session);
        return this.service.getByNovedad(Long.parseLong(id));
    }

    @GetMapping("/categories")
    public String[] getCategories(HttpSession session) {
        new SessionCheck(session);
        return this.service.getCategories();
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
