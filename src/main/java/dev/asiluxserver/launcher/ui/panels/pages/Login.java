package dev.asiluxserver.launcher.ui.panels.pages;

import dev.asiluxserver.launcher.AUpdater;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.assets.Colors;
import dev.asiluxserver.launcher.ui.assets.Fonts;
import dev.asiluxserver.launcher.ui.assets.effects.BlurDropShadow;
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
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicBoolean;

public class Login extends Panel {

    GridPane loginPanel = new GridPane();
    GridPane mainPane = new GridPane();
    GridPane middlePane = new GridPane();
    GridPane topPane = new GridPane();
    GridPane bottomPane = new GridPane();

    TextField usernameField = new TextField();
    PasswordField passwordField = new PasswordField();
    Button connectionButton = new Button("SE CONNECTER");
    AtomicBoolean connectWithMojang = new AtomicBoolean(true);
    AtomicBoolean connectWithMicrosoft = new AtomicBoolean(false);

    Saver saver = AUpdater.getInstance().getSaver();

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
        loginPanel.setStyle(
                "-fx-background-color: rgba(0,0,0,0);" +
                        "-fx-opacity: 100%;"
        );
        loginPanel.setTranslateY(-55);

        RowConstraints bottomConstraints = new RowConstraints();
        bottomConstraints.setValignment(VPos.TOP);
        loginPanel.getRowConstraints().addAll(new RowConstraints(), bottomConstraints);
        loginPanel.add(topPane, 0 , 0);
        loginPanel.add(mainPane, 0 , 1);
        loginPanel.add(bottomPane, 0 , 2);



        GridPane.setHgrow(mainPane, Priority.ALWAYS);
        GridPane.setVgrow(mainPane, Priority.ALWAYS);

        GridPane.setHgrow(bottomPane, Priority.ALWAYS);
        GridPane.setVgrow(bottomPane, Priority.ALWAYS);

        RowConstraints topConstraints = new RowConstraints();
        topConstraints.setValignment(VPos.TOP);
        mainPane.getRowConstraints().addAll(new RowConstraints(), topConstraints);
        mainPane.add(middlePane, 0,0);

        /* Top Panel */
        topPane.setMaxHeight(60);
        topPane.setMinHeight(60);
        topPane.setMaxWidth(400);
        topPane.setMinWidth(400);
        GridPane.setHgrow(topPane, Priority.ALWAYS);
        GridPane.setVgrow(topPane, Priority.ALWAYS);
        setAlignment(topPane, HPos.CENTER, VPos.CENTER);
        topPane.setTranslateY(25);

        /* Middle Panel */
        middlePane.setMaxHeight(350);
        middlePane.setMinHeight(350);
        middlePane.setMaxWidth(280);
        middlePane.setMinWidth(280);
        GridPane.setHgrow(middlePane, Priority.ALWAYS);
        GridPane.setVgrow(middlePane, Priority.ALWAYS);
        setAlignment(middlePane, HPos.CENTER, VPos.TOP);

        /*
         * STYLE DES PANEL
         */

        /* Style Main Panel */
        mainPane.setStyle("-fx-background-color: rgba(153, 189, 95, 0.75);");

        /* Style Bottom Panel*/
        bottomPane.setStyle("-fx-background-color: rgba(24,24,24,0.75);");

        /* Style Top Panel */
        topPane.setStyle("-fx-background-color: rgba(27, 27, 27, 0.75);");

