package com.github.diegof856.ListaContatos.validator;

import com.github.diegof856.ListaContatos.commands.dto.ErrorDetail;
import com.github.diegof856.ListaContatos.entity.Address;
import com.github.diegof856.ListaContatos.entity.Contact;
import com.github.diegof856.ListaContatos.exceptions.ValidateFieldsCustomizerException;
import com.github.diegof856.ListaContatos.factory.FactoryValidate;
import com.github.diegof856.ListaContatos.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ValidatorContact {

    private final ContactRepository repository;
    private final FactoryValidate factoryValidate;

    public void validate(Contact contact, UUID id ){
        List<ErrorDetail> errors = new ArrayList<>();
        if(!mustcontainonlyLetters(contact.getName())){
            errors.add(this.factoryValidate.createErroDetails("nome","O nome deve conter apenas letras"));
        }
        if(!thereAreOnlyNumbers(contact.getPhone())){
            errors.add(this.factoryValidate.createErroDetails("telefone","Deve conter apenas numeros."));
        }else if(contact.getPhone().length() !=11){
             errors.add(this.factoryValidate.createErroDetails("telefone","A quantidade numeros deve ser exatamente 11"));
        }else{
            Optional<Contact> existing = repository.findByPhone(contact.getPhone());
            if (existing.isPresent()){
                if (id == null) {
                    errors.add(factoryValidate.createErroDetails("telefone", "Número já cadastrado."));
                }
            }
        }
        if(contact.getAddresses().isEmpty()){
            errors.add(this.factoryValidate.createErroDetails("Endereco","Deve conter pelo menos 1 endereço."));
        }else{
            for(Address address: contact.getAddresses()){
                if(!thereAreOnlyNumbers(address.getNumber())){
                    errors.add(this.factoryValidate.createErroDetails("numero","Deve conter apenas numeros."));
                }
                if(!mustcontainonlyLetters(address.getCity())){
                     errors.add(this.factoryValidate.createErroDetails("cidade","Deve conter apenas letras."));
                }
                if(!mustcontainonlyLetters(address.getState())){
                   errors.add(this.factoryValidate.createErroDetails("estado","Deve conter apenas letras."));
                }
            }
        }
        if (!errors.isEmpty()) {
            throw new ValidateFieldsCustomizerException(errors);
        }


    }
    private boolean thereAreOnlyNumbers(String phone){
        return phone.matches("\\d+");
    }
    private boolean mustcontainonlyLetters(String name){
        return name.matches("[\\p{L}\\s]+");
    }

}
