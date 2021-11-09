package dev.asiluxserver.launcher.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Updater {

    private final String address;
    private final URL resourceLocator;

    public Updater(String address) throws MalformedURLException {
        this.address = address;
        this.resourceLocator = new URL(this.address);
        try {
            System.out.println(getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLatestVersion() {
        return "";
    }

    public String getWhatsNew() {
        return "";
    }

    private String getData() throws IOException {
        InputStream html = resourceLocator.openStream();
        StringBuilder buffer = new StringBuilder();
        int c = 0;
        while(c != -1) {
            c = html.read();
            buffer.append((char)c);
        }
        return buffer.toString();
    }
}
