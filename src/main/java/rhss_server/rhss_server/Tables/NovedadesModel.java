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
    private long usuario_id;
    private LocalDate fecha_creacion;
    private String numero;

    public NovedadesModel () {
        LocalDate current = LocalDate.now();
        final int nro = (int) Math.floor(Math.random() * 100000);
        this.numero = nro + "-" + current.getYear() + current.getMonthValue() + current.getDayOfMonth();
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }
    public void setEmpresa_id(byte empresa_id) {
        this.empresa_id = empresa_id;
    }
    public void setFecha_creacion(LocalDate fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    public void setUsuario_id(long usuario_id) {
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
    public String getNumero() {
        return numero;
    }
    public long getUsuario_id() {
        return usuario_id;
    }
    public byte getEmpresa_id() {
        return empresa_id;
    }

}
