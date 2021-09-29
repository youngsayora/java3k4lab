package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public  class XMLCreator {
    public static void createXML(ArrayList<Student> list) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        Document doc = factory.newDocumentBuilder().newDocument();

        Element root = doc.createElement("root");
        root.setAttribute("root", "http://www.javacore.ru/schemas/");
        doc.appendChild(root);
        Element[] elements = new Element[list.size()];
        for(int i = 0; i< list.size(); i++){
            elements[i] = doc.createElement("Student" );
            elements[i].setAttribute("lastName", list.get(i).lastName);
            elements[i].setAttribute("averageMark", list.get(i).averageMark.toString());
            elements[i].setAttribute("age", list.get(i).age.toString());
            root.appendChild(elements[i]);
        }
        File file = new File("students.xml");

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(doc), new StreamResult(file));

    }
}
