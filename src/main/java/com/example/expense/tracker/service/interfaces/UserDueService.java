package com.example.expense.tracker.service.interfaces;


import com.example.expense.tracker.dto.UserDueDto;
import com.example.expense.tracker.dto.UserDueSettlementDto;

public interface UserDueService {
    String addUserDues(UserDueDto userDueDto);

    String settleDuesFirstInFirstOut(UserDueSettlementDto userDueSettlementDto);

    String settleDuesLatestRepaymentDateFirst(UserDueSettlementDto userDueSettlementDto);
}
