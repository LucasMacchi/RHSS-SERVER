package rhss_server.rhss_server.Interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rhss_server.rhss_server.Tables.AltaTable;

public interface IAltaRepo extends JpaRepository<AltaTable, Long> {

    List<AltaTable> findByNovedad(long novedad_id);

}
