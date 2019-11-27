package com.company;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ProjectTeam {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<Integer> memberID;
    private ArrayList<String> memberActivity;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Empty constructor so it can be initialized without arguments
    public ProjectTeam() {
    }

    // Constructor to create a new project with ProjectTeam' class
    public ProjectTeam(int id, LocalDate startDate, LocalDate endDate, ArrayList<Integer> memberID, ArrayList<String> memberActivity) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.memberID = memberID;
        this.memberActivity = memberActivity;
    }

    // Id only has the function to get its value due to not be allowed to be edited
    public int getId() {
        return id;
    }

    // Returns the startDate value and cannot be settled to any other because the start will always be the day of the update
    public LocalDate getStartDate() {
        return startDate;
    }

    // Returns the endDate value
    public LocalDate getEndDate() {
        return endDate;
    }

    // Change the endDate value
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    // Returns the list with the IDs of the programmers included in the project (cannot be settled because it is synchronized with the programmer'ID
    public ArrayList<Integer> getMemberID() {
        return memberID;
    }

    // Returns the list with the activities of the programmers included in the project (cannot be settled)
    public ArrayList<String> getMemberActivity() {
        return memberActivity;
    }

    // Function to add a new Project to list of projects (list2)
    public void addProject(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2, ArrayList<LocalDate> dateToday) throws ParseException {
        Report report = new Report();
        ActiveProgrammers person = new ActiveProgrammers();
        ArrayList<Integer> memberId = new ArrayList<>();
        ArrayList<String> memberActivity = new ArrayList<>();
        ProjectTeam p = new ProjectTeam();
        // Variable i to get the list2 size
        int i = list2.size();
        // Variable p to get the last project of list2
        p = list2.get(i - 1);
        // Variable to get the last project ID
        int number = p.getId() + 1;
        // Variable used to count the number of inactive programmers
        int n = 0;
        boolean exit = false;
        // Loop to get the number of inactive programmers
        for (ActiveProgrammers programmer : list1) {
            if (!programmer.getActive()) {
                n++;
            }
        }
        // Validates if the inactive programmers are less than 2. If there is less than 2, then the user cannot add a new project
        if (n < 2) {
            System.out.println("To create a new project, a minimum of 2 inactive programmers is mandatory!");
            return;
        // If there are more than 2 inactive programmers, then the user can choose which ones to add
        } else {
            System.out.println("Note that the start date of the project will be the date system!");
            LocalDate sDate = dateToday.get(0);
            System.out.println("Insert the planned end date for the project (format yyyy-MM-dd)");
            String end = Main.scanner.next();
            LocalDate eDate = LocalDate.parse(end);
            // Validates if the endDate introduced by the user is before the start date. If it is, the user cannot continue.
            if(eDate.isBefore(sDate)) {
                System.out.println("You entered a final date that begins before the start date of the project!");
                return;
            } else {
                System.out.println("You have the following programmers available:");
                while (!exit) {
                    for (ActiveProgrammers programmer : list1) {
                        if (!programmer.getActive()) {
                            System.out.println("ID " + programmer.getId() + ": " + programmer.getFirstName() + " " + programmer.getLastName());
                        }
                    }
                    // If there is only 2 inactive programmers, the user is obligated to add only these ones.
                    if (n == 2) {
                        System.out.println("These are the only programmers that can be added to the project. Do you want to continue? (Y/N");
                        String yn = Main.scanner.next();
                        // If the user wants to continue, the programmers are added to the project and their active status and their start date changes
                        if (yn.equals("Y") || yn.equals("y")) {
                            System.out.println("These programmers will be added to your new project!");
                            for (ActiveProgrammers programmer : list1) {
                                if (!programmer.getActive()) {
                                    memberId.add(programmer.getId());
                                    programmer.setActive(true);
                                    programmer.setStartDate(sDate);
                                }
                            }
                            // Activities insertion for each programmer added before
                            System.out.println("Now, please choose the activities to the corresponding programmers. Ex: activity 1 will be corresponded to the first programmer in the list added, and so on.");
                            System.out.println("Activity for programmer 1:");
                            String activity1 = Main.scanner.next();
                            System.out.println("Activity for programmer 2:");
                            String activity2 = Main.scanner.next();
                            memberActivity.add(activity1);
                            memberActivity.add(activity2);
                            ProjectTeam project = new ProjectTeam(number, sDate, eDate, memberId, memberActivity);
                            list2.add(project);
                            System.out.println("The project was created with success!");
                            exit = true;
                        // If the user does not want to continue, the cycle will break.
                        } else if (yn.equals("N") || yn.equals("n")) {
                            return;
                        // To validate if user only inserted yes or no.
                        } else {
                            System.out.println("Invalid character!");
                            break;
                        }
                    // If there are more than 2 inactive programmers, the user can choose at minimum 2 of them.
                    } else {
                        // Variables to check the course of for cycles
                        boolean verify = false;
                        boolean verify2 = false;
                        boolean verify3 = false;
                        while (!exit) {
                            System.out.println("Please choose the programmers to be added by inserting the number of their ID (minimum of 2 programmers have to be added)");
                            String string  = Main.scanner.next();
                            try {
                                int programmer1 = Integer.parseInt(string);
                                Main.scanner.nextLine();
                                for (ActiveProgrammers programmer : list1) {
                                    if (!programmer.getActive()) {
                                        if (programmer.getId() == programmer1) {
                                            person = programmer;
                                            System.out.println("The programmer " + programmer.getFirstName() + " " + programmer.getLastName() + " will be added to your project");
                                            System.out.println("Please insert the second programmer to be added.");
                                            verify = true;
                                        }
                                    }
                                }
                                // Only continues if the programmer ID inserted by the user correspond to a programmer' ID and the programmer is inactive
                                if (verify) {
                                    int programmer2 = Main.scanner.nextInt();
                                    for (ActiveProgrammers programmer : list1) {
                                        if (!programmer.getActive()) {
                                            if (programmer.getId() == programmer2) {
                                                System.out.println("The programmer " + programmer.getFirstName() + " " + programmer.getLastName() + " was added to your project");
                                                System.out.println("Now, please choose the activities to the corresponding programmers. Ex: activity 1 will be corresponded to the first programmer in the list added, and so on");
                                                System.out.println("Activity for programmer 1:");
                                                String activity1 = Main.scanner.next();
                                                System.out.println("Activity for programmer 2:");
                                                String activity2 = Main.scanner.next();
                                                memberId.add(programmer1);
                                                memberId.add(programmer2);
                                                memberActivity.add(activity1);
                                                memberActivity.add(activity2);
                                                person.setStartDate(sDate);
                                                programmer.setStartDate(sDate);
                                                person.setActive(true);
                                                programmer.setActive(true);
                                                System.out.println("The programmers and corresponding activities were added with success!");
                                                n--;
                                                n--;
                                                verify2 = true;
                                                exit = true;
                                            }
                                        }
                                    }
                                    // Only continues if the second programmer was successfully added.
                                    if (!verify2) {
                                        System.out.println("The ID of the second programmer is not valid. Please insert the IDs again.");
                                    }
                                } else {
                                    System.out.println("The ID is not valid!");
                                    break;
                                }
                            } catch (Exception e) {
                                System.out.println("Please insert a valid number");
                            }
                        }
                        // To give the user the opportunity to choose another programmer, if there are more inactive programmers, but only if the other 2 programmers were successfully added.
                        exit = false;
                        if (verify2) {
                            while (!exit) {
                                // If there are more inactive programmers, the user can choose to continue adding programmers to the project or not.
                                if (n > 0) {
                                    System.out.println("Do you want to continue adding programmers? (Y/N)");
                                    String yn = Main.scanner.next();
                                    //If the user wants to continue to add more programmers:
                                    if (yn.equals("Y") || yn.equals("y")) {
                                        for (ActiveProgrammers programmer : list1) {
                                            if (!programmer.getActive()) {
                                                System.out.println("ID " + programmer.getId() + ": " + programmer.getFirstName() + " " + programmer.getLastName());
                                            }
                                        }
                                        System.out.println("Please choose the programmer to be added by inserting the number of his ID");
                                        String programmer2  = Main.scanner.next();
                                        try {
                                            int nextProgrammer = Integer.parseInt(programmer2);
                                            for (ActiveProgrammers programmer : list1) {
                                                if (!programmer.getActive()) {
                                                    if (programmer.getId() == nextProgrammer) {
                                                        System.out.println("The programmer " + programmer.getFirstName() + " " + programmer.getLastName() + " will be added to your project");
                                                        System.out.println("Please insert the programmer' activity:");
                                                        String activity = Main.scanner.next();
                                                        memberId.add(nextProgrammer);
                                                        memberActivity.add(activity);
                                                        programmer.setStartDate(sDate);
                                                        programmer.setActive(true);
                                                        n--;
                                                        verify3 = true;
                                                        if (n == 0) {
                                                            ProjectTeam project = new ProjectTeam(number, sDate, eDate, memberId, memberActivity);
                                                            list2.remove(project);
                                                            list2.add(project);
                                                            System.out.println("New project created with success! You does not have more programmers available to add.");
                                                            exit = true;
                                                        } else if (n > 0) {
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                            // Verifies if the ID inserted was not valid without breaking the cycle
                                            if (!verify3) {
                                                System.out.println("ID is not valid. Please insert again.");
                                            }
                                        } catch (Exception e) {
                                            System.out.println("Please insert a valid number");
                                        }
                                    // If the user does not choose to continue adding more programmers, then the project is created.
                                    } else if (yn.equals("N") || yn.equals("n")) {
                                        System.out.println("New project created with success!");
                                        ProjectTeam project = new ProjectTeam(number, sDate, eDate, memberId, memberActivity);
                                        list2.add(project);
                                        exit = true;
                                    } else {
                                        System.out.println("Invalid character! Please try again.");
                                        break;
                                    }
                                } else {
                                    System.out.println("You does not have more programmers available!");
                                    System.out.println("New project created with success!");
                                    exit = true;
                                }
                            }
                        } else {
                            System.out.println("Something went wrong! Please try again.");
                            break;
                        }
                    }
                }
            }
        }
    }
}