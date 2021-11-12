package dev.asiluxserver.launcher;


import dev.asiluxserver.launcher.utils.XML.XMLPatchParser;
import javafx.application.Application;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        //Test lecture du fichier patch.xml | Résultat: Renvoie les éléments attendus.
        new XMLPatchParser("https://zuhowks.github.io/patch.xml");
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
