package rhss_server.rhss_server.Tables;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "glpi_rhss_usuarios", schema = "rrhh")
public class UsuarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long usuario_id;

    private String nombre;

    private String apellido;

    private boolean admin;

    private boolean administrativo;

    private String email;

    private byte empresa_id;

    @Column(unique = true)
    private String username;

    private LocalDate fecha_creacion;

    private boolean activado;

    public String getApellido() {
        return this.apellido;
    }
    public String getEmail() {
        return this.email;
    }
    public String getNombre() {
        return this.nombre;
    }
    public byte getEmpresa_id() {
        return this.empresa_id;
    }
    public Boolean getActivado () {
        return this.activado;
    }
    public LocalDate getFecha_creacion() {
        return this.fecha_creacion;
    }
    public Boolean getAdmin () {
        return this.admin;
    }
    public Boolean getAdministrativo () {
        return this.administrativo;
    }
    public String getUsername() {
        return username;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    public void setAdministrativo(boolean administrativo) {
        this.administrativo = administrativo;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setEmpresa_id(byte empresa_id) {
        this.empresa_id = empresa_id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setActivado(boolean activado) {
        this.activado = activado;
    }

}
