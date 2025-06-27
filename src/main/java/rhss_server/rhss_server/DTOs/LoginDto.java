package rhss_server.rhss_server.DTOs;

import jakarta.validation.constraints.NotEmpty;

public class LoginDto {
    @NotEmpty(message = "Ingrese un nombre de usuario valido")
    public String username;
    @NotEmpty(message = "Ingrese una contrasena valida.")
    public String password;
}
