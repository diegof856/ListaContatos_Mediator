package com.github.diegof856.ListaContatos;

import com.github.diegof856.ListaContatos.commands.dto.AddressDTO;
import com.github.diegof856.ListaContatos.commands.dto.ContactResponseDTO;
import com.github.diegof856.ListaContatos.controller.ContactListController;
import com.github.diegof856.ListaContatos.controller.factory.FactoryInstance;
import com.github.diegof856.ListaContatos.entity.Address;
import com.github.diegof856.ListaContatos.entity.Contact;
import com.github.diegof856.ListaContatos.exceptions.NotFoundExceptions;
import com.github.diegof856.ListaContatos.mediator.Mediator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.hamcrest.Matchers.containsString;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;


@WebMvcTest(ContactListController.class)
class ListaContatosApplicationTests {
    @Autowired
    MockMvc mvc;

    @MockitoBean
    Mediator mediator;
    @MockitoBean
    FactoryInstance factoryInstance;


    Address address;
    Address address2;
    List<Address> addressList;
    Contact contact;
    Contact contact2;
    String json;
    String invalidJson;
    List<ContactResponseDTO> listContacts;
    ContactResponseDTO contactResponseDTO;
    ContactResponseDTO contactResponseDTO2;
    List<AddressDTO> listAddressDTOS;
    AddressDTO addressDTO;
    AddressDTO addressDTO2;
    @BeforeEach
    void setUp() {
        this.address = new Address("Rua mais Flores", "20", "vitoria", "Pe");
        this.address2 = new Address("Rua das Flores", "25", "vitoria", "sp");

        this.addressList = List.of(this.address);
        this.contact = new Contact(LocalDate.of(2000, 05, 31), "diego@email.com", "Diego Felipe da silva", UUID.randomUUID(), "99949-4566");
        this.contact.setAddresses(this.addressList);

        this.addressList = List.of(this.address,this.address2);
        this.contact2 = new Contact(LocalDate.of(2005, 06, 03), "diego@email.com", "Diego", UUID.randomUUID(), "99949-3566");
        this.contact2.setAddresses(addressList);

        this.addressDTO = new AddressDTO(this.address.getStreet(),this.address.getNumber(),this.address.getCity(),this.address.getState());
        this.addressDTO2 = new AddressDTO(this.address2.getStreet(),this.address2.getNumber(),this.address2.getCity(),this.address2.getState());
        this.listAddressDTOS = List.of(this.addressDTO, this.addressDTO2);

        this.contactResponseDTO = new ContactResponseDTO(this.contact.getId().toString(),this.contact.getName(),this.contact.getPhone(),this.contact.getEmail(),this.contact.getDate(),this.listAddressDTOS);
        this.contactResponseDTO2 = new ContactResponseDTO(this.contact2.getId().toString(),this.contact2.getName(),this.contact2.getPhone(),this.contact2.getEmail(),this.contact2.getDate(),this.listAddressDTOS);

        this.listContacts = List.of(this.contactResponseDTO, this.contactResponseDTO2);
        this.json = """
                 {
                        "nome": "Diego Felipe da silva",
                        "telefone": "99949-4566",
                        "email": "diego@email.com",
                        "dataNascimento": "2000-05-31",
                        "enderecos": [
                            {
                                "rua": "Rua mais Flores",
                                "numero": "20",
                                "cidade": "vitoria",
                                "estado": "Pe"
                            }
                        ]
                }
                """;
        this.invalidJson = """
                {
                    "nome": "Diego Felipe da silva",
                    "dataNascimento": "2000-05-31",
                    "enderecos": [
                        {
                            "rua": "Rua mais Flores",
                            "numero": "20",
                            "cidade": "vitoria",
                            "estado": "Pe"
                        }
                    ]
                }
                """;

    }

    @Test
    @DisplayName("deve retornar o status duzentos e quatro  corretamente")
    void shouldCorrectlyReturnStatusTwoHundredAndFour() throws Exception {

        when(this.mediator.send(Mockito.any())).thenReturn(this.contact);

        ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/contatos").contentType(MediaType.APPLICATION_JSON).content(this.json));
        result.andExpect(MockMvcResultMatchers.status().isCreated());


    }
    @Test
    @DisplayName("deve retornar um URI com ID caso o contato foi persistido corretamente\"")
    void shouldReturnAUriWithTheIdIfItWasPersistedCorrectly()throws  Exception{
        when(this.mediator.send(Mockito.any())).thenReturn(contact);
        ResultActions result = mvc.perform(MockMvcRequestBuilders.post("/contatos").contentType(MediaType.APPLICATION_JSON).content(this.json));
        result.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().exists("Location"))
                .andExpect(MockMvcResultMatchers.header().string("Location",containsString(this.contact.getId().toString())));
    }
    @Test
    @DisplayName("deve retornar uma lista erros caso o contato nao foi salvo corretamente")
    void shouldReturnAnErrorIfTheContactWasNotSavedCorrectly() throws  Exception{
        this.mvc.perform(MockMvcRequestBuilders.post("/contatos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.invalidJson))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].Campo").value("telefone"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].Erro").value("campo obrigatorio"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].Campo").value("email"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].Erro").value("campo obrigatorio"));

    }
    @Test
    @DisplayName("deve retornar uma lista de todos os contatos")
    void shouldReturnAListOfAllContacts() throws Exception {
        when(this.mediator.send(Mockito.any())).thenReturn(this.listContacts);
        this.mvc.perform(MockMvcRequestBuilders.get("/contatos")
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].nome").value("Diego Felipe da silva"))
                .andExpect(MockMvcResultMatchers.jsonPath("[1].telefone").value("99949-3566"));


    }
    @Test
    @DisplayName("deve retornar um contato com sucesso")
    void shouldReturnAContactSuccessfully() throws Exception{
        String id = this.contact.getId().toString();
        when(this.mediator.send(Mockito.any())).thenReturn(this.contactResponseDTO);
        this.mvc.perform(MockMvcRequestBuilders.get("/contatos/"+id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value(this.contact.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.telefone").value(this.contact.getPhone()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(this.contact.getEmail()));
    }
    @Test
    @DisplayName("deve retornar erro quatrocentos e quatro caso o contato não seja encontrado")
    void shouldReturnTheNotFoundError() throws Exception{
        String id = this.contact.getId().toString();
        when(this.mediator.send(Mockito.any())).thenThrow(NotFoundExceptions.class);
        this.mvc.perform(MockMvcRequestBuilders.get("/contatos/"+id)).andExpect(MockMvcResultMatchers.status().isNotFound());

    }

}
