package rhss_server.rhss_server.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import rhss_server.rhss_server.DTOs.AltaDto;
import rhss_server.rhss_server.Services.AltaService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/alta")
@Validated
public class AltaController {

    @Autowired
    private AltaService service;
    
    @PostMapping("/create")
    public String createAlta(@Valid @RequestBody AltaDto data) {        
        return this.service.postAlta(data);
    }

}
