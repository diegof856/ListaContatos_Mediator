package com.github.diegof856.ListaContatos.commands;

import com.github.diegof856.ListaContatos.commands.dto.AddressDTO;
import com.github.diegof856.ListaContatos.entity.Contact;
import com.github.diegof856.ListaContatos.mediator.Command;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record UpdateContactCommand(UUID id, String nome, String telefone, String email, LocalDate dataNascimento, List<AddressDTO> enderecos) implements Command<Void> {
}
