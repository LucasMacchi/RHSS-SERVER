package rhss_server.rhss_server.Services;
import rhss_server.rhss_server.Interfaces.IEmpresasRepo;
import rhss_server.rhss_server.Interfaces.ILegajoRepo;
import rhss_server.rhss_server.Tables.EmpresaModel;
import rhss_server.rhss_server.Tables.LegajosTable;
import rhss_server.rhss_server.DTOs.EmpresaDto;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;;

@Service
public class DataService {

    @Value("${PATH_SAVE_FILES}")
    private String pathToSave;
    @Autowired
    private IEmpresasRepo EmpresaRepo;
    @Autowired
    private ILegajoRepo LegajoRepo;


    public List<EmpresaModel> getAllEmpresas () {
        List<EmpresaModel> empresas = EmpresaRepo.findAll();
        return empresas;
    }

    public String createEmpresa (EmpresaDto body) {
        EmpresaModel newEmpresa = new EmpresaModel();
        newEmpresa.setNombre(body.nombre);
        EmpresaRepo.save(newEmpresa);
        return String.format("Empresa %s Creada",body.nombre);
    }

        public List<LegajosTable> getAllLegajos () {
        List<LegajosTable> legajos = LegajoRepo.findAll();
        return legajos;
    }


    public List<LegajosTable> getLegajos (String nombre) {
        List<LegajosTable> legajos = LegajoRepo.findByFullnameContaining(nombre);
        return legajos;
    }

    
    public String dataUploader (MultipartFile file) {

        try {
            File dir = new File(pathToSave);
            System.out.println("DIR: "+dir.getAbsolutePath());
            if(!dir.exists()) {
                Boolean res = dir.mkdir();
                System.out.println("PASO 3: "+res);
            }
                Path filepPath = Paths.get(pathToSave, file.getOriginalFilename());
                Files.write(filepPath, file.getBytes());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"No se pudo cargar el archivo.");
        }
        return "Uploaded";
    }

}
