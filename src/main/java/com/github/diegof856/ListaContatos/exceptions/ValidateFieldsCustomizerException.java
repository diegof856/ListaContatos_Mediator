package com.github.diegof856.ListaContatos.exceptions;

import com.github.diegof856.ListaContatos.commands.dto.ErrorDetail;
import lombok.Getter;

import java.util.List;

@Getter
public class ValidateFieldsCustomizerException extends RuntimeException{
    private final List<ErrorDetail> errors;
    public ValidateFieldsCustomizerException(List<ErrorDetail> errors){
        super();
        this.errors = errors;

    }

}
