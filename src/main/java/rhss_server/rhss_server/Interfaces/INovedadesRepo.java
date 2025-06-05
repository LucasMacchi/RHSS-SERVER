package rhss_server.rhss_server.Interfaces;
import org.springframework.data.jpa.repository.JpaRepository;

import rhss_server.rhss_server.Tables.NovedadesModel;

public interface INovedadesRepo extends JpaRepository<NovedadesModel, Long> {}