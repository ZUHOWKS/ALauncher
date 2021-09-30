package dev.asiluxserver.launcher.ui.panels;

import dev.asiluxserver.launcher.Main;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.panel.Panel;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.Objects;

public class HomePanel extends Panel {

    private GridPane centerPane = new GridPane();

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);



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
        GridPane.setHalignment(this.centerPane, HPos.LEFT);

        ScrollPane scrollPane = new ScrollPane();
        setGrow(scrollPane);
        scrollPane.getStylesheets().addAll(Main.class.getResource("/css/scroll-pane.css").toExternalForm());

        VBox vBox = new VBox();
        setGrow(vBox);
        vBox.setMinWidth(900);
        vBox.setMaxWidth(900);
        vBox.setMinHeight(100);
        vBox.setAlignment(Pos.CENTER);
        vBox.setTranslateX(30);

        GridPane topPanel = new GridPane();
        setGrow(topPanel);
        setAlignment(topPanel, HPos.LEFT, VPos.TOP);
        vBox.setMinWidth(300);
        vBox.setMaxWidth(300);
        vBox.setMinHeight(500);
        vBox.setMaxHeight(1000);
        addToTopPanel(topPanel);

        this.centerPane.getChildren().add(scrollPane);
        scrollPane.setContent(vBox);
        vBox.getChildren().add(0, topPanel);
    }

    private void addToTopPanel(GridPane pane) {
        Image asiluxLogo = new Image(Objects.requireNonNull(Main.class.getResource("/asilux-logo-500px.png")).toExternalForm());
        ImageView asiluxView = new ImageView(asiluxLogo);
        asiluxView.setFitWidth(250);
        asiluxView.setFitHeight(125);
        setGrow(asiluxView);
        GridPane.setValignment(asiluxView, VPos.TOP);
        GridPane.setHalignment(asiluxView, HPos.LEFT);
        asiluxView.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(210, 210, 210, 0.2), 4, 0, 0, 0));
        asiluxView.setTranslateY(-200);
        asiluxView.setTranslateX(-50);

        Separator separator1 = new Separator();
        setGrow(separator1);
        setAlignment(separator1, HPos.LEFT ,VPos.TOP);
        separator1.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 12, 0, 0, 0));
        separator1.setOpacity(10);
        separator1.setMaxHeight(38);
        separator1.setMinHeight(38);
        separator1.setMaxWidth(300);
        separator1.setMinWidth(300);
        separator1.setTranslateY(-100);
        separator1.setTranslateX(-5);

        Rectangle shadowS1 = new Rectangle();
        setGrow(shadowS1);
        setAlignment(shadowS1, HPos.LEFT ,VPos.TOP);
        shadowS1.setStyle("-fx-background-color: rgba(37, 37, 37, 0);");
        shadowS1.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(255, 255, 255, 0.3), 5, 0, 0, 0));
        shadowS1.setHeight(75);
        shadowS1.setWidth(300);
        shadowS1.setTranslateY(-100);
        shadowS1.setTranslateX(-5);


        pane.getChildren().addAll(asiluxView, separator1);
    }

}
