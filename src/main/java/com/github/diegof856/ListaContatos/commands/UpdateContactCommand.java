package com.github.diegof856.ListaContatos.commands;

import com.github.diegof856.ListaContatos.commands.dto.AddressDTO;
import com.github.diegof856.ListaContatos.entity.Contact;
import com.github.diegof856.ListaContatos.mediator.Command;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record UpdateContactCommand(
        UUID id,
        @NotBlank(message = "campo obrigatorio")
        String nome,
        @NotBlank(message = "campo obrigatorio")
        String telefone,
        @NotBlank(message = "campo obrigatorio")
        String email,
        @NotNull(message = "campo obrigatorio")
        LocalDate dataNascimento,
        List<AddressDTO> enderecos) implements Command<Void> {
}
