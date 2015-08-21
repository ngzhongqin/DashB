package com.dashb.utils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParserExample {

static class Employee {
    private final int id;
    private final String name;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}



static class EmployeeXMLParser {

    private final Document document;

    public EmployeeXMLParser(String fileName) {
        document = loadXml(fileName);
    }

    Employee findEmployee(int index) {
        NodeList listOfEmployee = document.getElementsByTagName("Employee");
        Node employeeNode = listOfEmployee.item(index - 1);

        if (employeeNode != null && employeeNode.getNodeType() == Node.ELEMENT_NODE) {
            String name = getElementValue((Element) employeeNode, "Name");
            String id = getElementValue((Element) employeeNode, "ID");
            return new Employee(Integer.parseInt(id), name);
        }

        return null;
    }

    private String getElementValue(Element parentElement, String elementName) {
        NodeList nodeList = parentElement.getElementsByTagName(elementName);
        Element element = (Element) nodeList.item(0);
        NodeList childNodes = element.getChildNodes();
        return (((Node) childNodes.item(0)).getNodeValue().trim()).toString();
    }

    private Document loadXml(String fileName) {
        DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(fileName));
            doc.getDocumentElement().normalize();
            return doc;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException("Error parsing the XML", e);
        } catch (SAXException e) {
            throw new IllegalStateException("Error parsing the XML", e);
        } catch (IOException e) {
            throw new IllegalStateException("Error accessing the XML", e);
        }
    }
};

void start() {
    EmployeeXMLParser employeeParser = new EmployeeXMLParser("test.xml");

    Scanner input = new Scanner(System.in);
    boolean successful = false;
    do {
        System.out.println("Enter the employee number that you're searching for: ");
        try {
            Employee employee = employeeParser.findEmployee(Integer.parseInt(input.next()));
            if (employee == null) {
                printTryAgain();
            } else {
                System.out.println("The employee Number is: " + employee.getId());
                System.out.println("The employee Name is: " + employee.getName());
                successful = true;
            }
        } catch (NumberFormatException nfe) {
            printTryAgain();
        }
    } while (!successful);
}

private void printTryAgain() {
    System.out.println("The employee number you searched for is incorrect or does not yet exist, try again.");
}

public static void main(String args[]) {
    new XMLParserExample().start();
} 

}