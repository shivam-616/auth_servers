package com.example.expensetracker.expense_tracker.repository;

import com.example.expensetracker.expense_tracker.entities.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long>
{
    public UserInfo findByUsername(String username);
}