package com.company;

import java.io.Console;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Report {
    public Console dateFormat;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    int k;
    CharSequence text;
    LocalDate date = LocalDate.parse("2019-11-27");

    // Function that prints the report about the IT company
    public void printReport(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2) {
        System.out.println("Report date: " + formatter.format(date));
        System.out.println("");

        // Calculate the number of programmers and projects in the IT Company
        System.out.println("IT Company is actually composed of " + list2.size() + " project teams, and " + list1.size() + " programmers.");
        //Prints the number of active programmers, the days worked in the current month and the days left worked
        System.out.println("This month, " + activeProgrammers(list1) + " programmers have been worked " + daysWorked(list1, list2) + " days, and " + daysLeftWork(list1, list2) + " days left worked.");
        System.out.println("====================================================================================");
        System.out.println("");
        System.out.println("Project teams details:");
        System.out.println("----------------------------------");
        // + daysLeftWork() + " days left worked.");

        // Displays the project details
        for (int i = 0; i < list2.size(); i++) {

            int size = list2.get(i).getMemberID().size();
            System.out.println("");
            System.out.println("Project team: " + list2.get(i).getId());
            System.out.println("*************************************");
            for (int j = 0; j < size; j++) {
                int search = list2.get(i).getMemberID().get(j);
                ActiveProgrammers p = new ActiveProgrammers();
                for (ActiveProgrammers person : list1) {
                    if (person.getId() == search) {
                        p = person;
                    }
                }
                System.out.println((j + 1) + ") Member: " +
                        p.getLastName() +
                        ", " + p.getFirstName() +
                        ", in charge of " + list2.get(i).getMemberActivity().get(j) +
                        " from " + formatter.format(p.getStartDate()) + " to " +
                        formatter.format(list2.get(i).getEndDate()) + " (duration of "
                        + duration(list2.get(i).getMemberID().get(j), list2) + " weeks), has worked "
                        + daysForProgrammer(p.getId(), list2) + " days this month (total salary "
                        + calculateSalary(daysForProgrammer(p.getId(), list2), p.getSalaryDay(), p.getPayment()) + "€)");
            }
        }
    }

    // Function to calculate the number of active programmers
    public int activeProgrammers(ArrayList<ActiveProgrammers> list1) {
        int n = 0;
        for(ActiveProgrammers programmers: list1) {
            if(programmers.getActive()) {
                n++;
            }
        }
        return n;
    }

    // Function to calculate the days worked by the active programmers
    public int daysWorked(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2) {
       int daysWorked = 0;
       int id;
        //Loop to get each project in list2
        for (int i = 0; i < list2.size(); i++) {
            //variable with the number of programmers in each project
            int size = list2.get(i).getMemberID().size();
            // Loop to get each programmer' ID from the list of the projects
            for (int j = 0; j < size; j++) {
                    id = list2.get(i).getMemberID().get(j);
                // Loop to get each programmer from list1 which are in the project list (list2)
                for (ActiveProgrammers programmers: list1) {
                        if(id == programmers.getId()) {
                            // Verifies if the local date' month is the same of programmer' start date of work
                            if(LocalDate.now().getMonth() == programmers.getStartDate().getMonth()) {
                                long diff = Math.abs(ChronoUnit.DAYS.between(LocalDate.now(), (programmers.getStartDate())));
                                daysWorked += diff;
                            // Verifies if the local date' month is bigger than the programmer' month of start date of work
                            } else if(LocalDate.now().getMonth().getValue()>programmers.getStartDate().getMonth().getValue()){
                                long diff = Math.abs(LocalDate.now().getDayOfMonth()-1);
                                daysWorked += diff;
                            }
                        }
                    }
                }
            }
            return daysWorked;
    }

    // Function to evaluate is a year is a leap year or not
    public static boolean isLeapYear(int year) {
        if(year >=1 && year <=9999) {
            if((year %4 == 0 && year %100 != 0) || year %400 == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // Function to calculate the days left worked by the active programmers
    public int daysLeftWork(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2) {
        int daysLeftWork = 0;
        int id;

        //Loop to get each project in list2
        for (int i = 0; i < list2.size(); i++) {
            //variable with the number of programmers in each project
            int size = list2.get(i).getMemberID().size();
            // Loop to get each programmer' ID from the list of the projects
            for (int j = 0; j < size; j++) {
                id = list2.get(i).getMemberID().get(j);
                // Loop to get each programmer from list1 which are in the project list (list2)
                for (ActiveProgrammers programmers: list1) {
                    if(id == programmers.getId()) {
                        // Verifies if the local date' month is the same of project' end date
                        if(LocalDate.now().getMonth() == list2.get(i).getEndDate().getMonth()) {
                                long diff = Math.abs(ChronoUnit.DAYS.between(LocalDate.now(), (list2.get(i).getEndDate())));
                            daysLeftWork += diff;
                        // Verifies if the local date' month is smaller than the project' end date
                        } else if(LocalDate.now().getMonth().getValue()<list2.get(i).getEndDate().getMonth().getValue()){
                            // Verifies which month is
                            if (LocalDate.now().getMonth().getValue() == 1
                                    || LocalDate.now().getMonth().getValue() == 3
                                    || LocalDate.now().getMonth().getValue() == 5
                                    || LocalDate.now().getMonth().getValue() == 7
                                    || LocalDate.now().getMonth().getValue() == 8
                                    || LocalDate.now().getMonth().getValue() == 10
                                    || LocalDate.now().getMonth().getValue() == 12) {
                                long diff = Math.abs(LocalDate.now().getDayOfMonth()-31);
                                daysLeftWork += diff;
                            } else if(LocalDate.now().getMonth().getValue() == 2) {
                                if(isLeapYear(LocalDate.now().getYear())) {
                                    long diff = Math.abs(LocalDate.now().getDayOfMonth()-29);
                                    daysLeftWork += diff;
                                } else {
                                    long diff = Math.abs(LocalDate.now().getDayOfMonth()-28);
                                    daysLeftWork += diff;
                                }
                            } else {
                                long diff = Math.abs(LocalDate.now().getDayOfMonth()-30);
                                daysLeftWork += diff;
                            }
                        }
                    }
                }
            }
        }
        return daysLeftWork;
    }

    // Function to calculate the duration of work
    public int duration(int memberID, ArrayList<ProjectTeam> list2) {
        int duration = 0;
        for (int i = 0; i < list2.size(); i++) {
            int size = list2.get(i).getMemberID().size();
            for (int j = 0; j < size; j++) {
                if(list2.get(i).getMemberID().get(j) == memberID) {
                    long diff = Math.abs(ChronoUnit.WEEKS.between(list2.get(i).getEndDate(), list2.get(i).getStartDate()));
                    duration += diff;
                }
            }
        }
        return duration;
    }

    // Function to calculate the days worked by each active programmer in the project
    public int daysForProgrammer(int memberID, ArrayList<ProjectTeam> list2) {
        this.k = 0;
        for (int i = 0; i < list2.size(); i++) {
            int size = list2.get(i).getMemberID().size();
            for (int j = 0; j < size; j++) {
                if(list2.get(i).getMemberID().get(j) == memberID) {
                    if (list2.get(i).getStartDate().getMonth() == list2.get(i).getEndDate().getMonth()) {
                        long diff = Math.abs(ChronoUnit.DAYS.between(list2.get(i).getStartDate(), list2.get(i).getEndDate()));
                        this.k = (int)diff;
                        return k;
                    } else if((list2.get(i).getStartDate().getMonth().getValue() < list2.get(i).getEndDate().getMonth().getValue())
                            && list2.get(i).getStartDate().getMonth().getValue() == LocalDate.now().getMonth().getValue()){
                        long diff = Math.abs(ChronoUnit.DAYS.between(list2.get(i).getStartDate(), LocalDate.now()));
                        this.k = (int)diff;
                        return k;
                    } else if ((list2.get(i).getStartDate().getMonth().getValue() < list2.get(i).getEndDate().getMonth().getValue())
                            && list2.get(i).getStartDate().getMonth().getValue() < LocalDate.now().getMonth().getValue()){
                        long diff = Math.abs(LocalDate.now().getDayOfMonth()-1);
                        this.k = (int)diff;
                    }
                }
            }
        }
        return this.k;
    }

    // Function to calculate the salary based on the payment and days worked by the active programmer
    public double calculateSalary(int k, double salaryDay, int payment) {
        double salary = 0;
        if(payment == 50) {
            salary = k * salaryDay * 0.50;
        } else if(payment == 100) {
            salary = k * salaryDay;
        }
        return salary;
    }

    public void update() {
        this.date.plusDays(1);
    }
}
