package rhss_server.rhss_server.Interfaces;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import rhss_server.rhss_server.Tables.UsuarioModel;

public interface IUsuarioRepo extends JpaRepository<UsuarioModel, Byte> {

    Optional<UsuarioModel> findByUsername(String username);
}