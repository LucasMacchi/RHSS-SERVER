package rhss_server.rhss_server.DTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class AltaDto {
    @NotNull(message = "Necesita una novedad.")
    @Min(value = 1)
    public long novedad;
    @NotNull(message = "Necesita un CUIT valido.")
    @Min(value = 1)
    public long cuit;
    public LocalDate fecha_ingreso;
    @NotEmpty(message = "Necesita una direccion valida.")
    public String direccion;
    @NotNull(message = "Necesita una fecha de nacimiento valido.")
    public LocalDate nacimiento;
    @NotNull(message = "Necesita una jornada valida.")
    @Min(value = 1)
    public short jornada;
    @NotEmpty(message = "Necesita un lugar de trabajo valido.")
    public String lugar;
    @NotEmpty(message = "Ingrese una categoria valida")
    public String categoria;

}
