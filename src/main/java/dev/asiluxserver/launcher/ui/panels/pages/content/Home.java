package dev.asiluxserver.launcher.ui.panels.pages.content;

import dev.asiluxserver.launcher.Main;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.assets.Colors;
import dev.asiluxserver.launcher.ui.assets.effects.BlurDropShadow;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.net.URL;

public class Home extends ContentPanel{

    GridPane contentPane = new GridPane();

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        setCanTakeAllSize(this.layout);

        setCanTakeAllSize(contentPane);
        this.layout.getChildren().add(contentPane);

        /* ACCEUIL */
        ColumnConstraints mainContraints = new ColumnConstraints();
        mainContraints.setHalignment(HPos.RIGHT);
        mainContraints.setMinWidth(800);
        mainContraints.setMaxWidth(1800);
        contentPane.getColumnConstraints().addAll(mainContraints, new ColumnConstraints());

        GridPane mainMenuPanel = new GridPane();
        setGrow(mainMenuPanel);
        setAlignment(mainMenuPanel, HPos.RIGHT, VPos.TOP);
        mainMenuPanel.setMinWidth(700);
        mainMenuPanel.setMaxWidth(700);
        mainMenuPanel.setMinHeight(700);
        mainMenuPanel.setMinHeight(700);
        addToMainMenuPanel(mainMenuPanel);

        /* NEWS ACCEUIL */
        GridPane newsMainMenuPanel = new GridPane();
        setGrow(newsMainMenuPanel);
        setAlignment(newsMainMenuPanel, HPos.RIGHT, VPos.CENTER);
        newsMainMenuPanel.setMinWidth(200);
        newsMainMenuPanel.setMaxWidth(300);
        newsMainMenuPanel.setMinHeight(320);
        newsMainMenuPanel.setMaxHeight(320);
        addNewsToMainPanel(newsMainMenuPanel);

