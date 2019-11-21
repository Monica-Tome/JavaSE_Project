package com.company;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class NewProject {
    private int id;
    private Date startDate;
    private Date endDate;
    private ArrayList<Integer> memberID;
    private ArrayList<String> memberActivity;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public NewProject() {
    }

    public NewProject(int id, Date startDate, Date endDate, ArrayList<Integer> memberID, ArrayList<String> memberActivity) {
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
    public Date getStartDate() {
        return startDate;
    }

    // Change the startDate value
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    // Returns the endDate value
    public Date getEndDate() {
        return endDate;
    }

    // Change the endDate value
    public void setEndDate(Date endDate) {
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
    public void printReport(ArrayList<ActiveProgrammers> list1, ArrayList<NewProject> list2) {
        System.out.println("IT Company is actually composed of " + list2.size() + " project teams, and " + list1.size() + " programmers.");
        System.out.println("This month, n3 programmers have been worked n4 days, and n5 days left worked.");
        System.out.println("====================================================================================");
        System.out.println("");
        System.out.println("Project teams details:");
        System.out.println("----------------------------------");

        for (NewProject project : list2) {
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
                        " from " + dateFormat.format(p.getStartDate()) + " to " +
                        dateFormat.format(list2.get(i).getEndDate()));
                // Still needs n3, n4, n5, n10, n11 and n12
            }
        }
    }

    // Function that prints the list of projects (list2)
    public ArrayList printProject(ArrayList<ActiveProgrammers> list1, ArrayList<NewProject> list2) {

        for (NewProject project: list2) {
            System.out.println("ID: " + project.getId() + " - The project started in " + dateFormat.format(project.getStartDate()) + " and is planned to end in " + dateFormat.format(project.getEndDate()) + ".");
            System.out.println("");
            System.out.println("The members of the project are: ");
            for (int i = 0; i <project.getMemberID().size(); i++) {
                ActiveProgrammers p = new ActiveProgrammers();
                int search = project.getMemberID().get(i);
                for (ActiveProgrammers person : list1) {
                    if (person.getId() == search) {
                        p = person;
                    }
                }
                System.out.println("ID: " + project.getMemberID().get(i) + " - " + p.getFirstName() + " " + p.getLastName() +". Activity: " + project.getMemberActivity().get(i));
            }
            System.out.println("---------------------------------");
        }
        return list2;
    }

//    // Function that allows the user to edit each project in the list of projects (list2)
////    public void editProject(ArrayList<ActiveProgrammers> list1, ArrayList<NewProject> list2) throws ParseException {
////        Scanner scanner = new Scanner(System.in);
////        Main menu = new Main();
////        ActiveProgrammers programmer = new ActiveProgrammers();
////
////        // variable to choose the ID of the project to be edited
////        int choice = scanner.nextInt();
////        menu.submenuEditProject();
////
////        // variable to choose the field of the project to be edited
////        int field = Main.scanner.nextInt();
////        switch (field) {
////            case 1:
////                System.out.println("Insert the start date of the project (format dd-MM-yyy: ");
////                // variable with the text introduced by the user
////                String date = Main.scanner.next();
////                Date start = dateFormat.parse(date);
////                for (NewProject project : list2) {
////                    if (choice == project.getId()) {
////                        project.setStartDate(start);
////                    }
////                }
////                break;
////            case 2:
////                System.out.println("Insert the planned end date for the project (format dd-MM-yyy: ");
////                // variable with the text introduced by the user
////                String end = Main.scanner.next();
////                Date endDate = dateFormat.parse(end);
////                for (NewProject project : list2) {
////                    if (choice == project.getId()) {
////                        project.setStartDate(endDate);
////                    }
////                }
////                break;
////            case 3:
////                System.out.println("The programmers included in this project are: ");
////                System.out.println("");
////                for (NewProject project: list2) {
////                    for (int i = 1; i < list2.size(); i++) {
////                        int search = project.getId().get(i);
////                        if(project.getId() == search) {
////                            for (ActiveProgrammers programmers: list1) {
////                                System.out.println("ID " + );
////                            }
////                        }
////                    }
////                }
//////                    System.out.println("ID: " + project.getId() + " - The project started in " + dateFormat.format(project.getStartDate()) + " and is planned to end in " + dateFormat.format(project.getEndDate()) + ".");
//////                    System.out.println("");
//////                    System.out.println("The members of the project are: ");
//////                    for (int i = 0; i <project.getMemberID().size(); i++) {
//////                        ActiveProgrammers p = new ActiveProgrammers();
//////                        int search = project.getMemberID().get(i);
//////                        for (ActiveProgrammers person : list1) {
//////                            if (person.getId() == search) {
//////                                p = person;
//////                            }
//////                        }
//////                        System.out.println("ID: " + project.getMemberID().get(i) + " - " + p.getFirstName() + " " + p.getLastName() +". Activity: " + project.getMemberActivity().get(i));
//////                    }
//////                    System.out.println("---------------------------------");
//////                }
////                System.out.println("ID: " + );
////                System.out.println("Insert the ID of the person you want to exchange for ");
////                for(ActiveProgrammers programmer: list1) {
////                    if(programmer.isActive() == false) {
////                        System.out.println("ID: " + programmer.getId() + " - " + programmer.getFirstName() + " " + programmer.getLastName() + ", started working in " + dateFormat.format(programmer.getStartDate()) + " and receives " + programmer.getSalaryHour() + "€ per day.");
////                    }
////                }
////        }
////    }

    // Function to add a new Project to list of projects (list2)
//    public void addProject(ArrayList<ActiveProgrammers> list1, ArrayList<NewProject> list2) throws ParseException {
//        ActiveProgrammers person = new ActiveProgrammers();
//        ArrayList<Integer> memberId = new ArrayList<>();
//        ArrayList<String> memberActivity = new ArrayList<>();
//        int number = list2.size() + 1;
//        System.out.println("Insert the start date of the project");
//        String start = Main.scanner.next();
//        Date date = dateFormat.parse(start);
//        System.out.println("Insert the planned end date for the project");
//        String end = Main.scanner.next();
//        Date eDate = dateFormat.parse(end);
//        int n = 0;
//        for (ActiveProgrammers programmer: list1) {
//            if(programmer.active == false) {
//                person = programmer;
//                System.out.println("ID " + programmer.getId() + " - " + programmer.getFirstName() + " " + programmer.getLastName() + ". Receives " + programmer.getSalaryDay() + "€ per day.");
//                n++;
//            }
//        }
//
//        System.out.println("Do you want to insert some programmer to your new project? (Y/N");
//        String yn = Main.scanner.next();
//            if(yn.equals("Y")) {
//                if(n > 2) {
//                    System.out.println("Which ones? You have to choose at least two programmers!");
//                } else if(n == 2) {
//
//                }
//            } else {
//                return;
//            }
//
//            NewProject project = new NewProject(number, start, end, members, activities);
//        list1.add(member);
//    }
}
