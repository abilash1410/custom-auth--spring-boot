package com.auth.customAuth.controller;


import com.auth.customAuth.model.Users;
import com.auth.customAuth.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public Users RegisterUser(@RequestBody Users user) {
       return userService.registerUser(user);
    }

    @GetMapping("/userslist")
    public List<Users> getAllUsers() {
        return userService.listOfusers();
    }

    @PostMapping("/login")
    public String LoginUser(@RequestBody Users user) {
        return userService.verifyUserinDB(user);
    }

}
