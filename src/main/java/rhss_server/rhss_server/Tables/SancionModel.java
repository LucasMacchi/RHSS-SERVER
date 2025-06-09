package rhss_server.rhss_server.Tables;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "glpi_rhss_sancion", schema = "rrhh")
public class SancionModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sancion_id;
    private int legajo;
    private String tipo;
    private LocalDate fecha;
    private LocalDate fecha_inicio;
    private LocalDate fecha_final;
    private String causa;

    @Column(name = "novedad_id")
    private int novedad;

    public void setCausa(String causa) {
        this.causa = causa;
    }
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setFecha_final(LocalDate fecha_final) {
        this.fecha_final = fecha_final;
    }
    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public void setNovedad_id(int novedad) {
        this.novedad = novedad;
    }
    public String getCausa() {
        return causa;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public LocalDate getFecha_final() {
        return fecha_final;
    }
    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }
    public int getLegajo() {
        return legajo;
    }
    public String getTipo() {
        return tipo;
    }
    public int getNovedad_id() {
        return novedad;
    }
}
