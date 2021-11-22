package dev.asiluxserver.launcher.ui.panels.pages;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import dev.asiluxserver.launcher.Launcher;
import dev.asiluxserver.launcher.ui.assets.Colors;
import dev.asiluxserver.launcher.ui.assets.Fonts;
import dev.asiluxserver.launcher.ui.assets.effects.BlurDropShadow;
import dev.asiluxserver.launcher.ui.compenents.CustomIcon;
import dev.asiluxserver.launcher.ui.compenents.CustomLabel;
import dev.asiluxserver.launcher.ui.panel.Panel;
import dev.asiluxserver.launcher.ui.panels.pages.tabs.Tab;
import dev.asiluxserver.launcher.ui.panels.pages.tabs.HomeTab;
import dev.asiluxserver.launcher.ui.panels.pages.tabs.SettingsTab;

import fr.theshark34.openlauncherlib.util.Saver;

import javafx.animation.Transition;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

/**
 * Un panneau d'affichage pour l'application principale, contient des onglets de navigation pour
 * la page d'accueil, les nouveautés, les mises à jour et les paramètres.
 * Contient une grille d'affichage (GridPanel) à gauche pour les onglets et une deuxième
 * grille d'affichage pour le contenu d'un onglet au centre.
 */
public class MainPage extends Panel {

    ColumnConstraints menuPainConstraint = new ColumnConstraints();
    GridPane centerPane = new GridPane();
    GridPane leftBarPanel = new GridPane();

    // Les onglets de navigation, utilisent les classes custom `CustomLabel` et `CustomIcon`
    CustomLabel homeLabel = new CustomLabel(
            " Accueil", Fonts.ARIAL.getFont(20), Colors.DEFAULT_WHITE,
            new CustomIcon(FontAwesomeIcon.HOME, Colors.DEFAULT_WHITE, 1.5, 1.5)
    );
    CustomLabel newsLabel = new CustomLabel(
            " Nouveauté", Fonts.ARIAL.getFont(20), Colors.DEFAULT_WHITE,
            new CustomIcon(FontAwesomeIcon.BELL, Colors.DEFAULT_WHITE, 1.3, 1.3)
    );
    CustomLabel updateLabel = new CustomLabel(
            " Mise à jour", Fonts.ARIAL.getFont(20), Colors.DEFAULT_WHITE,
            new CustomIcon(FontAwesomeIcon.CODE, Colors.DEFAULT_WHITE, 1.4, 1.4)
    );
    CustomLabel settingsLabel = new CustomLabel(
            " Paramètres", Fonts.ARIAL.getFont(20), Colors.DEFAULT_WHITE,
            new CustomIcon(FontAwesomeIcon.GEARS, Colors.DEFAULT_WHITE, 1.4, 1.4)
    );
    Rectangle userLocationRectangle = new Rectangle(10, 30);

    Saver saver = Launcher.getInstance().getSaver();
    Node prevUserInfoPose = homeLabel;
    Node activeLink = null;
    Tab currentPage = null;

    // Les onglets de navigation
    HomeTab homeTab = new HomeTab();
    SettingsTab settingsTab = new SettingsTab();

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getStyleSheetPath() {
        return super.getStyleSheetPath();
    }

    public MainPage() {
        super();

        /* BASE PANEL */
        menuPainConstraint.setHalignment(HPos.LEFT);
        menuPainConstraint.setMinWidth(160);
        menuPainConstraint.setMaxWidth(160);
        this.layout.getColumnConstraints().addAll(menuPainConstraint, new ColumnConstraints());

        setGrow(leftBarPanel);
        setAlignment(leftBarPanel, HPos.LEFT, VPos.CENTER);
        leftBarPanel.setMinWidth(160);
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
        setPage(homeTab, homeLabel); //Ouvre l'onglet Home
    }

    /*
     * BAR DE NAVIGATION + GRID PANE DE L'ONGLET
     */

    /* Barre de navigation */
    private void leftBarNav(GridPane pane, Node button) {

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
        String avatarUrl = "https://minotar.net/avatar/" + Launcher.getInstance().getAuthInfo().getUuid() + ".png";
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
                    setCycleDuration(Duration.millis(500));
                }

                double n = 0.0005;

