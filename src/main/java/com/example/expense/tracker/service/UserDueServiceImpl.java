package com.example.expense.tracker.service;

import com.example.expense.tracker.config.Constants;
import com.example.expense.tracker.dto.UserDueDto;
import com.example.expense.tracker.dto.UserDueSettlementDto;
import com.example.expense.tracker.exception.InternalServerErrorException;
import com.example.expense.tracker.exception.UserNotFoundException;
import com.example.expense.tracker.models.User;
import com.example.expense.tracker.models.UserDues;
import com.example.expense.tracker.repository.UserDueRepository;
import com.example.expense.tracker.repository.UserRepository;
import com.example.expense.tracker.service.interfaces.UserDueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class UserDueServiceImpl implements UserDueService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDueServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDueRepository userDueRepository;

    @Override
    public String addUserDues(UserDueDto userDueDto) {
        LOGGER.info("Inside {} to add users dues",UserDueServiceImpl.class);

        try{
            LOGGER.info("Calling user repository to fetch the userId mapped with {}", userDueDto.getUserEmailId());

            User user = userRepository.getByEmail(userDueDto.getUserEmailId());

            if(user == null)
                throw new UserNotFoundException("User with email " + userDueDto.getUserEmailId() +" not found.");

            LOGGER.info("Calling user repository to fetch the OweUserId mapped with {}", userDueDto.getOweEmailId());

            User userOwe = userRepository.getByEmail(userDueDto.getOweEmailId());

            if(userOwe == null)
                throw new UserNotFoundException("User with email " + userDueDto.getOweEmailId() +" not found.");

            UserDues userDues = mapToUserWithDues(user.getUserId(),userDueDto,userOwe.getUserId());

            LOGGER.info("Calling userDues repository to save the user dues.");

            userDueRepository.save(userDues);
        }catch(Exception exception){
            throw new InternalServerErrorException("Sorry we are unable to process this request, Please try again later.");
        }
        return Constants.SUCCESS;
    }

    @Override
    public String settleDuesFirstInFirstOut(UserDueSettlementDto userDueSettlementDto) {
        return settleDues(userDueSettlementDto.getAmount(),1,userDueSettlementDto.getUserEmailId());
    }

    @Override
    public String settleDuesLatestRepaymentDateFirst(UserDueSettlementDto userDueSettlementDto) {
        return settleDues(userDueSettlementDto.getAmount(),2,userDueSettlementDto.getUserEmailId());
    }

    private String settleDues(Double amount, int algorithm, String userEmailId){
        LOGGER.info("Calling user repository to fetch the userId mapped with {}", userEmailId);

        User user = userRepository.getByEmail(userEmailId);

        if(user == null)
            throw new UserNotFoundException("User with email " + userEmailId +" not found.");


        LOGGER.info("Calling userDue repository to fetch all the dues mapped with {}", userEmailId);

        List<UserDues> userDuesList = userDueRepository.findAllByUserId(user.getUserId());

        if(algorithm == 1){
            LOGGER.info("Sorting the userDues list with first in first out fashion.");

            Collections.sort(userDuesList, sortByFirstInFirstOut);

        } else if (algorithm == 2){
            LOGGER.info("Sorting the userDues list with Latest repayment date first fashion.");

            Collections.sort(userDuesList, sortByLatestRepaymentDateFirst);
        }


        for(UserDues userDue : userDuesList){
            if(!userDue.isPaid() && amount - userDue.getAmount() >= 0){
                amount -= userDue.getAmount();
                userDue.setPaid(true);
                userDueRepository.save(userDue);

                LOGGER.info("User due for id {} has been settled",userDue.getOweId());
            }
        }

        return Constants.SUCCESS;
    }

    private Comparator<UserDues> sortByFirstInFirstOut = new Comparator<UserDues>() {
        @Override
        public int compare(UserDues o1, UserDues o2) {
            return o1.getRepaymentDate().compareTo(o2.getRepaymentDate());
        }
    };

    private Comparator<UserDues> sortByLatestRepaymentDateFirst = new Comparator<UserDues>() {
        Date date = new Date();
        @Override
        public int compare(UserDues o1, UserDues o2) {
            return date.compareTo(o2.getRepaymentDate());
        }
    };

    private UserDues mapToUserWithDues(Long userId, UserDueDto userDueDto, Long userIdOwe) {
        UserDues userDues = new UserDues();
        userDues.setUserId(userId);
        userDues.setOweId(userIdOwe);
        userDues.setAmount(userDueDto.getAmount());
        userDues.setRepaymentDate(userDueDto.getRepaymentDate());
        return userDues;
    }
}
