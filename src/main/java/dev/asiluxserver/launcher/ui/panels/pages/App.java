package dev.asiluxserver.launcher.ui.panels.pages;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import dev.asiluxserver.launcher.Launcher;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.assets.Colors;
import dev.asiluxserver.launcher.ui.assets.effects.BlurDropShadow;
import dev.asiluxserver.launcher.ui.panel.IPanel;
import dev.asiluxserver.launcher.ui.panel.Panel;
import dev.asiluxserver.launcher.ui.panels.pages.content.Home;
import dev.asiluxserver.launcher.ui.panels.pages.content.Settings;

import fr.theshark34.openlauncherlib.util.Saver;

import javafx.animation.Transition;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

public class App extends Panel {

    private static App instance;

    ColumnConstraints menuPainConstraint = new ColumnConstraints();
    GridPane centerPane = new GridPane();
    GridPane leftBarPanel = new GridPane();

    Label homeLabel = new Label(" Accueil");
    Label newsLabel = new Label(" Nouveautés");
    Label updateLabel = new Label(" Mises à jour");
    Label settingsLabel = new Label(" Paramètres");
    Rectangle userLocationRectangle = new Rectangle(10, 30);

    Saver saver = Launcher.getInstance().getSaver();
    Node prevUserInfoPose = homeLabel;

    @Override
    public String getName() {
        return null;
    }

    public App() {
        App.instance = this;
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        /* BASE PANEL */
        menuPainConstraint.setHalignment(HPos.LEFT);
        menuPainConstraint.setMinWidth(140);
        menuPainConstraint.setMaxWidth(140);
        this.layout.getColumnConstraints().addAll(menuPainConstraint, new ColumnConstraints());

        setGrow(leftBarPanel);
        setAlignment(leftBarPanel, HPos.LEFT, VPos.CENTER);
        leftBarPanel.setMinWidth(140);
        leftBarPanel.setMaxWidth(140);
        leftBarPanel.setStyle("-fx-background-color: rgba(24,24,24,1);");

        this.layout.add(leftBarPanel, 0, 0);
        this.layout.add(centerPane, 1, 0);
        setGrow(centerPane);
        setAlignment(this.centerPane, HPos.RIGHT, VPos.CENTER);

        /* ACCUEIL */
        ColumnConstraints mainConstraints = new ColumnConstraints();
        mainConstraints.setHalignment(HPos.RIGHT);
        mainConstraints.setMinWidth(800);
        mainConstraints.setMaxWidth(1800);
        centerPane.getColumnConstraints().addAll(mainConstraints, new ColumnConstraints());

        GridPane mainMenuPanel = new GridPane();
        setGrow(mainMenuPanel);
        setAlignment(mainMenuPanel, HPos.RIGHT, VPos.TOP);
        mainMenuPanel.setMinWidth(700);
        mainMenuPanel.setMaxWidth(700);
        mainMenuPanel.setMinHeight(700);
        mainMenuPanel.setMinHeight(700);

        /* NEWS ACCUEIL */
        GridPane newsMainMenuPanel = new GridPane();
        setGrow(newsMainMenuPanel);
        setAlignment(newsMainMenuPanel, HPos.RIGHT, VPos.CENTER);
        newsMainMenuPanel.setMinWidth(200);
        newsMainMenuPanel.setMaxWidth(300);
        newsMainMenuPanel.setMinHeight(320);
        newsMainMenuPanel.setMaxHeight(320);

        centerPane.add(mainMenuPanel, 0, 0);
        centerPane.add(newsMainMenuPanel, 1, 0);
    }

    @Override
    public void onShow() {
        super.onShow();
        setPage(new Home(), homeLabel); //Ouvre l'onglet Home
    }

    /*
     * BAR DE NAVIGATION + GRID PANE DE L'ONGLET
     */

