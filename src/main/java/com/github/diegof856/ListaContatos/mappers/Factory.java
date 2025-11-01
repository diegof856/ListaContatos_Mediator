package com.github.diegof856.ListaContatos.mappers;

import com.github.diegof856.ListaContatos.commands.CreateContactCommand;
import com.github.diegof856.ListaContatos.commands.UpdateContactCommand;
import com.github.diegof856.ListaContatos.commands.dto.AddressDTO;
import com.github.diegof856.ListaContatos.commands.dto.ContactResponseDTO;
import com.github.diegof856.ListaContatos.entity.Address;
import com.github.diegof856.ListaContatos.entity.Contact;
import com.github.diegof856.ListaContatos.mediator.Request;

import java.util.List;

public interface Factory {
    Address createAddress(AddressDTO addressDTO);
    AddressDTO createAddressDTO(Address address);
    Contact createContact(CreateContactCommand command);
    Contact createContactUpdate(UpdateContactCommand command);

    ContactResponseDTO createContactDTO(Contact contact, List<AddressDTO> addressDTOs);
}
