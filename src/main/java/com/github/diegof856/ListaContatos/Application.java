package com.github.diegof856.ListaContatos;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {


        SpringApplicationBuilder builder= new SpringApplicationBuilder(Application.class);
        builder.bannerMode(Banner.Mode.OFF);
        builder.run(args);
	}

}
