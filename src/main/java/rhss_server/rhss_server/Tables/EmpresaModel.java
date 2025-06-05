package rhss_server.rhss_server.Tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "glpi_rhss_empresas", schema = "rrhh")
public class EmpresaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private byte empresa_id;

    @Column(name = "nombre")
    private String nombre;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public long getEmpresa_id() {
        return empresa_id;
    }
    public String getNombre() {
        return nombre;
    }
}
