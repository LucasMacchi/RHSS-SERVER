package rhss_server.rhss_server.Interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rhss_server.rhss_server.Tables.ArchivoModel;

public interface IArchivoRepo extends JpaRepository<ArchivoModel, Long> {
        List<ArchivoModel> findByNovedad(long novedad);

}
