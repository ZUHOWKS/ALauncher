package dev.asiluxserver.launcher.ui.panels.pages;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import dev.asiluxserver.launcher.Launcher;
import dev.asiluxserver.launcher.Main;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.panel.Panel;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.util.Objects;

public class App extends Panel {

    GridPane centerPane = new GridPane();
    Saver saver = Launcher.getInstance().getSaver();

    Node activeLink = null;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        /* BASE PANEL */
        ColumnConstraints menuPainContraint = new ColumnConstraints();
        menuPainContraint.setHalignment(HPos.LEFT);
        menuPainContraint.setMinWidth(100);
        menuPainContraint.setMaxWidth(100);
        this.layout.getColumnConstraints().addAll(menuPainContraint, new ColumnConstraints());

        GridPane leftBarPanel = new GridPane();
        setGrow(leftBarPanel);
        setAlignment(leftBarPanel, HPos.LEFT, VPos.CENTER);
        leftBarPanel.setMinWidth(100);
        leftBarPanel.setMaxWidth(100);
        leftBarPanel.setStyle("-fx-background-color: rgba(24,24,24,1);");

        this.layout.add(leftBarPanel, 0, 0);
        this.layout.add(this.centerPane, 1, 0);
        setGrow(this.centerPane);
        setAlignment(this.centerPane, HPos.RIGHT, VPos.CENTER);



