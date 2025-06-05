package rhss_server.rhss_server.Tables;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "glpi_rhss_licencia", schema = "rrhh")
public class LicenciaTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long licencia_id;
    private int legajo;
    private LocalDate fecha;
    private LocalDate fecha_salida;
    private LocalDate fecha_entrada;
    private String causa;
    private int novedad_id;
    private String categoria;

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void setCausa(String causa) {
        this.causa = causa;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setFecha_entrada(LocalDate fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }
    public void setFecha_salida(LocalDate fecha_salida) {
        this.fecha_salida = fecha_salida;
    }
    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }
    public void setNovedad_id(int novedad_id) {
        this.novedad_id = novedad_id;
    }
    public String getCategoria() {
        return categoria;
    }
    public String getCausa() {
        return causa;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public LocalDate getFecha_entrada() {
        return fecha_entrada;
    }
    public LocalDate getFecha_salida() {
        return fecha_salida;
    }
    public int getLegajo() {
        return legajo;
    }
    public long getLicencia_id() {
        return licencia_id;
    }
    public int getNovedad_id() {
        return novedad_id;
    }
}