        contentPane.add(mainMenuPanel, 0, 0);
        contentPane.add(newsMainMenuPanel, 1, 0);
    }

    private void addToMainMenuPanel(GridPane pane) {

        Image asiluxLogo = new Image("images/asilux-logo-500px.png");
        ImageView asiluxView = new ImageView(asiluxLogo);
        asiluxView.setFitWidth(500);
        asiluxView.setFitHeight(250);
        asiluxView.setEffect(new BlurDropShadow(Colors.GREY_1, 6, 0));
        setGrow(asiluxView);
        GridPane.setValignment(asiluxView, VPos.CENTER);
        GridPane.setHalignment(asiluxView, HPos.CENTER);
        asiluxView.setTranslateY(-25);

        //TODO: Barre de chargement

        Button PlayButton = new Button("INSTALLER");
        setGrow(PlayButton);
        setAlignment(PlayButton, HPos.CENTER ,VPos.CENTER);
        PlayButton.setMinWidth(220);
        PlayButton.setMinHeight(60);
        PlayButton.setMaxWidth(220);
        PlayButton.setMaxHeight(60);
        PlayButton.setStyle(
                "-fx-background-color: #91B248FF;" +
                        "-fx-font-size: 32; " +
                        "-fx-text-fill: rgba(255,255,255,1);"
        );
        PlayButton.setFont(Font.font("Arial", FontWeight.MEDIUM, 32));
        PlayButton.setTextAlignment(TextAlignment.CENTER);
        PlayButton.setEffect(new BlurDropShadow(Colors.DARK_GREY_2, 3, 0.3));
        PlayButton.setTranslateY(150);
        PlayButton.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        PlayButton.setOnMouseClicked(e-> {


            Timeline clickedAnimation = new Timeline(
                    new KeyFrame(
                            Duration.ZERO,
                            new KeyValue(PlayButton.backgroundProperty(),
                                    new Background(
                                            new BackgroundFill(
                                                    Color.valueOf("#74923AFF"),
                                                    CornerRadii.EMPTY, Insets.EMPTY
                                            )
                                    )
                            )
                    ),
                    new KeyFrame(
                            Duration.millis(700),
                            new KeyValue(
                                    PlayButton.backgroundProperty(),
                                    new Background(
                                            new BackgroundFill(
                                                    Color.valueOf("#91B248FF"),
                                                    CornerRadii.EMPTY, Insets.EMPTY
                                            )
                                    )
                            )
                    )
            );
            clickedAnimation.setOnFinished(
                    ev -> PlayButton.setStyle(
                            "-fx-background-color: #91B248FF;" +
                                    "-fx-font-size: 32;" +
                                    "-fx-text-fill: rgba(255,255,255,1);"
                    )
            );
            clickedAnimation.play();
        });
        PlayButton.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));

        pane.getChildren().addAll(asiluxView, PlayButton);
    }

    /* Tableau des News */
    private void addNewsToMainPanel(GridPane pane) {

        /* RECTANGLE BACKGROUND */
        Rectangle windowsBackground = new Rectangle();
        setGrow(windowsBackground);
        setAlignment(windowsBackground, HPos.CENTER, VPos.BOTTOM);
        windowsBackground.setWidth(220);
        windowsBackground.setHeight(340);
        windowsBackground.setFill(Colors.BLACK_2.getColor());
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
        URL resource = Main.class.getResource("/css/scroll-pane.css");
        if (resource != null) {
            scrollPane.getStylesheets().addAll(resource.toExternalForm());
        }

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
        Label NewsTitle = new Label("PATCH NOTE");
        setGrow(NewsTitle);
        setAlignment(NewsTitle, HPos.CENTER ,VPos.TOP);
        NewsTitle.setTextFill(Colors.DEFAULT_WHITE.getColor());
        NewsTitle.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
        NewsTitle.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_1, 6, 0));
        NewsTitle.setTranslateY(-35);

        /* SEPARATOR */
        Separator separator1 = new Separator();
        setGrow(separator1);
        setAlignment(separator1, HPos.CENTER ,VPos.TOP);
        separator1.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_1, 12, 0));
        separator1.setOpacity(10);
        separator1.setMaxHeight(38);
        separator1.setMinHeight(38);
        separator1.setMaxWidth(195);
        separator1.setMinWidth(195);
        separator1.setTranslateY(-25);

        Separator separator2 = new Separator();
        setGrow(separator2);
        setAlignment(separator2, HPos.CENTER ,VPos.BOTTOM);
        separator2.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_1, 12, 0));
        separator2.setOpacity(10);
        separator2.setMaxHeight(38);
        separator2.setMinHeight(38);
        separator2.setMaxWidth(195);
        separator2.setMinWidth(195);
        separator2.setTranslateY(25);

        /* LABEL NEWS */
        //TODO: METHODE STRING BUILDER | Créer une méthode afin de lire un fichier (le fichier des news),
        // qui va créer un string builder avec une longueur de chaine de caractère maximale de 18.
        Label newsLabel = new Label(
                "[ANNONCE] BETA OUVERTE\n" +
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
                "..................\n"
        );
        setGrow(newsLabel);
        setAlignment(newsLabel, HPos.CENTER ,VPos.TOP);
        newsLabel.setStyle("-fx-font-size: 14;");
        newsLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        newsLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_1, 6, 0));
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

    private void labelMainMenuPanel(GridPane pane) {

        Label playMainMenuPanel = new Label("INSTALLER");
        setGrow(playMainMenuPanel);
        setAlignment(playMainMenuPanel, HPos.LEFT ,VPos.TOP);
        playMainMenuPanel.setStyle("-fx-font-size: 16;");
        playMainMenuPanel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        playMainMenuPanel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_1, 6, 0));
        playMainMenuPanel.setTranslateY(-80);
        playMainMenuPanel.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        playMainMenuPanel.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        playMainMenuPanel.setOnMouseClicked(e-> {
            //TODO: METHODES ACTUALISATION DU MENU
        });

        Label newsMainMenuPanel = new Label("NEWS");
        setGrow(newsMainMenuPanel);
        setAlignment(newsMainMenuPanel, HPos.LEFT ,VPos.TOP);
        newsMainMenuPanel.setStyle("-fx-font-size: 16;");
        newsMainMenuPanel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        newsMainMenuPanel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_4, 6, 0));
        newsMainMenuPanel.setTranslateY(-80);
        newsMainMenuPanel.setTranslateX(65);
        newsMainMenuPanel.setOnMouseEntered(e -> this.layout.setCursor(Cursor.HAND));
        newsMainMenuPanel.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        newsMainMenuPanel.setOnMouseClicked(e-> {
            //TODO: METHODES ACTUALISATION DU MENU
        });

        pane.getChildren().addAll(playMainMenuPanel, newsMainMenuPanel);
    }
}
