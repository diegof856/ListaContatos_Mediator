package com.github.diegof856.ListaContatos.controller;

import com.github.diegof856.ListaContatos.commands.CreateContactCommand;
import com.github.diegof856.ListaContatos.commands.UpdateContactCommand;
import com.github.diegof856.ListaContatos.commands.dto.ContactResponseDTO;


import com.github.diegof856.ListaContatos.controller.factory.FactoryInstance;

import com.github.diegof856.ListaContatos.mediator.Mediator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/contatos")
@RequiredArgsConstructor
@Tag(name = "Contatos")
public class ContactListController implements GenericController {

    private final Mediator mediator;
    private final FactoryInstance factoryInstance;
    @PostMapping
    @Operation(summary = "Salvar", description = "Cadastrar novo contato")
    @ApiResponses({
            @ApiResponse(responseCode = "201",description = "Cadastrado com sucesso."),
            @ApiResponse(responseCode = "422",description = "Erro de validação.")
    })
    public ResponseEntity<Void> saveContact(@RequestBody @Valid CreateContactCommand command){
        URI location = generateHeaderLocation(this.mediator.send(command).getId());
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    @Operation(summary = "Listar Contatos", description = "Listar contatos.")
    @ApiResponse(responseCode = "200", description = "Retorna a lista de todos os contatos.")
    public ResponseEntity<List<ContactResponseDTO>> getAllContact(){
        List<ContactResponseDTO> contacts = this.mediator.send(this.factoryInstance.createGetAllQuery());
        return ResponseEntity.ok(contacts);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Atualiza contato", description = "Atualiza o contato a partir do id.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Contato atualizado com sucesso."),
            @ApiResponse(responseCode = "422", description = "Erro de validação.")
    })
    public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody @Valid UpdateContactCommand command){
        this.mediator.send(this.factoryInstance.createUpdateCommand(id,command));
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta contato", description = "Deleta o contato a partir do id.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Contato deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Contato não encontrado.")
    })
    public ResponseEntity<Void> delete(@PathVariable("id")String id){
        this.mediator.send(this.factoryInstance.createDeleteCommnad(id));
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    @Operation(summary = "Obtém detalhes", description = "Obtém detalhes do contato a partir do id.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Contato encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Contato não encontrado.")
    })
    public ResponseEntity<ContactResponseDTO> getDetails(@PathVariable("id")String id){
        return ResponseEntity.ok(this.mediator.send(this.factoryInstance.createGetContactById(id)));
    }
}
