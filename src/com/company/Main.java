package com.company;

import java.util.ArrayList;
import java.util.Scanner;
import java.text.ParseException;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws ParseException {
        //Initialize ArrayLists and read xml file before enter the menu
        ArrayList<ActiveProgrammers> list1 = new ArrayList<>();
        ArrayList<NewProject> list2 = new ArrayList<>();
        ReadFile member = new ReadFile();
        member.readFile(list1, list2);
        ActiveProgrammers programmers = new ActiveProgrammers();
        NewProject project = new NewProject();
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
                // To see the report
                case 1:
                    project.printReport(list1, list2);
                    System.out.println("");
                    System.out.println("Menu");
                    System.out.println("\t Enter a number to return to main menu: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                // To edit programmers' data
                case 2:
                    System.out.println("These are the programmers in the company:");
                    System.out.println("-----------------------------------------");
                    // Prints the list of programmers
                    programmers.printProgrammers(list1);
                    System.out.println("\t *Enter the ID of the programmer you want to edit: ");
                    // Functions to edit programmers' fields
                    programmers.editProgrammer(list1);
                    System.out.println("\t Enter a number to return to main menu: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;

                // To edit projects' data
                case 3:
                    System.out.println("These are the projects in the company:");
                    System.out.println("-----------------------------------------");
                    // Prints the list of projects and the programmers included in each project
                    project.printProject(list1, list2);
                    System.out.println("\t *Enter the ID of the project you want to edit: ");
                    // Functions to edit projects' fields
//                    project.editProject(list1, list2);
                    System.out.println("\t Enter a number to return to main menu: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;

                // To insert a new programmer in xml file
                case 4:
                    programmers.addProgrammer(list1);
                    break;

                // To insert a new project in xml file
                case 5:
                    System.out.println("Still updating5");
                    break;

                // To update the system
                case 6:
                    System.out.println("Still updating6");
                    break;

                // To delete a programmer from xml file
                case 7:
                    System.out.println("Still updating7");
                    break;

                // To delete a project from xml file
                case 8:
                    System.out.println("Still updating8");
                    break;

                // To exit
                case 9:
                    quit = true;
                    break;
            }
        }
    }

    // Main menu options
    public void menu() {
        System.out.println("\t 1 - To see the report of programmers and projects");
        System.out.println("\t 2 - To edit programmers");
        System.out.println("\t 3 - To edit projects");
        System.out.println("\t 4 - To insert new programmer");
        System.out.println("\t 5 - To insert new project");
        System.out.println("\t 6 - To update the system");
        System.out.println("\t 7 - To delete a programmer");
        System.out.println("\t 8 - To delete a project");
        System.out.println("\t 9 - To exit");
        System.out.println("Enter your choice: ");
    }

    // Menu to choose the programmer' field to edit
    public void submenuEditProgrammer() {
        System.out.println("\t *Enter the choice number to edit: ");
        System.out.println("\t 1 - First name");
        System.out.println("\t 2 - Last name");
        System.out.println("\t 3 - Start date of work ");
        System.out.println("\t 4 - Salary per day ");
    }

    // Menu to choose the project' field to edit
    public void submenuEditProject() {
        System.out.println("\t *Enter the choice number to edit: ");
        System.out.println("\t 1 - Start date");
        System.out.println("\t 2 - End date");
        System.out.println("\t 3 - Insert new programmer");
        System.out.println("\t 4 - Delete a programmer");
    }
}
