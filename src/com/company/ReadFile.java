package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReadFile {

    // Function that reads the tags in xml file and add the information to lists: one for the programmers (list1) and the other to the projects (list2)
    public ArrayList readFile(ArrayList<ActiveProgrammers> list1, ArrayList<NewProject> list2 ) {

        // To format Strings with date in day-month-year format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Verifies if the file exists and if it is true, the file is read after try statement
        try {
            File file = new File(".\\src\\information.xml");
            if (!file.exists()) {
                System.out.println("No exists");
            }

            // With parse, file is converted so the cicle for can read the context with .getTextContent()
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            // To read programmers' information and store in list1
            NodeList programmerList = doc.getElementsByTagName("programmer");
            for (int i = 0; i < programmerList.getLength(); i++) {
                Node p = programmerList.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element person = (Element) p;
                    int id = Integer.parseInt(person.getElementsByTagName("id").item(0).getTextContent());
                    String firstName = person.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = person.getElementsByTagName("lastName").item(0).getTextContent();
                    Date startDate = dateFormat.parse(person.getElementsByTagName("startDate").item(0).getTextContent());
                    int salaryDay = Integer.parseInt(person.getElementsByTagName("salaryDay").item(0).getTextContent());
                    boolean active = Boolean.parseBoolean(person.getElementsByTagName("active").item(0).getTextContent());

                    ActiveProgrammers member = new ActiveProgrammers(id, firstName, lastName, startDate, salaryDay, active);
                    list1.add(member);
                }
            }

            // To read projects' information and store in list2
            NodeList projectsList = doc.getElementsByTagName("project");
            for (int j = 0; j < projectsList.getLength(); j++) {
                Node pr = projectsList.item(j);
                if (pr.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) pr;
                    int count = element.getElementsByTagName("memberId").getLength();
                    int Id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    Date start = dateFormat.parse(element.getElementsByTagName("startDate").item(0).getTextContent());
                    Date end = dateFormat.parse(element.getElementsByTagName("endDate").item(0).getTextContent());
                    ArrayList<Integer> memberId = new ArrayList<>();
                    ArrayList<String> memberActivity = new ArrayList<>();
                    for (int i = 0; i < count ; i++) {
                        int mID = Integer.parseInt(element.getElementsByTagName("memberId").item(i).getTextContent());
                        memberId.add(mID);
                        String activity = element.getElementsByTagName("activity").item(i).getTextContent();
                        memberActivity.add(activity);
                    }

                    NewProject member = new NewProject(Id, start, end, memberId, memberActivity);
                    list2.add(member);
                }
            }
        } catch (ParserConfigurationException | ParseException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}