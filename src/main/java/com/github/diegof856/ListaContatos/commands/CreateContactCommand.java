package com.github.diegof856.ListaContatos.commands;

import com.github.diegof856.ListaContatos.commands.dto.AddressDTO;
import com.github.diegof856.ListaContatos.mediator.Command;
import com.github.diegof856.ListaContatos.entity.Contact;

import java.time.LocalDate;
import java.util.List;

public record CreateContactCommand(String nome, String telefone, String email, LocalDate dataNascimento, List<AddressDTO> enderecos) implements Command<Contact> {
}
