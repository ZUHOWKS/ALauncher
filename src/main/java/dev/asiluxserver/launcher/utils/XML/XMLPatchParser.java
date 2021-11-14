package dev.asiluxserver.launcher.utils.XML;

import dev.asiluxserver.launcher.utils.patch.PatchMessage;
import dev.asiluxserver.launcher.utils.patch.PatchNote;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.sound.midi.Patch;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

public class XMLPatchParser {

    String url;
    PatchMessage patchMessage;

    public XMLPatchParser(String url) {
        this.url = url;
    }

    public PatchMessage getPatch() {
        return patchMessage;
    }

    public void readEvent() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new URL("https://zuhowks.github.io/patch.xml").openStream());
            document.getDocumentElement().normalize();
            this.patchMessage.setTitle(document.getDocumentElement().getAttribute("id").toString().toUpperCase(Locale.ROOT));
            NodeList nList = document.getElementsByTagName("categories");

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    NodeList noteListe = ((Element) nNode).getElementsByTagName("note");
                    PatchNote patchNote = new PatchNote(eElement.getAttribute("id").toUpperCase(Locale.ROOT));

                    for (int j = 0; j < noteListe.getLength(); j++) {
                        Node noteNode = noteListe.item(j);
                        patchNote.addNote(noteNode.getTextContent());
                    }
                    this.patchMessage.addPatchNote(patchNote);
                }
            }
        }
        catch(IOException | ParserConfigurationException | SAXException e) {
            System.out.println(e);
        }
    }
}
