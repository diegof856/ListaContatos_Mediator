package com.github.diegof856.ListaContatos.mappers;

import com.github.diegof856.ListaContatos.commands.CreateContactCommand;
import com.github.diegof856.ListaContatos.commands.UpdateContactCommand;
import com.github.diegof856.ListaContatos.commands.dto.ContactResponseDTO;
import com.github.diegof856.ListaContatos.commands.dto.AddressDTO;
import com.github.diegof856.ListaContatos.entity.Address;
import com.github.diegof856.ListaContatos.entity.Contact;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ContactMapper {

    public Contact toEntityCreate(CreateContactCommand command){
        Contact contact = new Contact(command.dataNascimento(), command.email(), command.nome(), UUID.randomUUID(),command.telefone());
        var addresses = command.enderecos().stream().map(addressDTO -> new Address(addressDTO.rua(),addressDTO.numero(),addressDTO.cidade(),addressDTO.estado())).collect(Collectors.toList());
        contact.setAddresses(addresses);
        return contact;
    }
    public Contact toEntityUpdate(UpdateContactCommand command){
        Contact contact = new Contact(command.dataNascimento(), command.email(), command.nome(), command.id(),command.telefone());
        var addresses = command.enderecos().stream().map(addressDTO -> new Address(addressDTO.rua(),addressDTO.numero(),addressDTO.cidade(),addressDTO.estado())).collect(Collectors.toList());
        contact.setAddresses(addresses);
        return contact;
    }

    public ContactResponseDTO toDTO(Contact contact){
        var addressDTOs = contact.getAddresses().stream().map(address -> new AddressDTO(address.getStreet(), address.getNumber(),address.getCity(),address.getState())).collect(Collectors.toList());
       return new ContactResponseDTO(contact.getId().toString(),contact.getName(),contact.getPhone(),contact.getEmail(),contact.getDate(),addressDTOs);
    }
}
