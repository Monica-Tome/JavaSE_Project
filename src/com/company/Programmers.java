package com.company;

import java.text.ParseException;
import java.util.ArrayList;

public interface Programmers {

    public void printProgrammers(ArrayList<ActiveProgrammers> list1);
    public void editProgrammer(ArrayList<ActiveProgrammers> list1) throws ParseException;
    public void addProgrammer(ArrayList<ActiveProgrammers> list1) throws ParseException;
    public void deleteProgrammer(ArrayList<ActiveProgrammers> list1);
}
