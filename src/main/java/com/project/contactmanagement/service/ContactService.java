package com.project.contactmanagement.service;

import com.project.contactmanagement.entity.Contact;
import com.project.contactmanagement.exception.ResourceNotFoundException;
import com.project.contactmanagement.model.ContactDetailsRequest;
import com.project.contactmanagement.model.ContactDetailsResponse;

import java.util.List;

public interface ContactService {
    public List<ContactDetailsResponse> fetchAllContacts();
    public ContactDetailsResponse fetchContactById(Long id) throws ResourceNotFoundException;
    public List<ContactDetailsResponse> fetchContactsByFirstName(String firstName) throws ResourceNotFoundException;
    public List<ContactDetailsResponse> fetchContactsByLastName(String lastName) throws ResourceNotFoundException;
    public ContactDetailsResponse fetchContactByEmail(String email) throws ResourceNotFoundException;
    public String createContact(ContactDetailsRequest contactDetailsRequest);
    public String updateContact(Long id, ContactDetailsRequest contactDetailsRequest) throws ResourceNotFoundException;
    public String deleteContact(Long id);
}
