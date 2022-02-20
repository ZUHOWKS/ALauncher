package dev.asiluxserver.launcher.ui.panels.pages;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import dev.asiluxserver.launcher.AUpdater;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.assets.Colors;
import dev.asiluxserver.launcher.ui.assets.Fonts;
import dev.asiluxserver.launcher.ui.assets.effects.BlurDropShadow;
import dev.asiluxserver.launcher.ui.panel.Panel;
import dev.asiluxserver.launcher.ui.panels.pages.content.ContentPanel;
import dev.asiluxserver.launcher.ui.panels.pages.content.Home;
import dev.asiluxserver.launcher.ui.panels.pages.content.Settings;

import dev.asiluxserver.launcher.ui.panels.pages.content.Update;
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
import javafx.util.Duration;

public class App extends Panel {

    private static App instance;

    ColumnConstraints menuPainConstraint = new ColumnConstraints();
    GridPane centerPane = new GridPane();
    GridPane leftBarPanel = new GridPane();

    Label homeLabel = new Label(" Accueil");
    Label newsLabel = new Label(" Nouveauté");
    Label updateLabel = new Label(" Mise à jour");
    Label settingsLabel = new Label(" Paramètres");
    Rectangle userLocationRectangle = new Rectangle(10, 30);

    Saver saver = AUpdater.getInstance().getSaver();
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
        menuPainConstraint.setHalignment(HPos.LEFT);
        menuPainConstraint.setMinWidth(160);
        menuPainConstraint.setMaxWidth(160);
        this.layout.getColumnConstraints().addAll(menuPainConstraint, new ColumnConstraints());

        setGrow(leftBarPanel);
        setAlignment(leftBarPanel, HPos.LEFT, VPos.CENTER);
        leftBarPanel.setMinWidth(160);
        leftBarPanel.setMaxHeight(1060);
        leftBarPanel.setMinHeight(710);
        leftBarPanel.setMaxWidth(160);
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

        Font SELAWK_FONT = Font.loadFont(Fonts.SELAWK_LIGHT.get(), 20);

        Double TIME_TRANSITION_UP = 150d;
        Double TIME_TRANSITION_DOWN = 300d;

        /* USER AVATAR BACKGROUND */
        Rectangle avatarRectangle = new Rectangle(0, 0, 64 ,64);
        avatarRectangle.setFill(Color.valueOf("#91B848FF"));
        avatarRectangle.setEffect(new BlurDropShadow(Colors.DARK_GREY_4, 3, 0.3));
        avatarRectangle.setArcWidth(12);
        avatarRectangle.setArcHeight(12);
        setTop(avatarRectangle);
        setCanTakeAllSize(avatarRectangle);
        setCenterH(avatarRectangle);
        avatarRectangle.setTranslateY(8d);

