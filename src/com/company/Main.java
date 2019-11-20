package com.company;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<ActiveProgrammers> list1 = new ArrayList<>();
        ArrayList<NewProject> list2 = new ArrayList<>();
        ReadFile member = new ReadFile();
        member.readFile(list1, list2);
        NewProject project = new NewProject();
        project.printProject(list1, list2);



    }
}
