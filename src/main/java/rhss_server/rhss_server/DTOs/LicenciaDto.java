package rhss_server.rhss_server.DTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class LicenciaDto {
    
    @NotNull(message = "Ingrese un legajo valido.")
    @Min(value = 1)
    public int legajo;

    @NotNull(message = "Fecha de salida no valida.")
    public LocalDate fecha_salida;

    @NotNull(message = "Fecha de entrada no valida.")
    public LocalDate fecha_entrada;

    @NotEmpty(message = "Necesita una causa o justificacion.")
    public String causa;

    @NotNull(message = "Necesita una novedad.")
    @Min(value = 1)
    public long novedad;

    @NotEmpty(message = "Necesita una categoria valida.")
    public String categoria;


}
