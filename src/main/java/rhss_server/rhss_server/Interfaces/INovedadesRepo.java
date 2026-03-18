package rhss_server.rhss_server.Interfaces;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import rhss_server.rhss_server.Tables.NovedadesModel;

public interface INovedadesRepo extends JpaRepository<NovedadesModel, Long> {

    List<NovedadesModel> findByFecha(LocalDate current);

    List<NovedadesModel> findByNumero(String nro);

    List<NovedadesModel> findByLegajo(Long legajo);
    
    @Query(value = "select * from glpi_rhss_novedad where legajo = ?1 AND empresa_id = ?2;", nativeQuery = true)
    List<NovedadesModel> findByLegajoAndEmpresaId(Long legajo,byte empresa);
}