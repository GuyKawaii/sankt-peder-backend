package com.example.sanktpederbackend.controller;

import com.example.sanktpederbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "*")
public class UserController {

    @Autowired
    UserService userService;
}
