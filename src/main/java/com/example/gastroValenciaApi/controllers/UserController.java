package com.example.gastroValenciaApi.controllers;

import com.example.gastroValenciaApi.models.UserModel;
import com.example.gastroValenciaApi.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public Optional<UserModel> getUserById(@PathVariable int id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public UserModel saveUser(@RequestBody UserModel user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }

}
