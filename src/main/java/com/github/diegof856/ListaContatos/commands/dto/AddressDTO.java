package com.github.diegof856.ListaContatos.commands.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Endereco")
public record AddressDTO(
        @NotBlank
        String rua,
        @NotBlank
        String numero,
        @NotBlank
        String cidade,
        @NotBlank
        String estado) {
}
