package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ManageFile {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // Function that reads the tags in xml file and add the information to lists: one for the programmers (list1) and the other to the projects (list2)
    public ArrayList readFile(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2, ArrayList<LocalDate> dateToday) {

        // Verifies if the file exists and if it is true, the file is read after try statement
        // If the file does not exist, then the file is created
        try {
            File file = new File(".\\src\\database.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            if (!file.exists()) {
                createFile(list1, list2, dateToday);
            }

            // With parse, file is converted so the cycle "for" can read the context with .getTextContent()
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);


            // To read date tag
            NodeList date1 = doc.getElementsByTagName("date");
            Element date2 = (Element) date1.item(0);
            String date3 = date2.getTextContent();
            LocalDate date4 = LocalDate.parse(date3);
            dateToday.add(date4);
            System.out.println("Date of the system: " + formatter.format(date4));

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
                    int daysWorked = Integer.parseInt(person.getElementsByTagName("daysWorked").item(0).getTextContent());
                    double salaryDay = Double.parseDouble(person.getElementsByTagName("salaryDay").item(0).getTextContent());
                    boolean active = Boolean.parseBoolean(person.getElementsByTagName("active").item(0).getTextContent());
                    int payment = Integer.parseInt(person.getElementsByTagName("payment").item(0).getTextContent());

                    // Everything read about programmers is stored in list1
                    ActiveProgrammers member = new ActiveProgrammers(id, firstName, lastName, date, daysWorked, salaryDay, active, payment);
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

                    // Everything read about projects is stored in list2
                    ProjectTeam member = new ProjectTeam(Id, date, endDate, memberId, memberActivity);
                    list2.add(member);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Function that creates predefined lists1 and 2
    public void createFile(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2, ArrayList<LocalDate> dateToday) throws TransformerException {
        // Initialize the ArrayList with the id's of respective programmers and their activities
        ArrayList<Integer> memberId1 = new ArrayList<>();
        ArrayList<String> memberActivity1 = new ArrayList<>();
        ArrayList<Integer> memberId2 = new ArrayList<>();
        ArrayList<String> memberActivity2 = new ArrayList<>();
        dateToday.add(LocalDate.parse("2019-11-27"));

        // Creation of pre-defined programmers for list1
        ActiveProgrammers programmer1 = new ActiveProgrammers(1, "Sara", "Silva", LocalDate.parse("2019-10-12"), 0, 40, true, 100);
        ActiveProgrammers programmer2 = new ActiveProgrammers(2, "Daniel", "Oz", LocalDate.parse("2019-10-12"), 0, 40, true, 100);
        ActiveProgrammers programmer3 = new ActiveProgrammers(3, "Afonso", "Pereira", LocalDate.parse("2019-11-15"), 0, 35, true, 100);
        ActiveProgrammers programmer4 = new ActiveProgrammers(4, "Luís", "Damásio", LocalDate.parse("2019-11-15"), 0, 40, true, 100);
        list1.add(programmer1);
        list1.add(programmer2);
        list1.add(programmer3);
        list1.add(programmer4);

        // Creation of pre-defined projects for list2
        memberId1.add(programmer1.getId());
        memberId1.add(programmer2.getId());
        memberActivity1.add("Front-end");
        memberActivity1.add("Back-end");
        ProjectTeam project1 = new ProjectTeam(1, LocalDate.parse("2019-10-12"), LocalDate.parse("2019-11-30"), memberId1, memberActivity1);
        memberId2.add(programmer3.getId());
        memberId2.add(programmer4.getId());
        memberActivity2.add("Design");
        memberActivity2.add("Testing");
        ProjectTeam project2 = new ProjectTeam(2, LocalDate.parse("2019-11-15"), LocalDate.parse("2019-12-30"), memberId2, memberActivity2);
        list2.add(project1);
        list2.add(project2);

        // Calling that function, the lists1 and 2 are updated if any changes were made or continue to be equal to the predefined ones if no alterations were made
        // The xml file is created
        save(list1, list2, dateToday);

    }

    //Function that creates a xml file with 2 projects, each of them with 2 programmers, and 4 programmers in total (4 active)
    public void save(ArrayList<ActiveProgrammers> list1, ArrayList<ProjectTeam> list2, ArrayList<LocalDate> dateToday) throws TransformerException {
        Report report = new Report();
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Creates the root element
            Element rootElement = doc.createElement("Company");
            doc.appendChild(rootElement);

            // Creates the date element
            Element date = doc.createElement("date");
            date.appendChild(doc.createTextNode(dateToday.get(0).toString()));
            rootElement.appendChild(date);

            // Delete the programmers from xml file to avoid increase of repeated data
            NodeList programmersList = doc.getElementsByTagName("programmer");
            for (int i = 0; i < programmersList.getLength(); i++) {
                Node eachNode = programmersList.item(i);
                eachNode.getParentNode().removeChild(eachNode);
            }

            // Creates the programmers information in the xml file
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
                Element daysWorked = doc.createElement("daysWorked");
                daysWorked.appendChild(doc.createTextNode(Integer.toString(report.daysForProgrammer(list1.get(i).getId(), list2, dateToday))));
                Element salaryDay = doc.createElement("salaryDay");
                salaryDay.appendChild(doc.createTextNode(Double.toString(list1.get(i).getSalaryDay())));
                Element active = doc.createElement("active");
                active.appendChild(doc.createTextNode(Boolean.toString(list1.get(i).getActive())));
                Element payment = doc.createElement("payment");
                payment.appendChild(doc.createTextNode(Integer.toString(list1.get(i).getPayment())));
                programmer.appendChild(id);
                programmer.appendChild(firstName);
                programmer.appendChild(lastName);
                programmer.appendChild(startDate);
                programmer.appendChild(daysWorked);
                programmer.appendChild(salaryDay);
                programmer.appendChild(active);
                programmer.appendChild(payment);
            }

            // Delete the projects from xml file to avoid increase of repeated data
            NodeList projectsList = doc.getElementsByTagName("project");
            for (int i = 0; i < projectsList.getLength(); i++) {
                Node eachNode = projectsList.item(i);
                eachNode.getParentNode().removeChild(eachNode);
            }

            // Creates the projects information in the xml file
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
                // Cycle to create the Arraylists (memberId and activity) in xml file
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
            // To style and indent the xml file
            transformer.setOutputProperty(OutputKeys.METHOD, "xml");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "no");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            DOMSource source = new DOMSource(doc);
            // Path of the file
            StreamResult result = new StreamResult(new File(".\\src\\database.xml"));
            transformer.transform(source, result);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}