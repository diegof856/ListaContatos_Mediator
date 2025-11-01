package com.github.diegof856.ListaContatos.mediator.handlers;

import com.github.diegof856.ListaContatos.commands.UpdateContactCommand;
import com.github.diegof856.ListaContatos.entity.Contact;
import com.github.diegof856.ListaContatos.exceptions.NotFoundExceptions;
import com.github.diegof856.ListaContatos.mappers.ContactMapper;
import com.github.diegof856.ListaContatos.mediator.RequestHandler;
import com.github.diegof856.ListaContatos.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class UpdateContactHandler implements RequestHandler<UpdateContactCommand, Void> {

    private final ContactRepository repository;
    private final ContactMapper mapper;



    @Override
    public Void handle(UpdateContactCommand request) {
        Contact update = this.mapper.toEntityUpdate(request);
        Contact contact = this.repository.findById(request.id()).orElseThrow(() -> new NotFoundExceptions("Contato não encontrado"));
       contact.setDate(update.getDate());
       contact.setEmail(update.getEmail());
       contact.setName(update.getName());
       contact.setPhone(update.getPhone());
       contact.setAddresses(update.getAddresses());
        this.repository.save(contact);
        return null;
    }
}
