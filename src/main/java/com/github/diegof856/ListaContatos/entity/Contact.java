package com.github.diegof856.ListaContatos.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Document(collection = "contact")
@Data
public class Contact {
    @Id
    private UUID id;
    private String name;
    private String email;
    private LocalDate date;
    private String phone;
    List<Address> addresses;

    public Contact(LocalDate date, String email, String name,UUID id,String phone) {
        this.phone =phone;
        this.date = date;
        this.email = email;
        this.name = name;
        this.id = id;
    }
}
