package dev.asiluxserver.launcher.ui.panels;

import dev.asiluxserver.launcher.Main;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.panel.Panel;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class HomePanel extends Panel {

    private GridPane centerPane = new GridPane();

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);



        ColumnConstraints menuPainContraint = new ColumnConstraints();
        menuPainContraint.setHalignment(HPos.LEFT);
        menuPainContraint.setMinWidth(300);
        menuPainContraint.setMaxWidth(300);
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

        ScrollPane scrollPane = new ScrollPane();
        setGrow(scrollPane);
        scrollPane.getStylesheets().addAll(Main.class.getResource("/css/scroll-pane.css").toExternalForm());

        VBox vBox = new VBox();
        setGrow(vBox);
        vBox.setMinWidth(900);
        vBox.setMaxWidth(900);
        vBox.setMinHeight(200);
        vBox.setAlignment(Pos.CENTER);
        vBox.setTranslateX(30);

        GridPane topPanel = new GridPane();
        setGrow(topPanel);
        topPanel.setValignment(topPanel, VPos.TOP);
        vBox.setMinWidth(880);
        vBox.setMaxWidth(880);
        vBox.setMinHeight(330);
        vBox.setMaxHeight(330);
        addToTopPanel(topPanel);

        this.centerPane.getChildren().add(scrollPane);
        scrollPane.setContent(vBox);
        vBox.getChildren().add(0, topPanel);
    }

    private void addToTopPanel(GridPane pane) {
        Label asiluxTitle = new Label("Asilux");
        setGrow(asiluxTitle);
        GridPane.setValignment(asiluxTitle, VPos.TOP);
        asiluxTitle.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 41));
        asiluxTitle.setTextFill(Color.rgb(225,225,225));
        asiluxTitle.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.rgb(210, 210, 210, 0.2), 4, 0, 0, 0));

        pane.getChildren().add(asiluxTitle);
    }

}
