package rhss_server.rhss_server.Tables;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "glpi_rhss_legajos")
public class LegajosTable {
    
    @Id
    private short legajo;
    private String fullname;
    private long cuil;
    private String sector;
    public long getCuil() {
        return cuil;
    }
    public String getFullname() {
        return fullname;
    }
    public short getLegajo() {
        return legajo;
    }
    public String getSector() {
        return sector;
    }

}
