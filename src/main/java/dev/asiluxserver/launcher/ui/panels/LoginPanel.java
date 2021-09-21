package dev.asiluxserver.launcher.ui.panels;

import dev.asiluxserver.launcher.Main;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.panel.Panel;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoginPanel extends Panel {

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        GridPane loginPanel = new GridPane();
        GridPane mainPanel = new GridPane();
        GridPane bottomPanel = new GridPane();

        AtomicBoolean connectWithMojang = new AtomicBoolean(false);

        loginPanel.setMaxWidth(400);
        loginPanel.setMinWidth(400);
        loginPanel.setMaxHeight(580);
        loginPanel.setMinHeight(580);
        setGrow(loginPanel);
        setAlignment(loginPanel);
        loginPanel.setOpacity(70);

        RowConstraints bottomConstraints = new RowConstraints();
        bottomConstraints.setValignment(VPos.BOTTOM);
        bottomConstraints.setMaxHeight(55);
        loginPanel.getRowConstraints().addAll(new RowConstraints(), bottomConstraints);
        loginPanel.add(mainPanel, 0 , 0);
        loginPanel.add(bottomPanel, 0 , 0);

        setGrow(mainPanel);
        setGrow(bottomPanel);

        mainPanel.setStyle("-fx-background-color: #181818;");
        bottomPanel.setStyle("-fx-background-color: #181818; -fx-opacity: 50%");
        bottomPanel.setOpacity(50d);
        Label noAccount = new Label("Vous n'avez pas encore de compte ?");
        Label registerHere = new Label("S'inscrire");

        setGrow(noAccount);
        setAlignment(noAccount, HPos.CENTER, VPos.BOTTOM);
        noAccount.setTranslateY(-27);
        noAccount.setStyle("-fx-text-fill: #bcc6e7; -fx-font-size: 14px");

        setGrow(registerHere);
        setAlignment(registerHere, HPos.CENTER, VPos.BOTTOM);
        registerHere.setStyle("-fx-font-size: 14px");
        registerHere.setTextFill(Color.rgb(100, 175, 100));
        registerHere.setUnderline(true);
        registerHere.setTranslateY(-10);
        registerHere.setOnMouseEntered(e-> {

            this.layout.setCursor(Cursor.HAND);
            registerHere.setTextFill(Color.rgb(200, 223, 200));

        });
        registerHere.setOnMouseExited(e-> {

            this.layout.setCursor(Cursor.DEFAULT);
            registerHere.setTextFill(Color.rgb(100, 175, 100));

        });
        registerHere.setOnMouseClicked(e-> {
            if (connectWithMojang.get()) {
                openUrl("https:///www.minecraft.net/fr-fr/");
            }

            else {
                openUrl("https://asilux.w4.cmws.fr/");
            }

        });


        bottomPanel.getChildren().addAll(noAccount, registerHere);
        this.layout.getChildren().add(loginPanel);

        Label connectionLabel = new Label("Se connecter:");
        setGrow(connectionLabel);
        setAlignment(connectionLabel, HPos.CENTER ,VPos.TOP);
        connectionLabel.setStyle("-fx-text-fill: #bcc6e7; -fx-font-size: 17px");

        mainPanel.getChildren().addAll(connectionLabel);

    }

    private void openUrl(String str) {

        try {
            Desktop.getDesktop().browse(new URI(str));

        } catch (IOException | URISyntaxException e) {
            Main.Logger.warn(e.getMessage());
        }
    }
}
