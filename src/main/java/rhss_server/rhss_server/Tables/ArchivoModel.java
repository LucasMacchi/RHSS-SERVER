package rhss_server.rhss_server.Tables;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "glpi_rhss_archivo")
public class ArchivoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long archivo_id;
    private String ruta;
    private String concepto;
    private LocalDate fecha;

    @Column(name = "novedad_id")
    private long novedad;

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
    public void setNovedad(long novedad) {
        this.novedad = novedad;
    }
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }
    public long getArchivo_id() {
        return archivo_id;
    }
    public String getConcepto() {
        return concepto;
    }
    public long getNovedad() {
        return novedad;
    }
    public String getRuta() {
        return ruta;
    }
    public LocalDate getFecha() {
        return fecha;
    }
}
