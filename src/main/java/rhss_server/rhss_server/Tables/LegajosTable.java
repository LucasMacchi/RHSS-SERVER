package rhss_server.rhss_server.Tables;

import java.time.LocalDate;

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
    private String direccion;
    private LocalDate fecha_egreso;
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
    public String getDireccion() {
        return direccion;
    }
    public LocalDate getFecha_egreso() {
        return fecha_egreso;
    }
    public void setCuil(long cuil) {
        this.cuil = cuil;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void setFecha_egreso(LocalDate fecha_egreso) {
        this.fecha_egreso = fecha_egreso;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public void setLegajo(short legajo) {
        this.legajo = legajo;
    }
    public void setSector(String sector) {
        this.sector = sector;
    }

}
