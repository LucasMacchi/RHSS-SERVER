package rhss_server.rhss_server.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class RegisterUserDto {
    @NotEmpty(message = "Ingrese un nombre valido")
    public String nombre;

    @NotEmpty(message = "Ingrese un apellido valido")
    public String apellido;

    @NotEmpty(message = "Ingrese un email valido")
    @Email
    public String email;

    @NotNull(message = "Ingrese una empresa valida")
    @Min(value = 1, message = "Ingrese una empresa valida")
    public byte empresa_id;

    @NotNull(message = "Aclare el rol")
    public boolean administrativo;

    @NotNull(message = "Ingrese un nombre de usuario")
    public String username;

    @NotNull(message = "Ingrese una contrasena valida")
    public String password;
}
