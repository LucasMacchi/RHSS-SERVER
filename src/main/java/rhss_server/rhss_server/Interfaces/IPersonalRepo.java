package rhss_server.rhss_server.Interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rhss_server.rhss_server.Tables.PersonalTable;

public interface IPersonalRepo extends JpaRepository<PersonalTable, Long> {

    List<PersonalTable> findByLegajo(int legajo);

    Optional<PersonalTable> findByNovedad(long novedad);}