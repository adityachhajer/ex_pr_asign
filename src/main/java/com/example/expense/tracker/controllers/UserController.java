package com.example.expense.tracker.controllers;

import com.example.expense.tracker.config.Constants;
import com.example.expense.tracker.dto.UserDto;
import com.example.expense.tracker.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto){
        LOGGER.info("Inside post mapping of UserController to add a new user.");

        userService.addUser(userDto);
        return new ResponseEntity<>(Constants.SUCCESS, HttpStatus.OK);
    }

}
