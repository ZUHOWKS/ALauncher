package dev.asiluxserver.launcher.ui.panels;

import dev.asiluxserver.launcher.Main;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.panel.Panel;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoginPanel extends Panel {

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        GridPane loginPanel = new GridPane();
        GridPane mainPanel = new GridPane();
        GridPane middlePannel = new GridPane();
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
        RowConstraints middleConstraints = new RowConstraints();
        topConstraints.setValignment(VPos.TOP);
        middleConstraints.setValignment(VPos.TOP);
        mainPanel.getRowConstraints().addAll(new RowConstraints(), topConstraints);
        mainPanel.add(topPanelContour, 0, 0);
        mainPanel.add(middlePannel, 0,1);

        topPanelContour.getRowConstraints().addAll(new RowConstraints(), middleConstraints);
        topPanelContour.add(topPanel, 0, 0);

        /* Top Panel */
        topPanel.setMaxHeight(42);
        topPanel.setMinHeight(42);
        topPanel.setMaxWidth(272);
        topPanel.setMinWidth(272);
        GridPane.setHgrow(topPanel, Priority.ALWAYS);
        GridPane.setVgrow(topPanel, Priority.ALWAYS);
        setAlignment(topPanel, HPos.CENTER, VPos.CENTER);

        /* Contour du Top Panel */
        topPanelContour.setMaxHeight(48);
        topPanelContour.setMinHeight(48);
        topPanelContour.setMaxWidth(278);
        topPanelContour.setMinWidth(278);
        GridPane.setHgrow(topPanelContour, Priority.ALWAYS);
        GridPane.setVgrow(topPanelContour, Priority.ALWAYS);
        setAlignment(topPanelContour, HPos.CENTER, VPos.TOP);
        topPanelContour.setTranslateY(23d);

        /* Middle Panel */
        middlePannel.setMaxHeight(350);
        middlePannel.setMinHeight(350);
        middlePannel.setMaxWidth(280);
        middlePannel.setMinWidth(280);
        GridPane.setHgrow(middlePannel, Priority.ALWAYS);
        GridPane.setVgrow(middlePannel, Priority.ALWAYS);
        setAlignment(middlePannel, HPos.CENTER, VPos.TOP);




        /*
        * STYLE DES PANEL
        */

        /* Style Main Panel */
        mainPanel.setStyle("-fx-background-color: rgba(143, 168, 95, 0.75);");

        /* Style Bottom Panel*/
        bottomPanel.setStyle("-fx-background-color: rgba(24,24,24,0.65);");

        /* Style Top Panel */
        topPanel.setStyle("-fx-background-color: rgba(37, 37, 37, 0.8);");

        /* Style Contour du Top Panel*/
        topPanelContour.setStyle("-fx-background-color: rgba(143, 168, 95, 0.5);");
        topPanelContour.setEffect(new GaussianBlur(5));
        topPanelContour.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(37, 37, 37, 0.17), 31, 0, 0, 0));

        /* Style Middle Panel */
        middlePannel.setEffect(new Bloom(18));
        middlePannel.setEffect(new BoxBlur(10,10,5));
        middlePannel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.15), 17, 0, 0, 0));


        /*
         *  ELEMENT DANS BOTTOM PANEL
         */

        /* Text sans action */
        Label noAccount = new Label("Vous n'avez pas encore de compte ?");
        setGrow(noAccount);
        setAlignment(noAccount, HPos.CENTER, VPos.TOP);
        noAccount.setTranslateY(6);
        noAccount.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 14px;");

        /* Text pour S'inscrire avec action */
        Label registerHere = new Label("S'inscrire");
        setGrow(registerHere);
        setAlignment(registerHere, HPos.CENTER, VPos.BOTTOM);
        registerHere.setStyle("-fx-font-size: 14px;");
        registerHere.setTranslateY(-6);
        registerHere.setTextFill(Color.rgb(143, 168, 95));
        registerHere.setUnderline(true);
        registerHere.setOnMouseEntered(e-> {

            this.layout.setCursor(Cursor.HAND);
            registerHere.setTextFill(Color.rgb(245, 255, 245));

        });
        registerHere.setOnMouseExited(e-> {

            this.layout.setCursor(Cursor.DEFAULT);
            registerHere.setTextFill(Color.rgb(143, 168, 95));

        });
        registerHere.setOnMouseClicked(e-> {
            if (connectWithMojang.get()) {
                openUrl("https:///www.minecraft.net/fr-fr/");
            }

            else {
                openUrl("https://asilux.w4.cmws.fr/");
            }

        });


        /*
        * ELEMENT DANS TOP PANEL CONTOUR -> TOP PANEL
        */

        /* Titre Connectez vous */
        Label connectionLabel = new Label("CONNECTEZ-VOUS");
        setGrow(connectionLabel);
        connectionLabel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 6, 0, 0, 0));
        setAlignment(connectionLabel, HPos.CENTER ,VPos.CENTER);
        connectionLabel.setStyle("-fx-font-size: 26;");
        connectionLabel.setTextFill(Color.rgb(255,255,255));

        /*
        * ELEMENT DANS MIDDLE PANEL
        */


        /* Text Nom d'utilisateur */
        Label usernameLabel = new Label("Nom d'utilisateur");
        setGrow(usernameLabel);
        setAlignment(usernameLabel, HPos.LEFT ,VPos.TOP);
        usernameLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 17));
        usernameLabel.setTextFill(Color.rgb(255,255,255));
        usernameLabel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.2), 4, 0, 0, 0));
        usernameLabel.setTranslateX(7d);
        usernameLabel.setTranslateY(17d);

        /* Text Field Nom d'utilisateur + Separator */
        TextField usernameField = new TextField();
        setGrow(usernameField);
        setAlignment(usernameField, HPos.LEFT ,VPos.TOP);
        usernameField.setStyle("-fx-background-color: rgba(37, 37, 37, 0.8); -fx-font-size: 16; -fx-text-fill: rgba(255,255,255,1)");
        usernameField.setMaxHeight(38);
        usernameField.setMinHeight(38);
        usernameField.setMaxWidth(268);
        usernameField.setMinWidth(268);
        usernameField.setTranslateX(6d);
        usernameField.setTranslateY(45d);

        Separator usernameSeparator = new Separator();
        setGrow(usernameSeparator);
        setAlignment(usernameSeparator, HPos.LEFT ,VPos.TOP);
        usernameSeparator.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 4, 0, 0, 0));
        usernameSeparator.setMaxHeight(38);
        usernameSeparator.setMinHeight(38);
        usernameSeparator.setMaxWidth(268);
        usernameSeparator.setMinWidth(268);
        usernameSeparator.setTranslateX(6d);
        usernameSeparator.setTranslateY(64d);

        /* Text Mot de passe */
        Label passwordLabel = new Label("Mots de passes");
        setGrow(passwordLabel);
        setAlignment(passwordLabel, HPos.LEFT ,VPos.TOP);
        passwordLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 17));
        passwordLabel.setTextFill(Color.rgb(255,255,255));
        passwordLabel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.2), 4, 0, 0, 0));
        passwordLabel.setTranslateX(7d);
        passwordLabel.setTranslateY(124d);

        /* Text Field Nom d'utilisateur + Separator */
        PasswordField passwordField = new PasswordField();
        setGrow(passwordField);
        setAlignment(passwordField, HPos.LEFT ,VPos.TOP);
        passwordField.setStyle("-fx-background-color: rgba(37, 37, 37, 0.8); -fx-font-size: 16; -fx-text-fill: rgba(255,255,255,1)");
        passwordField.setMaxHeight(38);
        passwordField.setMinHeight(38);
        passwordField.setMaxWidth(268);
        passwordField.setMinWidth(268);
        passwordField.setTranslateX(6d);
        passwordField.setTranslateY(152d);

        Separator passwordSeparator = new Separator();
        setGrow(passwordSeparator);
        setAlignment(passwordSeparator, HPos.LEFT ,VPos.TOP);
        passwordSeparator.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 4, 0, 0, 0));
        passwordSeparator.setMaxHeight(38);
        passwordSeparator.setMinHeight(38);
        passwordSeparator.setMaxWidth(268);
        passwordSeparator.setMinWidth(268);
        passwordSeparator.setTranslateX(6d);
        passwordSeparator.setTranslateY(171d);

        /* Text Oublie de Mot de passe avec action */
        Label forgotenpasswordLabel = new Label("Mot de passe oublié ?");
        setGrow(forgotenpasswordLabel);
        setAlignment(forgotenpasswordLabel, HPos.LEFT ,VPos.TOP);
        forgotenpasswordLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        forgotenpasswordLabel.setTextFill(Color.rgb(225,225,225));
        forgotenpasswordLabel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(210, 210, 210, 0.2), 4, 0, 0, 0));
        forgotenpasswordLabel.setTranslateX(6d);
        forgotenpasswordLabel.setTranslateY(193d);
        registerHere.setOnMouseEntered(e-> {

            this.layout.setCursor(Cursor.HAND);
            registerHere.setTextFill(Color.rgb(245, 255, 245));

        });
        registerHere.setOnMouseExited(e-> {

            this.layout.setCursor(Cursor.DEFAULT);
            registerHere.setTextFill(Color.rgb(143, 168, 95));

        });
        registerHere.setOnMouseClicked(e-> {
            if (connectWithMojang.get()) {
                openUrl("https:///www.minecraft.net/fr-fr/password/forgot");
            }

            else {
                openUrl("https://asilux.w4.cmws.fr/#");
            }

        });

        /* Bouton pour se connecter*/

        Button connectionButton = new Button("SE CONNECTER");
        setGrow(connectionButton);
        setAlignment(connectionButton, HPos.CENTER ,VPos.BOTTOM);
        connectionButton.setStyle("-fx-background-color: #91B848FF; -fx-font-size: 21; -fx-border-radius: 2px; -fx-text-fill: rgba(255,255,255,1)");
        connectionButton.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 21));
        connectionButton.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(37, 37, 37, 0.2), 3, 0.3, 0, 0));
        connectionButton.setMaxHeight(38);
        connectionButton.setMinHeight(38);
        connectionButton.setMaxWidth(268);
        connectionButton.setMinWidth(268);
        connectionButton.setTranslateY(-65d);
        connectionButton.setOnMouseClicked(e-> {
            if (connectWithMojang.get()) {
                //TODO:MOJANG LOGIN
            }

            else {
                //TODO:MINEWEBLOGIN
            }

        });

        Separator connectionChoseS1 = new Separator();
        setGrow(connectionChoseS1);
        setAlignment(connectionChoseS1, HPos.CENTER ,VPos.BOTTOM);
        connectionChoseS1.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 4, 0, 0, 0));
        connectionChoseS1.setMaxHeight(38);
        connectionChoseS1.setMinHeight(38);
        connectionChoseS1.setMaxWidth(268);
        connectionChoseS1.setMinWidth(268);
        connectionChoseS1.setTranslateY(-45d);

        Separator connectionChoseS2 = new Separator();
        setGrow(connectionChoseS2);
        setAlignment(connectionChoseS2, HPos.LEFT ,VPos.BOTTOM);
        connectionChoseS2.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 4, 0, 0, 0));
        connectionChoseS2.setMaxHeight(38);
        connectionChoseS2.setMinHeight(38);
        connectionChoseS2.setMaxWidth(98);
        connectionChoseS2.setMinWidth(98);
        connectionChoseS2.setTranslateY(-30d);

        Separator connectionChoseS3 = new Separator();
        setGrow(connectionChoseS3);
        setAlignment(connectionChoseS3, HPos.RIGHT ,VPos.BOTTOM);
        connectionChoseS3.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 4, 0, 0, 0));
        connectionChoseS3.setMaxHeight(38);
        connectionChoseS3.setMinHeight(38);
        connectionChoseS3.setMaxWidth(98);
        connectionChoseS3.setMinWidth(98);
        connectionChoseS3.setTranslateY(-30d);

        Label connectionwithLabel = new Label("AVEC");
        setGrow(connectionwithLabel);
        setAlignment(connectionwithLabel, HPos.CENTER ,VPos.BOTTOM);
        connectionwithLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 19));
        connectionwithLabel.setTextFill(Color.rgb(225,225,225));
        connectionwithLabel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(210, 210, 210, 0.2), 4, 0, 0, 0));
        connectionwithLabel.setTranslateY(-37d);

        Image mojangLogo = new Image(Objects.requireNonNull(Main.class.getResource("/mojang-icone.png")).toExternalForm());
        ImageView mojangView = new ImageView(mojangLogo);
        mojangView.setFitWidth(20);
        mojangView.setFitHeight(20);
        Button mojangAccountButton = new Button(" Mojang", mojangView);
        setGrow(mojangAccountButton);
        setAlignment(mojangAccountButton, HPos.LEFT ,VPos.BOTTOM);
        mojangAccountButton.setStyle("-fx-background-color: #D92626FF; -fx-font-size: 18; -fx-border-radius: 2px; -fx-text-fill: rgba(255,255,255,1)");
        mojangAccountButton.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 18));
        mojangAccountButton.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(37, 37, 37, 0.1), 3, 0.3, 0, 0));
        mojangAccountButton.setMaxHeight(38);
        mojangAccountButton.setMinHeight(38);
        mojangAccountButton.setMaxWidth(134);
        mojangAccountButton.setMinWidth(134);
        mojangAccountButton.setTranslateX(-6d);
        mojangAccountButton.setTranslateY(2d);
        mojangAccountButton.setOnMouseClicked(e-> {
            if (connectWithMojang.get()) {
                //TODO:MOJANG LOGIN
            }

            else {
                //TODO:MINEWEBLOGIN
            }

        });



        /*
        * Label Registery
        */

        topPanel.getChildren().addAll(connectionLabel);
        bottomPanel.getChildren().addAll(noAccount, registerHere);
        middlePannel.getChildren().addAll(usernameLabel, usernameField, usernameSeparator, passwordLabel, passwordField, passwordSeparator,forgotenpasswordLabel, connectionButton,
                connectionChoseS1, connectionChoseS2, connectionChoseS3, connectionwithLabel, mojangAccountButton);
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
