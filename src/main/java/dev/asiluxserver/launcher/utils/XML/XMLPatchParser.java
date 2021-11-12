package dev.asiluxserver.launcher.utils.XML;

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
import java.net.URL;
import java.util.Locale;

public class XMLPatchParser {
    public XMLPatchParser(String url) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new URL(url).openStream());
            document.getDocumentElement().normalize();
            System.out.println("Root Element :" + document.getDocumentElement().getAttribute("id"));
            NodeList nList = document.getElementsByTagName("categories");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("");
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    NodeList noteListe = ((Element) nNode).getElementsByTagName("note");
                    System.out.println(eElement.getAttribute("id").toUpperCase(Locale.ROOT));
                    for (int j = 0; j < noteListe.getLength(); j++) {
                        Node noteNode = (Element) noteListe.item(j);
                        System.out.println("   - " + noteNode.getTextContent());
                    }
                }
            }
        }
        catch(IOException | ParserConfigurationException | SAXException e) {
            System.out.println(e);
        }
    }
}
