package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class ManageFile {

    // Function that reads the tags in xml file and add the information to lists: one for the programmers (list1) and the other to the projects (list2)
    public ArrayList readFile(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2) {

        // To format Strings with date in day-month-year format
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Verifies if the file exists and if it is true, the file is read after try statement
        try {
            File file = new File("\\src\\information.xml");
            File newFile = new File(".\\src\\myDatabase.xml");
            Document doc = null;
            if (!file.exists()) {
                save(list1, list2);
            }

            // With parse, file is converted so the cycle "for" can read the context with .getTextContent()
            DocumentBuilder builder = factory.newDocumentBuilder();
            if (!file.exists()) {
                doc = builder.parse(newFile);
            } else {
                doc = builder.parse(file);
            }

            // To read programmers' information and store in list1
            NodeList programmerList = doc.getElementsByTagName("programmer");
            for (int i = 0; i < programmerList.getLength(); i++) {
                Node p = programmerList.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element person = (Element) p;
                    int id = Integer.parseInt(person.getElementsByTagName("id").item(0).getTextContent());
                    String firstName = person.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = person.getElementsByTagName("lastName").item(0).getTextContent();
                    String startDate = person.getElementsByTagName("startDate").item(0).getTextContent();
                    LocalDate date = LocalDate.parse(startDate);
                    double salaryDay = Double.parseDouble(person.getElementsByTagName("salaryDay").item(0).getTextContent());
                    boolean active = Boolean.parseBoolean(person.getElementsByTagName("active").item(0).getTextContent());

                    ActiveProgrammers member = new ActiveProgrammers(id, firstName, lastName, date, salaryDay, active);
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
                    String start = element.getElementsByTagName("startDate").item(0).getTextContent();
                    LocalDate date = LocalDate.parse(start);
                    String end = element.getElementsByTagName("endDate").item(0).getTextContent();
                    LocalDate endDate = LocalDate.parse(end);
                    ArrayList<Integer> memberId = new ArrayList<>();
                    ArrayList<String> memberActivity = new ArrayList<>();
                    for (int i = 0; i < count; i++) {
                        int mID = Integer.parseInt(element.getElementsByTagName("memberId").item(i).getTextContent());
                        memberId.add(mID);
                        String activity = element.getElementsByTagName("activity").item(i).getTextContent();
                        memberActivity.add(activity);
                    }

                    ProjectTeam member = new ProjectTeam(Id, date, endDate, memberId, memberActivity);
                    list2.add(member);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void createFile(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2) {
        // Initialize the ArrayList with the id's of respective programmers and their activities
        ArrayList<Integer> memberId1 = new ArrayList<>();
        ArrayList<String> memberActivity1 = new ArrayList<>();
        ArrayList<Integer> memberId2 = new ArrayList<>();
        ArrayList<String> memberActivity2 = new ArrayList<>();

        // Creation of pre-defined programmers for list3
        ActiveProgrammers programmer1 = new ActiveProgrammers(1, "Sara", "Silva", LocalDate.parse("2019-10-12"), 12, true);
        ActiveProgrammers programmer2 = new ActiveProgrammers(2, "Daniel", "Oz", LocalDate.parse("2019-10-12"), 12, true);
        ActiveProgrammers programmer3 = new ActiveProgrammers(3, "Afonso", "Pereira", LocalDate.parse("2019-11-12"), 15, true);
        ActiveProgrammers programmer4 = new ActiveProgrammers(4, "Luís", "Damásio", LocalDate.parse("2019-11-12"), 20, true);
        list1.add(programmer1);
        list1.add(programmer2);
        list1.add(programmer3);
        list1.add(programmer4);

        // Creation of pre-defined projects for list4
        memberId1.add(programmer1.getId());
        memberId1.add(programmer2.getId());
        memberActivity1.add("Front-end");
        memberActivity1.add("Back-end");
        ProjectTeam project1 = new ProjectTeam(1, LocalDate.parse("2019-10-12"), LocalDate.parse("2019-11-30"), memberId1, memberActivity1);
        memberId2.add(programmer3.getId());
        memberId2.add(programmer4.getId());
        memberActivity2.add("Design");
        memberActivity2.add("Testing");
        ProjectTeam project2 = new ProjectTeam(2, LocalDate.parse("2019-11-12"), LocalDate.parse("2019-11-30"), memberId2, memberActivity2);
        list2.add(project1);
        list2.add(project2);

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Creates the programmers information in the default xml file
            Element rootElement = doc.createElement("Company");
            doc.appendChild(rootElement);
            for (int i = 0; i < list1.size(); i++) {
                Element programmer = doc.createElement("programmer");
                rootElement.appendChild(programmer);
                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(Integer.toString(list1.get(i).getId())));
                Element firstName = doc.createElement("firstName");
                firstName.appendChild(doc.createTextNode(list1.get(i).getFirstName()));
                Element lastName = doc.createElement("lastName");
                lastName.appendChild(doc.createTextNode(list1.get(i).getLastName()));
                Element startDate = doc.createElement("startDate");
                startDate.appendChild(doc.createTextNode((list1.get(i).getStartDate()).toString()));
                Element salaryDay = doc.createElement("salaryDay");
                salaryDay.appendChild(doc.createTextNode(Double.toString(list1.get(i).getSalaryDay())));
                Element active = doc.createElement("active");
                active.appendChild(doc.createTextNode(Boolean.toString(list1.get(i).getActive())));
                programmer.appendChild(id);
                programmer.appendChild(firstName);
                programmer.appendChild(lastName);
                programmer.appendChild(startDate);
                programmer.appendChild(salaryDay);
                programmer.appendChild(active);
            }

            // Creates the projects information in the default xml file
            for (int k = 0; k < list2.size(); k++) {
                int size = list2.get(k).getMemberID().size();
                Element project = doc.createElement("project");
                rootElement.appendChild(project);
                Element Id = doc.createElement("id");
                Id.appendChild(doc.createTextNode(Integer.toString(list2.get(k).getId())));
                project.appendChild(Id);
                Element start = doc.createElement("startDate");
                start.appendChild(doc.createTextNode(list2.get(k).getStartDate().toString()));
                project.appendChild(start);
                Element end = doc.createElement("endDate");
                end.appendChild(doc.createTextNode(list2.get(k).getEndDate().toString()));
                project.appendChild(end);
                // Cycle to create the arraylists (memberId and activity) in xml file
                for (int i = 0; i < size; i++) {
                    Element memberId = doc.createElement("memberId");
                    memberId.appendChild(doc.createTextNode(Integer.toString(list2.get(k).getMemberID().get(i))));
                    Element activity = doc.createElement("activity");
                    activity.appendChild(doc.createTextNode(list2.get(k).getMemberActivity().get(i)));
                    project.appendChild(memberId);
                    project.appendChild(activity);
                }

            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(".\\src\\myDatabase.xml"));
            transformer.transform(source, result);
            System.out.println("File saved!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    //Function that creates a xml file with 2 projects, each of them with 2 programmers, and 6 programmers in total (4 active and 2 inactive)
    public void save(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2) throws TransformerException {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Creates the programmers information in the default xml file
            Element rootElement = doc.createElement("Company");
            doc.appendChild(rootElement);

                for (int i = 0; i < list1.size(); i++) {
                Element programmer = doc.createElement("programmer");
                rootElement.appendChild(programmer);
                Element id = doc.createElement("id");
                id.appendChild(doc.createTextNode(Integer.toString(list1.get(i).getId())));
                Element firstName = doc.createElement("firstName");
                firstName.appendChild(doc.createTextNode(list1.get(i).getFirstName()));
                Element lastName = doc.createElement("lastName");
                lastName.appendChild(doc.createTextNode(list1.get(i).getLastName()));
                Element startDate = doc.createElement("startDate");
                startDate.appendChild(doc.createTextNode((list1.get(i).getStartDate()).toString()));
                Element salaryDay = doc.createElement("salaryDay");
                salaryDay.appendChild(doc.createTextNode(Double.toString(list1.get(i).getSalaryDay())));
                Element active = doc.createElement("active");
                active.appendChild(doc.createTextNode(Boolean.toString(list1.get(i).getActive())));
                programmer.appendChild(id);
                programmer.appendChild(firstName);
                programmer.appendChild(lastName);
                programmer.appendChild(startDate);
                programmer.appendChild(salaryDay);
                programmer.appendChild(active);
            }

            // Creates the projects information in the default xml file
            for (int k = 0; k < list2.size(); k++) {
                int size = list2.get(k).getMemberID().size();
                Element project = doc.createElement("project");
                rootElement.appendChild(project);
                Element Id = doc.createElement("id");
                Id.appendChild(doc.createTextNode(Integer.toString(list2.get(k).getId())));
                project.appendChild(Id);
                Element start = doc.createElement("startDate");
                start.appendChild(doc.createTextNode(list2.get(k).getStartDate().toString()));
                project.appendChild(start);
                Element end = doc.createElement("endDate");
                end.appendChild(doc.createTextNode(list2.get(k).getEndDate().toString()));
                project.appendChild(end);
                // Cycle to create the arraylists (memberId and activity) in xml file
                for (int i = 0; i < size; i++) {
                    Element memberId = doc.createElement("memberId");
                    memberId.appendChild(doc.createTextNode(Integer.toString(list2.get(k).getMemberID().get(i))));
                    Element activity = doc.createElement("activity");
                    activity.appendChild(doc.createTextNode(list2.get(k).getMemberActivity().get(i)));
                    project.appendChild(memberId);
                    project.appendChild(activity);
                }

            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(".\\src\\myDatabase.xml"));
            transformer.transform(source, result);
            System.out.println("File saved!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}


