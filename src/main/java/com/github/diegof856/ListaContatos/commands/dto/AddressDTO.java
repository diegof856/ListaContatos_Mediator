package com.github.diegof856.ListaContatos.commands.dto;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Endereco")
public record AddressDTO(String rua, String numero, String cidade, String estado) {
}
