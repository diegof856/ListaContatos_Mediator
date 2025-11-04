package com.github.diegof856.ListaContatos.mediator.handlers;

import com.github.diegof856.ListaContatos.commands.CreateContactCommand;
import com.github.diegof856.ListaContatos.entity.Address;
import com.github.diegof856.ListaContatos.entity.Contact;
import com.github.diegof856.ListaContatos.exceptions.ValidateFieldsCustomizerException;
import com.github.diegof856.ListaContatos.mappers.ContactMapper;
import com.github.diegof856.ListaContatos.repository.ContactRepository;

import com.github.diegof856.ListaContatos.validator.ValidatorContact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateContactHandlerTest {

    @Mock
    private ContactRepository repository;

    @Mock
    private ContactMapper mapper;

    @Mock
    private ValidatorContact validatorContact;

    @InjectMocks
    private CreateContactHandler handler;
    Contact contact;
    Address address;
    CreateContactCommand command;
    @BeforeEach
    void setUp(){
        this.address = new Address("Rua mais Flores", "20", "vitoria", "Pe");
        this.contact = new Contact(LocalDate.of(2000, 05, 31), "diego@email.com", "Diego Felipe da silva", UUID.randomUUID(), "81999494566");
        this.command =mock(CreateContactCommand.class);
    }
    @Test
    @DisplayName("Cenário feliz (criação bem-sucedida)")
    void shouldSaveSuccessfully(){
        when(this.mapper.toEntityCreate(this.command)).thenReturn(this.contact);
        when(this.repository.insert(any(Contact.class))).thenReturn(this.contact);
        Contact result = this.handler.handle(this.command);
        verify(this.validatorContact, times(1)).validate(this.contact, null);
        verify(this.repository, times(1)).insert(contact);
        assertEquals(this.contact, result);
    }
    @Test
    @DisplayName("Falha de validação (lança exceção personalizada)")
    void shouldThrowExceptionWhenValidationFails() {
        when(this.mapper.toEntityCreate(this.command)).thenReturn(this.contact);
        doThrow(new ValidateFieldsCustomizerException(null))
                .when(this.validatorContact).validate(this.contact, null);

        assertThrows(ValidateFieldsCustomizerException.class, () -> this.handler.handle(this.command));

        verify(this.validatorContact, times(1)).validate(this.contact, null);
        verify(this.repository, never()).insert((Contact) any());
    }
    @Test
    @DisplayName("Contato persistido possui ID diferente do de entrada")
    void shouldReturnSameInstanceFromRepositoryInsert() {

        Contact inputContact = new Contact();
        inputContact.setName("Maria");
        inputContact.setPhone("11988887777");

        Contact persistedContact = new Contact();
        persistedContact.setId(UUID.randomUUID());
        persistedContact.setName("Maria");
        persistedContact.setPhone("11988887777");

        when(this.mapper.toEntityCreate(this.command)).thenReturn(inputContact);
        when(this.repository.insert(any(Contact.class))).thenReturn(persistedContact);

        Contact result = this.handler.handle(this.command);

        verify(this.repository).insert(inputContact);
        assertEquals(persistedContact, result);
        assertNotEquals(inputContact.getId(), result.getId());
    }




}