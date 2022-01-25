package com.example.expense.tracker.service;

import com.example.expense.tracker.dto.TransactionDto;
import com.example.expense.tracker.exception.InternalServerErrorException;
import com.example.expense.tracker.exception.UserNotFoundException;
import com.example.expense.tracker.models.Transactions;
import com.example.expense.tracker.models.User;
import com.example.expense.tracker.repository.TransactionRepository;
import com.example.expense.tracker.service.interfaces.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseServiceImpl.class);

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public String addUserTransaction(TransactionDto transactions) {
        LOGGER.info("Inside {} to add users expenses",ExpenseServiceImpl.class);

        LOGGER.info("Calling user repository to fetch the userId mapped with {}", transactions.getEmailId());
        try{
            User user = null; //call userRepo

            if(user == null)
                throw new UserNotFoundException("User with email " + transactions.getEmailId() +" not found.");

            Transactions transactions1 = mapToTransaction(user.getUserId(),transactions);

            LOGGER.info("Calling transaction repository to save the user transaction.");

            transactionRepository.save(transactions1);
        }catch(Exception exception){
            throw new InternalServerErrorException("Sorry we are unable to process this request, Please try again later.");
        }
        return "Success";
    }

    private Transactions mapToTransaction(Long userId, TransactionDto trans) {
        Transactions transactions = new Transactions();
        transactions.setTransactionDate(trans.getTransactionDate());
        transactions.setAmount(trans.getAmount());
        transactions.setCategories(trans.getCategories());
        transactions.setDescription(trans.getDescription());
        transactions.setCurrencyType(trans.getCurrencyType());
        transactions.setTransactionType(trans.getTransactionType());
        transactions.setUserId(userId);
        transactions.setModeOfPayment(trans.getModeOfPayment());
        return transactions;
    }
}
