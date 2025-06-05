package rhss_server.rhss_server.Services;
import rhss_server.rhss_server.Interfaces.IEmpresasRepo;
import rhss_server.rhss_server.Tables.EmpresaModel;
import rhss_server.rhss_server.DTOs.EmpresaDto;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

@Service
public class DataService {

    @Autowired
    private IEmpresasRepo EmpresaRepo;

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

}
