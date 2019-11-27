package com.company;

import javax.xml.transform.TransformerException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ParseException, TransformerException {
        //Initialize ArrayLists and read xml file before enter the menu
        ArrayList<ActiveProgrammers> list1 = new ArrayList<>();
        ArrayList<ProjectTeam> list2 = new ArrayList<>();
        ArrayList<LocalDate> dateToday = new ArrayList<>();
        ManageFile member = new ManageFile();
        ActiveProgrammers programmers = new ActiveProgrammers();
        ProjectTeam project = new ProjectTeam();
        Report report = new Report();
        Main menu = new Main();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Read the xml file before everything else
        try{
            member.readFile(list1, list2, dateToday);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        //Creates a menu to choose options
        boolean quit = false;
        String choice;
        while(!quit) {
            System.out.println("Menu: ");
            menu.menu();
            choice = scanner.next();
            scanner.nextLine();
            switch (choice) {

                // To update date (+1 day) and automatically save
                case "0":
                    report.update(dateToday);
                    report.checkProjectDate(list1, list2, dateToday);
                    System.out.println("System updated to " + formatter.format(dateToday.get(0)));
                    break;

                // To initialize the report of the company (date, active projects, active programmers and salaries)
                case "1":
                    System.out.println("");
                    report.printReport(list1, list2, dateToday);
                    System.out.println("Menu");
                    System.out.println("\t Enter a number to return to main menu: ");
                    choice = scanner.next();
                    scanner.nextLine();
                    break;

                // To edit programmer's salary per day
                case "2":
                    System.out.println("These are the programmers in the company:");
                    System.out.println("-----------------------------------------");
                    // Prints the list of programmers
                    programmers.printProgrammers(list1);
                    System.out.println("\t *Enter the ID of the programmer you want to edit: ");
                    // Functions to edit programmers' fields
                    programmers.editProgrammer(list1);
                    break;

                // To insert a new programmer in xml file
                case "3":
                    programmers.addProgrammer(list1, dateToday);
                    break;

                // To insert a new project in xml file
                case "4":
                    project.addProject(list1, list2, dateToday);
                    break;

                // To delete a programmer from xml file
                case "5":
                    programmers.deleteProgrammer(list1);
                    break;

                // To save the alterations made
                case "6":
                    member.save(list1, list2, dateToday);
                    System.out.println("File saved!");
                    break;

                // To exit the menu
                case "7":
                    quit = true;
                    break;

                // If the user does not insert a valid number (as a string):
                default:
                    System.out.println("Please insert a valid number");
            }
        }
    }

    // Main menu options
    public void menu() {
        System.out.println("\t 0 - To update the system");
        System.out.println("\t 1 - To see the report of the company");
        System.out.println("\t 2 - To edit programmer's salary per day");
        System.out.println("\t 3 - To insert new programmer");
        System.out.println("\t 4 - To create new project");
        System.out.println("\t 5 - To delete a programmer");
        System.out.println("\t 6 - To save changes");
        System.out.println("\t 7 - To exit");
        System.out.println("Enter your choice: ");
    }

}