    /* Barre de navigation */
    private void leftBarNav(GridPane pane, Node button) {

        /* USER AVATAR BACKGROUND */
        Rectangle avatarRectangle = new Rectangle(0, 0, 64 ,64);
        avatarRectangle.setFill(Color.valueOf("#91B848FF"));
        avatarRectangle.setEffect(new BlurDropShadow(Colors.DARK_GREY_2, 3, 0.3));
        avatarRectangle.setArcWidth(12);
        avatarRectangle.setArcHeight(12);
        setTop(avatarRectangle);
        setCanTakeAllSize(avatarRectangle);
        setCenterH(avatarRectangle);
        avatarRectangle.setTranslateY(8d);

        /* USER AVATAR */
        String avatarUrl = "https://minotar.net/avatar/MHF_Steve.png";
        //ou + Launcher.getInstance().getAuthInfo().getUuid() + ".png";
        ImageView avatarView = new ImageView();
        avatarView.setImage(new Image(avatarUrl));
        avatarView.setPreserveRatio(true);
        avatarView.setFitHeight(50d);
        avatarView.setStyle("");
        avatarView.setEffect(new BlurDropShadow(Colors.DARK_GREY_1, 7, 0.5));
        setTop(avatarView);
        setCanTakeAllSize(avatarView);
        setCenterH(avatarView);
        avatarView.setTranslateY(15d);


        avatarView.setOnMouseEntered(e-> {

            //Change User Cursor
            this.layout.setCursor(Cursor.HAND);

            /* UP AVATAR SCALE */
            Transition avatarUpScale = new Transition() {
                {
                    setCycleDuration(Duration.millis(500));
                }

                double n = 0.05;

                @Override
                protected void interpolate(double frac) {
                    if (n <= 0.05) {
                        n = n + 0.005;
                    } else {
                        n = n - 0.0025;
                    }

                    if (avatarView.getFitHeight() < 58d) {
                        avatarView.setFitHeight(avatarView.getFitHeight() + 0.05 + n);
                        if (avatarView.getTranslateY() > 11d) {
                            avatarView.setTranslateY(avatarView.getTranslateY() - 0.025 - 0.5 * n);
                        } else {
                            avatarView.setTranslateY(11d);
                        }
                    } else {
                        n = 0.0001;
                        avatarView.setFitHeight(58d);
                        avatarView.setTranslateY(11d);
                    }
                }
            };

            if (avatarView.getFitHeight() < 58d) {
                avatarUpScale.play();
            }
        });

        avatarView.setOnMouseExited(e-> {

            //Change User Cursor
            this.layout.setCursor(Cursor.DEFAULT);
            /* DOWN AVATAR SCALE */
            Transition avatarDownScale = new Transition() {
                {
                    setCycleDuration(Duration.millis(500));
                }

                double n = 0.05;

                @Override
                protected void interpolate(double frac) {
                    if (n <= 0.05) {
                        n = n + 0.005;
                    } else {
                        n = n - 0.0025;
                    }

                    if (avatarView.getFitHeight() > 50d) {
                        avatarView.setFitHeight(avatarView.getFitHeight() - 0.05 - n);
                        if (avatarView.getTranslateY() < 15d) {
                            avatarView.setTranslateY(avatarView.getTranslateY() + 0.025 + 0.5 * n);
                        } else {
                            avatarView.setTranslateY(15d);
                        }
                    } else {
                        n = 0.0001;
                        avatarView.setFitHeight(50d);
                        avatarView.setTranslateY(15d);
                    }
                }
            };

            if (avatarView.getFitHeight() > 50d) {
                avatarDownScale.play();
            }
        });

        /* TITLE HOME */
        FontAwesomeIconView homeIcon = new FontAwesomeIconView(FontAwesomeIcon.HOME);
        homeIcon.setFill(Colors.DEFAULT_WHITE.getColor());
        homeIcon.setScaleX(1.15);
        homeIcon.setScaleY(1.15);
        homeLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 17));
        homeLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        homeLabel.setGraphic(homeIcon);
        homeLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_2, 4, 0));
        setGrow(homeLabel);
        setTop(homeLabel);
        setLeft(homeLabel);
        homeLabel.setTranslateX(17);
        homeLabel.setTranslateY(100);
        homeLabel.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        homeLabel.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        homeLabel.setOnMouseClicked(e-> setPage(new Home(), homeLabel));

        /* TITLE NEWS */
        FontAwesomeIconView newsIcon = new FontAwesomeIconView(FontAwesomeIcon.BELL);
        newsIcon.setFill(Colors.DEFAULT_WHITE.getColor());
        newsIcon.setScaleX(0.95);
        newsIcon.setScaleY(0.95);
        newsLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 17));
        newsLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        newsLabel.setGraphic(newsIcon);
        newsLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_2, 4, 0));
        setGrow(newsLabel);
        setTop(newsLabel);
        setLeft(newsLabel);
        newsLabel.setTranslateX(17);
        newsLabel.setTranslateY(150);

        /* TITLE UPDATE */
        FontAwesomeIconView updateIcon = new FontAwesomeIconView(FontAwesomeIcon.CODE);
        updateIcon.setFill(Colors.DEFAULT_WHITE.getColor());
        updateIcon.setScaleX(1.1);
        updateIcon.setScaleY(1.1);

        updateLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 17));
        updateLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        updateLabel.setGraphic(updateIcon);
        updateLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_2, 4, 0));
        setGrow(updateLabel);
        setTop(updateLabel);
        setLeft(updateLabel);
        updateLabel.setTranslateX(17);
        updateLabel.setTranslateY(200);

        /* TITLE SETTINGS */
        FontAwesomeIconView settingsIcon = new FontAwesomeIconView(FontAwesomeIcon.GEARS);
        settingsIcon.setFill(Colors.DEFAULT_WHITE.getColor());
        settingsIcon.setScaleX(1.05);
        settingsIcon.setScaleY(1.05);
        settingsLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 17));
        settingsLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        settingsLabel.setGraphic(settingsIcon);
        settingsLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_2, 4, 0));
        setGrow(settingsLabel);
        setTop(settingsLabel);
        setLeft(settingsLabel);
        settingsLabel.setTranslateX(17);
        settingsLabel.setTranslateY(250);
        settingsLabel.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        settingsLabel.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        settingsLabel.setOnMouseClicked(e-> setPage(new Settings(), settingsLabel));

        /* LOG OUT BUTTON */
        FontAwesomeIconView logOutIcon = new FontAwesomeIconView(FontAwesomeIcon.SIGN_OUT);
        logOutIcon.setFill(Colors.DEFAULT_WHITE.getColor());
        logOutIcon.setScaleX(0.9);
        logOutIcon.setScaleY(0.9);
        Button logOutBtn = new Button("Se déconnnecter");
        logOutBtn.setMinWidth(120);
        logOutBtn.setMinHeight(35);
        logOutBtn.setMaxWidth(120);
        logOutBtn.setMaxHeight(35);
        logOutBtn.setStyle(
                "-fx-background-color: #EE494E;" +
                        "-fx-font-size: 14;" +
                        "-fx-text-fill: rgba(255,255,255,1);"
        );
        logOutBtn.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        logOutBtn.setTextFill(Colors.DEFAULT_WHITE.getColor());
        logOutBtn.setGraphic(logOutIcon);
        logOutBtn.setEffect(new BlurDropShadow(Colors.BLACK_1, 7, 3));
        logOutBtn.setDisable(false);
        setGrow(logOutBtn);
        setBottom(logOutBtn);
        setCenterH(logOutBtn);
        logOutBtn.setTranslateY(-15d);
        //TODO: DÉCONNECTION

        /* USER INFO LOCATION */
        userLocationRectangle.setFill(Color.valueOf("#91B848FF"));
        userLocationRectangle.setArcWidth(8);
        userLocationRectangle.setArcHeight(8);
        setGrow(userLocationRectangle);
        setLeft(userLocationRectangle);
        setTop(userLocationRectangle);
        //TODO: Animation avec userLocationRectangle
        userLocationRectangle.setTranslateX(2);
        userLocationRectangle.setTranslateY(prevUserInfoPose.getTranslateY() - 5);
        Transition userLocationAnimation = new Transition() {
            {
                setCycleDuration(Duration.millis(800));
            }
            @Override
            protected void interpolate(double frac) {
                if (userLocationRectangle.getTranslateY() > button.getTranslateY()) {
                    if (userLocationRectangle.getTranslateY() >= button.getTranslateY() - 5
                            && userLocationRectangle.getTranslateY() < 600) {
                        userLocationRectangle.setTranslateY(userLocationRectangle.getTranslateY() - 5);
                    }
                } else {
                    if (userLocationRectangle.getTranslateY() + 5 != button.getTranslateY()
                            && userLocationRectangle.getTranslateY() < 600) {
                        userLocationRectangle.setTranslateY(userLocationRectangle.getTranslateY() + 5);
                    }
                }
            }
        };
        userLocationAnimation.setOnFinished(e-> userLocationRectangle.setTranslateY(button.getTranslateY() - 5));
        userLocationAnimation.play();
        prevUserInfoPose = button;


        /* REGISTRY @PANE PANEL */
        pane.getChildren().addAll(avatarRectangle, avatarView,
                homeLabel, newsLabel, updateLabel, settingsLabel, logOutBtn,
                userLocationRectangle
        );

    }

    /* METHODES NAVIGATION */
    public void setPage(IPanel panel, Node navButton) {

        this.centerPane.getChildren().clear();
        this.leftBarPanel.getChildren().clear();

        if (panel != null) {
            this.centerPane.getChildren().add(panel.getLayout());
            leftBarNav(leftBarPanel, navButton);
            if (panel.getStyleSheetPath() != null) {
                this.panelManager.getStage().getScene().getStylesheets().clear();
                this.panelManager.getStage().getScene().getStylesheets().addAll(
                        this.getStyleSheetPath(),
                        panel.getStyleSheetPath()
                );
            }
            panel.init(this.panelManager);
            panel.onShow();
        }
    }

}
