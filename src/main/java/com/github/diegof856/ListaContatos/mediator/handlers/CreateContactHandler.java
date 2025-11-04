package com.github.diegof856.ListaContatos.mediator.handlers;

import com.github.diegof856.ListaContatos.commands.CreateContactCommand;
import com.github.diegof856.ListaContatos.mappers.ContactMapper;
import com.github.diegof856.ListaContatos.mediator.RequestHandler;
import com.github.diegof856.ListaContatos.entity.Contact;
import com.github.diegof856.ListaContatos.repository.ContactRepository;
import com.github.diegof856.ListaContatos.validator.ValidatorContact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateContactHandler implements RequestHandler<CreateContactCommand, Contact> {
    private final ContactRepository repository;
    private final ContactMapper mapper;
    private final ValidatorContact validatorContact;

    @Override
    public Contact handle(CreateContactCommand request) {
        Contact contact = this.mapper.toEntityCreate(request);
        this.validatorContact.validate(contact,null);
        return this.repository.insert(contact);
    }
}
