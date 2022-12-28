package com.example.cruisecompany.Model.DAO;

import com.example.cruisecompany.Model.Entity.User;

import java.sql.Date;

public interface UserDao {
    void register(String email, String passwordMD5, Date dateOfBirthday, String gender, String phoneNumber, String name, String surname);

    User getUser(String email, String passwordMD5);

    boolean isUserExist(String email);
}
