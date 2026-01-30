package rhss_server.rhss_server.Tables;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "glpi_rhss_categoria")
public class CategoriaTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long categoria_id;
    private String descripcion;
    private boolean licencia;
    private boolean ausente;
    private boolean alta;
    private boolean sancion;
    private boolean personal;

    public long getCategoria_id() {
        return categoria_id;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public boolean getLicencia() {
        return licencia;
    }
    public boolean getAusente() {
        return ausente;
    }
    public boolean getAlta() {
        return alta;
    }
    public boolean getSancion() {
        return sancion;
    }
    public boolean getPersonal() {
        return personal;
    }

}
