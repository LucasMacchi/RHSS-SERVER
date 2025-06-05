package rhss_server.rhss_server.Tables;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "glpi_rhss_ausente", schema = "rrhh")
public class AusenteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ausente_id;
    private boolean justificado;
    private LocalDate fecha;
    private LocalDate fecha_ausentada;
    private int novedad_id;
    private int legajo;

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setFecha_ausentada(LocalDate fecha_ausentada) {
        this.fecha_ausentada = fecha_ausentada;
    }
    public void setNovedad_id(int novedad_id) {
        this.novedad_id = novedad_id;
    }
    public void setJustificado(boolean justificado) {
        this.justificado = justificado;
    }
    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public LocalDate getFecha_ausentada() {
        return fecha_ausentada;
    }
    public int getNovedad_id() {
        return novedad_id;
    }
    public boolean getJustificado() {
        return this.justificado;
    }
    public int getLegajo() {
        return legajo;
    }
}
