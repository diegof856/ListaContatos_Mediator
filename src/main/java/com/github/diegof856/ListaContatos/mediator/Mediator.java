package com.github.diegof856.ListaContatos.mediator;

public interface Mediator {
    <R> R send(Request<R> request);
}
