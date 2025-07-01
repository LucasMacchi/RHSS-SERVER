package rhss_server.rhss_server.DTOs;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
public class DeleteFileDto {
    @NotNull(message = "Necesita una id ade archivo valido.")
    @Min(value = 1)
    public long archivo_id;
    @NotEmpty(message = "Necesita una direccion valida.")
    public String url;

}
