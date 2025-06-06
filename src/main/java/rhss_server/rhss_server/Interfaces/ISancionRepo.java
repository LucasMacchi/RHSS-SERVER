package rhss_server.rhss_server.Interfaces;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import rhss_server.rhss_server.Tables.SancionModel;

public interface ISancionRepo extends JpaRepository<SancionModel, Long> {

    List<SancionModel> findByLegajo(long legajo);}
