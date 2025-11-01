package com.github.diegof856.ListaContatos.mediator;

public interface RequestHandler<T extends Request<R>, R> {

    R handle(T request);
}
