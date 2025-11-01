package com.github.diegof856.ListaContatos.mappers.implFactory;

import com.github.diegof856.ListaContatos.commands.CreateContactCommand;
import com.github.diegof856.ListaContatos.commands.UpdateContactCommand;
import com.github.diegof856.ListaContatos.commands.dto.AddressDTO;
import com.github.diegof856.ListaContatos.commands.dto.ContactResponseDTO;
import com.github.diegof856.ListaContatos.entity.Address;
import com.github.diegof856.ListaContatos.entity.Contact;
import com.github.diegof856.ListaContatos.mappers.Factory;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.UUID;

@Component
public class FactoryImpl implements Factory {
    @Override
    public Address createAddress(AddressDTO addressDTO) {
        return new Address(addressDTO.rua(),addressDTO.numero(),addressDTO.cidade(),addressDTO.estado());

    }

    @Override
    public AddressDTO createAddressDTO(Address address) {
       return new AddressDTO(address.getStreet(), address.getNumber(),address.getCity(),address.getState());
    }

    @Override
    public Contact createContact(CreateContactCommand command) {
        return new Contact(command.dataNascimento(), command.email(), command.nome(), UUID.randomUUID(),command.telefone());
    }

    @Override
    public Contact createContactUpdate(UpdateContactCommand command) {
        return new Contact(command.dataNascimento(), command.email(), command.nome(), command.id(),command.telefone());
    }

    @Override
    public ContactResponseDTO createContactDTO(Contact contact, List<AddressDTO> addressDTOs) {
        return new ContactResponseDTO(contact.getId().toString(),contact.getName(),contact.getPhone(),contact.getEmail(),contact.getDate(),addressDTOs);
    }


}