        String avatarUrl = "https://minotar.net/avatar/MHF_Steve.png"; //+ Launcher.getInstance().getAuthProfile().getId() + ".png";
        ImageView avatarView = new ImageView();
        Image avatarImg = new Image(avatarUrl);
        avatarView.setImage(avatarImg);
        avatarView.setPreserveRatio(true);
        avatarView.setFitHeight(50d);
        avatarView.setStyle("");
        avatarView.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(37, 37, 37, 0.3), 7, 0.5, 0, 0));
        setTop(avatarView);
        setCanTakeAllSize(avatarView);
        setCenterH(avatarView);
        avatarView.setTranslateY(15d);

        Transition animation = new Transition() {
            {
                setCycleDuration(Duration.millis(500));
            }
            @Override
            protected void interpolate(double frac) {
                avatarView.setFitHeight( (double) (avatarView.getFitHeight() + 0.1));
                avatarView.setTranslateX(avatarView.getX() - 0.1);
            }
        };
        avatarView.setOnMouseEntered(e-> animation.play());

        Rectangle avatarRectangle = new Rectangle(0, 0, 64 ,64);
        avatarRectangle.setFill(Color.valueOf("#91B848FF"));
        avatarRectangle.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(37, 37, 37, 0.2), 3, 0.3, 0, 0));
        avatarRectangle.setArcWidth(12);
        avatarRectangle.setArcHeight(12);
        setTop(avatarRectangle);
        setCanTakeAllSize(avatarRectangle);
        setCenterH(avatarRectangle);
        avatarRectangle.setTranslateY(8d);

        leftBarPanel.getChildren().add(avatarRectangle);
        leftBarPanel.getChildren().add(avatarView);

        /* ACCEUIL */
        ColumnConstraints mainContraints = new ColumnConstraints();
        mainContraints.setHalignment(HPos.RIGHT);
        mainContraints.setMinWidth(800);
        mainContraints.setMaxWidth(1800);
        centerPane.getColumnConstraints().addAll(mainContraints, new ColumnConstraints());

        GridPane mainMenuPanel = new GridPane();
        setGrow(mainMenuPanel);
        setAlignment(mainMenuPanel, HPos.RIGHT, VPos.TOP);
        mainMenuPanel.setMinWidth(700);
        mainMenuPanel.setMaxWidth(700);
        mainMenuPanel.setMinHeight(700);
        mainMenuPanel.setMinHeight(700);
        addTomainMenuPanel(mainMenuPanel);

        /* NEWS ACCEUIL */
        GridPane actuMainMenuPanel = new GridPane();
        setGrow(actuMainMenuPanel);
        setAlignment(actuMainMenuPanel, HPos.RIGHT, VPos.CENTER);
        actuMainMenuPanel.setMinWidth(200);
        actuMainMenuPanel.setMaxWidth(300);
        actuMainMenuPanel.setMinHeight(320);
        actuMainMenuPanel.setMaxHeight(320);
        addNewsToMainPanel(actuMainMenuPanel);

        centerPane.add(mainMenuPanel, 0, 0);
        centerPane.add(actuMainMenuPanel, 1, 0);
    }
    private void addTomainMenuPanel(GridPane pane) {

        Image asiluxLogo = new Image("images/asilux-logo-500px.png");
        ImageView asiluxView = new ImageView(asiluxLogo);
        asiluxView.setFitWidth(500);
        asiluxView.setFitHeight(250);
        setGrow(asiluxView);
        GridPane.setValignment(asiluxView, VPos.CENTER);
        GridPane.setHalignment(asiluxView, HPos.CENTER);
        asiluxView.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(210, 210, 210, 0.2), 4, 0, 0, 0));
        asiluxView.setTranslateY(-25);

        //TODO: Barre de chargement

        Button PlayButton = new Button("INSTALLER");
        setGrow(PlayButton);
        setAlignment(PlayButton, HPos.CENTER ,VPos.CENTER);
        PlayButton.setMinWidth(220);
        PlayButton.setMinHeight(60);
        PlayButton.setMaxWidth(220);
        PlayButton.setMaxHeight(60);
        PlayButton.setStyle("-fx-background-color: #91B248FF; -fx-font-size: 32;  -fx-text-fill: rgba(255,255,255,1);");
        PlayButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 32));
        PlayButton.setTextAlignment(TextAlignment.CENTER);
        PlayButton.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(37, 37, 37, 0.2), 3, 0.3, 0, 0));
        PlayButton.setTranslateY(150);
        PlayButton.setOnMouseEntered(e-> {
            this.layout.setCursor(Cursor.HAND);
        });
        PlayButton.setOnMouseClicked(e-> {


            Timeline clickedAnimation = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(PlayButton.backgroundProperty(), new Background(new BackgroundFill(Color.valueOf("#74923AFF"), CornerRadii.EMPTY, Insets.EMPTY)))),
                    new KeyFrame(Duration.millis(700), new KeyValue(PlayButton.backgroundProperty(), new Background(new BackgroundFill(Color.valueOf("#91B248FF"), CornerRadii.EMPTY, Insets.EMPTY)))));
            clickedAnimation.setOnFinished(ev -> {
                PlayButton.setStyle("-fx-background-color: #91B248FF; -fx-font-size: 32; -fx-text-fill: rgba(255,255,255,1);");
            });
            clickedAnimation.play();
        });
        PlayButton.setOnMouseExited(e-> {
            this.layout.setCursor(Cursor.DEFAULT);
        });


        Button SettingsButtom = new Button();
        setGrow(SettingsButtom);
        setAlignment(SettingsButtom, HPos.CENTER ,VPos.CENTER);
        FontAwesomeIconView settingsIcone = new FontAwesomeIconView(FontAwesomeIcon.GEARS);
        settingsIcone.setSize("25px");
        settingsIcone.setFill(Color.rgb(255,255,255));
        SettingsButtom.setMinWidth(35);
        SettingsButtom.setMinHeight(35);
        SettingsButtom.setMaxWidth(35);
        SettingsButtom.setMaxHeight(35);
        SettingsButtom.setGraphic(settingsIcone);
        SettingsButtom.setStyle("-fx-background-color: rgba(0,0,0,0); -fx-border-color: rgba(255,255,255); -fx-borer-radius: 2px");
        //SettingsButtom.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(37, 37, 37, 0.2), 3, 0.3, 0, 0));
        SettingsButtom.setTranslateX(150);
        SettingsButtom.setTranslateY(150);
        SettingsButtom.setOnMouseEntered(e-> {
            this.layout.setCursor(Cursor.HAND);
        });
        SettingsButtom.setOnMouseClicked(e-> {

            Timeline clickedAnimation = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(settingsIcone.fillProperty(), Color.rgb(225,225,225))),
                    new KeyFrame(Duration.millis(250), new KeyValue(settingsIcone.fillProperty(), Color.rgb(235,235,235))),
                    new KeyFrame(Duration.millis(500), new KeyValue(settingsIcone.fillProperty(), Color.rgb(255,255,255))));
            clickedAnimation.setOnFinished(ev -> settingsIcone.setFill(Color.rgb(255,255,255)));
            clickedAnimation.play();
        });
        SettingsButtom.setOnMouseExited(e-> {
            this.layout.setCursor(Cursor.DEFAULT);
        });


        pane.getChildren().addAll(asiluxView, PlayButton, SettingsButtom);
    }
    /*
     * ELEMENTS DE LA PAGE D'ACCUEILLE
     */

    /* Barre de navigation */
    private void leftBarNav(GridPane pane) {

    }

    /* Tableau des News */
    private void addNewsToMainPanel(GridPane pane) {

        /* RECTANGLE BACKGROUND */
        Rectangle windowsBackground = new Rectangle();
        setGrow(windowsBackground);
        setAlignment(windowsBackground, HPos.CENTER, VPos.BOTTOM);
        windowsBackground.setWidth(220);
        windowsBackground.setHeight(340);
        windowsBackground.setFill(Color.rgb(27,27,27,0.95));
        windowsBackground.setArcHeight(25);
        windowsBackground.setArcWidth(25);
        windowsBackground.setSmooth(true);

        /* PANEL NEWS MAIN */
        GridPane windowsPanel = new GridPane();
        setGrow(windowsPanel);
        setAlignment(windowsPanel, HPos.CENTER, VPos.TOP);
        windowsPanel.setMinWidth(200);
        windowsPanel.setMaxWidth(220);
        windowsPanel.setMinHeight(275);
        windowsPanel.setMaxHeight(275);
        windowsPanel.setTranslateY(45);

        /* PANEL NEWS LABEL */
        GridPane newsPanel = new GridPane();
        setGrow(newsPanel);
        setAlignment(newsPanel, HPos.LEFT, VPos.TOP);
        newsPanel.setMinWidth(200);
        newsPanel.setMaxWidth(220);
        newsPanel.setMinHeight(250);
        newsPanel.setMaxHeight(1000);
        newsPanel.setTranslateY(45);

        /* SCROLL PANE STYLE */
        ScrollPane scrollPane = new ScrollPane();
        setGrow(scrollPane);
        setAlignment(scrollPane, HPos.CENTER, VPos.CENTER);
        scrollPane.getStylesheets().addAll(Main.class.getResource("/css/scroll-pane.css").toExternalForm());

        /* SCROLL BAR */
        VBox vBox = new VBox();
        setGrow(vBox);
        vBox.setMinWidth(200);
        vBox.setMaxWidth(300);
        vBox.setMinHeight(250);
        vBox.setMaxHeight(newsPanel.getMaxHeight());
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setTranslateY(10);

        /* TITLE */
        Label NewsTitle = new Label("NEWS!");
        setGrow(NewsTitle);
        setAlignment(NewsTitle, HPos.CENTER ,VPos.TOP);
        NewsTitle.setTextFill(Color.rgb(255,255,255));
        NewsTitle.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
        NewsTitle.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 6, 0, 0, 0));
        NewsTitle.setTranslateY(-35);

        /* SEPARATOR */
        Separator separator1 = new Separator();
        setGrow(separator1);
        setAlignment(separator1, HPos.CENTER ,VPos.TOP);
        separator1.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 12, 0, 0, 0));
        separator1.setOpacity(10);
        separator1.setMaxHeight(38);
        separator1.setMinHeight(38);
        separator1.setMaxWidth(195);
        separator1.setMinWidth(195);
        separator1.setTranslateY(-25);

        Separator separator2 = new Separator();
        setGrow(separator2);
        setAlignment(separator2, HPos.CENTER ,VPos.BOTTOM);
        separator2.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 12, 0, 0, 0));
        separator2.setOpacity(10);
        separator2.setMaxHeight(38);
        separator2.setMinHeight(38);
        separator2.setMaxWidth(195);
        separator2.setMinWidth(195);
        separator2.setTranslateY(25);

        /* LABEL NEWS */
        //TODO: METHODE STRING BUILDER | Créer une méthode afin de lire un fichier (le fichier des news), qui va créer un string builder avec une longueur de chaine de caractère maximale de 18.
        Label newsLabel = new Label("[ANNONCE] BETA OUVERTE\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n");
        setGrow(newsLabel);
        setAlignment(newsLabel, HPos.CENTER ,VPos.TOP);
        newsLabel.setStyle("-fx-font-size: 14;");
        newsLabel.setTextFill(Color.rgb(255,255,255));
        newsLabel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 6, 0, 0, 0));
        newsLabel.setTranslateX(2);
        newsLabel.setTranslateY(-45);

        /* REGISTRY MAIN PANEL */
        windowsPanel.getChildren().addAll(NewsTitle, separator1, separator2);
        newsPanel.getChildren().add(newsLabel);

        windowsPanel.getChildren().add(scrollPane);
        scrollPane.setContent(vBox);
        vBox.getChildren().add(0, newsPanel);

        /* REGISTRY @PANE PANEL */
        pane.getChildren().addAll(windowsBackground, windowsPanel);


    }

    private void labelmainMenuPanel(GridPane pane) {

        Label playMainMenuPanel = new Label("INSTALLER");
        setGrow(playMainMenuPanel);
        setAlignment(playMainMenuPanel, HPos.LEFT ,VPos.TOP);
        playMainMenuPanel.setStyle("-fx-font-size: 16;");
        playMainMenuPanel.setTextFill(Color.rgb(255,255,255));
        playMainMenuPanel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 6, 0, 0, 0));;
        playMainMenuPanel.setTranslateY(-80);
        playMainMenuPanel.setOnMouseEntered(e-> {

            this.layout.setCursor(Cursor.HAND);

        });
        playMainMenuPanel.setOnMouseExited(e-> {

            this.layout.setCursor(Cursor.DEFAULT);

        });
        playMainMenuPanel.setOnMouseClicked(e-> {
            //TODO: METHODES ACTUALISATION DU MENU
        });

        Label actuMainMenuPanel = new Label("NEWS");
        setGrow(actuMainMenuPanel);
        setAlignment(actuMainMenuPanel, HPos.LEFT ,VPos.TOP);
        actuMainMenuPanel.setStyle("-fx-font-size: 16;");
        actuMainMenuPanel.setTextFill(Color.rgb(225,225,225));
        actuMainMenuPanel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(225, 225, 225, 0.1), 6, 0, 0, 0));;
        actuMainMenuPanel.setTranslateY(-80);
        actuMainMenuPanel.setTranslateX(65);
        actuMainMenuPanel.setOnMouseEntered(e -> {

            this.layout.setCursor(Cursor.HAND);

        });
        actuMainMenuPanel.setOnMouseExited(e-> {

            this.layout.setCursor(Cursor.DEFAULT);

        });
        actuMainMenuPanel.setOnMouseClicked(e-> {
            //TODO: METHODES ACTUALISATION DU MENU
        });

        pane.getChildren().addAll(playMainMenuPanel, actuMainMenuPanel);
    }

}
