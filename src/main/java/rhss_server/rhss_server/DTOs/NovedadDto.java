package rhss_server.rhss_server.DTOs;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class NovedadDto {
    @NotEmpty(message = "Ingrese una causa.")
    public String causa;

    @NotEmpty(message = "Ingrese un Solicitante.")
    public String solicitante;

    @NotNull(message = "Ingrese una Empresa valida.")
    @Min(value = 1)
    public byte empresa_id;

    @NotNull(message = "Ingrese un usuario valido.")
    @Min(value = 1)
    public long usuario_id;

    @NotNull(message = "Ingrese un legajo valido.")
    @Min(value = 1)
    public short legajo;
}
