package rhss_server.rhss_server.Controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import rhss_server.rhss_server.Tables.EmpresaModel;
import rhss_server.rhss_server.Tables.LegajosTable;
import rhss_server.rhss_server.DTOs.DeleteFileDto;
import rhss_server.rhss_server.DTOs.EmpresaDto;
import rhss_server.rhss_server.DTOs.LegajosDto;
import rhss_server.rhss_server.Services.DataService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data")
@Validated
public class DataControllers {

    @Autowired
    private DataService service;

    @GetMapping("/ping")
    public String getUsuarioPing () {
        LocalDateTime current = LocalDateTime.now();
        return "Data Route pinged at "+current;
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file, 
    @RequestParam("concepto") String concepto, @RequestParam("novedad") int novedad,
    @RequestParam("carpeta") String carpeta) {
        return this.service.dataUploader(file, carpeta, concepto, novedad);
    }

    @PostMapping("/download")
    public Resource downloadFile(@RequestParam("url") String url) {
        return this.service.fileDonwload(url);
    }

    @DeleteMapping("/delete")
    public String deleteFile (@Valid @RequestBody DeleteFileDto data) {
        return this.service.deleteFile(data);
    }
    
    @GetMapping("/all/empresas")
    public List<EmpresaModel> allEmpresas () {
        return service.getAllEmpresas();
    }

    @PostMapping("/empresa")
    public String createEmpresa (@Valid @RequestBody EmpresaDto body) {
        return service.createEmpresa(body);
    }

    @PostMapping("/empresa/legajos")
    public List<LegajosTable> getAllLegajos(@Valid @RequestBody LegajosDto data) {
        System.out.println(data.empresa);
        return this.service.getAllLegajos(data.empresa);
    }

    @GetMapping("/legajos/{nombre}")
    public List<LegajosTable> getLegajos(@PathVariable String nombre) {
        return this.service.getLegajos(nombre);
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
