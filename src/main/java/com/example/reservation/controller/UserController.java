package com.example.reservation.controller;

import com.example.reservation.entity.User;
import com.example.reservation.repository.UserRepository;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // -----------------------
    // ユーザー登録（POST）
    // -----------------------
    @PostMapping
    public User createUser(
            @RequestParam String name,
            @RequestParam String email) {

        User user = new User();
        user.setName(name);
        user.setEmail(email);

        return userRepository.save(user);
    }

    // ------------------------
    // ユーザー一覧（GET)
    // ------------------------
    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // ------------------------
    // ユーザー取得（GET）
    // ------------------------
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
}
