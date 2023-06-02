package com.project.contactmanagement.security;

import com.project.contactmanagement.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails userDetails = userInfoRepository.findByName(username).map(UserDetailsConfig::new)
                .orElseThrow(() -> new UsernameNotFoundException("Username "+username+" doesn't exist."));
        return userDetails;
    }
}
