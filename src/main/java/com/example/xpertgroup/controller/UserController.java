package com.example.xpertgroup.controller;

import com.example.xpertgroup.model.user.Credentials;
import com.example.xpertgroup.model.user.User;
import com.example.xpertgroup.model.user.UserDTO;
import com.example.xpertgroup.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class UserController {

    UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<?> save(@RequestBody UserDTO user){
        boolean created = userService.save(user);
        if (!created) return new ResponseEntity<>("An error has occurred", HttpStatusCode.valueOf(500));

        return new ResponseEntity<>("User created successfully", HttpStatusCode.valueOf(200));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credentials credentials){
        UserDTO user = userService.getByCredentials(credentials.getUsername(), credentials.getPassword());
        if(user == null){
            return new ResponseEntity<>("User not found", HttpStatusCode.valueOf(500));
        }

        return new ResponseEntity<>(user, HttpStatusCode.valueOf(200));
    }

}
