package rhss_server.rhss_server.DTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SancionDto {
    @NotNull(message = "Ingrese un Legajo valido.")
    @Min(value = 1, message = "Ingrese un legajo valido.")
    public int legajo;

    @NotEmpty(message = "Tipo invalido")
    public String tipo;

    public LocalDate fecha_inicio;

    public LocalDate fecha_final;

    @NotEmpty(message = "Ingrese una causa.")
    public String causa;

    @NotNull(message = "Tiene que ser una novedad valida.")
    public long novedad_id;
}
