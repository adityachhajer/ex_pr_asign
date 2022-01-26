package com.example.expense.tracker.controllers;

import com.example.expense.tracker.config.Constants;
import com.example.expense.tracker.dto.UserDto;
import com.example.expense.tracker.dto.UserDueDto;
import com.example.expense.tracker.service.interfaces.UserDueService;
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
@RequestMapping("/userdues")
public class UserDueController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDueController.class);

    @Autowired
    private UserDueService userDueService;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> addUserDues(@RequestBody UserDueDto userDueDto){
        LOGGER.info("Inside post mapping of UserDueController to add a new user due.");

        userDueService.addUserDues(userDueDto);
        return new ResponseEntity<>(Constants.SUCCESS, HttpStatus.OK);
    }
}
