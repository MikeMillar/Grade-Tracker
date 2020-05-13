package Main.Models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.WordUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.lang.model.util.Elements;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Datasource {
    
    private static ObservableList<Classes> classes;
    private static final Datasource instance = new Datasource();
    
    
    
    private Datasource() {
        classes = FXCollections.observableArrayList();
    }
    
    public ObservableList<Classes> getClasses() {
        return classes;
    }
    
    public static Datasource getInstance() {
        return instance;
    }
    
    public void addClass(Classes classes1) {
        classes.add(classes1);
        System.out.println("added class");
        System.out.println(classes.size());
    }
    
    public void removeClass(Classes classes1) {
        classes.remove(classes1);
    }
    
    public String escapeText(String input) {
        return StringEscapeUtils.escapeXml(input);
    }
    
    public boolean save() {
        // TODO - Save under username hashcode (Implement users)
        if (classes.size() == 0) {
            System.out.println("Nothing to save!");
            return true;
        }
        
        String user = "michael_millar";
        int userHash = user.hashCode();
        String filePath = "E:\\Programming Projects\\Java\\Grade-Tracker\\SaveData\\" + userHash + ".xml";
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.newDocument();
            
            Element root = doc.createElement(user);
            doc.appendChild(root);
            
            for (Classes c: classes) {
                Element courseElement = doc.createElement("Course");
                root.appendChild(courseElement);
    
                Element CourseNumber = doc.createElement("CourseNumber");
                CourseNumber.appendChild(doc.createTextNode(escapeText(c.getCourseNumber())));
                courseElement.appendChild(CourseNumber);
                
                Element courseName = doc.createElement("CourseName");
                courseName.appendChild(doc.createTextNode(escapeText(c.getName())));
                courseElement.appendChild(courseName);
                
                Element professor = doc.createElement("Professor");
                professor.appendChild(doc.createTextNode(escapeText(c.getProfessor())));
                courseElement.appendChild(professor);
                
                Element types = doc.createElement("AssignmentTypes");
                types.appendChild(doc.createTextNode(escapeText(c.getTypeString())));
                courseElement.appendChild(types);
                
                for (Assignment a: c.getAssignments()) {
                    Element assignment = doc.createElement("Assignment");
                    courseElement.appendChild(assignment);
                    
                    Element assignmentName = doc.createElement("AssignmentName");
                    assignmentName.appendChild(doc.createTextNode(escapeText(a.getName())));
                    assignment.appendChild(assignmentName);
                    
                    Element aType = doc.createElement("AssignmentType");
                    aType.appendChild(doc.createTextNode(escapeText(a.getType())));
                    assignment.appendChild(aType);
                    
                    Element description = doc.createElement("AssignmentDescription");
                    description.appendChild(doc.createTextNode(escapeText(a.getDescription())));
                    assignment.appendChild(description);
                    
                    Element points = doc.createElement("Points");
                    points.appendChild(doc.createTextNode(escapeText(a.getScoreString())));
                    assignment.appendChild(points);
                }
            }
    
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
    
            System.out.println("File saved!");
            return true;
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean load() {
        // TODO - Load from username hashcode (Implement users)
        
        String user = "michael_millar";
        int userHash = user.hashCode();
        String filePath = "E:\\Programming Projects\\Java\\Grade-Tracker\\SaveData\\" + userHash + ".xml";
        
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(file);
            doc.getDocumentElement().normalize();
    
            NodeList nList = doc.getElementsByTagName("Course");
            if (nList.getLength() == 0) {
                System.out.println("Nothing to load");
                return true;
            }
            
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                Element element = (Element) node;
                String courseNum = element.getElementsByTagName("CourseNumber").item(0).getTextContent();
                String courseName = element.getElementsByTagName("CourseName").item(0).getTextContent();
                String professor = element.getElementsByTagName("Professor").item(0).getTextContent();
                String assignmentTypes = element.getElementsByTagName("AssignmentTypes").item(0).getTextContent();
                Classes course = new Classes(courseNum, courseName, professor, assignmentTypes);
                classes.add(course);
                
                NodeList aList = element.getElementsByTagName("Assignment");
                if (aList.item(0) != null) {
                    for (int j = 0; j < aList.getLength(); j++) {
                        Element a = (Element) aList.item(j);
                        String name = a.getElementsByTagName("AssignmentName").item(0).getTextContent();
                        String type = a.getElementsByTagName("AssignmentType").item(0).getTextContent();
                        String description = a.getElementsByTagName("AssignmentDescription").item(0).getTextContent();
                        String[] scores = a.getElementsByTagName("Points").item(0).getTextContent().split("/");
                        scores[0] = scores[0].replaceAll("\\s", "");
                        scores[1] = scores[1].replaceAll("\\s", "");
                        int earned = Integer.parseInt(scores[0]);
                        int max = Integer.parseInt(scores[1]);
                        Assignment assignment = new Assignment(name, type, description, max, earned);
                        course.addAssignment(assignment);
                    }
                }
            }
            System.out.println("Finished loading courses.");
            return true;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
