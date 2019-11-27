package com.company;

import javax.xml.transform.TransformerException;
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
    ArrayList<LocalDate> dateToday = new ArrayList<>();

    // Function that prints the report about the IT company
    public void printReport(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2, ArrayList<LocalDate> dateToday) {
        System.out.println("Report: ");
        System.out.println("");

        // Calculate the number of programmers and projects in the IT Company
        System.out.println("IT Company is actually composed of " + list2.size() + " project teams, and " + list1.size() + " programmers.");
        //Prints the number of active programmers, the days worked in the current month and the days left worked
        System.out.println("This month, " + activeProgrammers(list1) + " programmers have been worked " + daysWorked(list1, list2, dateToday) + " days, and " + daysLeftWork(list1, list2, dateToday) + " days left worked.");
        System.out.println("====================================================================================");
        System.out.println("");
        System.out.println("Project teams details:");
        System.out.println("----------------------------------");

        // Displays the project details
        for (int i = 0; i < list2.size(); i++) {

            int size = list2.get(i).getMemberID().size();
            System.out.println("");
            System.out.println("Project team: " + list2.get(i).getId());
            System.out.println("*************************************");
            // Loop to get each programmer inserted in each project
            for (int j = 0; j < size; j++) {
                int search = list2.get(i).getMemberID().get(j);
                ActiveProgrammers p = new ActiveProgrammers();
                for (ActiveProgrammers person : list1) {
                    if (person.getId() == search) {
                        p = person;
                    }
                }
                // Prints the programmer' details for each project
                System.out.println((j + 1) + ") Programmer: " +
                        p.getLastName() +
                        ", " + p.getFirstName() +
                        ", in charge of " + list2.get(i).getMemberActivity().get(j) +
                        " from " + formatter.format(p.getStartDate()) + " to " +
                        formatter.format(list2.get(i).getEndDate()) + " (duration of "
                        + duration(list2.get(i).getMemberID().get(j), list2) + " weeks), has worked "
                        + (daysForProgrammer(p.getId(), list2, dateToday)) + " days this month (total salary "
                        + calculateSalary(daysForProgrammer(p.getId(), list2, dateToday), p.getSalaryDay(), p.getPayment()) + "€)");
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
    public int daysWorked(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2, ArrayList<LocalDate> dateToday) {
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
                            if(dateToday.get(0).getMonthValue() == programmers.getStartDate().getMonthValue()) {
                                long diff = Math.abs(ChronoUnit.DAYS.between(dateToday.get(0), (programmers.getStartDate())));
                                daysWorked += diff;
                            // Verifies if the local date' month is bigger than the programmer' month of start date of work
                            } else if(dateToday.get(0).getMonth().getValue()>programmers.getStartDate().getMonth().getValue()){
                                long diff = Math.abs(dateToday.get(0).getDayOfMonth()-1);
                                daysWorked += diff;
                            }
                        }
                    }
                }
            }
            return daysWorked;
    }

    // Function to evaluate if the year is a leap year
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
    public int daysLeftWork(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2, ArrayList<LocalDate> dateToday) {
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
                        if(dateToday.get(0).getMonth() == list2.get(i).getEndDate().getMonth()) {
                                long diff = Math.abs(ChronoUnit.DAYS.between(dateToday.get(0), (list2.get(i).getEndDate())));
                            daysLeftWork += diff;
                        // Verifies if the local date' month is smaller than the project' end date
                        } else if(dateToday.get(0).getMonth().getValue()<list2.get(i).getEndDate().getMonth().getValue()){
                            // Verifies which month is
                            if (dateToday.get(0).getMonth().getValue() == 1
                                    || dateToday.get(0).getMonth().getValue() == 3
                                    || dateToday.get(0).getMonth().getValue() == 5
                                    || dateToday.get(0).getMonth().getValue() == 7
                                    || dateToday.get(0).getMonth().getValue() == 8
                                    || dateToday.get(0).getMonth().getValue() == 10
                                    || dateToday.get(0).getMonth().getValue() == 12) {
                                long diff = Math.abs(dateToday.get(0).getDayOfMonth()-31);
                                daysLeftWork += diff;
                            } else if(dateToday.get(0).getMonth().getValue() == 2) {
                                if(isLeapYear(dateToday.get(0).getYear())) {
                                    long diff = Math.abs(dateToday.get(0).getDayOfMonth()-29);
                                    daysLeftWork += diff;
                                } else {
                                    long diff = Math.abs(dateToday.get(0).getDayOfMonth()-28);
                                    daysLeftWork += diff;
                                }
                            } else {
                                long diff = Math.abs(dateToday.get(0).getDayOfMonth()-30);
                                daysLeftWork += diff;
                            }
                        }
                    }
                }
            }
        }
        return daysLeftWork;
    }

    // Function to calculate the duration of work in weeks
    public int duration(int memberID, ArrayList<ProjectTeam> list2) {
        int duration = 0;
        //Loop to get each project in list2
        for (int i = 0; i < list2.size(); i++) {
            //variable with the number of programmers in each project
            int size = list2.get(i).getMemberID().size();
            // Loop to get each programmer' ID from the list of the projects
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
    public int daysForProgrammer(int memberID, ArrayList<ProjectTeam> list2, ArrayList<LocalDate> dateToday) {
        this.k = 0;
        //Loop to get each project in list2
        for (int i = 0; i < list2.size(); i++) {
            //variable with the number of programmers in each project
            int size = list2.get(i).getMemberID().size();
            // Loop to get each programmer' ID from the list of the projects
            for (int j = 0; j < size; j++) {
                if(list2.get(i).getMemberID().get(j) == memberID) {
                    // Verifies if the start date' month is the same of the end date
                    if (list2.get(i).getStartDate().getMonth() == list2.get(i).getEndDate().getMonth()) {
                        // If it is, then the days worked will be the difference of the two variables, in days
                        long diff = Math.abs(ChronoUnit.DAYS.between(list2.get(i).getStartDate(), list2.get(i).getEndDate()));
                        this.k = (int)diff;
                        return k;
                    // Verifies if the start date' month is before the month of the end date and if the is equal of dateToday' month
                    } else if((list2.get(i).getStartDate().getMonth().getValue() < list2.get(i).getEndDate().getMonth().getValue())
                            && list2.get(i).getStartDate().getMonth().getValue() == dateToday.get(0).getMonth().getValue()){
                        // If it is, then the days worked will be the difference of the two variables, in days
                        long diff = Math.abs(ChronoUnit.DAYS.between(list2.get(i).getStartDate(), dateToday.get(0)));
                        this.k = (int)diff;
                        return k;
                    // Verifies if the start date' month is before the month of the end date and before the dateToday' month
                    } else if ((list2.get(i).getStartDate().getMonth().getValue() < list2.get(i).getEndDate().getMonth().getValue())
                            && list2.get(i).getStartDate().getMonth().getValue() < dateToday.get(0).getMonth().getValue()){
                        // If it is, then the project started before the actual month and the days worked will be the difference between the system date' day and the day 1
                        long diff = Math.abs(dateToday.get(0).getDayOfMonth()-1);
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
        // Checks if the payment regimen is 50 or 100%
        if(payment == 50) {
            salary = k * salaryDay * 0.50;
        } else if(payment == 100) {
            salary = k * salaryDay;
        }
        return salary;
    }

    // Function that update the date (+1 day) and save
    public void update(ArrayList<LocalDate> dateToday) throws TransformerException {
        ManageFile manage = new ManageFile();
        ArrayList<ActiveProgrammers> list1 = new ArrayList<>();
        ArrayList<ProjectTeam> list2 = new ArrayList<>();
        dateToday.set(0, dateToday.get(0).plusDays(1));
        // Function to save the date changed
        manage.save(list1, list2, dateToday);
    }

    // Function that checks if a project' end date is before the system date so the project is deleted from xml file
    public void checkProjectDate(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2, ArrayList<LocalDate> dateToday) throws TransformerException {
        ManageFile manage = new ManageFile();
        for (int i = 0; i < list2.size(); i++) {
            for (ProjectTeam project : list2) {
                if(list2.get(i).getEndDate().isBefore(dateToday.get(0))) {
                    int size = list2.get(i).getMemberID().size();
                    for (int j = 0; j < size; j++) {
                            int id = list2.get(i).getMemberID().get(j);
                        for(ActiveProgrammers programmers: list1) {
                            if(programmers.getId()==id) {
                                programmers.setActive(false);
                                programmers.setStartDate(dateToday.get(0));
                                programmers.setPayment(50);
                            }
                        }
                    }
                    // Remove the project from list2
                    list2.remove(list2.get(i));
                    System.out.println("The project with ID " + list2.get(i).getId() + " was deleted. The finish date of the project was reached.");
                }
            }
        }
        // Checks if the system date' day is 1. If is is, then the days worked by each programmer are settled to 0
        for(ActiveProgrammers programmers: list1) {
            if(dateToday.get(0).getDayOfMonth() == 1) {
                programmers.setDaysWorked(0);
            }
        }
        // Calling the save function, xml file are updated
        manage.save(list1, list2, dateToday);
    }
}
