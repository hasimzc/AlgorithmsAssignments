import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MissionExploration {

    /**
     * Given a Galaxy object, prints the solar systems within that galaxy.
     * Uses exploreSolarSystems() and printSolarSystems() methods of the Galaxy object.
     *
     * @param galaxy a Galaxy object
     */
    public void printSolarSystems(Galaxy galaxy) {
        galaxy.printSolarSystems(galaxy.exploreSolarSystems());
        // TODO: YOUR CODE HERE
    }

    /**
     * TODO: Parse the input XML file and return a list of Planet objects.
     *
     * @param filename the input XML file
     * @return a list of Planet objects
     */
    public Galaxy readXML(String filename) {
        List<Planet> planetList = new ArrayList<>();
        DocumentBuilderFactory DBFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder DBuilder= DBFactory.newDocumentBuilder();
            try {
                Document document = DBuilder.parse(filename);
                NodeList planets = document.getElementsByTagName("Planet");
                for (int i = 0;i<planets.getLength();i++){
                    Node p = planets.item(i);
                    if (p.getNodeType() == Node.ELEMENT_NODE){
                        Element planet = (Element) p;
                        String ID = planet.getElementsByTagName("ID").item(0).getTextContent();
                        int technologyLevel = Integer.parseInt(planet.getElementsByTagName("TechnologyLevel").item(0).getTextContent());
                        NodeList neighboursNode = planet.getElementsByTagName("PlanetID");
                        List<String> neighbours = new ArrayList<>();
                        for (int j = 0;j<neighboursNode.getLength();j++){
                            Node n = neighboursNode.item(j);
                            if (n.getNodeType()==Node.ELEMENT_NODE){
                                Element neighbour = (Element) n;
                                neighbours.add(neighboursNode.item(j).getTextContent());
                            }
                        }
                        Planet planetClass = new Planet(ID,technologyLevel,neighbours);
                        planetList.add(planetClass);
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
        return new Galaxy(planetList);
    }
}
