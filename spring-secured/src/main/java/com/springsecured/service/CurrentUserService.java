package com.springsecured.service;

import com.springsecured.model.CurrentUser;
import com.springsecured.model.CurrentUserEntity;
import com.springsecured.repository.CurrentUserInMemoryRepository;
import com.springsecured.repository.CurrentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService implements UserDetailsService {

    private final CurrentUserInMemoryRepository currentUserInMemoryRepository;
    private final CurrentUserRepository currentUserRepository;

    @Autowired
    public CurrentUserService(CurrentUserInMemoryRepository currentUserInMemoryRepository, CurrentUserRepository currentUserRepository) {
        this.currentUserInMemoryRepository = currentUserInMemoryRepository;
        this.currentUserRepository = currentUserRepository;
    }

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {

        final CurrentUserEntity user = currentUserRepository.findByUsername(username);
        if (user != null) {
            CurrentUser currentUser = new CurrentUser();
            currentUser.setUsername(user.getUsername());
            currentUser.setPassword(user.getPassword());

            return currentUser;
        }

        final CurrentUser currentUser = currentUserInMemoryRepository.findUserByUsername(username);
        if (currentUser == null) {
            throw new UsernameNotFoundException("Failed to find user with username: " + username);
        }

        return currentUser;
    }
}
