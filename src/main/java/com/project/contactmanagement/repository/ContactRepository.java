package com.project.contactmanagement.repository;

import com.project.contactmanagement.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<List<Contact>> findByFirstName(String firstName);
    Optional<List<Contact>> findByLastName(String lastName);
    Optional<Contact> findByEmail(String email);
}
