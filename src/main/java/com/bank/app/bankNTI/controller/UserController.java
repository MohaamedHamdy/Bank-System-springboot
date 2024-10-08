package com.bank.app.bankNTI.controller;

import com.bank.app.bankNTI.dto.UserDto;
import com.bank.app.bankNTI.entity.User;
import com.bank.app.bankNTI.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping("/{userId}")
    public User getUserDetails(@PathVariable long userId) {
        return userService.getUserDetails(userId);
    }

}
