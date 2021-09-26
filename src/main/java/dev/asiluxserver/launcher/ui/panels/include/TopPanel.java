package dev.asiluxserver.launcher.ui.panels.include;

import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.panel.Panel;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.paint.Material;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class TopPanel extends Panel {
    private GridPane topBar;

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        this.topBar = this.layout;
        this.layout.setStyle("-fx-background-color: rgb(31,35,37);");
        GridPane topBarButtom = new GridPane();
        this.layout.getChildren().add(topBarButtom);

        Label title = new Label(); //TODO: Mettre un titre ou une image
        this.layout.getChildren().add(title);
        title.setFont(Font.font("Consolas", FontWeight.THIN, FontPosture.REGULAR, 21.0));
        title.setStyle("-fx-text-fill: white;");
        GridPane.setHalignment(title, HPos.CENTER);

        topBarButtom.setMinWidth(100.0d);
        topBarButtom.setMaxWidth(100.0d);
        setGrow(topBarButtom);
        GridPane.setHalignment(topBarButtom, HPos.RIGHT);
        MaterialDesignIconView close = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_CLOSE);
        MaterialDesignIconView fullscreen = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_MAXIMIZE);
        MaterialDesignIconView hide = new MaterialDesignIconView(MaterialDesignIcon.WINDOW_MINIMIZE);
        GridPane.setVgrow(close, Priority.ALWAYS);
        GridPane.setVgrow(fullscreen, Priority.ALWAYS);
        GridPane.setVgrow(hide, Priority.ALWAYS);

        // Bouton Close //
        close.setFill(Color.rgb(223, 0, 73));
        close.setOpacity(0.7F);
        close.setSize("18.0px");
        close.setOnMouseEntered(e-> close.setOpacity(1.0f));
        close.setOnMouseExited(e-> close.setOpacity(0.7f));
        close.setOnMouseClicked(e-> System.exit(0));
        close.setTranslateX(70.0d);

        // Bouton Fullscreen //
        fullscreen.setFill(Color.rgb(245, 255, 239));
        fullscreen.setOpacity(0.7F);
        fullscreen.setSize("16.0px");
        fullscreen.setOnMouseEntered(e-> fullscreen.setOpacity(1.0f));
        fullscreen.setOnMouseExited(e-> fullscreen.setOpacity(0.7f));
        fullscreen.setOnMouseClicked(e-> this.panelManager.getStage().setMaximized(!this.panelManager.getStage().isMaximized()));
        fullscreen.setTranslateX(50.0d);

        // Bouton Hide //
        hide.setFill(Color.rgb(95, 188, 85));
        hide.setOpacity(0.7F);
        hide.setSize("18.0px");
        hide.setOnMouseEntered(e-> hide.setOpacity(1.0f));
        hide.setOnMouseExited(e-> hide.setOpacity(0.7f));
        hide.setOnMouseClicked(e-> this.panelManager.getStage().setIconified(true));
        hide.setTranslateX(30.0d);

        topBarButtom.getChildren().addAll(close, fullscreen, hide);

    }
}
