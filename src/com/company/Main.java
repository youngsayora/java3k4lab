package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Student> StudentsAnswer = new ArrayList<>();
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, SAXException, IOException {
        ArrayList<Student> Students = GetFile("students.txt");
        XMLCreator.createXML(Students);

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(new File("students.xml"), handler);

        for (Student student : StudentsAnswer)
            System.out.println(student.lastName+" Средний балл:"+ student.averageMark+ " Возраст:" + student.age);

    }


    public static ArrayList<Student> GetFile(String path){
        ArrayList<Student> value = new ArrayList<>();
        File file = new File(path);
        try {

            int count=0;
            Scanner size2scanner = new Scanner(file);
            while(size2scanner.hasNextLine()) {
                size2scanner.nextLine();
                count++;
            }
            size2scanner.close();




            Scanner scanner = new Scanner(file);

            for (int i = 0; i < count; i++) {
                String[] strings = scanner.nextLine().split(" ");
                Student temp = new Student(strings[0],Double.parseDouble(strings[1]),Integer.parseInt(strings[2]));
                value.add(temp);

            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return value;
    }
    private static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("Student")) {
                String lastname = attributes.getValue("lastName");
                String averageMark = attributes.getValue("averageMark");
                String age = attributes.getValue("age");
                StudentsAnswer.add(new Student(lastname, Double.parseDouble(averageMark)  , Integer.parseInt(age)));
            }
        }
    }
}
