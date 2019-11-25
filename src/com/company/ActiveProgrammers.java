package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;


public class ActiveProgrammers implements Programmers {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate startDate;
    private double salaryDay;
    boolean active;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    Scanner scanner = new Scanner(System.in);
    Main menu = new Main();

    public ActiveProgrammers() {
    }

    public ActiveProgrammers(int id, String firstName, String lastName, LocalDate startDate, double salaryDay, boolean active) {
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
    public LocalDate getStartDate() {
        return startDate;
    }

    // Change the startDate value
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    // Returns the salaryHour value
    public double getSalaryDay() {
        return salaryDay;
    }

    // Change the salaryHour value
    public void setSalaryDay(double salaryDay) {
        this.salaryDay = salaryDay;
    }

    // Returns the boolean value of active
    public boolean getActive() { return active; }

    // Change the boolean value of active
    public void setActive(boolean active) { this.active = active; }

    // Function that prints the list of programmers (list1)
    public ArrayList printProgrammers(ArrayList<ActiveProgrammers> list1) {

        for (ActiveProgrammers programmer: list1) {
            System.out.println("ID: " + programmer.getId() + " - " + programmer.getFirstName() + " " + programmer.getLastName() + ", started working in " + formatter.format(programmer.getStartDate()) + " and receives " + programmer.getSalaryDay() + "€ per day.");
        }
        return list1;
    }

    // Function that allows the user to edit each programmer in the list of programmers (list1)
    public void editProgrammer(ArrayList<ActiveProgrammers> list1) {
        // variable to choose the ID of the programmer to be edited
        int choice = scanner.nextInt();
        boolean verify = false;
        for(ActiveProgrammers programmer: list1) {
            if(programmer.getId() == choice) {
                System.out.println("Please insert the new salary by each day");
                double salary = Main.scanner.nextDouble();
                programmer.setSalaryDay(salary);
                System.out.println("You edited the salary with success!");
                System.out.println("ID: " + programmer.getId() + " - " + programmer.getFirstName() + " " + programmer.getLastName() + ", started working in " + formatter.format(programmer.getStartDate()) + " and receives " + programmer.getSalaryDay() + "€ per day.");
                verify = true;
                return;
            }
        }
        if(!verify) {
            System.out.println("ID is not valid!");
        }
    }

    // Function to add a new Programmer to list of programmers (list1)
    public void addProgrammer(ArrayList<ActiveProgrammers> list1) {
        ActiveProgrammers p;
        int i = list1.size();
        p = list1.get(i-1);
        int number = p.getId()+1;
        System.out.println("Please insert the first name");
        String firstName = Main.scanner.next();
        System.out.println("Now, please insert the last name");
        String lastName = Main.scanner.next();
        System.out.println("The start date will be initialized once the programmer is inserted in a new project");
        String start = "0000-01-01";
        LocalDate date = LocalDate.parse(start);
        System.out.println("Insert the salary per hour");
        double salary = scanner.nextDouble();
        this.active = false;
        ActiveProgrammers member = new ActiveProgrammers(number, firstName, lastName, date, salary, this.active);
        list1.add(member);
    }

    public void deleteProgrammer(ArrayList<ActiveProgrammers> list1) {
        System.out.println("Warning: you can only remove programmers which are not inserted in a project");
        int n = 0;
        boolean verify = false;
        for(ActiveProgrammers programmer: list1) {
            if(!programmer.getActive()) {
                n++;
                System.out.println("ID: " + programmer.getId() + " - " + programmer.getFirstName() + " " + programmer.getLastName() + ", started working in " + formatter.format(programmer.getStartDate()) + " and receives " + programmer.getSalaryDay() + "€ per day.");
            }
        }
        if(n == 0) {
            System.out.println("All the programmers are inserted in projects, you cannot delete anyone!");
            return;
        } else {
            System.out.println("From the list above, select the ID of the programmer you want to delete");
            int choice = scanner.nextInt();
            for(ActiveProgrammers programmers: list1) {
                if(programmers.getId() == choice) {
                    list1.remove(programmers);
                    System.out.println("Programmer " + programmers.getFirstName() + " " + programmers.getLastName() + " deleted!");
                    verify = true;
                    return;
                }
            }
            if (!verify) {
                System.out.println("ID is not valid!");
            }
        }
    }
}