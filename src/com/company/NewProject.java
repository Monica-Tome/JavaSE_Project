package com.company;

import java.util.ArrayList;

public class NewProject {
    private int id;
    private String startDate;
    private String endDate;
    private ArrayList<Integer> memberID;
    private ArrayList<String> memberActivity;

    public NewProject() {
    }

    public NewProject(int id, String startDate, String endDate, ArrayList<Integer> memberID, ArrayList<String> memberActivity) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.memberID = memberID;
        this.memberActivity = memberActivity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public ArrayList<Integer> getMemberID() {
        return memberID;
    }

    public void setMemberID(ArrayList<Integer> memberID) {
        this.memberID = memberID;
    }

    public ArrayList<String> getMemberActivity() {
        return memberActivity;
    }

    public void setMemberActivity(ArrayList<String> memberActivity) {
        this.memberActivity = memberActivity;
    }

    public void printProject(ArrayList<ActiveProgrammers> list1, ArrayList<NewProject> list2) {
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
                        " from " + p.getStartDate() + " to " +
                        list2.get(i).getEndDate());
                // Still needs n3, n4, n5, n10, n11 and n12
            }
        }
    }
}
