package com.github.diegof856.ListaContatos.repository;

import com.github.diegof856.ListaContatos.entity.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContactRepository extends MongoRepository<Contact, UUID> {

    Optional<Contact> findByPhone(String phone);
}
