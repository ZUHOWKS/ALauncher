package dev.asiluxserver.launcher;


import dev.asiluxserver.launcher.utils.Updater;
import javafx.application.Application;

import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;


public class Main {

    public static void main(String[] args) throws IOException {
        Updater updater = new Updater("https://warna38.github.io/web/");

        try {
            Class.forName("javafx.application.Application");
            Application.launch(Launcher.class, args);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Erreur:\n" + e.getMessage() + " not found",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE
            );
        }

    }
}
