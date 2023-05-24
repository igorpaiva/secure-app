package com.springsecured.service;

import com.springsecured.model.CurrentUser;
import com.springsecured.repository.CurrentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService implements UserDetailsService {

    private final CurrentUserRepository currentUserRepository;

    @Autowired
    public CurrentUserService(CurrentUserRepository currentUserRepository) {
        this.currentUserRepository = currentUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final CurrentUser currentUser = currentUserRepository.findUserByUsername(username);
        if (currentUser == null) {
            throw new UsernameNotFoundException("Failed to find user with username: " + username);
        }

        return currentUser;
    }
}
