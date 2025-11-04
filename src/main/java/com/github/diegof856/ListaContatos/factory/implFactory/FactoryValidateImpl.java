package com.github.diegof856.ListaContatos.factory.implFactory;

import com.github.diegof856.ListaContatos.commands.dto.ErrorDetail;
import com.github.diegof856.ListaContatos.factory.FactoryValidate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class FactoryValidateImpl implements FactoryValidate {
    @Override
    public ErrorDetail createErroDetails(String field, String messageErro) {
        return new ErrorDetail(HttpStatus.BAD_REQUEST, field, messageErro);
    }
}
