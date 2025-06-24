package com.guptem.journalApp.services;

import com.guptem.journalApp.entities.JournalEntry;
import com.guptem.journalApp.entities.User;
import com.guptem.journalApp.repository.UserRepo;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long ID) {
        return userRepo.findById(ID)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + ID));
    }

    public void deleteUserById(Long ID) {
        userRepo.deleteById(ID);
    }

    public User updateUser(Long Id, User newUser) {
        User oldUser = userRepo.findById(Id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + Id));

        // Update only if new value is not null and not empty
        if (newUser.getUsername() != null && !newUser.getUsername().isEmpty()) {
            oldUser.setUsername(newUser.getUsername());
        }
        if (newUser.getPassword() != null && !newUser.getPassword().isEmpty()) {
            oldUser.setPassword(newUser.getPassword());
        }

        return userRepo.save(oldUser);
    }

    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

}
