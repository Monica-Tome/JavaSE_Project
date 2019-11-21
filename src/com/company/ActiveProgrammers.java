package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class ActiveProgrammers implements Programmers {
    private int id;
    private String firstName;
    private String lastName;
    private Date startDate;
    private int salaryDay;
    boolean active;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public ActiveProgrammers() {
    }

    public ActiveProgrammers(int id, String firstName, String lastName, Date startDate, int salaryDay, boolean active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.salaryDay = salaryDay;
        this.active = active;
    }

    // Id only has the function to get its value due to not be allowed to be edited
    public int getId() {
        return id;
    }

    // Returns the firstName value
    public String getFirstName() {
        return firstName;
    }

    // Change the firstName value
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Returns the lastName value
    public String getLastName() {
        return lastName;
    }

    // Change the lastName value
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Returns the startDate value
    public Date getStartDate() {
        return startDate;
    }

    // Change the startDate value
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    // Returns the salaryHour value
    public int getSalaryHour() {
        return salaryDay;
    }

    // Change the salaryHour value
    public void setSalaryHour(int salaryDay) {
        this.salaryDay = salaryDay;
    }

    // Returns the boolean value of active
    public boolean isActive() { return active; }

    // Change the boolean value of active
    public void setActive(boolean active) { this.active = active; }

    // Function that prints the list of programmers (list1)
    public ArrayList printProgrammers(ArrayList<ActiveProgrammers> list1) {

        for (ActiveProgrammers programmer: list1) {
            System.out.println("ID: " + programmer.getId() + " - " + programmer.getFirstName() + " " + programmer.getLastName() + ", started working in " + dateFormat.format(programmer.getStartDate()) + " and receives " + programmer.getSalaryHour() + "€ per day.");
        }
        return list1;
    }

    // Function that allows the user to edit each programmer in the list of programmers (list1)
    public void editProgrammer(ArrayList<ActiveProgrammers> list1) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        Main menu = new Main();

        // variable to choose the ID of the programmer to be edited
        int choice = scanner.nextInt();
        menu.submenuEditProgrammer();

        // variable to choose the field of the programmer to be edited
        int field = Main.scanner.nextInt();
        switch (field) {
            case 1:
                System.out.println("Insert the first name: ");
                // variable with the text introduced by the user
                String choiceString = Main.scanner.next();
                for (ActiveProgrammers programmer : list1) {
                    if (choice == programmer.getId()) {
                       programmer.setFirstName(choiceString);
                    }
                }
                break;
            case 2:
                System.out.println("Insert the last name: ");
                // variable with the text introduced by the user
                choiceString = Main.scanner.next();
                for (ActiveProgrammers programmer : list1) {
                    if (choice == programmer.getId()) {
                        programmer.setLastName(choiceString);
                    }
                }
                break;
            case 3:
                System.out.println("Insert the start date of work (format dd-MM-yyy: ");
                // variable with the text introduced by the user
                String date = Main.scanner.next();
                Date start = dateFormat.parse(date);
                for (ActiveProgrammers programmer : list1) {
                    if (choice == programmer.getId()) {
                        programmer.setStartDate(start);
                    }
                }
                break;
            case 4:
                System.out.println("Insert the salary per hour: ");
                // variable with the text introduced by the user
                int salary = Main.scanner.nextInt();
                for (ActiveProgrammers programmer : list1) {
                    if (choice == programmer.getId()) {
                        programmer.setSalaryHour(salary);
                    }
                }
                break;
        }
    }
}