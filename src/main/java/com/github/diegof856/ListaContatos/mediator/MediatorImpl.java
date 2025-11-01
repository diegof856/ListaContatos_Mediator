package com.github.diegof856.ListaContatos.mediator;

import lombok.RequiredArgsConstructor;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MediatorImpl implements Mediator{

    private final ApplicationContext context;
    @Override
    public <R> R send(Request<R> request) {
        String handlerName = request.getClass().getSimpleName().replace("Command", "Handler").replace("Query", "Handler");
        handlerName = Character.toLowerCase(handlerName.charAt(0)) + handlerName.substring(1);
        RequestHandler<Request<R>, R> handler =
                (RequestHandler<Request<R>, R>) this.context.getBean(handlerName);
        return handler.handle(request);
    }
}
