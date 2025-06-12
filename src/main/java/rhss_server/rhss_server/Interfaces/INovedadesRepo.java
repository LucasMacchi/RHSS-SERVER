package rhss_server.rhss_server.Interfaces;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rhss_server.rhss_server.Tables.NovedadesModel;

public interface INovedadesRepo extends JpaRepository<NovedadesModel, Long> {

    List<NovedadesModel> findByFecha(LocalDate current);

    List<NovedadesModel> findByNumero(String nro);}