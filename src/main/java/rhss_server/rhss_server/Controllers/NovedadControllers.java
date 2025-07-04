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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import rhss_server.rhss_server.DTOs.NovedadDto;
import rhss_server.rhss_server.DTOs.NovedadFilterDto;
import rhss_server.rhss_server.Services.NovedadesService;
import rhss_server.rhss_server.Tables.NovedadesModel;
import rhss_server.rhss_server.Utils.NovLegajo;

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
    public String createNovedad(@RequestParam("causa") String causa, @RequestParam("solicitante") String solicitante,
    @RequestParam("empresa_id") byte empresa_id, @RequestParam("legajo") short legajo,
    @RequestParam("categoria") String categoria, @RequestParam("email") String email,
    @RequestParam("telefono") String telefono, @RequestParam(value = "archivos",required = false) List<MultipartFile> adjuntos) {
        NovedadDto data = new NovedadDto();
        data.causa = causa;
        data.solicitante = solicitante;
        data.empresa_id = empresa_id;
        data.legajo = legajo;
        data.categoria = categoria;
        data.email = email;
        data.telefono = telefono;
        System.err.println("ADJUNTOS ===> "+adjuntos);
        return this.service.postNovedad(data, adjuntos);
    }

    @PostMapping("/all")
    public List<NovedadesModel> getAllNovedades(@Valid @RequestBody NovedadFilterDto data) {
        return this.service.getAllNov(data);
    }
    @PostMapping("/allsup")
    public List<NovedadesModel> getAllNovedadesSup(@Valid @RequestBody NovedadFilterDto data) {
        return this.service.getAllNovSup(data);
    }

    @GetMapping("/uniq/{id}")
    public NovLegajo getUniqNov(@PathVariable String id) {
        return this.service.getNov(Long.parseLong(id));
    }

    @GetMapping("/legajo/{legajo}")
    public List<NovedadesModel> getNovByLegajo(@PathVariable String legajo) {
        return this.service.getLegNov(Long.parseLong(legajo));
    }

    @GetMapping("/nro/{nro}")
    public List<NovedadesModel> getByNro(@PathVariable String nro) {
        return this.service.getNroNov(nro);
    }
    
    @GetMapping("/today")
    public List<NovedadesModel> getNovedadesToday() {
        return this.service.getTodayNov();
    }

    @GetMapping("/categories")
    public String[] getCategories() {
        return this.service.getCategories();
    }

    @PatchMapping("/state/{id}")
    public String changeState (@PathVariable long id ) {
        return this.service.changeStateNov(id);
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
