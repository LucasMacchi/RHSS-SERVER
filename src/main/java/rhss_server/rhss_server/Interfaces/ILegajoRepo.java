package rhss_server.rhss_server.Interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rhss_server.rhss_server.Tables.LegajosTable;

public interface ILegajoRepo extends JpaRepository<LegajosTable, Short>{

    List<LegajosTable> findByFullnameContaining(String nombre);}
