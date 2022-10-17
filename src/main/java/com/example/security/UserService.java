package com.example.security;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * Abstraction class to save @Autowired userRepo from being in every class. Unsure if necessary.
 */

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public List<User> listAllUsers() {
        return userRepo.findAll();
    }

    public void save(User user) {
        userRepo.save(user);
    }
    
}
