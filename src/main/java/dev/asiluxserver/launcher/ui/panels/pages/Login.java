package dev.asiluxserver.launcher.ui.panels.pages;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import dev.asiluxserver.launcher.Launcher;
import dev.asiluxserver.launcher.Main;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.panel.Panel;
import fr.litarvan.openauth.AuthPoints;
import fr.litarvan.openauth.AuthenticationException;
import fr.litarvan.openauth.Authenticator;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.litarvan.openauth.model.AuthAgent;
import fr.litarvan.openauth.model.response.AuthResponse;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Login extends Panel {

    GridPane loginPanel = new GridPane();
    GridPane mainPanel = new GridPane();
    GridPane middlePannel = new GridPane();
    GridPane topPanel = new GridPane();
    GridPane topPanelContour = new GridPane();
    GridPane bottomPanel = new GridPane();

    TextField usernameField = new TextField();
    PasswordField passwordField = new PasswordField();
    Button connectionButton = new Button("SE CONNECTER");
    AtomicBoolean connectWithMojang = new AtomicBoolean(true);
    AtomicBoolean connectWithMicrosoft = new AtomicBoolean(false);

    Saver saver = Launcher.getInstance().getSaver();

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);


        AtomicBoolean antiSpamConnection = new AtomicBoolean(false);

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
        connectionLabel.setStyle("-fx-font-size: 26;");
        connectionLabel.setTextFill(Color.rgb(255,255,255));
        connectionLabel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 6, 0, 0, 0));
        setGrow(connectionLabel);
        setAlignment(connectionLabel, HPos.CENTER ,VPos.CENTER);


        /*
         * ELEMENT DANS MIDDLE PANEL
         */


        /* Text Nom d'utilisateur */
        Label usernameLabel = new Label("Adresse mail:");
        usernameLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 17));
        usernameLabel.setTextFill(Color.rgb(255,255,255));
        usernameLabel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.2), 4, 0, 0, 0));
        setGrow(usernameLabel);
        setAlignment(usernameLabel, HPos.LEFT ,VPos.TOP);
        usernameLabel.setTranslateX(7d);
        usernameLabel.setTranslateY(17d);

        /* Text Nom d'utilisateur Erreur */
        Label errorUsernameLabel = new Label("");
        errorUsernameLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 17));
        errorUsernameLabel.setTextFill(Color.rgb(225,53,53));
        errorUsernameLabel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.2), 4, 0, 0, 0));
        setGrow(errorUsernameLabel);
        setAlignment(errorUsernameLabel, HPos.LEFT ,VPos.TOP);
        errorUsernameLabel.setTranslateX(7d);
        errorUsernameLabel.setTranslateY(84d);


        /* Text Field Nom d'utilisateur + Separator */
        setGrow(usernameField);
        setAlignment(usernameField, HPos.LEFT ,VPos.TOP);
        usernameField.setStyle("-fx-background-color: rgba(37, 37, 37, 0.8); -fx-font-size: 16; -fx-text-fill: rgba(255,255,255,1);");
        usernameField.setMaxHeight(38);
        usernameField.setMinHeight(38);
        usernameField.setMaxWidth(268);
        usernameField.setMinWidth(268);
        usernameField.setTranslateX(6d);
        usernameField.setTranslateY(45d);
        usernameField.focusedProperty().addListener((_a, oldValue, newValue) -> {
            if (!newValue) this.updateLoginBtnState(usernameField, null);
        });
        usernameField.setOnKeyPressed(e-> {
            updateLoginBtnState(usernameField, null);
        });

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
        Label passwordLabel = new Label("Mots de passes:");
        setGrow(passwordLabel);
        setAlignment(passwordLabel, HPos.LEFT ,VPos.TOP);
        passwordLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 17));
        passwordLabel.setTextFill(Color.rgb(255,255,255));
        passwordLabel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.2), 4, 0, 0, 0));
        passwordLabel.setTranslateX(7d);
        passwordLabel.setTranslateY(124d);

        /* Text Error Mot de passe */
        Label errorPasswordLabel = new Label("");
        setGrow(errorPasswordLabel);
        setAlignment(errorPasswordLabel, HPos.LEFT ,VPos.TOP);
        errorPasswordLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 17));
        errorPasswordLabel.setTextFill(Color.rgb(225,53,53));
        errorPasswordLabel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.2), 4, 0, 0, 0));
        errorPasswordLabel.setTranslateX(7d);
        errorPasswordLabel.setTranslateY(211d);

        /* Text Field Nom d'utilisateur + Separator */
        passwordField.setMaxHeight(38);
        passwordField.setMinHeight(38);
        passwordField.setMaxWidth(268);
        passwordField.setMinWidth(268);
        passwordField.setStyle("-fx-background-color: rgba(37, 37, 37, 0.8); -fx-font-size: 16; -fx-text-fill: rgba(255,255,255,1);");
        setGrow(passwordField);
        setAlignment(passwordField, HPos.LEFT ,VPos.TOP);
        passwordField.setTranslateX(6d);
        passwordField.setTranslateY(152d);
        passwordField.focusedProperty().addListener((_a, oldValue, newValue) -> {
            if (!newValue) this.updateLoginBtnState(passwordField, null);
        });
        passwordField.setOnKeyPressed(e-> {
            updateLoginBtnState(passwordField, null);
        });

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
        forgotenpasswordLabel.setTranslateY(196d);
        forgotenpasswordLabel.setOnMouseEntered(e-> {

            this.layout.setCursor(Cursor.HAND);
            forgotenpasswordLabel.setTextFill(Color.rgb(245, 255, 245));

        });
        forgotenpasswordLabel.setOnMouseExited(e-> {

            this.layout.setCursor(Cursor.DEFAULT);
            forgotenpasswordLabel.setTextFill(Color.rgb(225, 225, 225));

        });
        forgotenpasswordLabel.setOnMouseClicked(e-> {
            if (connectWithMojang.get()) {
                openUrl("https:///www.minecraft.net/fr-fr/password/forgot");
            }

            else {
                openUrl("https://asilux.w4.cmws.fr/#"); //TODO: CHANGER LE SITE INTERNET
            }

        });

        /* ICONES CONNECTION API */
        ImageView mojangIcone = new ImageView(new Image("/images/icone/mojang-icone.png"));
        mojangIcone.setFitHeight(23);
        mojangIcone.setFitWidth(23);
        mojangIcone.setTranslateX(-5);
        mojangIcone.setTranslateY(2);

        ImageView microsoftIcone = new ImageView(new Image("/images/icone/microsoft.png"));
        microsoftIcone.setFitHeight(23);
        microsoftIcone.setFitWidth(23);
        microsoftIcone.setTranslateX(-5);
        microsoftIcone.setTranslateY(2);

        ImageView asiluxIcone = new ImageView(new Image("images/asilux-icone.png"));
        asiluxIcone.setFitHeight(37);
        asiluxIcone.setFitWidth(37);
        asiluxIcone.setTranslateX(-5);

        /* Bouton pour se connecter*/
        connectionButton.setMaxHeight(40);
        connectionButton.setMinHeight(40);
        connectionButton.setMaxWidth(268);
        connectionButton.setMinWidth(268);
        connectionButton.setStyle("-fx-background-color: #91B848FF; -fx-font-size: 20; -fx-border-radius: 2px; -fx-text-fill: rgba(255,255,255,1);");
        connectionButton.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
        connectionButton.setGraphic(mojangIcone);
        connectionButton.setTextAlignment(TextAlignment.CENTER);
        connectionButton.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(37, 37, 37, 0.2), 3, 0.3, 0, 0));
        setGrow(connectionButton);
        setAlignment(connectionButton, HPos.CENTER ,VPos.BOTTOM);
        connectionButton.setTranslateY(-76d);
        connectionButton.setDisable(true);
        connectionButton.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        connectionButton.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        connectionButton.setOnMouseClicked(e-> {
            if (connectWithMojang.get()) {
                this.authenticate(usernameField.getText(), passwordField.getText());
            } else if (connectWithMicrosoft.get()) {
                this.authenticateMS(usernameField.getText(), passwordField.getText());
            }
            Timeline clickedAnimation = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(connectionButton.backgroundProperty(), new Background(new BackgroundFill(Color.valueOf("#74923AFF"), CornerRadii.EMPTY, javafx.geometry.Insets.EMPTY)))),
                    new KeyFrame(Duration.millis(500), new KeyValue(connectionButton.backgroundProperty(), new Background(new BackgroundFill(Color.valueOf("#91B848FF"), CornerRadii.EMPTY, Insets.EMPTY)))));
            clickedAnimation.setOnFinished(ev -> connectionButton.setStyle("-fx-background-color: #91B848FF; -fx-font-size: 20; -fx-border-radius: 2px; -fx-text-fill: rgba(255,255,255,1);"));
            clickedAnimation.play();

        });

        /*
         * SEPARATEUR MOD DE CONNECTION
         */

        /* Separateurs */
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
        connectionChoseS2.setMaxWidth(70);
        connectionChoseS2.setMinWidth(70);
        connectionChoseS2.setTranslateX(-20d);
        connectionChoseS2.setTranslateY(-30d);

        Separator connectionChoseS3 = new Separator();
        setGrow(connectionChoseS3);
        setAlignment(connectionChoseS3, HPos.RIGHT ,VPos.BOTTOM);
        connectionChoseS3.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 4, 0, 0, 0));
        connectionChoseS3.setMaxHeight(38);
        connectionChoseS3.setMinHeight(38);
        connectionChoseS3.setMaxWidth(70);
        connectionChoseS3.setMinWidth(70);
        connectionChoseS3.setTranslateX(20d);
        connectionChoseS3.setTranslateY(-30d);

        /* Label se connecter */
        Label connectionwithLabel = new Label("CONNEXION AVEC");
        setGrow(connectionwithLabel);
        setAlignment(connectionwithLabel, HPos.CENTER ,VPos.BOTTOM);
        connectionwithLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 18));
        connectionwithLabel.setTextFill(Color.rgb(225,225,225));
        connectionwithLabel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(210, 210, 210, 0.2), 4, 0, 0, 0));
        connectionwithLabel.setTranslateY(-37d);

        /* Bouttons mode de connection + Microsoft */
        ImageView microsoftImage = new ImageView(new Image("images/icone/microsoft.png"));
        microsoftImage.setFitHeight(55);
        microsoftImage.setFitWidth(55);
        setCanTakeAllSize(microsoftImage);
        setBottom(microsoftImage);
        setLeft(microsoftImage);
        microsoftImage.setTranslateX(25d);
        microsoftImage.setTranslateY(30d);
        microsoftImage.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        microsoftImage.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        microsoftImage.setOnMouseClicked(e-> {
            connectionButton.setGraphic(microsoftIcone);
            connectWithMojang.set(false);
            connectWithMicrosoft.set(true);
        });

        ImageView mojangImage = new ImageView(new Image("images/icone/mojang-icone.png"));
        mojangImage.setFitHeight(55);
        mojangImage.setFitWidth(55);
        setCanTakeAllSize(mojangImage);
        setBottom(mojangImage);
        setCenterH(mojangImage);
        mojangImage.setTranslateY(30d);
        mojangImage.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        mojangImage.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        mojangImage.setOnMouseClicked(e-> {
            connectionButton.setGraphic(mojangIcone);
            connectWithMicrosoft.set(false);
            connectWithMojang.set(true);

        });

        ImageView asiluxImage = new ImageView(new Image("images/asilux-icone.png"));
        asiluxImage.setFitHeight(75);
        asiluxImage.setFitWidth(75);
        setCanTakeAllSize(asiluxImage);
        setBottom(asiluxImage);
        setRight(asiluxImage);
        asiluxImage.setTranslateX(-15d);
        asiluxImage.setTranslateY(40d);

        /*
        * REGISTERY HANDLERS
        */
        topPanel.getChildren().addAll(connectionLabel);
        bottomPanel.getChildren().addAll(noAccount, registerHere);
        middlePannel.getChildren().addAll(usernameLabel, usernameField, usernameSeparator, errorUsernameLabel,
                passwordLabel, passwordField, passwordSeparator, errorPasswordLabel, forgotenpasswordLabel, connectionButton,
                connectionChoseS2, connectionChoseS3, connectionwithLabel, microsoftImage, mojangImage, asiluxImage);
        this.layout.getChildren().add(loginPanel);

    }

    private void openUrl(String str) {
        try {
            Desktop.getDesktop().browse(new URI(str));
        } catch (IOException | URISyntaxException e) {
            //TODO: Message d'erreur
        }
    }

    private void updateLoginBtnState(TextField textField, Label errorLabel) {
        if (textField.getText().length() == 0) {
            if (errorLabel != null) {
                errorLabel.setText("Le champs ne peux être vide");
            }
        } else {
            if (errorLabel != null) {
                errorLabel.setText("");
            }

        }
        connectionButton.setDisable(!(usernameField.getText().length() > 0 && passwordField.getText().length() > 0));
    }

    public void authenticate(String user, String password) {

        if (connectWithMojang.get()) {
            Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);

            try {
                AuthResponse response = authenticator.authenticate(AuthAgent.MINECRAFT, user, password, null);

                saver.set("accessToken", response.getAccessToken());
                saver.set("clientToken", response.getClientToken());
                saver.save();

                Launcher.getInstance().setAuthInfos(new AuthInfos(
                        response.getSelectedProfile().getName(),
                        response.getAccessToken(),
                        response.getClientToken(),
                        response.getSelectedProfile().getId()
                ));

                this.logger.info("Hello " + response.getSelectedProfile().getName());

                panelManager.showPanel(new App());
            } catch (AuthenticationException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Une erreur est survenu lors de la connexion");
                alert.setContentText(e.getMessage());
                alert.show();
            }
        }
    }

    public void authenticateMS(String email, String password) {
        MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
        try {
            MicrosoftAuthResult result = authenticator.loginWithCredentials(email, password);
            saver.set("msAccessToken", result.getAccessToken());
            saver.set("msRefreshToken", result.getRefreshToken());
            saver.save();
            Launcher.getInstance().setAuthInfos(new AuthInfos(
                    result.getProfile().getName(),
                    result.getAccessToken(),
                    result.getProfile().getId()
            ));

            this.logger.info("Hello " + result.getProfile().getName());

            panelManager.showPanel(new App());

        } catch (MicrosoftAuthenticationException error) {
            Launcher.getInstance().getLogger().err(error.toString());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(error.getMessage());
            alert.show();
        }
    }
}
