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

import jakarta.validation.Valid;
import rhss_server.rhss_server.DTOs.NovedadDto;
import rhss_server.rhss_server.Services.NovedadesService;
import rhss_server.rhss_server.Tables.NovedadesModel;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/novedad")
@Validated
public class NovedadControllers {
    
    @Autowired
    private NovedadesService service;

    @GetMapping("/ping")
    public String getUsuarioPing () {
        LocalDateTime current = LocalDateTime.now();
        return "Novedad Route pinged at "+current;
    }
    
    @PostMapping("/create")
    public String createNovedad(@Valid @RequestBody NovedadDto data) {
        return this.service.postNovedad(data);
    }

    @GetMapping("/all")
    public List<NovedadesModel> getAllNovedades() {
        return this.service.getAllNov();
    }

    @GetMapping("/uniq/{id}")
    public NovedadesModel getMethodName(@PathVariable String id) {
        return this.service.getNov(Long.parseLong(id));
    }
    
    
    

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions
    (MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
    
}
