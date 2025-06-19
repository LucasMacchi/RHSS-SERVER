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
import rhss_server.rhss_server.DTOs.SancionDto;
import rhss_server.rhss_server.Services.SancionService;
import rhss_server.rhss_server.Tables.SancionModel;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/sancion")
@Validated
public class SancionControllers {

    @Autowired
    private SancionService service;
    

    @GetMapping("/ping")
    public String getUsuarioPing () {
        LocalDateTime current = LocalDateTime.now();
        return "Sancion Route pinged at "+current;
    }

    @PostMapping("/create")
    public String createSancion(@Valid @RequestBody SancionDto data,HttpSession session) {
        return this.service.postSancion(data);
    }

    @GetMapping("/all")
    public List<SancionModel> getMethodName(HttpSession session) {
        return this.service.getAllSanciones();
    }

    @GetMapping("/legajo/{id}")
    public List<SancionModel> getSancionesLegajo(@PathVariable String id,HttpSession session) {
        return this.service.getSancionLegajo(Long.parseLong(id));
    }

    @GetMapping("/novedad/{id}")
    public List<SancionModel> getSancionNov(@PathVariable String id,HttpSession session) {
        return this.service.getSancionNovedad(Long.parseLong(id));
    }

    @GetMapping("/uniq/{id}")
    public SancionModel getSancionId(@PathVariable String id,HttpSession session) {
        return this.service.getSancionId(Long.parseLong(id));
    }

    @GetMapping("/categories")
    public String[] getCategories(HttpSession session) {
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
