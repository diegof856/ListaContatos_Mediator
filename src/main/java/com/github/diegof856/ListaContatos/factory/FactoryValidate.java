package com.github.diegof856.ListaContatos.factory;

import com.github.diegof856.ListaContatos.commands.dto.ErrorDetail;

public interface FactoryValidate {
    ErrorDetail createErroDetails( String field, String messageErro);
}
