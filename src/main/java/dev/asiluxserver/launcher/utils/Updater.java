package dev.asiluxserver.launcher.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class Updater {

    private final String address;
    private final Connection connection;
    private final Document html;

    public Updater(String address) throws IOException {
        this.address = address;
        this.connection = Jsoup.connect(address);
        this.html = connection.get();
        for (Element element : html.select("meta")) {
            System.out.println(element);
        }
    }

    public String getLatestVersion() {
        return "";
    }

    public String getWhatsNew() {
        return "";
    }
}