        /* USER AVATAR */
        String avatarUrl = "https://minotar.net/avatar/" + AUpdater.getInstance().getAuthInfos().getUuid() + ".png";
        ImageView avatarView = new ImageView();
        avatarView.setImage(new Image(avatarUrl));
        avatarView.setPreserveRatio(true);
        avatarView.setFitHeight(50d);
        avatarView.setStyle("");
        avatarView.setEffect(new BlurDropShadow(Colors.DARK_GREY_3, 7, 0.5));
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
                    setCycleDuration(Duration.millis(250));
                }

                double n = 0.0005;

                @Override
                protected void interpolate(double frac) {
                    if (n <= 0.05 && frac < 400) {
                        n = n + 0.0005;
                    } else {
                        n = n - 0.0005;
                    }

                    if (avatarView.getScaleX() < 1.17 && avatarView.getScaleY() < 1.17) {
                        avatarView.setScaleX(avatarView.getScaleX() + 0.0005 + n);
                        avatarView.setScaleY(avatarView.getScaleY() + 0.0005 + n);
                    } else {
                        n = 0.0001;
                        avatarView.setScaleX(1.17);
                        avatarView.setScaleY(1.17);
                        avatarView.setTranslateY(15d);
                    }
                }
            };
            avatarUpScale.play();
        });

        avatarView.setOnMouseExited(e-> {

            //Change User Cursor
            this.layout.setCursor(Cursor.DEFAULT);
            /* DOWN AVATAR SCALE */
            Transition avatarDownScale = new Transition() {
                {
                    setCycleDuration(Duration.millis(250));
                }

                double n = 0.0005;

                @Override
                protected void interpolate(double frac) {
                    if (n <= 0.05 && frac < 400) {
                        n = n + 0.0005;
                    } else {
                        n = n - 0.0005;
                    }

                    if (avatarView.getScaleX() > 1 && avatarView.getScaleY() > 1) {
                        avatarView.setScaleX(avatarView.getScaleX() - 0.0005 - n);
                        avatarView.setScaleY(avatarView.getScaleY() - 0.0005 - n);
                    } else {
                        n = 0.0001;
                        avatarView.setScaleX(1);
                        avatarView.setScaleY(1);
                        avatarView.setTranslateY(15d);
                    }
                }
            };
            avatarDownScale.play();
        });

        /* TITLE HOME */
        FontAwesomeIconView homeIcon = new FontAwesomeIconView(FontAwesomeIcon.HOME);
        homeIcon.setFill(Colors.DEFAULT_WHITE.getColor());
        homeIcon.setScaleX(1.5);
        homeIcon.setScaleY(1.5);
        homeLabel.setFont(SELAWK_FONT);
        homeLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        homeLabel.setGraphic(homeIcon);
        homeLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
        setGrow(homeLabel);
        setTop(homeLabel);
        setLeft(homeLabel);
        homeLabel.setTranslateX(18);
        homeLabel.setTranslateY(100);
        if (activeLink == homeLabel) {
            homeLabel.setScaleX(1.125);
            homeLabel.setScaleY(1.075);
            homeLabel.setTranslateX(28);
        }
        homeLabel.setOnMouseEntered(e-> {
            this.layout.setCursor(Cursor.HAND);
            homeLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_1, 5, 0));
            Transition transitionScale = scaleUp(homeLabel, Duration.millis(TIME_TRANSITION_UP), 1.075, 0.005, 0.05, 0);
            Transition ttX = translateX(homeLabel, Duration.millis(TIME_TRANSITION_UP), 28, 0.4);
            ttX.play();
            transitionScale.play();
        });
        homeLabel.setOnMouseExited(e-> {
            this.layout.setCursor(Cursor.DEFAULT);
            homeLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
            Transition transition = scaleDown(homeLabel, Duration.millis(TIME_TRANSITION_DOWN), 1, 0.0025);
            Transition ttX = translateX(homeLabel, Duration.millis(TIME_TRANSITION_DOWN), 18, 0.25);
            ttX.play();
            transition.play();

        });
        homeLabel.setOnMouseClicked(e-> setPage(new Home(), homeLabel));

        /* TITLE NEWS */
        FontAwesomeIconView newsIcon = new FontAwesomeIconView(FontAwesomeIcon.BELL);
        newsIcon.setFill(Colors.DEFAULT_WHITE.getColor());
        newsIcon.setScaleX(1.3);
        newsIcon.setScaleY(1.3);
        newsLabel.setFont(SELAWK_FONT);
        newsLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        newsLabel.setGraphic(newsIcon);
        newsLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
        setGrow(newsLabel);
        setTop(newsLabel);
        setLeft(newsLabel);
        newsLabel.setTranslateX(18);
        newsLabel.setTranslateY(150);
        if (activeLink == newsLabel) {
            newsLabel.setScaleX(1.125);
            newsLabel.setScaleY(1.075);
            newsLabel.setTranslateX(28);
        }
        newsLabel.setOnMouseEntered(e-> {
            this.layout.setCursor(Cursor.HAND);
            newsLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_1, 5, 0));
            Transition transitionScale = scaleUp(newsLabel, Duration.millis(TIME_TRANSITION_UP), 1.075, 0.005, 0.05, 0);
            Transition ttX = translateX(newsLabel, Duration.millis(TIME_TRANSITION_UP), 28, 0.4);
            ttX.play();
            transitionScale.play();
        });
        newsLabel.setOnMouseExited(e-> {
            this.layout.setCursor(Cursor.DEFAULT);
            newsLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
            Transition transition = scaleDown(newsLabel, Duration.millis(TIME_TRANSITION_DOWN), 1, 0.005);
            Transition ttX = translateX(newsLabel, Duration.millis(TIME_TRANSITION_DOWN), 18, 0.25);
            ttX.play();
            transition.play();

        });

        /* TITLE UPDATE */
        FontAwesomeIconView updateIcon = new FontAwesomeIconView(FontAwesomeIcon.CODE);
        updateIcon.setFill(Colors.DEFAULT_WHITE.getColor());
        updateIcon.setScaleX(1.4);
        updateIcon.setScaleY(1.4);
        updateLabel.setFont(SELAWK_FONT);
        updateLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        updateLabel.setGraphic(updateIcon);
        updateLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
        setGrow(updateLabel);
        setTop(updateLabel);
        setLeft(updateLabel);
        updateLabel.setTranslateX(18);
        updateLabel.setTranslateY(200);
        if (activeLink == updateLabel) {
            updateLabel.setScaleX(1.125);
            updateLabel.setScaleY(1.075);
            updateLabel.setTranslateX(28);
        }
        updateLabel.setOnMouseEntered(e-> {
            this.layout.setCursor(Cursor.HAND);
            updateLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_1, 5, 0));
            Transition transitionScale = scaleUp(updateLabel, Duration.millis(TIME_TRANSITION_UP), 1.075, 0.005, 0.05, 0);
            Transition ttX = translateX(updateLabel, Duration.millis(TIME_TRANSITION_UP), 28, 0.4);
            ttX.play();
            transitionScale.play();
        });
        updateLabel.setOnMouseExited(e-> {
            this.layout.setCursor(Cursor.DEFAULT);
            updateLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
            Transition transition = scaleDown(updateLabel, Duration.millis(TIME_TRANSITION_DOWN), 1, 0.005);
            Transition ttX = translateX(updateLabel, Duration.millis(TIME_TRANSITION_DOWN), 18, 0.25);
            ttX.play();
            transition.play();

        });
        updateLabel.setOnMouseClicked(e-> setPage(new Update(), updateLabel));

        /* TITLE SETTINGS */
        FontAwesomeIconView settingsIcon = new FontAwesomeIconView(FontAwesomeIcon.GEARS);
        settingsIcon.setFill(Colors.DEFAULT_WHITE.getColor());
        settingsIcon.setScaleX(1.4);
        settingsIcon.setScaleY(1.4);
        settingsLabel.setFont(SELAWK_FONT);
        settingsLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        settingsLabel.setGraphic(settingsIcon);
        settingsLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
        setGrow(settingsLabel);
        setTop(settingsLabel);
        setLeft(settingsLabel);
        settingsLabel.setTranslateX(18);
        settingsLabel.setTranslateY(250);
        if (activeLink == settingsLabel) {
            settingsLabel.setScaleX(1.125);
            settingsLabel.setScaleY(1.075);
            settingsLabel.setTranslateX(28);
        }
        settingsLabel.setOnMouseEntered(e-> {
            this.layout.setCursor(Cursor.HAND);
            settingsLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_1, 5, 0));
            Transition transitionScale = scaleUp(settingsLabel, Duration.millis(TIME_TRANSITION_UP), 1.075, 0.005, 0.05, 0);
            Transition ttX = translateX(settingsLabel, Duration.millis(TIME_TRANSITION_UP), 28, 0.4);
            ttX.play();
            transitionScale.play();
        });
        settingsLabel.setOnMouseExited(e-> {
            this.layout.setCursor(Cursor.DEFAULT);
            settingsLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
            Transition transition = scaleDown(settingsLabel, Duration.millis(TIME_TRANSITION_DOWN), 1, 0.005);
            Transition ttX = translateX(settingsLabel, Duration.millis(TIME_TRANSITION_DOWN), 18, 0.25);
            ttX.play();
            transition.play();

        });
        settingsLabel.setOnMouseClicked(e-> setPage(new Settings(), settingsLabel));

        /* LOG OUT BUTTON */
        FontAwesomeIconView logOutIcon = new FontAwesomeIconView(FontAwesomeIcon.SIGN_OUT);
        logOutIcon.setFill(Colors.DEFAULT_WHITE.getColor());
        logOutIcon.setScaleX(1.25);
        logOutIcon.setScaleY(1.25);
        Button logOutBtn = new Button("Se déconnecter");
        logOutBtn.setMinWidth(120);
        logOutBtn.setMinHeight(35);
        logOutBtn.setMaxWidth(120);
        logOutBtn.setMaxHeight(35);
        logOutBtn.setStyle(
                "-fx-background-color: #EE494E;" +
                        "-fx-font-size: 14;" +
                        "-fx-text-fill: rgba(255,255,255,1);"
        );
        logOutBtn.setFont(Font.loadFont(Fonts.SELAWK.get(), 14));
        logOutBtn.setTextFill(Colors.DEFAULT_WHITE.getColor());
        logOutBtn.setGraphic(logOutIcon);
        logOutBtn.setEffect(new BlurDropShadow(Colors.BLACK_2, 7, 3));
        logOutBtn.setDisable(false);
        setGrow(logOutBtn);
        setBottom(logOutBtn);
        setCenterH(logOutBtn);
        logOutBtn.setTranslateY(-45d);
        logOutBtn.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        logOutBtn.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        logOutBtn.setOnMouseClicked(e-> {
            if (currentPage instanceof Home && ((Home) currentPage).isDownloading()) {
                return;
            }
            saver.remove("accessToken");
            saver.remove("clientToken");
            saver.remove("msAccessToken");
            saver.remove("msRefreshToken");
            saver.save();
            AUpdater.getInstance().setAuthInfos(null);
            this.panelManager.showPanel(new Login());
        });

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
                setCycleDuration(Duration.millis(700));
            }

            @Override
            protected void interpolate(double frac) {
                if (button.getTranslateY() - userLocationRectangle.getTranslateY() <= 0) {
                    if (userLocationRectangle.getTranslateY() > button.getTranslateY() * 1.5) {
                        if (userLocationRectangle.getScaleY() < 1.6)
                            userLocationRectangle.setScaleY(userLocationRectangle.getScaleY() + 0.05);
                        userLocationRectangle.setTranslateY(userLocationRectangle.getTranslateY() - 5);

                    } else if (userLocationRectangle.getScaleY() > 1){
                        userLocationRectangle.setScaleY(userLocationRectangle.getScaleY() - 0.05);
                    }
                    userLocationRectangle.setTranslateY(userLocationRectangle.getTranslateY() - 2.5);

                } else if (button.getTranslateY() - userLocationRectangle.getTranslateY() > 2.5) {
                    if (userLocationRectangle.getTranslateY() < button.getTranslateY() * 0.5) {
                        if (userLocationRectangle.getScaleY() < 1.6)
                            userLocationRectangle.setScaleY(userLocationRectangle.getScaleY() + 0.05);
                        userLocationRectangle.setTranslateY(userLocationRectangle.getTranslateY() + 5);

                    } else if (userLocationRectangle.getScaleY() > 1){
                        userLocationRectangle.setScaleY(userLocationRectangle.getScaleY() - 0.05);
                    }
                    userLocationRectangle.setTranslateY(userLocationRectangle.getTranslateY() + 2.5);

                }
            }
        };
        userLocationAnimation.setOnFinished(e-> {
            userLocationRectangle.setScaleY(1);

        });
        userLocationAnimation.play();
        prevUserInfoPose = button;


        /* REGISTRY @PANE PANEL */
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

    /* ANIMATION HELPER */ //TODO: Create a class AnimationHelper in utils.

    private Transition scaleUp(
            Node element, Duration duration,
            double MaxSize, double speed, double XSuppl, double YSuppl) {
        return new Transition() {
            {
                setCycleDuration(duration);
            }

            @Override
            protected void interpolate(double frac) {

                if (element.getScaleX() + XSuppl < MaxSize )
                    element.setScaleX(element.getScaleX() + speed + XSuppl * 0.25);

                if (element.getScaleY() + YSuppl < MaxSize)
                    element.setScaleY(element.getScaleY() + speed + YSuppl * 0.25);
            }
        };
    }

    private Transition scaleDown(Node element, Duration duration, double MinSize, double speed) {
        Transition transition = new Transition() {
            {
                setCycleDuration(duration);
            }

            @Override
            protected void interpolate(double frac) {

                if (element.getScaleX() > MinSize && element.getScaleY() > MinSize) {
                    element.setScaleX(element.getScaleX() - speed);
                    element.setScaleY(element.getScaleY() - speed);
                } else {
                    element.setScaleX(MinSize);
                    element.setScaleY(MinSize);
                }
            }
        };
        /*
        transition.setOnFinished(e-> {
            element.setScaleX(MinSize);
            element.setScaleY(MinSize);
        });
        */
        return transition;
    }

    private Transition translateX(Node element, Duration duration, double EndX, double speed) {
        Transition transition = new Transition() {
            {
                setCycleDuration(duration);
            }

            @Override
            protected void interpolate(double frac) {
                if (EndX < element.getTranslateX()) {
                    element.setTranslateX(element.getTranslateX() - speed);
                } else {
                    element.setTranslateX(element.getTranslateX() + speed);
                }
            }
        };
        //transition.setOnFinished(e-> element.setTranslateX(EndX));
        return transition;
    }
}
