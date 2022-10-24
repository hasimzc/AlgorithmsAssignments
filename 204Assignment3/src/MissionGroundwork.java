import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;

public class MissionGroundwork {

    /**
     * Given a list of Project objects, prints the schedule of each of them.
     * Uses getEarliestSchedule() and printSchedule() methods of the current project to print its schedule.
     * @param projectList a list of Project objects
     */
    public void printSchedule(List<Project> projectList) {
        for (int i=0 ;i< projectList.size();i++){
            projectList.get(i).printSchedule(projectList.get(i).getEarliestSchedule());
        }
        System.out.println(projectList.get(0).getProjectDuration());
        // TODO: YOUR CODE HERE
    }

    /**
     * TODO: Parse the input XML file and return a list of Project objects
     *
     * @param filename the input XML file
     * @return a list of Project objects
     */
    public List<Project> readXML(String filename) {
        List<Project> projectList = new ArrayList<>();
        // TODO: YOUR CODE HERE
        DocumentBuilderFactory DBFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder DBuilder= DBFactory.newDocumentBuilder();
            try {
                Document document = DBuilder.parse(filename);
                NodeList projects = document.getElementsByTagName("Project");
                for (int i=0;i<projects.getLength();i++) {
                    Node p = projects.item(i);
                    List<Task> taskList = new ArrayList<>();
                    if (p.getNodeType()==Node.ELEMENT_NODE){
                        Element project = (Element) p;
                        String name = project.getElementsByTagName("Name").item(0).getTextContent();
                        NodeList tasks = project.getElementsByTagName("Task");
                        for (int j =0;j<tasks.getLength();j++){
                            Node t = tasks.item(j);
                            if (t.getNodeType()==Node.ELEMENT_NODE){
                                Element task = (Element) t;
                                int taskID = Integer.parseInt(task.getElementsByTagName("TaskID").item(0).getTextContent());
                                String description = task.getElementsByTagName("Description").item(0).getTextContent();
                                int duration = Integer.parseInt(task.getElementsByTagName("Duration").item(0).getTextContent());
                                NodeList dependenciesNode = task.getElementsByTagName("DependsOnTaskID");
                                List<Integer> dependencies = new ArrayList<>();
                                for (int k=0;k<dependenciesNode.getLength();k++){
                                    Node d = dependenciesNode.item(k);
                                    if (d.getNodeType()==Node.ELEMENT_NODE) {
                                        Element dependencie = (Element) d;
                                        dependencies.add(Integer.parseInt(dependenciesNode.item(k).getTextContent()));
                                    }
                                }
                                Task taskClass = new Task(taskID,description,duration,dependencies);
                                taskList.add(taskClass);
                             }
                        }
                        Project project1 = new Project(name,taskList);
                        projectList.add(project1);
                    }
                }
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return projectList;
    }


}