                @Override
                protected void interpolate(double frac) {
                    if (n <= 0.05 && frac < 400) {
                        n = n + 0.001;
                    } else {
                        n = n - 0.0005;
                    }

                    if (avatarView.getScaleX() < 1.17 && avatarView.getScaleY() < 1.17) {
                        avatarView.setScaleX(avatarView.getScaleX() + 0.001 + n);
                        avatarView.setScaleY(avatarView.getScaleY() + 0.001 + n);
                    } else {
                        n = 0.0001;
                        avatarView.setScaleX(1.17);
                        avatarView.setScaleY(1.17);
                        avatarView.setTranslateY(15d);
                    }
                }
            };

            if (avatarView.getScaleX() < 1.17d && avatarView.getScaleY() < 1.17d) {
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

                double n = 0.0005;

                @Override
                protected void interpolate(double frac) {
                    if (n <= 0.05 && frac < 400) {
                        n = n + 0.001;
                    } else {
                        n = n - 0.0005;
                    }

                    if (avatarView.getScaleX() > 1 && avatarView.getScaleY() > 1) {
                        avatarView.setScaleX(avatarView.getScaleX() - 0.001 - n);
                        avatarView.setScaleY(avatarView.getScaleY() - 0.001 - n);
                    } else {
                        n = 0.0001;
                        avatarView.setScaleX(1);
                        avatarView.setScaleY(1);
                        avatarView.setTranslateY(15d);
                    }
                }
            };

            if (avatarView.getScaleX() > 1 && avatarView.getScaleY() > 1) {
                avatarDownScale.play();
            }
        });

        /* TITLE HOME */
        homeLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
        setGrow(homeLabel);
        setTop(homeLabel);
        setLeft(homeLabel);
        homeLabel.setTranslateX(18);
        homeLabel.setTranslateY(100);
        homeLabel.setOnMouseEntered(e-> {
            this.layout.setCursor(Cursor.HAND);
            homeLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_1, 5, 0));
            Transition transitionScale = scaleUp(homeLabel, Duration.millis(500), 1.075, 0.005, 0.05, 0);
            Transition ttX = translateX(homeLabel, Duration.millis(500), 28, 0.4);
            ttX.play();
            transitionScale.play();
        });
        homeLabel.setOnMouseExited(e-> {
            this.layout.setCursor(Cursor.DEFAULT);
            homeLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
            Transition transition = scaleDown(homeLabel, Duration.millis(650), 1, 0.0025);
            Transition ttX = translateX(homeLabel, Duration.millis(650), 18, 0.25);
            ttX.play();
            transition.play();

        });
        homeLabel.setOnMouseClicked(e-> setPage(homeTab, homeLabel));

        /* TITLE NEWS */
        newsLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
        setGrow(newsLabel);
        setTop(newsLabel);
        setLeft(newsLabel);
        newsLabel.setTranslateX(18);
        newsLabel.setTranslateY(150);
        newsLabel.setOnMouseEntered(e-> {
            this.layout.setCursor(Cursor.HAND);
            newsLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_1, 5, 0));
            Transition transitionScale = scaleUp(newsLabel, Duration.millis(500), 1.075, 0.005, 0.05, 0);
            Transition ttX = translateX(newsLabel, Duration.millis(500), 28, 0.4);
            ttX.play();
            transitionScale.play();
        });
        newsLabel.setOnMouseExited(e-> {
            this.layout.setCursor(Cursor.DEFAULT);
            newsLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
            Transition transition = scaleDown(newsLabel, Duration.millis(650), 1, 0.005);
            Transition ttX = translateX(newsLabel, Duration.millis(650), 18, 0.25);
            ttX.play();
            transition.play();

        });

        /* TITLE UPDATE */
        updateLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        updateLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
        setGrow(updateLabel);
        setTop(updateLabel);
        setLeft(updateLabel);
        updateLabel.setTranslateX(18);
        updateLabel.setTranslateY(200);
        updateLabel.setOnMouseEntered(e-> {
            this.layout.setCursor(Cursor.HAND);
            updateLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_1, 5, 0));
            Transition transitionScale = scaleUp(
                    updateLabel, Duration.millis(500), 1.075, 0.005, 0.05, 0
            );
            Transition ttX = translateX(updateLabel, Duration.millis(500), 28, 0.4);
            ttX.play();
            transitionScale.play();
        });
        updateLabel.setOnMouseExited(e-> {
            this.layout.setCursor(Cursor.DEFAULT);
            updateLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
            Transition transition = scaleDown(updateLabel, Duration.millis(650), 1, 0.005);
            Transition ttX = translateX(updateLabel, Duration.millis(650), 18, 0.25);
            ttX.play();
            transition.play();

        });

        /* TITLE SETTINGS */
        settingsLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
        setGrow(settingsLabel);
        setTop(settingsLabel);
        setLeft(settingsLabel);
        settingsLabel.setTranslateX(18);
        settingsLabel.setTranslateY(250);
        settingsLabel.setOnMouseEntered(e-> {
            this.layout.setCursor(Cursor.HAND);
            settingsLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_1, 5, 0));
            Transition transitionScale = scaleUp(
                    settingsLabel, Duration.millis(500), 1.075, 0.005, 0.05, 0
            );
            Transition ttX = translateX(settingsLabel, Duration.millis(500), 28, 0.4);
            ttX.play();
            transitionScale.play();
        });
        settingsLabel.setOnMouseExited(e-> {
            this.layout.setCursor(Cursor.DEFAULT);
            settingsLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
            Transition transition = scaleDown(settingsLabel, Duration.millis(650), 1, 0.005);
            Transition ttX = translateX(settingsLabel, Duration.millis(650), 18, 0.25);
            ttX.play();
            transition.play();

        });
        settingsLabel.setOnMouseClicked(e-> setPage(settingsTab, settingsLabel));

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
        logOutBtn.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 14));
        logOutBtn.setTextFill(Colors.DEFAULT_WHITE.getColor());
        logOutBtn.setGraphic(logOutIcon);
        logOutBtn.setEffect(new BlurDropShadow(Colors.BLACK_2, 7, 3));
        logOutBtn.setDisable(false);
        setGrow(logOutBtn);
        setBottom(logOutBtn);
        setCenterH(logOutBtn);
        logOutBtn.setTranslateY(-25d);
        logOutBtn.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        logOutBtn.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        logOutBtn.setOnMouseClicked(e-> {
            if (currentPage instanceof HomeTab && ((HomeTab) currentPage).isDownloading()) {
                return;
            }
            saver.remove("accessToken");
            saver.remove("clientToken");
            saver.remove("msAccessToken");
            saver.remove("msRefreshToken");
            saver.save();
            Launcher.getInstance().setAuthInfo(null);
            panelManager.showPanel(panelManager.getLauncher().getLoginPage());
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
                setCycleDuration(Duration.millis(600));
            }

            @Override
            protected void interpolate(double frac) {
                if (button.getTranslateY() - userLocationRectangle.getTranslateY() <= 0) {
                    if (
                            userLocationRectangle.getScaleY() < 1.6 &&
                                    userLocationRectangle.getTranslateY() > button.getTranslateY() * 1.5) {
                        userLocationRectangle.setScaleY(userLocationRectangle.getScaleY() + 0.05);
                    } else if (userLocationRectangle.getScaleY() > 1){
                        userLocationRectangle.setScaleY(userLocationRectangle.getScaleY() - 0.05);
                    }
                    userLocationRectangle.setTranslateY(userLocationRectangle.getTranslateY() - 5);

                } else if (button.getTranslateY() - userLocationRectangle.getTranslateY() > 5) {
                    if (userLocationRectangle.getScaleY() < 1.6 &&
                            userLocationRectangle.getTranslateY() < button.getTranslateY() * 0.5) {
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


        /* REGISTRY @PANE PANEL */
        pane.getChildren().addAll(avatarRectangle, avatarView,
                homeLabel, newsLabel, updateLabel, settingsLabel, logOutBtn,
                userLocationRectangle
        );

    }

    /* METHODES NAVIGATION */
    public void setPage(Tab panel, Node navButton) {
        if (currentPage instanceof HomeTab && ((HomeTab) currentPage).isDownloading()) {
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
            panel.onShow();
        }
    }

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
        transition.setOnFinished(e-> {
            element.setScaleX(MinSize);
            element.setScaleY(MinSize);
        });
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
        transition.setOnFinished(e-> element.setTranslateX(EndX));
        return transition;
    }
}
