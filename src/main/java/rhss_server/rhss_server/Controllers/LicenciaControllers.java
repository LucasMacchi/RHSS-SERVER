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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import rhss_server.rhss_server.DTOs.LicenciaDto;
import rhss_server.rhss_server.Services.LicenciaService;
import rhss_server.rhss_server.Tables.LicenciaTable;
import rhss_server.rhss_server.Utils.SessionCheck;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/licencia")
@Validated
public class LicenciaControllers {

    @Autowired
    private LicenciaService service;
    
    @GetMapping("/ping")
    public String getUsuarioPing () {
        LocalDateTime current = LocalDateTime.now();
        return "Licencia Route pinged at "+current;
    }

    @PostMapping("/create")
    public String postLicencia(@Valid @RequestBody LicenciaDto data, HttpSession session) {
        new SessionCheck(session);
        return this.service.postLicencia(data);
    }

    @GetMapping("/all")
    public List<LicenciaTable> getAllLic(HttpSession session) {
        new SessionCheck(session);
        return this.service.getAllLicencias();
    }

    @GetMapping("/legajo/{legajo}")
    public List<LicenciaTable> getByLegajoLic(@PathVariable String legajo, HttpSession session) {
        new SessionCheck(session);
        return this.service.getAllByLegajo(Integer.parseInt(legajo));
    }

    @GetMapping("/novedad/{novedad}")
    public List<LicenciaTable> getByNovLic(@PathVariable String novedad, HttpSession session) {
        new SessionCheck(session);
        return this.service.getAllByNov(Long.parseLong(novedad));
    }

    @GetMapping("/id/{id}")
    public LicenciaTable getById(@PathVariable String id, HttpSession session) {
        new SessionCheck(session);
        return this.service.getById(Long.parseLong(id));
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
