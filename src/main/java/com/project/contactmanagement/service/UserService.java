package com.project.contactmanagement.service;

import com.project.contactmanagement.entity.UserInfo;
import com.project.contactmanagement.exception.ResourceNotFoundException;
import com.project.contactmanagement.model.UserRequest;
import com.project.contactmanagement.model.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    String addUser(UserRequest userRequest);
    String deleteUser(Long id) throws ResourceNotFoundException;

    List<UserResponse> getAllUsers();
}
