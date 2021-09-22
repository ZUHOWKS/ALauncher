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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

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
        GridPane topPanel = new GridPane();
        GridPane topPanelContour = new GridPane();
        GridPane bottomPanel = new GridPane();

        AtomicBoolean connectWithMojang = new AtomicBoolean(false);

        loginPanel.setMaxWidth(400);
        loginPanel.setMinWidth(400);
        loginPanel.setMaxHeight(580);
        loginPanel.setMinHeight(580);
        GridPane.setHgrow(loginPanel, Priority.ALWAYS);
        GridPane.setVgrow(loginPanel, Priority.ALWAYS);
        GridPane.setHalignment(loginPanel, HPos.CENTER);
        GridPane.setValignment(loginPanel, VPos.CENTER);
        loginPanel.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-opacity: 100%;");

        RowConstraints bottomConstraints = new RowConstraints();
        bottomConstraints.setValignment(VPos.BOTTOM);
        bottomConstraints.setMaxHeight(55);
        loginPanel.getRowConstraints().addAll(new RowConstraints(), bottomConstraints);
        loginPanel.add(mainPanel, 0 , 0);
        loginPanel.add(bottomPanel, 0 , 1);
        


        GridPane.setHgrow(mainPanel, Priority.ALWAYS);
        GridPane.setVgrow(mainPanel, Priority.ALWAYS);

        GridPane.setHgrow(bottomPanel, Priority.ALWAYS);
        GridPane.setVgrow(bottomPanel, Priority.ALWAYS);

        RowConstraints topConstraints = new RowConstraints();
        topConstraints.setValignment(VPos.TOP);
        topConstraints.setMaxHeight(33);
        mainPanel.getRowConstraints().addAll(new RowConstraints(), topConstraints);

        mainPanel.add(topPanelContour, 0, 1);
        mainPanel.add(topPanel, 0, 1);



        /* Top Panel intérieur qui encadre le text se connecter... */
        topPanel.setMaxHeight(42);
        topPanel.setMinHeight(42);
        topPanel.setMaxWidth(272);
        topPanel.setMinWidth(272);
        GridPane.setHgrow(topPanel, Priority.ALWAYS);
        GridPane.setVgrow(topPanel, Priority.ALWAYS);
        setAlignment(topPanel);
        topPanel.setTranslateY(23d);

        /* Contour du Top Panel intérieur qui encadre le text se connecter... */
        topPanelContour.setMaxHeight(48);
        topPanelContour.setMinHeight(48);
        topPanelContour.setMaxWidth(278);
        topPanelContour.setMinWidth(278);
        GridPane.setHgrow(topPanelContour, Priority.ALWAYS);
        GridPane.setVgrow(topPanelContour, Priority.ALWAYS);
        setAlignment(topPanelContour);
        topPanelContour.setTranslateY(23d);




        /* Style de chaque Panel */
        mainPanel.setStyle("-fx-background-color: rgba(79, 167, 79, 0.85);");
        bottomPanel.setStyle("-fx-background-color: rgba(24,24,24,0.65);");
        topPanel.setStyle("-fx-background-color: rgb(79, 167, 79);");
        topPanelContour.setStyle("-fx-background-color: #ffffff;");

        /* Label du Bottom Panel */
        Label noAccount = new Label("Vous n'avez pas encore de compte ?");
        Label registerHere = new Label("S'inscrire");

        setGrow(noAccount);
        setAlignment(noAccount, HPos.CENTER, VPos.TOP);
        noAccount.setTranslateY(6);
        noAccount.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14px;");

        setGrow(registerHere);
        setAlignment(registerHere, HPos.CENTER, VPos.BOTTOM);
        registerHere.setStyle("-fx-font-size: 14px;");
        registerHere.setTranslateY(-6);
        registerHere.setTextFill(Color.rgb(100, 175, 100));
        registerHere.setUnderline(true);
        registerHere.setOnMouseEntered(e-> {

            this.layout.setCursor(Cursor.HAND);
            registerHere.setTextFill(Color.rgb(245, 255, 245));

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

        Label connectionLabel = new Label("CONNECTEZ-VOUS");
        setGrow(connectionLabel);
        setAlignment(connectionLabel, HPos.CENTER ,VPos.CENTER);
        connectionLabel.setStyle("-fx-font-size: 24;");
        connectionLabel.setTextFill(Color.rgb(255,255,255));

        topPanel.getChildren().addAll(connectionLabel);

        bottomPanel.getChildren().addAll(noAccount, registerHere);
        this.layout.getChildren().add(loginPanel);

    }

    private void openUrl(String str) {

        try {
            Desktop.getDesktop().browse(new URI(str));

        } catch (IOException | URISyntaxException e) {
            Main.Logger.warn(e.getMessage());
        }
    }
}
