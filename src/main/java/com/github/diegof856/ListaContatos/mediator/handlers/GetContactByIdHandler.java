package com.github.diegof856.ListaContatos.mediator.handlers;

import com.github.diegof856.ListaContatos.commands.dto.ContactResponseDTO;
import com.github.diegof856.ListaContatos.entity.Contact;
import com.github.diegof856.ListaContatos.exceptions.NotFoundExceptions;
import com.github.diegof856.ListaContatos.mappers.ContactMapper;
import com.github.diegof856.ListaContatos.mediator.RequestHandler;
import com.github.diegof856.ListaContatos.queries.GetContactByIdQuery;
import com.github.diegof856.ListaContatos.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetContactByIdHandler implements RequestHandler<GetContactByIdQuery, ContactResponseDTO> {
    private final ContactRepository repository;
    private final ContactMapper mapper;

    @Override
    public ContactResponseDTO handle(GetContactByIdQuery request) {
        Contact contact = this.repository.findById(request.id()).orElseThrow(() -> new NotFoundExceptions("Contato não encontra"));
        return this.mapper.toDTO(contact);
    }
}
