package com.github.diegof856.ListaContatos.controller.factory.impl;

import com.github.diegof856.ListaContatos.commands.DeleteContactCommand;
import com.github.diegof856.ListaContatos.commands.UpdateContactCommand;
import com.github.diegof856.ListaContatos.controller.factory.FactoryInstance;
import com.github.diegof856.ListaContatos.queries.GetAllContactQuery;
import com.github.diegof856.ListaContatos.queries.GetContactByIdQuery;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class FactoryInstanceImpl implements FactoryInstance {
    @Override
    public UpdateContactCommand createUpdateCommand(String id, UpdateContactCommand command) {
        return new UpdateContactCommand(UUID.fromString(id), command.nome(), command.telefone(), command.email(), command.dataNascimento(), command.enderecos());
    }

    @Override
    public DeleteContactCommand createDeleteCommnad(String id) {
        return new DeleteContactCommand(UUID.fromString(id));
    }

    @Override
    public GetAllContactQuery createGetAllQuery() {
        return new GetAllContactQuery();
    }

    @Override
    public GetContactByIdQuery createGetContactById(String id) {
        return new GetContactByIdQuery(UUID.fromString(id));
    }
}
