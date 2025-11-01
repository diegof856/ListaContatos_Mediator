package com.github.diegof856.ListaContatos.controller;

import com.github.diegof856.ListaContatos.commands.CreateContactCommand;
import com.github.diegof856.ListaContatos.commands.UpdateContactCommand;
import com.github.diegof856.ListaContatos.commands.dto.ContactResponseDTO;


import com.github.diegof856.ListaContatos.controller.factory.FactoryInstance;

import com.github.diegof856.ListaContatos.mediator.Mediator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/contatos")
@RequiredArgsConstructor
public class ContactListController implements GenericController {

    private final Mediator mediator;
    private final FactoryInstance factoryInstance;
    @PostMapping
    public ResponseEntity<Void> saveContact(@RequestBody @Valid CreateContactCommand command){
        URI location = generateHeaderLocation(this.mediator.send(command).getId());
        return ResponseEntity.created(location).build();
    }
    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> getAllContact(){
        List<ContactResponseDTO> contacts = this.mediator.send(this.factoryInstance.createGetAllQuery());
        return ResponseEntity.ok(contacts);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String id, @RequestBody @Valid UpdateContactCommand command){
        this.mediator.send(this.factoryInstance.createUpdateCommand(id,command));
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id")String id){
        this.mediator.send(this.factoryInstance.createDeleteCommnad(id));
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ContactResponseDTO> getDetails(@PathVariable("id")String id){
        return ResponseEntity.ok(this.mediator.send(this.factoryInstance.createGetContactById(id)));
    }
}
