package rhss_server.rhss_server.Interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rhss_server.rhss_server.Tables.LicenciaTable;

public interface ILicenciaRepo extends JpaRepository<LicenciaTable, Long> {

    List<LicenciaTable> findByLegajo(int legajo);

    List<LicenciaTable> findByNovedad(long novedad);}
