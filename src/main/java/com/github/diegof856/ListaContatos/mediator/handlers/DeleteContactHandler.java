package com.github.diegof856.ListaContatos.mediator.handlers;

import com.github.diegof856.ListaContatos.commands.DeleteContactCommand;
import com.github.diegof856.ListaContatos.entity.Contact;
import com.github.diegof856.ListaContatos.exceptions.NotFoundExceptions;
import com.github.diegof856.ListaContatos.mediator.RequestHandler;
import com.github.diegof856.ListaContatos.repository.ContactRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DeleteContactHandler implements RequestHandler<DeleteContactCommand,Void> {

    private final ContactRepository repository;

    @Override
    public Void handle(DeleteContactCommand request) {
        if(!this.repository.existsById(request.id())){
            throw new NotFoundExceptions("Contato não encontra");
        }
        this.repository.deleteById(request.id());
        return null;
    }
}
