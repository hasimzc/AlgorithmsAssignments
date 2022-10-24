import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;

import org.w3c.dom.Document;

public class XMLParser {
    /**
     * TODO: Parse the input XML file and return a dictionary as described in the assignment insturctions
     *
     * @param filename the input XML file
     * @return a dictionary as described in the assignment insturctions
     */
    public static Map<String, Malware> parse(String filename) {
        Map<String,Malware> malwareMap = new HashMap<String, Malware>();
        DocumentBuilderFactory DBFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder DBuilder= DBFactory.newDocumentBuilder();
            try {
                Document document = DBuilder.parse(filename);
                document.getDocumentElement().normalize();
                NodeList rows = document.getElementsByTagName("row");
                for(int i = 0;i<rows.getLength();i++){
                    Node r = rows.item(i);
                    if (r.getNodeType()==Node.ELEMENT_NODE){
                        Element row = (Element) r;
                        String title = row.getElementsByTagName("title").item(0).getTextContent();
                        String hash = row.getElementsByTagName("hash").item(0).getTextContent();
                        int level = Integer.parseInt(row.getElementsByTagName("level").item(0).getTextContent());
                        Malware malware = new Malware(title,level,hash);
                        malwareMap.put(hash,malware);
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
        // TODO: YOUR CODE HERE
        return malwareMap;
    }
}
