package com.company;

import java.util.ArrayList;

public class ActiveProgrammers implements Programmers {
    private int id;
    private String firstName;
    private String lastName;
    private String startDate;
    private int salaryDay;
    boolean active;

    public ActiveProgrammers() {
    }

    public ActiveProgrammers(int id, String firstName, String lastName, String startDate, int salaryDay, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.salaryDay = salaryDay;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getSalaryHour() {
        return salaryDay;
    }

    public void setSalaryHour(int salaryDay) {
        this.salaryDay = salaryDay;
    }

}
