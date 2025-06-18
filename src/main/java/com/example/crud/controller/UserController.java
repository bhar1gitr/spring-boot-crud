package com.example.crud.controller;

import com.example.crud.model.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private List<User> users = new ArrayList<>();
    private int idCounter = 1;

    // Create
    @PostMapping
    public User createUser(@RequestBody User user) {
        user.setId(idCounter++);
        users.add(user);
        return user;
    }

    // Read All
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    // Read by ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Update
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        for (User user : users) {
            if (user.getId() == id) {
                System.out.println(id);
                user.setName(updatedUser.getName());
                user.setEmail(updatedUser.getEmail());
                return user;
            }
        }
        return null;
    }

    // Delete
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable int id) {
        boolean removed = users.removeIf(u -> u.getId() == id);
        return removed ? "User deleted." : "User not found.";
    }
}
