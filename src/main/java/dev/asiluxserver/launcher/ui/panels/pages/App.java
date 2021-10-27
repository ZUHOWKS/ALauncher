package dev.asiluxserver.launcher.ui.panels.pages;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import dev.asiluxserver.launcher.Launcher;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.panel.IPanel;
import dev.asiluxserver.launcher.ui.panel.Panel;
import dev.asiluxserver.launcher.ui.panels.pages.content.ContentPanel;
import dev.asiluxserver.launcher.ui.panels.pages.content.Home;
import dev.asiluxserver.launcher.ui.panels.pages.content.Settings;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.animation.Transition;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
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

    ColumnConstraints menuPainContraint = new ColumnConstraints();
    GridPane centerPane = new GridPane();
    GridPane leftBarPanel = new GridPane();

    Label homeLabel = new Label(" Accueille");
    Label newsLabel = new Label(" Nouveauté");
    Label updateLabel = new Label(" Mise à jour");
    Label settingsLabel = new Label(" Paramétre");
    Rectangle userLocationRectangle = new Rectangle(10, 30);

    Saver saver = Launcher.getInstance().getSaver();
    Node prevUserInfoPose = homeLabel;
    Node activeLink = null;
    ContentPanel currentPage = null;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getStyleSheetPath() {
        return super.getStyleSheetPath();
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        /* BASE PANEL */
        menuPainContraint.setHalignment(HPos.LEFT);
        menuPainContraint.setMinWidth(140);
        menuPainContraint.setMaxWidth(140);
        this.layout.getColumnConstraints().addAll(menuPainContraint, new ColumnConstraints());

        setGrow(leftBarPanel);
        setAlignment(leftBarPanel, HPos.LEFT, VPos.CENTER);
        leftBarPanel.setMinWidth(140);
        leftBarPanel.setMaxWidth(140);
        leftBarPanel.setStyle("-fx-background-color: rgba(24,24,24,1);");

        this.layout.add(leftBarPanel, 0, 0);
        this.layout.add(centerPane, 1, 0);
        setGrow(centerPane);
        setAlignment(this.centerPane, HPos.RIGHT, VPos.CENTER);
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
        avatarRectangle.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(37, 37, 37, 0.2), 3, 0.3, 0, 0));
        avatarRectangle.setArcWidth(12);
        avatarRectangle.setArcHeight(12);
        setTop(avatarRectangle);
        setCanTakeAllSize(avatarRectangle);
        setCenterH(avatarRectangle);
        avatarRectangle.setTranslateY(8d);

        /* USER AVATAR */
        String avatarUrl = "https://minotar.net/avatar/MHF_Steve.png"; //ou + Launcher.getInstance().getAuthInfos().getUuid() + ".png";
        ImageView avatarView = new ImageView();
        avatarView.setImage(new Image(avatarUrl));
        avatarView.setPreserveRatio(true);
        avatarView.setFitHeight(50d);
        avatarView.setStyle("");
        avatarView.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(37, 37, 37, 0.3), 7, 0.5, 0, 0));
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
        FontAwesomeIconView homeIcone = new FontAwesomeIconView(FontAwesomeIcon.HOME);
        homeIcone.setFill(Color.rgb(255,255,255));
        homeIcone.setScaleX(1.15);
        homeIcone.setScaleY(1.15);
        homeLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 17));
        homeLabel.setTextFill(Color.rgb(255,255,255));
        homeLabel.setGraphic(homeIcone);
        homeLabel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.2), 4, 0, 0, 0));
        setGrow(homeLabel);
        setTop(homeLabel);
        setLeft(homeLabel);
        homeLabel.setTranslateX(17);
        homeLabel.setTranslateY(100);
        homeLabel.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        homeLabel.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        homeLabel.setOnMouseClicked(e-> setPage(new Home(), homeLabel));

        /* TITLE NEWS */
        FontAwesomeIconView newsIcone = new FontAwesomeIconView(FontAwesomeIcon.BELL);
        newsIcone.setFill(Color.rgb(255,255,255));
        newsIcone.setScaleX(0.95);
        newsIcone.setScaleY(0.95);
        newsLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 17));
        newsLabel.setTextFill(Color.rgb(255,255,255));
        newsLabel.setGraphic(newsIcone);
        newsLabel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.2), 4, 0, 0, 0));
        setGrow(newsLabel);
        setTop(newsLabel);
        setLeft(newsLabel);
        newsLabel.setTranslateX(17);
        newsLabel.setTranslateY(150);

        /* TITLE UPDATE */
        FontAwesomeIconView updateIcone = new FontAwesomeIconView(FontAwesomeIcon.CODE);
        updateIcone.setFill(Color.rgb(255,255,255));
        updateIcone.setScaleX(1.1);
        updateIcone.setScaleY(1.1);

        updateLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 17));
        updateLabel.setTextFill(Color.rgb(255,255,255));
        updateLabel.setGraphic(updateIcone);
        updateLabel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.2), 4, 0, 0, 0));
        setGrow(updateLabel);
        setTop(updateLabel);
        setLeft(updateLabel);
        updateLabel.setTranslateX(17);
        updateLabel.setTranslateY(200);

        /* TITLE SETTINGS */
        FontAwesomeIconView settingsIcone = new FontAwesomeIconView(FontAwesomeIcon.GEARS);
        settingsIcone.setFill(Color.rgb(255,255,255));
        settingsIcone.setScaleX(1.05);
        settingsIcone.setScaleY(1.05);
        settingsLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 17));
        settingsLabel.setTextFill(Color.rgb(255,255,255));
        settingsLabel.setGraphic(settingsIcone);
        settingsLabel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.2), 4, 0, 0, 0));
        setGrow(settingsLabel);
        setTop(settingsLabel);
        setLeft(settingsLabel);
        settingsLabel.setTranslateX(17);
        settingsLabel.setTranslateY(250);
        settingsLabel.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        settingsLabel.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        settingsLabel.setOnMouseClicked(e-> setPage(new Settings(), settingsLabel));

        /* LOG OUT BUTTON */
        FontAwesomeIconView logOutIcone = new FontAwesomeIconView(FontAwesomeIcon.SIGN_OUT);
        logOutIcone.setFill(Color.rgb(255,255,255));
        logOutIcone.setScaleX(0.9);
        logOutIcone.setScaleY(0.9);
        Button logOutBtn = new Button("Se déconnnecter");
        logOutBtn.setMinWidth(120);
        logOutBtn.setMinHeight(35);
        logOutBtn.setMaxWidth(120);
        logOutBtn.setMaxHeight(35);
        logOutBtn.setStyle("-fx-background-color: #EE494E; -fx-font-size: 14; -fx-text-fill: rgba(255,255,255,1);");
        logOutBtn.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        logOutBtn.setTextFill(Color.rgb(255,255,255));
        logOutBtn.setGraphic(logOutIcone);
        logOutBtn.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(27, 27, 27, 0.3), 7, 3, 0, 0));
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

        userLocationRectangle.setTranslateX(2);
        userLocationRectangle.setTranslateY(prevUserInfoPose.getTranslateY() - 5);
        Transition userLocationAnimation = new Transition() {
            {
                setCycleDuration(Duration.millis(600));
            }

            @Override
            protected void interpolate(double frac) {
                if (button.getTranslateY() - userLocationRectangle.getTranslateY() <= 0) {
                    if (userLocationRectangle.getScaleY() < 1.6 && (userLocationRectangle.getTranslateY() > button.getTranslateY() * 1.5)) {
                        userLocationRectangle.setScaleY(userLocationRectangle.getScaleY() + 0.05);
                    } else if (userLocationRectangle.getScaleY() > 1){
                        userLocationRectangle.setScaleY(userLocationRectangle.getScaleY() - 0.05);
                    }
                    userLocationRectangle.setTranslateY(userLocationRectangle.getTranslateY() - 5);

                } else if (button.getTranslateY() - userLocationRectangle.getTranslateY() > 5) {
                    if (userLocationRectangle.getScaleY() < 1.6 && (userLocationRectangle.getTranslateY() < button.getTranslateY() * 0.5)) {
                        userLocationRectangle.setScaleY(userLocationRectangle.getScaleY() + 0.05);
                    } else if (userLocationRectangle.getScaleY() > 1){
                        userLocationRectangle.setScaleY(userLocationRectangle.getScaleY() - 0.05);
                    }
                    userLocationRectangle.setTranslateY(userLocationRectangle.getTranslateY() + 5);

                }
            }
        };
        userLocationAnimation.setOnFinished(e-> {

            userLocationRectangle.setTranslateY(button.getTranslateY() - 5);
            userLocationRectangle.setScaleY(1);

        });
        userLocationAnimation.play();
        prevUserInfoPose = button;


        /* REGISTERY @PANE PANEL */
        pane.getChildren().addAll(avatarRectangle, avatarView,
                homeLabel, newsLabel, updateLabel, settingsLabel, logOutBtn,
                userLocationRectangle
        );

    }

    /* METHODES NAVIGATION */
    public void setPage(ContentPanel panel, Node navButton) {
        if (currentPage instanceof Home && ((Home) currentPage).isDownloading()) {
            return;
        }
        if (activeLink != null)
            activeLink.getStyleClass().remove("active");
        activeLink = navButton;
        activeLink.getStyleClass().add("active");

        this.leftBarPanel.getChildren().clear();
        leftBarNav(leftBarPanel, navButton);
        this.centerPane.getChildren().clear();
        if (panel != null) {
            this.centerPane.getChildren().add(panel.getLayout());
            currentPage = panel;
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
