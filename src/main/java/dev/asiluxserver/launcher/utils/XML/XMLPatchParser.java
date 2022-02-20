package dev.asiluxserver.launcher.utils.XML;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;

public class XMLPatchParser {

    private String url = "";
    private String version;

    public XMLPatchParser(String url) {
        this.url = url;
    }

    public void readEvent() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new URL("https://zuhowks.github.io/patch.xml").openStream());
            document.getDocumentElement().normalize();
            NodeList nList = document.getElementsByTagName("version");

            this.version = ((Element) nList.item(0)).getAttribute("id").toLowerCase(Locale.ROOT);


        } catch(IOException | ParserConfigurationException | SAXException e) {
            System.out.println(e);
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public String getVersion() {
        return version;
    }
}
