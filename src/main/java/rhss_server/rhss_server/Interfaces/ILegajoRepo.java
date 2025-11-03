package rhss_server.rhss_server.Interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rhss_server.rhss_server.Tables.LegajosTable;

public interface ILegajoRepo extends JpaRepository<LegajosTable, Long>{

    List<LegajosTable> findByFullnameContaining(String nombre);

    List<LegajosTable> findByEmpresa(String empresa);

    List<LegajosTable> findByLegajoAndEmpresa (long id,String empresa);

    @Query(value = "select * from glpi_rhss_legajos where fecha_egreso > NOW();", nativeQuery = true)
    List<LegajosTable> findAllActivated();

}
