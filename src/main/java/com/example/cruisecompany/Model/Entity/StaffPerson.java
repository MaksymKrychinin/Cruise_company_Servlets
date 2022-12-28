package com.example.cruisecompany.Model.Entity;

public class StaffPerson {
    private int id;
    private String onCruiseNumber;
    private String name;
    private String role;

    public int getId() {
        return id;
    }

    public String getOnCruiseNumber() {
        return onCruiseNumber;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public StaffPerson(int id, String onCruiseNumber, String name, String role) {
        this.id = id;
        this.onCruiseNumber = onCruiseNumber;
        this.name = name;
        this.role = role;
    }

    @Override
    public String toString() {
        return "StaffPerson{" +
                "onCruiseNumber='" + onCruiseNumber + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
