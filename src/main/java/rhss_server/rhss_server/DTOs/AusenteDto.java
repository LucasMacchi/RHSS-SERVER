package rhss_server.rhss_server.DTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class AusenteDto {
    @NotEmpty(message = "Necesita una causa o justificacion.")
    public String causa;

    @NotNull(message = "Fecha ausente no valida.")
    public LocalDate fecha_ausente;

    @NotNull(message = "Necesita una novedad.")
    @Min(value = 1)
    public long novedad_id;

    @NotNull(message = "Es necesario un legajo.")
    @Min(value = 1)
    public short legajo;

    public boolean justificado;
}
