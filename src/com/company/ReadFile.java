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
import java.util.ArrayList;

public class ReadFile {

    public ArrayList readFile(ArrayList<ActiveProgrammers> list1, ArrayList<NewProject> list2 ) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            File file = new File(".\\src\\information.xml");
            if (!file.exists()) {
                System.out.println("No exists");
            }

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList programmerList = doc.getElementsByTagName("programmer");
            for (int i = 0; i < programmerList.getLength(); i++) {
                Node p = programmerList.item(i);
                if (p.getNodeType() == Node.ELEMENT_NODE) {
                    Element person = (Element) p;
                    int id = Integer.parseInt(person.getElementsByTagName("id").item(0).getTextContent());
                    String firstName = person.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = person.getElementsByTagName("lastName").item(0).getTextContent();
                    String startDate = person.getElementsByTagName("startDate").item(0).getTextContent();
                    int salaryDay = Integer.parseInt(person.getElementsByTagName("salaryDay").item(0).getTextContent());
                    boolean active = Boolean.parseBoolean(person.getElementsByTagName("active").item(0).getTextContent());

                    ActiveProgrammers member = new ActiveProgrammers(id, firstName, lastName, startDate, salaryDay, active);
                    list1.add(member);
                }
            }

            NodeList projectsList = doc.getElementsByTagName("project");
            for (int j = 0; j < projectsList.getLength(); j++) {
                Node pr = projectsList.item(j);
                if (pr.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) pr;
                    int count = element.getElementsByTagName("memberId").getLength();
                    int Id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    String start = element.getElementsByTagName("startDate").item(0).getTextContent();
                    String end = element.getElementsByTagName("endDate").item(0).getTextContent();
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
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}