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

    @Value("${RAILWAY_VOLUME_MOUNT_PATH}")
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

    
    public String dataUploader (MultipartFile file, String folder) {
        final String path = pathToSave+'/'+folder;
        try {
            File dir = new File(path);
            System.out.println("DIR: "+dir.getAbsolutePath()+" / FILENAME: "+file.getOriginalFilename());
            if(!dir.exists()) {
                Boolean res = dir.mkdir();
                System.out.println("DIRCTORY CREATED: "+res);
            }
            System.out.println("Puede escribirse "+ dir.canWrite());
            Path filepPath = Paths.get(path, file.getOriginalFilename());
            Files.write(filepPath, file.getBytes());
            System.out.println("FILE SAVED");
        } catch (Exception e) {
            System.out.println("ERROR TO SAVE");
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatusCode.valueOf(404),"No se pudo cargar el archivo.");
        }
        return "Uploaded";
    }

}
