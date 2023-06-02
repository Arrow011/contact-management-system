package com.project.contactmanagement.controller;

import com.project.contactmanagement.exception.ResourceNotFoundException;
import com.project.contactmanagement.model.ContactDetailsRequest;
import com.project.contactmanagement.model.ContactDetailsResponse;
import com.project.contactmanagement.service.ContactService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/contact/v1")
public class ContactManagementController {

    @Autowired
    private ContactService contactService;

    @Operation(
            summary = "Retrieve all the contacts.",
            description = "Get list of all present contact objects. The response is Contact object with id, firstName, lastName, email and phoneNumber.",
            tags = { "Fetch all contacts" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ContactDetailsResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/all")
    public ResponseEntity<List<ContactDetailsResponse>> getAllContacts() {
        return new ResponseEntity<>(contactService.fetchAllContacts(), HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve a contact from contact id.",
            description = "Get a contact object by id. The response is Contact object with id, firstName, lastName, email and phoneNumber.",
            tags = { "Fetch contact using id" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ContactDetailsResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/id/{id}")
    public ResponseEntity<ContactDetailsResponse> getContactById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(contactService.fetchContactById(id), HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve all the contacts that match the first name.",
            description = "Get list of all present contact objects by first name. The response is list of Contact objects with id, firstName, lastName, email and phoneNumber.",
            tags = { "Fetch contacts using first name" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ContactDetailsResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/firstName/{firstName}")
    public ResponseEntity<List<ContactDetailsResponse>> getContactByFirstName(@PathVariable("firstName") String firstName) throws ResourceNotFoundException {
        return new ResponseEntity<>(contactService.fetchContactsByFirstName(firstName), HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve all the contacts that match the last name.",
            description = "Get list of all present contact objects by last name. The response is a list of Contact objects with id, firstName, lastName, email and phoneNumber.",
            tags = { "Fetch contacts using last name" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ContactDetailsResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/lastName/{lastName}")
    public ResponseEntity<List<ContactDetailsResponse>> getContactsByLastName(@PathVariable("lastName") String lastName) throws ResourceNotFoundException {
        return new ResponseEntity<>(contactService.fetchContactsByLastName(lastName), HttpStatus.OK);
    }

    @Operation(
            summary = "Retrieve all the contacts that match the input email.",
            description = "Get a contact object by email. The response is Contact object with id, firstName, lastName, email and phoneNumber.",
            tags = { "Fetch contact using email" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ContactDetailsResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/email/{emailId}")
    public ResponseEntity<ContactDetailsResponse> getContactByEmail(@PathVariable("emailId") String emailId) throws ResourceNotFoundException {
        return new ResponseEntity<>(contactService.fetchContactByEmail(emailId), HttpStatus.OK);
    }

    @Operation(
            summary = "Add new contact to list.",
            description = "Adding new contact object to the database. The response is a string message on successful operation.",
            tags = { "Add contact" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })})
    @PostMapping("/create")
    public ResponseEntity<String> createContact(@RequestBody ContactDetailsRequest contactDetailsRequest) {
        return new ResponseEntity<>(contactService.createContact(contactDetailsRequest), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Update existing contact.",
            description = "Updating existing contact object with new details. The response is a string message on successful operation.",
            tags = { "Update existing contact" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PutMapping("/update/id/{id}")
    public ResponseEntity<String> updateContactDetails(@PathVariable("id") Long id, @RequestBody ContactDetailsRequest contactDetailsRequest) throws ResourceNotFoundException {
        return new ResponseEntity<>(contactService.updateContact(id, contactDetailsRequest), HttpStatus.OK);
    }

    @Operation(
            summary = "Delete existing contacts.",
            description = "Remove existing contact object from the database using id. The response is a string message on successful operation.",
            tags = { "Delete existing contact" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/delete/id/{id}")
    public ResponseEntity<String> deleteContactById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(contactService.deleteContact(id), HttpStatus.OK);
    }
}
