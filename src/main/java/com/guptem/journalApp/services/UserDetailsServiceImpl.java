package com.guptem.journalApp.services;

import com.guptem.journalApp.entities.User;
import com.guptem.journalApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    @Autowired
    public UserDetailsServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("❌ Username not found: " + username));

        System.out.println("🔐 Username: " + user.getUsername());
        System.out.println("🔐 Encoded Password: " + user.getPassword());
        System.out.println("🔐 Roles: " + user.getRoles());

        UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
        builder.password(user.getPassword());  // should be BCrypt encoded
        builder.roles(user.getRoles().toArray(new String[0]));  // should be like ["USER", "ADMIN"] not ["ROLE_USER"]

        return builder.build();
    }
}
