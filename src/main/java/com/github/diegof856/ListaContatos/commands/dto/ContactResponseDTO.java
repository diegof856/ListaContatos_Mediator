package com.github.diegof856.ListaContatos.commands.dto;


import com.github.diegof856.ListaContatos.entity.Contact;
import com.github.diegof856.ListaContatos.mediator.Query;

import java.time.LocalDate;
import java.util.List;

public record ContactResponseDTO(String id, String nome, String telefone, String email, LocalDate dataNascimento, List<AddressDTO> enderecos)implements Query<Contact> {
}
