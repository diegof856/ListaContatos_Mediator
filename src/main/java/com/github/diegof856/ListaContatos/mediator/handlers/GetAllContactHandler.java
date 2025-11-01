package com.github.diegof856.ListaContatos.mediator.handlers;

import com.github.diegof856.ListaContatos.commands.dto.ContactResponseDTO;

import com.github.diegof856.ListaContatos.mappers.ContactMapper;
import com.github.diegof856.ListaContatos.mediator.RequestHandler;
import com.github.diegof856.ListaContatos.queries.GetAllContactQuery;
import com.github.diegof856.ListaContatos.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class GetAllContactHandler implements RequestHandler<GetAllContactQuery, List<ContactResponseDTO>> {

    private final ContactRepository repository;
    private final ContactMapper mapper;
    @Override
    public List<ContactResponseDTO> handle(GetAllContactQuery request) {
        return this.repository.findAll().stream().map(contact -> this.mapper.toDTO(contact)).collect(Collectors.toList());
    }
}
