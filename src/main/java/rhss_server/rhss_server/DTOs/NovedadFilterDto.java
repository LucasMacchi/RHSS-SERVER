package rhss_server.rhss_server.DTOs;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class NovedadFilterDto {
    public String solicitante;
    @NotNull(message = "empresa_id invalido")
    public byte empresa_id;
    public String categoria;
    @NotNull(message = "fecha_inicio invalido")
    public LocalDate fecha_inicio;
    @NotNull(message = "fecha_final invalido")
    public LocalDate fecha_final;
}