package com.example.cruisecompany.Model.Entity;

import java.sql.Date;

public class User {
    private int idUsers;
    private String email;
    private String password;
    private String role;
    private Date dateOfBirthday;
    private String gender;
    private String phoneNumber;
    private String name;
    private String surname;


    public User(int idUsers, String email, String password, String role, Date dateOfBirthday, String gender, String phoneNumber, String name, String surname) {
        this.idUsers = idUsers;
        this.email = email;
        this.password = password;
        this.role = role;
        this.dateOfBirthday = dateOfBirthday;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
    }
    public User(int idUsers, String email, String role, Date dateOfBirthday, String gender, String phoneNumber, String name, String surname) {
        this.idUsers = idUsers;
        this.email = email;
        this.role = role;
        this.dateOfBirthday = dateOfBirthday;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
    }
    public int getIdUsers() {
        return idUsers;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public Date getDateOfBirthday() {
        return dateOfBirthday;
    }

    public String getGender() {
        return gender;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
