package rhss_server.rhss_server.Tables;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "glpi_rhss_personal", schema = "rrhh")
public class PersonalTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sancion_id;
    private int legajo;
    private String categoria;
    private LocalDate fecha;
    private LocalDate fecha_ocurrido;
    private String causa;
    private int novedad_id;

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }
    public void setCausa(String causa) {
        this.causa = causa;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setFecha_ocurrido(LocalDate fecha_ocurrido) {
        this.fecha_ocurrido = fecha_ocurrido;
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
    public LocalDate getFecha_ocurrido() {
        return fecha_ocurrido;
    }
    public int getNovedad_id() {
        return novedad_id;
    }
    public int getLegajo() {
        return legajo;
    }
}
