package rhss_server.rhss_server.Tables;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "glpi_rhss_alta")
public class AltaTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long alta_id;
    @Column(name = "novedad_id")
    private long novedad;
    private long cuit;
    private int legajo;
    private LocalDate fecha = LocalDate.now();
    private LocalDate fecha_ingreso;
    private String direccion;
    private LocalDate nacimiento;
    private short jornada;
    private String lugar;
    private String categoria;
    public long getAlta_id() {
        return alta_id;
    }
    public String getCategoria() {
        return categoria;
    }
    public long getCuit() {
        return cuit;
    }
    public String getDireccion() {
        return direccion;
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public LocalDate getFecha_ingreso() {
        return fecha_ingreso;
    }
    public short getJornada() {
        return jornada;
    }
    public int getLegajo() {
        return legajo;
    }
    public String getLugar() {
        return lugar;
    }
    public LocalDate getNacimiento() {
        return nacimiento;
    }
    public long getNovedad() {
        return novedad;
    }
    public void setCuit(long cuit) {
        this.cuit = cuit;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public void setFecha_ingreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
    public void setJornada(short jornada) {
        this.jornada = jornada;
    }
    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }
    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }
    public void setNovedad(long novedad) {
        this.novedad = novedad;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
