package dev.asiluxserver.launcher.ui.panels;

import dev.asiluxserver.launcher.Main;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.panel.Panel;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class HomePanel extends Panel {

    private GridPane centerPane = new GridPane();
    private AtomicInteger mainMenuPanelPage = new AtomicInteger(0);

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


        /* ACCEUIL */
        ColumnConstraints mainContraints = new ColumnConstraints();
        mainContraints.setHalignment(HPos.RIGHT);
        mainContraints.setMinWidth(800);
        mainContraints.setMaxWidth(1800);
        centerPane.getColumnConstraints().addAll(mainContraints, new ColumnConstraints());

        GridPane mainMenuPanel = new GridPane();
        setGrow(mainMenuPanel);
        setAlignment(mainMenuPanel, HPos.RIGHT, VPos.CENTER);
        mainMenuPanel.setMinWidth(600);
        mainMenuPanel.setMaxWidth(600);
        mainMenuPanel.setMinHeight(600);
        mainMenuPanel.setMinHeight(600);
        addTomainMenuPanel(mainMenuPanel);

        /* NEWS ACCEUIL */
        GridPane actuMainMenuPanel = new GridPane();
        setGrow(actuMainMenuPanel);
        setAlignment(actuMainMenuPanel, HPos.RIGHT, VPos.CENTER);
        actuMainMenuPanel.setMinWidth(200);
        actuMainMenuPanel.setMaxWidth(300);
        actuMainMenuPanel.setMinHeight(320);
        actuMainMenuPanel.setMaxHeight(320);
        addToactuMainPanel(actuMainMenuPanel);

        centerPane.add(mainMenuPanel, 0, 0);
        centerPane.add(actuMainMenuPanel, 1, 0);
    }

    private void addTomainMenuPanel(GridPane pane) {

        Image asiluxLogo = new Image(Objects.requireNonNull(Main.class.getResource("/asilux-logo-500px.png")).toExternalForm());
        ImageView asiluxView = new ImageView(asiluxLogo);
        asiluxView.setFitWidth(500);
        asiluxView.setFitHeight(250);
        setGrow(asiluxView);
        GridPane.setValignment(asiluxView, VPos.CENTER);
        GridPane.setHalignment(asiluxView, HPos.CENTER);
        asiluxView.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(210, 210, 210, 0.2), 4, 0, 0, 0));

        Separator separator1 = new Separator();
        setGrow(separator1);
        setAlignment(separator1, HPos.CENTER ,VPos.CENTER);
        separator1.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 12, 0, 0, 0));
        separator1.setOpacity(10);
        separator1.setMaxHeight(38);
        separator1.setMinHeight(38);
        separator1.setMaxWidth(400);
        separator1.setMinWidth(400);
        separator1.setTranslateY(115);

        Button PlayButton = new Button("JOUER");
        setGrow(PlayButton);
        setAlignment(PlayButton, HPos.CENTER ,VPos.CENTER);
        PlayButton.setMinWidth(220);
        PlayButton.setMinHeight(60);
        PlayButton.setMaxWidth(240);
        PlayButton.setMaxHeight(80);
        PlayButton.setStyle("-fx-background-color: #91B848FF; -fx-font-size: 42; -fx-border-radius: 2px; -fx-text-fill: rgba(255,255,255,1)");
        PlayButton.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 42));
        PlayButton.setTextAlignment(TextAlignment.CENTER);
        PlayButton.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(37, 37, 37, 0.2), 3, 0.3, 0, 0));
        PlayButton.setTranslateY(175);


        pane.getChildren().addAll(asiluxView, separator1, PlayButton);
    }

    private void addToactuMainPanel(GridPane pane) {


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

        /* ScrollPane Style */
        ScrollPane scrollPane = new ScrollPane();
        setGrow(scrollPane);
        setAlignment(scrollPane, HPos.CENTER, VPos.CENTER);
        scrollPane.getStylesheets().addAll(Main.class.getResource("/css/scroll-pane.css").toExternalForm());

        /* Scroll Bar */
        VBox vBox = new VBox();
        setGrow(vBox);
        vBox.setMinWidth(200);
        vBox.setMaxWidth(300);
        vBox.setMinHeight(250);
        vBox.setMaxHeight(newsPanel.getMaxHeight());
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setTranslateY(10);

        Label NewsTitle = new Label("NEWS!");
        setGrow(NewsTitle);
        setAlignment(NewsTitle, HPos.CENTER ,VPos.TOP);
        NewsTitle.setTextFill(Color.rgb(255,255,255));
        NewsTitle.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
        NewsTitle.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 6, 0, 0, 0));
        NewsTitle.setTranslateY(-35);

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

        windowsPanel.getChildren().addAll(NewsTitle, separator1, separator2);
        newsPanel.getChildren().add(newsLabel);

        windowsPanel.getChildren().add(scrollPane);
        scrollPane.setContent(vBox);
        vBox.getChildren().add(0, newsPanel);

        pane.getChildren().addAll(windowsBackground, windowsPanel);


    }

    private void labelmainMenuPanel(GridPane pane) {

        Label playMainMenuPanel = new Label("JOUER");
        setGrow(playMainMenuPanel);
        setAlignment(playMainMenuPanel, HPos.LEFT ,VPos.TOP);
        playMainMenuPanel.setStyle("-fx-font-size: 16;");
        playMainMenuPanel.setTextFill(Color.rgb(255,255,255));
        playMainMenuPanel.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 6, 0, 0, 0));;
        playMainMenuPanel.setTranslateY(-80);
        playMainMenuPanel.setOnMouseEntered(e-> {

            this.layout.setCursor(Cursor.HAND);
            if (mainMenuPanelPage.get() != 0) {
                playMainMenuPanel.setTextFill(Color.rgb(225, 255, 245));
            }

        });
        playMainMenuPanel.setOnMouseExited(e-> {

            this.layout.setCursor(Cursor.DEFAULT);
            if (mainMenuPanelPage.get() != 0) {
                playMainMenuPanel.setTextFill(Color.rgb(225, 225, 225));
            }

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
            if (mainMenuPanelPage.get() != 1) {
                actuMainMenuPanel.setTextFill(Color.rgb(225, 255, 245));
            }

        });
        actuMainMenuPanel.setOnMouseExited(e-> {

            this.layout.setCursor(Cursor.DEFAULT);
            if (mainMenuPanelPage.get() != 1) {
                actuMainMenuPanel.setTextFill(Color.rgb(225, 225, 225));
            }

        });
        actuMainMenuPanel.setOnMouseClicked(e-> {
            //TODO: METHODES ACTUALISATION DU MENU
        });

        pane.getChildren().addAll(playMainMenuPanel, actuMainMenuPanel);
    }

}
