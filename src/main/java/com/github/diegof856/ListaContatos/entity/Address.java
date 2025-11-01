package com.github.diegof856.ListaContatos.entity;

import lombok.Data;

@Data
public class Address {
    private String street;
    private String number;
    private String city;
    private String state;

    public Address( String street, String number, String city, String state) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.state = state;
    }
}
