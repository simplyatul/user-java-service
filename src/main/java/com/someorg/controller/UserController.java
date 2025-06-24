package com.someorg.controller;

import com.someorg.model.User;
import com.someorg.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/someorg/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByEmail(@RequestParam("email_id") String emailId) {
        return ResponseEntity.ok(userService.getUserByEmail(emailId));
    }

    @DeleteMapping("/user/{email_id}")
    public ResponseEntity<?> deleteUser(@PathVariable("email_id") String emailId) {
        userService.deleteUser(emailId);
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
