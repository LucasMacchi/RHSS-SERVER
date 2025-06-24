package rhss_server.rhss_server.Interfaces;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rhss_server.rhss_server.Tables.UsuarioModel;

public interface IUsuarioRepo extends JpaRepository<UsuarioModel, Long> {

    Optional<UsuarioModel> findByUsername(String username);
}