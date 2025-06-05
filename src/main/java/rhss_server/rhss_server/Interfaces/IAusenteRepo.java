package rhss_server.rhss_server.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import rhss_server.rhss_server.Tables.AusenteModel;

public interface IAusenteRepo extends JpaRepository<AusenteModel, Long> {}
