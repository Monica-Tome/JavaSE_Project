package com.company;

import java.text.ParseException;
import java.util.ArrayList;

public interface Programmers {

    public ArrayList printProgrammers(ArrayList<ActiveProgrammers> list1);
    public void editProgrammer(ArrayList<ActiveProgrammers> list1) throws ParseException;
}
