package com.company;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ProjectTeam {
    private int id;
    private LocalDate startDate;
    private LocalDate endDate;
    private ArrayList<Integer> memberID;
    private ArrayList<String> memberActivity;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public ProjectTeam() {
    }

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

    // Returns the startDate value
    public LocalDate getStartDate() {
        return startDate;
    }

    // Change the startDate value
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    // Returns the endDate value
    public LocalDate getEndDate() {
        return endDate;
    }

    // Change the endDate value
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    // Returns the list with the IDs of the programmers included in the project
    public ArrayList<Integer> getMemberID() {
        return memberID;
    }

    // Change the list with the IDs of the programmers included in the project
    public void setMemberID(ArrayList<Integer> memberID) {
        this.memberID = memberID;
    }

    // Returns the list with the activities of the programmers included in the project
    public ArrayList<String> getMemberActivity() {
        return memberActivity;
    }

    // Change the list with the activities of the programmers included in the project
    public void setMemberActivity(ArrayList<String> memberActivity) {
        this.memberActivity = memberActivity;
    }

    // Function that prints the report with n1 - n12 variables
    public void printReport(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2) {
        System.out.println("IT Company is actually composed of " + list2.size() + " project teams, and " + list1.size() + " programmers.");
        System.out.println("This month, n3 programmers have been worked n4 days, and n5 days left worked.");
        System.out.println("====================================================================================");
        System.out.println("");
        System.out.println("Project teams details:");
        System.out.println("----------------------------------");

        for (ProjectTeam project : list2) {
            int size = project.getMemberActivity().size();
            System.out.println("");
            System.out.println("Project team: " + project.getId());
            System.out.println("*************************************");
            for (int i = 0; i < size; i++) {
                int search = project.getMemberID().get(i);
                ActiveProgrammers p = new ActiveProgrammers();
                for (ActiveProgrammers person : list1) {
                    if (person.getId() == search) {
                        p = person;
                    }
                }
                System.out.println((i + 1) + ") Member: " +
                        p.getLastName() +
                        ", " + p.getFirstName() +
                        ", in charge of " + project.getMemberActivity().get(i) +
                        " from " + formatter.format(p.getStartDate()) + " to " +
                        formatter.format(list2.get(i).getEndDate()));
                // Still needs n3, n4, n5, n10, n11 and n12
            }
        }
    }

    // Function that prints the list of projects (list2)
    public ArrayList printProject(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2) {

        for (ProjectTeam project : list2) {
            System.out.println("ID: " + project.getId() + " - The project started in " + formatter.format(project.getStartDate()) + " and is planned to end in " + formatter.format(project.getEndDate()) + ".");
            System.out.println("");
            System.out.println("The members of the project are: ");
            for (int i = 0; i < project.getMemberID().size(); i++) {
                ActiveProgrammers p = new ActiveProgrammers();
                int search = project.getMemberID().get(i);
                for (ActiveProgrammers person : list1) {
                    if (person.getId() == search) {
                        p = person;
                    }
                }
                System.out.println("ID: " + project.getMemberID().get(i) + " - " + p.getFirstName() + " " + p.getLastName() + ". Activity: " + project.getMemberActivity().get(i));
            }
            System.out.println("---------------------------------");
        }
        return list2;
    }

    // Function to add a new Project to list of projects (list2)
    public void addProject(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2) throws ParseException {
        ActiveProgrammers person = new ActiveProgrammers();
        ArrayList<Integer> memberId = new ArrayList<>();
        ArrayList<String> memberActivity = new ArrayList<>();
        ProjectTeam p = new ProjectTeam();
        int i = list2.size();
        p = list2.get(i-1);
        int number = p.getId()+1;
        System.out.println("Insert the start date of the project");
        String start = Main.scanner.next();
        LocalDate sDate = LocalDate.parse(start);
        System.out.println("Insert the planned end date for the project");
        String end = Main.scanner.next();
        LocalDate eDate = LocalDate.parse(end);
        int n = 0;
        boolean exit = false;
        for (ActiveProgrammers programmer : list1) {
            if (!programmer.getActive()) {
                n++;
            }
        }
        if (n < 2) {
            System.out.println("To create a new project, a minimum of 2 inactive programmers is mandatory!");
            return;
        } else {
            System.out.println("You have the following programmers available:");
            while (!exit) {
                for (ActiveProgrammers programmer : list1) {
                    if (!programmer.getActive()) {
                        System.out.println(programmer.getId() + ": " + programmer.getFirstName() + " " + programmer.getLastName());
                    }
                }
                if (n == 2) {
                    System.out.println("These are the only programmers that can be added to the project. Do you want to continue? (Y/N");
                    String yn = Main.scanner.next();
                    if (yn.equals("Y") || yn.equals("y")) {
                        System.out.println("These programmers will be added to your new project!");
                        for (ActiveProgrammers programmer : list1) {
                            if (!programmer.getActive()) {
                                memberId.add(programmer.getId());
                                programmer.setActive(true);
                                programmer.setStartDate(sDate);
                            }
                        }
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
                    } else if (yn.equals("N") || yn.equals("n")) {
                        return;
                    } else {
                        System.out.println("Invalid character!");
                        break;
                    }
                } else {
                    while (!exit) {
                        System.out.println("Please choose 2 programmers to be added by inserting the number of their ID");
                        int programmer1 = Main.scanner.nextInt();
                        Main.scanner.nextLine();
                        for(ActiveProgrammers programmer: list1) {
                            if(!programmer.getActive()) {
                                if(programmer.getId() == programmer1) {
                                    person = programmer;
                                    System.out.println("The programmer " + programmer.getFirstName() + " " + programmer.getLastName() + " will be added to your project");
                                } else {
                                    System.out.println("The ID is not valid!");
                                    break;
                                }
                            }
                        }
                        int programmer2 = Main.scanner.nextInt();
                        for(ActiveProgrammers programmer: list1) {
                            if(!programmer.getActive()) {
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
                                    ProjectTeam project = new ProjectTeam(number, sDate, eDate, memberId, memberActivity);
                                    list2.add(project);
                                    exit = true;
                                } else {
                                    System.out.println("The ID is not valid! Please insert the ID values again.");
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}