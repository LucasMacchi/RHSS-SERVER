package rhss_server.rhss_server.Interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rhss_server.rhss_server.Tables.PersonalTable;

public interface IPersonalRepo extends JpaRepository<PersonalTable, Long> {

    List<PersonalTable> findByLegajo(int legajo);

    List<PersonalTable> findByNovedad(long novedad);}