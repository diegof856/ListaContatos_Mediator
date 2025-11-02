package com.github.diegof856.ListaContatos.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Contatos",
                version = "v1",
                contact = @Contact(
                        name = "Diego Felipe",
                        email = "diegofelipe1025@gmail.com",
                        url = "https://github.com/diegof856"
                )
        ),
        security = {
                @SecurityRequirement(name = "basicAuth")
        }

)
@SecurityScheme(
        name = "basicAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "basic"
)
public class OpenApiConfiguration {
}
