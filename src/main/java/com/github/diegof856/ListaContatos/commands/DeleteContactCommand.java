package com.github.diegof856.ListaContatos.commands;

import com.github.diegof856.ListaContatos.mediator.Command;

import java.util.UUID;

public record DeleteContactCommand(UUID id) implements Command<Void> {
}
