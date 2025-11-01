package com.github.diegof856.ListaContatos.mappers;

import com.github.diegof856.ListaContatos.commands.CreateContactCommand;
import com.github.diegof856.ListaContatos.commands.UpdateContactCommand;
import com.github.diegof856.ListaContatos.commands.dto.ContactResponseDTO;
import com.github.diegof856.ListaContatos.entity.Contact;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ContactMapper {

    private final Factory factory;
    public Contact toEntityCreate(CreateContactCommand command){
        Contact contact = this.factory.createContact(command);
        var addresses = command.enderecos().stream().map(this.factory::createAddress).collect(Collectors.toList());
        contact.setAddresses(addresses);
        return contact;
    }
    public Contact toEntityUpdate(UpdateContactCommand command){
        Contact contact = this.factory.createContactUpdate(command);
        var addresses = command.enderecos().stream().map(this.factory::createAddress).collect(Collectors.toList());
        contact.setAddresses(addresses);
        return contact;
    }
    public ContactResponseDTO toDTO(Contact contact){
        var addressDTOs = contact.getAddresses().stream().map(this.factory::createAddressDTO).collect(Collectors.toList());
       return this.factory.createContactDTO(contact,addressDTOs);
    }
}
