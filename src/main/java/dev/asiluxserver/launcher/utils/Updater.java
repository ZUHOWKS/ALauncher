package dev.asiluxserver.launcher.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Updater {

    public String version = "1.0.1-beta";

    private final String address;
    private final Connection connection;
    private final Document html;

    public Updater(String address) throws IOException {
        this.address = address;
        this.connection = Jsoup.connect(address);
        this.html = connection.get();
    }

    private Element getElementByName(String name) {
        for (Element tag : html.select("meta")) {
            if (tag.attr("property").equals(name)) {
                return tag;
            }
        }
        return null;
    }

    public String getLatestVersion() {
        return "";
    }

    public String getWhatsNew() {
        return "";
    }

    /**
    * Is v2 (strictly) superior to v1?
    * */
    public boolean isSuperiorVersion(String v1, String v2) {
        return false;
    }
}
