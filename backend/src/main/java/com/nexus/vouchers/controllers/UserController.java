package com.nexus.vouchers.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexus.vouchers.models.User;
import com.nexus.vouchers.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService<User> userService;
    
}