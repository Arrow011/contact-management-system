package com.project.contactmanagement.controller;

import com.project.contactmanagement.entity.UserInfo;
import com.project.contactmanagement.exception.ResourceNotFoundException;
import com.project.contactmanagement.model.ContactDetailsResponse;
import com.project.contactmanagement.model.UserRequest;
import com.project.contactmanagement.model.UserResponse;
import com.project.contactmanagement.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(
            summary = "Retrieve all the users.",
            description = "Get list of all present user objects. The response is list of user objects.",
            tags = { "Fetch all users" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = UserResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/all")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Operation(
            summary = "Add a new user.",
            description = "Adding new new User object to the database. The response is a string message on successful operation.",
            tags = { "Add user" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) })})
    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.addUser(userRequest), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Delete existing users.",
            description = "Remove existing user object from the database using id. The response is a string message on successful operation.",
            tags = { "Delete existing user" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = String.class)) }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(userService.deleteUser(id), HttpStatus.OK);
    }
}
