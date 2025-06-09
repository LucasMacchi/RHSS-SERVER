package rhss_server.rhss_server.DTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class PersonalDto {

    @NotNull(message = "Es necesario un legajo.")
    @Min(value = 1)
    public int legajo;

    @NotEmpty(message = "Necesita una categoria valida.")
    public String categoria;

    @NotNull(message = "Fecha de ocurrencia no valida.")
    public LocalDate fecha_ocurrido;

    @NotEmpty(message = "Necesita una causa o justificacion.")
    public String causa;

    @NotNull(message = "Necesita una novedad.")
    @Min(value = 1)
    public int novedad_id;
}
