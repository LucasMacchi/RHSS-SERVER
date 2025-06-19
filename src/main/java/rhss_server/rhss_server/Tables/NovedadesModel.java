package rhss_server.rhss_server.Tables;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import rhss_server.rhss_server.Utils.dateShorter;

@Entity
@Table(name = "glpi_rhss_novedad")
public class NovedadesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long novedad_id;
    private String solicitante;
    private short legajo;
    private String causa;
    private byte empresa_id;
    private long usuario_id;
    @Column(name = "fecha_creacion")
    private LocalDate fecha;
    private String numero;
    private String categoria;

    public NovedadesModel () {
        LocalDate current = LocalDate.now();
        String dateShrt = new dateShorter(current).getMonth();
        final int nro = (int) Math.floor(Math.random() * 100000);
        
        this.numero = nro + "-" + dateShrt;
    }

    public void setCausa(String causa) {
        this.causa = causa;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public void setEmpresa_id(byte empresa_id) {
        this.empresa_id = empresa_id;
    }
    public void setFecha_creacion(LocalDate fecha) {
        this.fecha = fecha;
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
    public LocalDate getFecha() {
        return fecha;
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
    public String getCategoria() {
        return categoria;
    }
    public long getNovedad_id() {
        return novedad_id;
    }

}
