package com.example.expense.tracker.service;

import com.example.expense.tracker.config.Constants;
import com.example.expense.tracker.dto.UserDto;
import com.example.expense.tracker.exception.EmailAlreadyPresentException;
import com.example.expense.tracker.models.User;
import com.example.expense.tracker.repository.UserRepository;
import com.example.expense.tracker.service.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String addUser(UserDto userDto) {
        LOGGER.info("Inside {} to add new user",UserServiceImpl.class);

        User user = mapToUser(userDto);

        try{
            userRepository.save(user);
        } catch (Exception e){
            LOGGER.error("User was not added to database.");
            throw new EmailAlreadyPresentException("User with same email "+ user.getEmail()+" already present");
        }
        return Constants.SUCCESS;
    }

    private User mapToUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        return user;
    }
}
