package com.github.diegof856.ListaContatos.queries;

import com.github.diegof856.ListaContatos.commands.dto.ContactResponseDTO;
import com.github.diegof856.ListaContatos.mediator.Query;

import java.util.UUID;

public record GetContactByIdQuery(UUID id) implements Query<ContactResponseDTO> {
}
