package com.github.diegof856.ListaContatos.queries;


import com.github.diegof856.ListaContatos.commands.dto.ContactResponseDTO;
import com.github.diegof856.ListaContatos.mediator.Query;


import java.util.List;

public record GetAllContactQuery() implements Query<List<ContactResponseDTO>> {
}
