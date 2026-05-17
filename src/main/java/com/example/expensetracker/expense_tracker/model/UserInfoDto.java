package com.example.expensetracker.expense_tracker.model;

import com.example.expensetracker.expense_tracker.entities.UserInfo;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming ()
public class UserInfoDto extends UserInfo
{

    private String firstName; // first_name

    private String lastName; //last_name

    private Long phoneNumber;

    private String email; // email


}
