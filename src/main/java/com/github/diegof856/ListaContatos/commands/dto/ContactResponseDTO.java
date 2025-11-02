package com.github.diegof856.ListaContatos.commands.dto;


import com.github.diegof856.ListaContatos.entity.Contact;
import com.github.diegof856.ListaContatos.mediator.Query;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.util.List;

@Schema(name = "Contato_resposta")
public record ContactResponseDTO(String id, String nome, String telefone, String email, LocalDate dataNascimento, List<AddressDTO> enderecos)implements Query<Contact> {
}
