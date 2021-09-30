package dev.asiluxserver.launcher;

import fr.arinonia.arilibfx.utils.AriLogger;
import javafx.application.Application;
import javafx.scene.text.Font;

import javax.swing.*;

public class Main {

    public static AriLogger Logger;

    public static void main(String[] args){
        Logger = new AriLogger("Asilux");
        try {
            Class.forName("javafx.application.Application");
            Application.launch(FxApplication.class, args);
        }
        catch(ClassNotFoundException e) {
            Logger.warn("Java FX not found :cry:");
            JOptionPane.showMessageDialog(null, "Une erreur dans Java a été détectée.\n" + e.getMessage() + "not found", "Erreur Java", JOptionPane.ERROR_MESSAGE);
        }
    }
}
