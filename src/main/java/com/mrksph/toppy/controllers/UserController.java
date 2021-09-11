package com.mrksph.toppy.controllers;

import com.mrksph.toppy.dto.UserDTO;
import com.mrksph.toppy.model.UserEntity;
import com.mrksph.toppy.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity registerUser(@RequestBody UserDTO request) {
        UserEntity userEntity = userService.registerUser(request);
        return ResponseEntity.ok(userEntity);
    }

    @PostMapping(path = "/login", produces = "application/json")
    public ResponseEntity loginUser(@RequestBody UserDTO request) {
        UserEntity userEntity = userService.loginUser(request);
        return ResponseEntity.ok(userEntity);
    }

    @GetMapping(path = "/{username}", produces = "application/json")
    public ResponseEntity getUserByUsername(@PathVariable String username) {
        UserEntity userByUsername = userService.getUserByUsername(username);
        return  ResponseEntity.ok(userByUsername);
    }
}
