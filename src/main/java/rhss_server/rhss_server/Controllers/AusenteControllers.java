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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import rhss_server.rhss_server.DTOs.AusenteDto;
import rhss_server.rhss_server.Services.AusenteService;
import rhss_server.rhss_server.Tables.AusenteModel;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/ausente")
@Validated
public class AusenteControllers {

    @Autowired
    AusenteService service;

    @GetMapping("/ping")
    public String getUsuarioPing () {
        LocalDateTime current = LocalDateTime.now();
        return "Ausente Route pinged at "+current;
    }

    @PostMapping("/create")
    public String createAusente(@Valid @RequestBody AusenteDto data) {
        return this.service.postAusente(data, "Ausente");
    }

    @PostMapping("/late/create")
    public String createLate(@Valid @RequestBody AusenteDto data) {
        return this.service.postAusente(data, "Tardanza");
    }

    @GetMapping("/novedad/{id}")
    public List<AusenteModel> getAusenteNov (@PathVariable String id) {
        return this.service.allAusentesByNovedad(Integer.parseInt(id));
    }

    @GetMapping("/legajo/{id}")
    public List<AusenteModel> getAusenteLeg (@PathVariable String id) {
        return this.service.allAusentesByLegajo(Integer.parseInt(id));
    }
    
    @PatchMapping("/justificar/{id}")
    public String justificarString (@PathVariable String id) {
        return this.service.justifyAusente(Long.parseLong(id));
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
