package com.example.expense.tracker.controllers;

import com.example.expense.tracker.config.Constants;
import com.example.expense.tracker.dto.UserDueDto;
import com.example.expense.tracker.dto.UserDueSettlementDto;
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

    @RequestMapping(value = "/1", method = RequestMethod.POST)
    public ResponseEntity<String> settleDuesFirstInFirstOutFashion
            (@RequestBody UserDueSettlementDto userDueSettlementDto){

        LOGGER.info("Inside post mapping of UserDueController to settle the dues with first in first out fashion.");

        userDueService.settleDuesFirstInFirstOut(userDueSettlementDto);
        return new ResponseEntity<>(Constants.SUCCESS, HttpStatus.OK);
    }

    @RequestMapping(value = "/2", method = RequestMethod.POST)
    public ResponseEntity<String> settleDuesLatestRepaymentDateFirst
            (@RequestBody UserDueSettlementDto userDueSettlementDto){

        LOGGER.info("Inside post mapping of UserDueController to settle the dues with Latest Repayment Date First fashion.");

        userDueService.settleDuesLatestRepaymentDateFirst(userDueSettlementDto);
        return new ResponseEntity<>(Constants.SUCCESS, HttpStatus.OK);
    }
}
