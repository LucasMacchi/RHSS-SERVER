package rhss_server.rhss_server.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class NovedadDto {
    @NotEmpty(message = "Ingrese una causa.")
    public String causa;

    @NotEmpty(message = "Ingrese un Solicitante.")
    public String solicitante;

    public byte empresa_id;

    @NotNull(message = "Ingrese un legajo valido.")
    @Min(value = 1)
    public short legajo;

    @NotEmpty(message = "Necesita una categoria valida.")
    public String categoria;

    @NotEmpty(message = "Necesita un email valido.")
    public String email;

    @NotEmpty(message = "Necesita un telefono valido.")
    public String telefono;
}
