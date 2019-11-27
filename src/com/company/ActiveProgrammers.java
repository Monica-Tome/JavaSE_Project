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
    private int payment;
    private int daysWorked;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    Scanner scanner = new Scanner(System.in);
    Main menu = new Main();

    // Empty constructor so it can be initialized without arguments
    public ActiveProgrammers() {
    }

    // Constructor to create a new programmer with ActiveProgrammers' class
    public ActiveProgrammers(int id, String firstName, String lastName, LocalDate startDate, int daysWorked, double salaryDay, boolean active, int payment) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.daysWorked = daysWorked;
        this.salaryDay = salaryDay;
        this.active = active;
        this.payment = payment;
    }

    // Id only has the function to get its value due to not be allowed to be edited
    public int getId() {
        return id;
    }

    // Returns the firstName value
    public String getFirstName() {
        return firstName;
    }

    // Returns the lastName value
    public String getLastName() {
        return lastName;
    }

    // Returns the startDate value
    public LocalDate getStartDate() {
        return startDate;
    }

    // Change the startDate value
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    // Returns the value of days worked by the programmer
    public int getDaysWorked() {
        return daysWorked;
    }

    // Change the days worked by the programmer
    public void setDaysWorked(int daysWorked) {
        this.daysWorked = daysWorked;
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
    public boolean getActive() {
        return active;
    }

    // Change the boolean value of active
    public void setActive(boolean active) {
        this.active = active;
    }

    // Returns the payment regimen
    public int getPayment() {
        return payment;
    }

    // Change the payment regimen to 50% or 100%
    public void setPayment(int payment) {
        if (payment == 100) {
            this.payment = 100;
        } else if (payment == 50) {
            this.payment = 50;
        } else {
            System.out.println("The payment regimen can only be 50% or 100%");
            return;
        }
    }

    // Function that prints the list of programmers (list1)
    public void printProgrammers(ArrayList<ActiveProgrammers> list1) {

        for (ActiveProgrammers programmer : list1) {
            System.out.println("ID: " + programmer.getId() + " - " + programmer.getFirstName() + " " + programmer.getLastName() + ", started working in " + formatter.format(programmer.getStartDate()) + " and receives " + programmer.getSalaryDay() + "€ per day.");
        }
    }

    // Function that allows the user to edit each programmer' salaryDay and payment regimen in the list of programmers (list1)
    public void editProgrammer(ArrayList<ActiveProgrammers> list1) {
        // variable to choose the ID of the programmer to be edited
        String input = scanner.next();
        try {
            int choice = Integer.parseInt(input);
            boolean verify = false;
            for (ActiveProgrammers programmer : list1) {
                if (programmer.getId() == choice) {
                    System.out.println("Please insert the new salary by each day");
                    String string = Main.scanner.next();
                    try {
                        double salary = Double.parseDouble(string);
                        System.out.println("Now, define the payment regimen to 50% or 100%. Insert only the number without the %.");
                        String string2 = Main.scanner.next();
                        try {
                            int payment = Math.abs(Integer.parseInt(string2));
                            if(payment == 50 || payment == 100) {
                                programmer.setPayment(payment);
                                programmer.setSalaryDay(salary);
                                System.out.println("The programmer was edited with success!");
                                System.out.println("ID: " + programmer.getId() + " - " + programmer.getFirstName() + " " + programmer.getLastName() + ", started working in " + formatter.format(programmer.getStartDate()) + " , receives " + programmer.getSalaryDay() + "€ per day and the payment regimen is " + programmer.getPayment() + "%.");
                                verify = true;
                                return;
                            } else {
                                System.out.println("The payment can only be defined in 50 or 100%");
                            }
                        } catch (Exception e) {
                            System.out.println("Please insert a valid number");
                        }
                    } catch (Exception e) {
                        System.out.println("Please insert a valid number");
                        break;
                    }
                }
            }
            // Validates the ID inserted with the ID of the correspondent programmer
            if (!verify) {
                System.out.println("ID is not valid!");
            }
        } catch (Exception e) {
            System.out.println("Please insert a valid number");
        }
    }

    // Function to add a new Programmer to list of programmers (list1)
    public void addProgrammer(ArrayList<ActiveProgrammers> list1, ArrayList<LocalDate> dateToday) {
        ActiveProgrammers p;
        int i = list1.size();
        p = list1.get(i - 1);
        int number = p.getId() + 1;
        System.out.println("Please insert the first name");
        String firstName = Main.scanner.next();
        System.out.println("Now, please insert the last name");
        String lastName = Main.scanner.next();
        System.out.println("The start date will be settled to the system date.");
        LocalDate date = dateToday.get(0);
        System.out.println("Insert the salary per day");
        String input = scanner.next();
        try {
            double salary = Math.abs(Double.parseDouble(input));
            this.active = false;
            this.daysWorked = 0;

            // Inserts the new programmer in the list1, without saving in the xml file. If the user wants to save, it has to be saved by calling the save function
            ActiveProgrammers member = new ActiveProgrammers(number, firstName, lastName, date, daysWorked, salary, this.active, payment);
            list1.add(member);
            System.out.println("The payment regimen for an inactive programmer is 50%.");
            System.out.println("New programmer added!");
        } catch (Exception e) {
            System.out.println("Please insert a valid number");
        }
    }

    // Function to delete a programmer that is not active
    public void deleteProgrammer(ArrayList<ActiveProgrammers> list1) {
        System.out.println("Warning: you can only remove programmers which are not inserted in a project");
        int n = 0;
        boolean verify = false;
        for (ActiveProgrammers programmer : list1) {
            if (!programmer.getActive()) {
                n++;
                System.out.println("ID: " + programmer.getId() + " - " + programmer.getFirstName() + " " + programmer.getLastName() + ", started working in " + formatter.format(programmer.getStartDate()) + " and receives " + programmer.getSalaryDay() + "€ per day.");
            }
        }

        // If there are no programmers inactive, then the user cannot delete anyone
        if (n == 0) {
            System.out.println("All the programmers are inserted in projects, you cannot delete anyone!");
            return;

            // If there are programmers active, the user can choose which one he wants to delete
        } else {
            System.out.println("From the list above, select the ID of the programmer you want to delete");
            String input = scanner.next();
            try {
                int choice = Integer.parseInt(input);
                for (ActiveProgrammers programmers : list1) {
                    if (programmers.getId() == choice) {
                        // The remove function removes the entire object (programmer)
                        list1.remove(programmers);
                        // Prints the list with the programmers last after removing the other one
                        System.out.println("Programmer " + programmers.getFirstName() + " " + programmers.getLastName() + " deleted!");
                        verify = true;
                        return;
                    }
                }
                // Validates the ID inserted with the ID of the correspondent programmer
                if (!verify) {
                    System.out.println("ID is not valid!");
                }
            } catch (Exception e) {
                System.out.println("Please insert a valid number");
            }
        }
    }
}