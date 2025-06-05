package rhss_server.rhss_server.DTOs;

import jakarta.validation.constraints.NotEmpty;

public class EmpresaDto {
    @NotEmpty(message = "Ingrese un nombre valido")
    public String nombre;
}
