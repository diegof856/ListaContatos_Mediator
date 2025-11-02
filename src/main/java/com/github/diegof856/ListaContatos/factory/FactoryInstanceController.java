package com.github.diegof856.ListaContatos.factory;

import com.github.diegof856.ListaContatos.commands.DeleteContactCommand;
import com.github.diegof856.ListaContatos.commands.UpdateContactCommand;
import com.github.diegof856.ListaContatos.queries.GetAllContactQuery;
import com.github.diegof856.ListaContatos.queries.GetContactByIdQuery;

public interface FactoryInstanceController {
    UpdateContactCommand createUpdateCommand(String id, UpdateContactCommand command);
    DeleteContactCommand createDeleteCommnad(String id);
    GetAllContactQuery createGetAllQuery();
    GetContactByIdQuery createGetContactById(String id);
}
