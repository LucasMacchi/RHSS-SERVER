package rhss_server.rhss_server.Interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import rhss_server.rhss_server.Tables.PersonalTable;

public interface IPersonalRepo extends JpaRepository<PersonalTable, Long> {}