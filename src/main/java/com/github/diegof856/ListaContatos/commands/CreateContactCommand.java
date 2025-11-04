package com.github.diegof856.ListaContatos.commands;

import com.github.diegof856.ListaContatos.commands.dto.AddressDTO;
import com.github.diegof856.ListaContatos.entity.Contact;
import com.github.diegof856.ListaContatos.mediator.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;
import java.util.List;

@Schema(name = "Contato")
public record CreateContactCommand(
        @NotBlank(message = "campo obrigatorio")
        String nome,
        @NotBlank(message = "campo obrigatorio")
        String telefone,
        @Email(message = "email invalido")
        @NotBlank(message = "campo obrigatorio")
        String email,
        @Past(message = "Não pode ser uma data futura")
        @NotNull(message = "campo obrigatorio")
        @Schema(name = "data_de_nascimento")
        LocalDate dataNascimento,
        List<AddressDTO> enderecos) implements Command<Contact> {
}
