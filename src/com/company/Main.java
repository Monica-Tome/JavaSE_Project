package com.company;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {
        //Initialize ArrayLists and read xml file before enter the menu
        ArrayList<ActiveProgrammers> list1 = new ArrayList<>();
        ArrayList<ProjectTeam> list2 = new ArrayList<>();
        ReadFile member = new ReadFile();
        member.readFile(list1, list2);
        ActiveProgrammers programmers = new ActiveProgrammers();
        ProjectTeam project = new ProjectTeam();
        Main menu = new Main();

        //Creates a menu to choose options
        boolean quit = false;
        int choice = 0;
        //
        while(!quit) {
            System.out.println("Menu: ");
            menu.menu();
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                // To update the system and see the report of the company
                case 1:
                    project.printReport(list1, list2);
                    System.out.println("");
                    System.out.println("Menu");
                    System.out.println("\t Enter a number to return to main menu: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                // To edit programmer's salary per day
                case 2:
                    System.out.println("These are the programmers in the company:");
                    System.out.println("-----------------------------------------");
                    // Prints the list of programmers
                    programmers.printProgrammers(list1);
                    System.out.println("\t *Enter the ID of the programmer you want to edit: ");
                    // Functions to edit programmers' fields
                    programmers.editProgrammer(list1);
                    break;

                // To insert a new programmer in xml file
                case 3:
                    programmers.addProgrammer(list1);
                    break;

                // To insert a new project in xml file
                case 4:
                    project.addProject(list1, list2);
                    break;

                // To delete a programmer from xml file
                case 5:
                    programmers.deleteProgrammer(list1);
                    break;

                // To delete a project from xml file
                case 6:
                    System.out.println("Still updating7");
                    break;

                // To save alterations made
                case 7:
                    System.out.println("Still updating8");
                    break;

                // To exit
                case 8:
                    quit = true;
                    break;
            }
        }
    }

    // Main menu options
    public void menu() {
        System.out.println("\t 1 - To update the system and see the report of the company");
        System.out.println("\t 2 - To edit programmer's salary per day");
        System.out.println("\t 3 - To insert new programmer");
        System.out.println("\t 4 - To insert new project");
        System.out.println("\t 5 - To delete a programmer");
        System.out.println("\t 6 - To delete a project");
        System.out.println("\t 7 - To save changes in XML file");
        System.out.println("\t 8 - To exit");
        System.out.println("Enter your choice: ");
    }

}
