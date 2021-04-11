package com.freaky.fit.controllers;

import com.freaky.fit.entities.UserDetails;
import com.freaky.fit.services.UserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("/user/details")
@Slf4j
public class UserDetailscontroller {

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping
    public ResponseEntity<UserDetails> addUsers(@RequestBody UserDetails userDetails) {
        log.info("User add request {}", userDetails);
        return userDetailsService.addUser(userDetails);
    }

    @PutMapping
    public ResponseEntity<UserDetails> updateUser(@RequestBody UserDetails userDetails) {
        log.info("User add request {}", userDetails);
        return userDetailsService.updateUser(userDetails);
    }

    @DeleteMapping
    public ResponseEntity<?> DeleteUser(@RequestParam String userId) {
        log.info("User Delete request {}", userId);
        return userDetailsService.deleteUser(userId);
    }

    @GetMapping
    public ResponseEntity<?> getUser(@RequestParam String userId) {
        log.info("User View request {}", userId);
        return userDetailsService.getUser(userId);
    }
}