        /* Style Middle Panel */
        middlePane.setEffect(new Bloom(18));
        middlePane.setEffect(new BoxBlur(10,10,5));
        middlePane.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_4, 17, 0));


        /*
         *  ELEMENT DANS BOTTOM PANEL
         */

        /* Text sans action */
        Label noAccount = new Label("Vous n'avez pas encore de compte ?");

        setGrow(noAccount);
        setAlignment(noAccount, HPos.CENTER, VPos.TOP);
        noAccount.setTranslateY(6);
        noAccount.setStyle("-fx-text-fill: #ffffff;");
        noAccount.setFont(Font.loadFont(Fonts.SELAWK_SEMI_LIGHT.get(), 14));
        /* Text pour S'inscrire avec action */
        Label registerHere = new Label("S'inscrire");
        setGrow(registerHere);
        setAlignment(registerHere, HPos.CENTER, VPos.BOTTOM);
        registerHere.setStyle("-fx-font-size: 14px;");
        registerHere.setFont(Font.loadFont(Fonts.SELAWK_SEMI_LIGHT.get(), 14));
        registerHere.setTranslateY(-6);
        registerHere.setTextFill(Colors.LIGHT_GREEN_3.getColor());
        registerHere.setUnderline(true);
        registerHere.setOnMouseEntered(e-> {

            this.layout.setCursor(Cursor.HAND);
            registerHere.setTextFill(Colors.LIGHT_GREEN_1.getColor());

        });
        registerHere.setOnMouseExited(e-> {

            this.layout.setCursor(Cursor.DEFAULT);
            registerHere.setTextFill(Colors.LIGHT_GREEN_3.getColor());

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
        connectionLabel.setFont(Font.loadFont(Fonts.SELAWK_SEMI_BOLD.get(), 26));
        connectionLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        connectionLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_2, 6, 0));
        setGrow(connectionLabel);
        setAlignment(connectionLabel, HPos.CENTER ,VPos.CENTER);


        /*
         * ELEMENT DANS MIDDLE PANEL
         */


        /* Text Nom d'utilisateur */
        Label usernameLabel = new Label("Adresse mail:");
        usernameLabel.setFont(Font.loadFont(Fonts.SELAWK_SEMI_LIGHT.get(), 16));
        usernameLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        usernameLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
        setGrow(usernameLabel);
        setAlignment(usernameLabel, HPos.LEFT ,VPos.TOP);
        usernameLabel.setTranslateX(7d);
        usernameLabel.setTranslateY(17d);

        /* Text Nom d'utilisateur Erreur */
        Label errorUsernameLabel = new Label("");
        errorUsernameLabel.setFont(Font.loadFont(Fonts.SELAWK_SEMI_LIGHT.get(), 16));
        errorUsernameLabel.setTextFill(Colors.RED_1.getColor());
        errorUsernameLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
        setGrow(errorUsernameLabel);
        setAlignment(errorUsernameLabel, HPos.LEFT ,VPos.TOP);
        errorUsernameLabel.setTranslateX(7d);
        errorUsernameLabel.setTranslateY(84d);


        /* Text Field Nom d'utilisateur + Separator */
        setGrow(usernameField);
        setAlignment(usernameField, HPos.LEFT ,VPos.TOP);
        usernameField.setStyle(
                "-fx-background-color: rgba(37, 37, 37, 0.8);" +
                        "-fx-font-size: 16;" +
                        "-fx-text-fill: rgba(255,255,255,1);"
        );
        usernameField.setFont(Font.loadFont(Fonts.SELAWK_SEMI_LIGHT.get(), 16));
        usernameField.setMaxHeight(38);
        usernameField.setMinHeight(38);
        usernameField.setMaxWidth(268);
        usernameField.setMinWidth(268);
        usernameField.setTranslateX(6d);
        usernameField.setTranslateY(45d);
        usernameField.focusedProperty().addListener((_a, oldValue, newValue) -> {
            if (!newValue) this.updateLoginBtnState(usernameField, null);
        });
        usernameField.setOnKeyPressed(e-> updateLoginBtnState(usernameField, null));

        Separator usernameSeparator = new Separator();
        setGrow(usernameSeparator);
        setAlignment(usernameSeparator, HPos.LEFT ,VPos.TOP);
        usernameSeparator.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_2, 4, 0));
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
        passwordLabel.setFont(Font.loadFont(Fonts.SELAWK_SEMI_LIGHT.get(), 16));
        passwordLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        passwordLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
        passwordLabel.setTranslateX(7d);
        passwordLabel.setTranslateY(124d);

        /* Text Error Mot de passe */
        Label errorPasswordLabel = new Label("");
        setGrow(errorPasswordLabel);
        setAlignment(errorPasswordLabel, HPos.LEFT ,VPos.TOP);
        errorPasswordLabel.setFont(Font.loadFont(Fonts.SELAWK_SEMI_LIGHT.get(), 16));
        errorPasswordLabel.setTextFill(Colors.RED_1.getColor());
        errorPasswordLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
        errorPasswordLabel.setTranslateX(7d);
        errorPasswordLabel.setTranslateY(211d);

        /* Text Field Nom d'utilisateur + Separator */
        passwordField.setMaxHeight(38);
        passwordField.setMinHeight(38);
        passwordField.setMaxWidth(268);
        passwordField.setMinWidth(268);
        passwordField.setStyle(
                "-fx-background-color: rgba(37, 37, 37, 0.8);" +
                        "-fx-font-size: 16;" +
                        "-fx-text-fill: rgba(255,255,255,1);"
        );
        passwordField.setFont(Font.loadFont(Fonts.SELAWK_SEMI_LIGHT.get(), 16));
        setGrow(passwordField);
        setAlignment(passwordField, HPos.LEFT ,VPos.TOP);
        passwordField.setTranslateX(6d);
        passwordField.setTranslateY(152d);
        passwordField.focusedProperty().addListener((_a, oldValue, newValue) -> {
            if (!newValue) this.updateLoginBtnState(passwordField, null);
        });
        passwordField.setOnKeyPressed(e-> updateLoginBtnState(passwordField, null));

        Separator passwordSeparator = new Separator();
        setGrow(passwordSeparator);
        setAlignment(passwordSeparator, HPos.LEFT ,VPos.TOP);
        passwordSeparator.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_2, 4, 0));
        passwordSeparator.setMaxHeight(38);
        passwordSeparator.setMinHeight(38);
        passwordSeparator.setMaxWidth(268);
        passwordSeparator.setMinWidth(268);
        passwordSeparator.setTranslateX(6d);
        passwordSeparator.setTranslateY(171d);

        /* Text Oublie de Mot de passe avec action */
        Label forgottenPasswordLabel = new Label("Mot de passe oublié ?");
        setGrow(forgottenPasswordLabel);
        setAlignment(forgottenPasswordLabel, HPos.LEFT ,VPos.TOP);
        forgottenPasswordLabel.setFont(Font.loadFont(Fonts.SELAWK.get(), 14));
        forgottenPasswordLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        forgottenPasswordLabel.setEffect(new BlurDropShadow(Colors.GREY_2, 4, 0));
        forgottenPasswordLabel.setTranslateX(6d);
        forgottenPasswordLabel.setTranslateY(196d);
        forgottenPasswordLabel.setOnMouseEntered(e-> {

            this.layout.setCursor(Cursor.HAND);
            forgottenPasswordLabel.setTextFill(Colors.LIGHT_GREEN_1.getColor());

        });
        forgottenPasswordLabel.setOnMouseExited(e-> {

            this.layout.setCursor(Cursor.DEFAULT);
            forgottenPasswordLabel.setTextFill(Colors.GREY_1.getColor());

        });
        forgottenPasswordLabel.setOnMouseClicked(e-> {
            if (connectWithMojang.get()) {
                openUrl("https:///www.minecraft.net/fr-fr/password/forgot");
            }

            else {
                openUrl("https://asilux.w4.cmws.fr/#"); //TODO: CHANGER LE SITE INTERNET
            }

        });

        /* ICÔNES CONNECTION API */
        ImageView mojangIcon = new ImageView(new Image("/images/icon/mojang-icon.png"));
        mojangIcon.setFitHeight(20);
        mojangIcon.setFitWidth(20);
        mojangIcon.setTranslateX(-5);
        mojangIcon.setTranslateY(2);

        ImageView microsoftIcon = new ImageView(new Image("/images/icon/microsoft.png"));
        microsoftIcon.setFitHeight(20);
        microsoftIcon.setFitWidth(20);
        microsoftIcon.setTranslateX(-5);
        microsoftIcon.setTranslateY(2);

        ImageView asylumIcon = new ImageView(new Image("images/asilux-icon.png"));
        asylumIcon.setFitHeight(37);
        asylumIcon.setFitWidth(37);
        asylumIcon.setTranslateX(-5);

        /* Bouton pour se connecter*/
        connectionButton.setMaxHeight(42);
        connectionButton.setMinHeight(42);
        connectionButton.setMaxWidth(268);
        connectionButton.setMinWidth(268);
        connectionButton.setStyle(
                        "-fx-border-radius: 2px;" +
                        "-fx-text-fill: rgba(255,255,255,1);"
        );
        connectionButton.setFont(Font.loadFont(Fonts.SELAWK.get(), 21));
        connectionButton.setGraphic(mojangIcon);
        connectionButton.setTextAlignment(TextAlignment.CENTER);
        connectionButton.setEffect(new BlurDropShadow(Colors.DARK_GREY_4, 15, 0));
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
        });

        double widthOfOneGradientCycle = 750;
        double gradientSlopeDegree = -20;
        double xStartStatic = 20;
        double yStartStatic = 20;
        double xEndStatic = xStartStatic + (widthOfOneGradientCycle * Math.cos(Math.toRadians(gradientSlopeDegree)));
        double yEndStatic = yStartStatic + (widthOfOneGradientCycle * Math.sin(Math.toRadians(gradientSlopeDegree)));
        Timeline timeline = new Timeline();
        for (int i = 0; i < 500; i++) {
            int innerIterator = i;
            KeyFrame kf = new KeyFrame(Duration.millis(10 * innerIterator), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent ae) {

                    double runningRadius = innerIterator * (widthOfOneGradientCycle * -0.002);
                    double xStartDynamic = xStartStatic + (runningRadius * Math.cos(Math.toRadians(gradientSlopeDegree)));
                    double yStartDynamic = yStartStatic + (runningRadius * Math.sin(Math.toRadians(gradientSlopeDegree)));
                    double xEndDynamic = xEndStatic + (runningRadius * Math.cos(Math.toRadians(gradientSlopeDegree)));
                    double yEndDynamic = yEndStatic + (runningRadius * Math.sin(Math.toRadians(gradientSlopeDegree)));

                    LinearGradient gradient = new LinearGradient(xStartDynamic, yStartDynamic, xEndDynamic, yEndDynamic,
                            false, CycleMethod.REPEAT, new Stop(0.05, Colors.LIGHT_GREEN_2.getColor()),
                            new Stop(0.35, Colors.LIGHT_GREEN_5.getColor()),
                            new Stop(0.75, Colors.LIGHT_GREEN_5.getColor()),
                            new Stop(1, Colors.LIGHT_GREEN_2.getColor()));
                    connectionButton.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            });
            timeline.getKeyFrames().add(kf);
        }
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        /*
         * SEPARATEUR MOD DE CONNECTION
         */

        /* Séparateurs */
        Separator connectionChoseS1 = new Separator();
        setGrow(connectionChoseS1);
        setAlignment(connectionChoseS1, HPos.CENTER ,VPos.BOTTOM);
        connectionChoseS1.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_2, 4, 0));
        connectionChoseS1.setMaxHeight(38);
        connectionChoseS1.setMinHeight(38);
        connectionChoseS1.setMaxWidth(268);
        connectionChoseS1.setMinWidth(268);
        connectionChoseS1.setTranslateY(-45d);

        Separator connectionChoseS2 = new Separator();
        setGrow(connectionChoseS2);
        setAlignment(connectionChoseS2, HPos.LEFT ,VPos.BOTTOM);
        connectionChoseS2.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_2, 4, 0));
        connectionChoseS2.setMaxHeight(38);
        connectionChoseS2.setMinHeight(38);
        connectionChoseS2.setMaxWidth(70);
        connectionChoseS2.setMinWidth(70);
        connectionChoseS2.setTranslateX(-20d);
        connectionChoseS2.setTranslateY(-30d);

        Separator connectionChoseS3 = new Separator();
        setGrow(connectionChoseS3);
        setAlignment(connectionChoseS3, HPos.RIGHT ,VPos.BOTTOM);
        connectionChoseS3.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_2, 4, 0));
        connectionChoseS3.setMaxHeight(38);
        connectionChoseS3.setMinHeight(38);
        connectionChoseS3.setMaxWidth(70);
        connectionChoseS3.setMinWidth(70);
        connectionChoseS3.setTranslateX(20d);
        connectionChoseS3.setTranslateY(-30d);

        /* Label se connecter */
        Label connectionWithLabel = new Label("CONNEXION AVEC");
        setGrow(connectionWithLabel);
        setAlignment(connectionWithLabel, HPos.CENTER ,VPos.BOTTOM);
        connectionWithLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 18));
        connectionWithLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        connectionWithLabel.setEffect(new BlurDropShadow(Colors.GREY_2, 4, 0));
        connectionWithLabel.setTranslateY(-37d);

        /* Boutons mode de connection + Microsoft */
        ImageView microsoftImage = new ImageView(new Image("images/icon/microsoft.png"));
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
            connectionButton.setGraphic(microsoftIcon);
            connectWithMojang.set(false);
            connectWithMicrosoft.set(true);
            //authenticateMS();
        });

        ImageView mojangImage = new ImageView(new Image("images/icon/mojang-icon.png"));
        mojangImage.setFitHeight(55);
        mojangImage.setFitWidth(55);
        setCanTakeAllSize(mojangImage);
        setBottom(mojangImage);
        setCenterH(mojangImage);
        mojangImage.setTranslateY(30d);
        mojangImage.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        mojangImage.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        mojangImage.setOnMouseClicked(e-> {
            connectionButton.setGraphic(mojangIcon);
            connectWithMicrosoft.set(false);
            connectWithMojang.set(true);

        });

        ImageView asiluxImage = new ImageView(new Image("images/asilux-icon.png"));
        asiluxImage.setFitHeight(75);
        asiluxImage.setFitWidth(75);
        setCanTakeAllSize(asiluxImage);
        setBottom(asiluxImage);
        setRight(asiluxImage);
        asiluxImage.setTranslateX(-15d);
        asiluxImage.setTranslateY(40d);

        /*
        * REGISTRY HANDLERS
        */
        topPane.getChildren().addAll(connectionLabel);
        bottomPane.getChildren().addAll(noAccount, registerHere);
        middlePane.getChildren().addAll(
                usernameLabel, usernameField, usernameSeparator, errorUsernameLabel,
                passwordLabel, passwordField, passwordSeparator, errorPasswordLabel,
                forgottenPasswordLabel,
                connectionButton, connectionChoseS2, connectionChoseS3, connectionWithLabel,
                microsoftImage, mojangImage, asiluxImage
        );
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
            Authenticator authenticator = new Authenticator(
                    Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS
            );

            try {
                AuthResponse response = authenticator.authenticate(AuthAgent.MINECRAFT, user, password, null);

                saver.set("accessToken", response.getAccessToken());
                saver.set("clientToken", response.getClientToken());
                saver.save();

                AUpdater.getInstance().setAuthInfos(new AuthInfos(
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
            AUpdater.getInstance().setAuthInfos(new AuthInfos(
                    result.getProfile().getName(),
                    result.getAccessToken(),
                    result.getProfile().getId()
            ));

            this.logger.info("Hello " + result.getProfile().getName());

            panelManager.showPanel(new App());

        } catch (MicrosoftAuthenticationException error) {
            AUpdater.getInstance().getLogger().err(error.toString());
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setContentText(error.getMessage());
            alert.show();
        }
    }

    public void authenticateWebMS() {
        MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
        authenticator.loginWithAsyncWebview().whenComplete((response, error) -> {
            if (error != null) {
                AUpdater.getInstance().getLogger().err(error.toString());
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setContentText(error.getMessage());
                alert.show();
                return;
            }

            saver.set("msAccessToken", response.getAccessToken());
            saver.set("msRefreshToken", response.getRefreshToken());
            saver.save();
            AUpdater.getInstance().setAuthInfos(new AuthInfos(
                    response.getProfile().getName(),
                    response.getAccessToken(),
                    response.getProfile().getId()
            ));
            this.logger.info("Hello " + response.getProfile().getName());

            panelManager.showPanel(new App());
        });
    }
}
