package rhss_server.rhss_server.Services;
import rhss_server.rhss_server.Interfaces.IEmpresasRepo;
import rhss_server.rhss_server.Interfaces.ILegajoRepo;
import rhss_server.rhss_server.Tables.EmpresaModel;
import rhss_server.rhss_server.Tables.LegajosTable;
import rhss_server.rhss_server.DTOs.EmpresaDto;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;;

@Service
public class DataService {

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

}
