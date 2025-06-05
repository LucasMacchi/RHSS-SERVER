package rhss_server.rhss_server.Tables;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "glpi_rhss_novedad", schema = "rrhh")
public class NovedadesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long novedad_id;
    private String solicitante;
    private short legajo;
    private String causa;
    private byte empresa_id;
    private int usuario_id;
    private LocalDate fecha_creacion;

    public void setCausa(String causa) {
        this.causa = causa;
    }
    public void setEmpresa_id(byte empresa_id) {
        this.empresa_id = empresa_id;
    }
    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }
    public void setLegajo(short legajo) {
        this.legajo = legajo;
    }
    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }
    public String getCausa() {
        return causa;
    }
    public LocalDate getFecha_creacion() {
        return fecha_creacion;
    }
    public short getLegajo() {
        return legajo;
    }
    public String getSolicitante() {
        return solicitante;
    }

}
