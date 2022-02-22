package zuhowks.asiluxteam.fr.launcher;


import javafx.application.Application;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        try {
            Class.forName("javafx.application.Application");
            Application.launch(AUpdater.class, args);
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
