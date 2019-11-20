package com.company;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Initialize ArrayLists and read xml file before enter the menu
        ArrayList<ActiveProgrammers> list1 = new ArrayList<>();
        ArrayList<NewProject> list2 = new ArrayList<>();
        ReadFile member = new ReadFile();
        member.readFile(list1, list2);

        //Creates a menu to choose what to do
        boolean quit = false;
        int choice = 0;
        while(!quit) {
            System.out.println("Menu: ");
            System.out.println("\t 1 - To see the report of programmers and projects");
            System.out.println("\t 2 - To insert new data");
            System.out.println("\t 3 - To edit data");
            System.out.println("\t 4 - To update the system");
            System.out.println("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    NewProject project = new NewProject();
                    project.printProject(list1, list2);
                    System.out.println("");
                    System.out.println("Menu");
                    System.out.println("\t 0 - To exit");
                    System.out.println("Enter 0 to exit: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                case 2:
                    System.out.println("To insert data");
                    System.out.println("\t 0 - To exit");
                    System.out.println("Enter 0 to exit: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                case 3:
                    System.out.println("To edit data");
                    System.out.println("\t 0 - To exit");
                    System.out.println("Enter 0 to exit: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                case 4:
                    System.out.println("To update the system");
                    System.out.println("\t 0 - To exit");
                    System.out.println("Enter 0 to exit: ");
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                case 0:
                    quit = true;
                    break;
            }
        }
    }
}
