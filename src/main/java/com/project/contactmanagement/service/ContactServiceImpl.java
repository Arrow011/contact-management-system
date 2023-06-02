package com.project.contactmanagement.service;

import com.project.contactmanagement.entity.Contact;
import com.project.contactmanagement.exception.ResourceNotFoundException;
import com.project.contactmanagement.model.ContactDetailsRequest;
import com.project.contactmanagement.model.ContactDetailsResponse;
import com.project.contactmanagement.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<ContactDetailsResponse> fetchAllContacts() {
        log.info("Entered fetchAllContacts");
        List<Contact> contactList = contactRepository.findAll();

        List<ContactDetailsResponse> listOfContacts = contactList.stream().map(
                contact -> {
                    return ContactDetailsResponse.builder()
                            .contactId(contact.getContactId())
                            .firstName(contact.getFirstName())
                            .lastName(contact.getLastName())
                            .email(contact.getEmail())
                            .phoneNumber(contact.getPhoneNumber()).build();
                }
        ).collect(Collectors.toList());

        return listOfContacts;
    }

    @Override
    public ContactDetailsResponse fetchContactById(Long id) throws ResourceNotFoundException {
        log.info("Entered fetchContactById");
        Contact contact = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact details for id: " + id + " not found."));

        ContactDetailsResponse contactDetails = ContactDetailsResponse.builder().contactId(contact.getContactId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phoneNumber(contact.getPhoneNumber())
                .build();

        return contactDetails;
    }

    @Override
    public List<ContactDetailsResponse> fetchContactsByFirstName(String firstName) throws ResourceNotFoundException {
        log.info("Entered fetchContactsByFirstName");
        List<Contact> contactList = contactRepository.findByFirstName(firstName).orElseThrow(() -> new ResourceNotFoundException("Contact details with first name : " + firstName + " not found."));

        List<ContactDetailsResponse> listOfContacts = contactList.stream().map(
                contact -> {
                    return ContactDetailsResponse.builder()
                            .contactId(contact.getContactId())
                            .firstName(contact.getFirstName())
                            .lastName(contact.getLastName())
                            .email(contact.getEmail())
                            .phoneNumber(contact.getPhoneNumber()).build();
                }
        ).collect(Collectors.toList());

        return listOfContacts;
    }

    @Override
    public List<ContactDetailsResponse> fetchContactsByLastName(String lastName) throws ResourceNotFoundException {
        log.info("Entered fetchContactsByLastName");
        List<Contact> contactList = contactRepository.findByLastName(lastName).orElseThrow(() -> new ResourceNotFoundException("Contact details with last name : " + lastName + " not found."));

        List<ContactDetailsResponse> listOfContacts = contactList.stream().map(
                contact -> {
                    return ContactDetailsResponse.builder()
                            .contactId(contact.getContactId())
                            .firstName(contact.getFirstName())
                            .lastName(contact.getLastName())
                            .email(contact.getEmail())
                            .phoneNumber(contact.getPhoneNumber()).build();
                }
        ).collect(Collectors.toList());

        return listOfContacts;
    }

    @Override
    public ContactDetailsResponse fetchContactByEmail(String email) throws ResourceNotFoundException {
        log.info("Entered fetchContactByEmail");
        Contact contact = contactRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Contact details with email id : " + email + " not found."));

        ContactDetailsResponse contactDetails = ContactDetailsResponse.builder()
                .contactId(contact.getContactId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phoneNumber(contact.getPhoneNumber())
                .build();

        return contactDetails;
    }

    @Override
    public String createContact(ContactDetailsRequest contactDetailsRequest) {
        log.info("Entered createContact");
        Contact contact = Contact.builder()
                .firstName(contactDetailsRequest.getFirstName())
                .lastName(contactDetailsRequest.getLastName())
                .email(contactDetailsRequest.getEmail())
                .phoneNumber(contactDetailsRequest.getPhoneNumber())
                .build();

        contactRepository.save(contact);
        log.info("Contact "+contact.getFirstName()+" added successfully.");
        return "Contact with id: "+contact.getContactId()+" successfully added to the system!";
    }

    @Override
    public String updateContact(Long id, ContactDetailsRequest contactDetailsRequest) throws ResourceNotFoundException {
        log.info("Entered updateContact");
        Contact existingContact = contactRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contact details for id: " + id + " not found."));

        existingContact.setFirstName(contactDetailsRequest.getFirstName());
        existingContact.setLastName(contactDetailsRequest.getLastName());
        existingContact.setEmail(contactDetailsRequest.getEmail());
        existingContact.setPhoneNumber(contactDetailsRequest.getPhoneNumber());

        contactRepository.save(existingContact);
        log.info("Contact with id: "+existingContact.getContactId()+" updated successfully.");

        return "Contact with id: " + id + " updated successfully!";
    }

    @Override
    public String deleteContact(Long id) {
        log.info("Entered deleteContact");

        contactRepository.deleteById(id);
        log.info("Contact with id: " + id + " deleted.");

        return "Contact Deleted Successfully.";
    }

}
