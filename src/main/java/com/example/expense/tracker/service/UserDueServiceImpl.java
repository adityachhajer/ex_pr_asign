package com.example.expense.tracker.service;

import com.example.expense.tracker.config.Constants;
import com.example.expense.tracker.dto.UserDueDto;
import com.example.expense.tracker.exception.InternalServerErrorException;
import com.example.expense.tracker.exception.UserNotFoundException;
import com.example.expense.tracker.models.Transactions;
import com.example.expense.tracker.models.User;
import com.example.expense.tracker.models.UserDues;
import com.example.expense.tracker.repository.UserDueRepository;
import com.example.expense.tracker.repository.UserRepository;
import com.example.expense.tracker.service.interfaces.UserDueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    private UserDues mapToUserWithDues(Long userId, UserDueDto userDueDto, Long userIdOwe) {
        UserDues userDues = new UserDues();
        userDues.setUserId(userId);
        userDues.setOweId(userIdOwe);
        userDues.setAmount(userDueDto.getAmount());
        userDues.setRepaymentDate(userDueDto.getRepaymentDate());
        return userDues;
    }
}
