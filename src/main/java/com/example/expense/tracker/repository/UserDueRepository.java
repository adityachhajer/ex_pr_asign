package com.example.expense.tracker.repository;

import com.example.expense.tracker.models.UserDues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDueRepository extends JpaRepository<UserDues,Long> {
}
