package com.project.contactmanagement.serviceImpl;

import com.project.contactmanagement.entity.UserInfo;
import com.project.contactmanagement.exception.ResourceNotFoundException;
import com.project.contactmanagement.model.UserRequest;
import com.project.contactmanagement.model.UserResponse;
import com.project.contactmanagement.repository.UserInfoRepository;
import com.project.contactmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserRequest userRequest) {
        UserInfo userInfo = UserInfo.builder()
                .name(userRequest.getName())
                .password(passwordEncoder.encode(userRequest.getPassword()))
                .email(userRequest.getEmail())
                .roles(userRequest.getRoles()).build();

        userInfoRepository.save(userInfo);
        return "User "+userRequest.getName()+" added successfully.";
    }

    @Override
    public String deleteUser(Long id) throws ResourceNotFoundException {
        UserInfo userInfo = userInfoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id "+id+" not found."));
        userInfoRepository.delete(userInfo);
        return "User with id "+id+" deleted successfully.";
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserInfo> listOfUsers = userInfoRepository.findAll();
        List<UserResponse> list = listOfUsers.stream().map(
                user -> {
                    return UserResponse.builder().name(user.getName())
                            .password(user.getPassword())
                            .id(user.getId())
                            .email(user.getEmail())
                            .roles(user.getRoles()).build();
                }
        ).collect(Collectors.toList());
        return list;
    }


}
