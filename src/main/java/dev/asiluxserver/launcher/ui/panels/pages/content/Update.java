package dev.asiluxserver.launcher.ui.panels.pages.content;

import dev.asiluxserver.launcher.Launcher;
import dev.asiluxserver.launcher.Main;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.assets.Colors;
import dev.asiluxserver.launcher.utils.patch.PatchLoader;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.w3c.dom.css.Rect;

import java.net.URL;
import java.util.ArrayList;

public class Update extends ContentPanel{

    Launcher launcher = Launcher.getInstance();

    private final Saver saver = launcher.getSaver();
    PatchLoader patchLoader = launcher.getPatchLoader();

    GridPane contentPane = new GridPane();
    GridPane patchPane = new GridPane();
    RowConstraints rowConstraints = new RowConstraints();

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        /* CONTENT */
        setCanTakeAllSize(contentPane);
        contentPane.setStyle("-fx-background-color: transparent;");
        contentPane.setMinWidth(480);
        contentPane.setMaxWidth(1760);

        rowConstraints.setValignment(VPos.TOP);
        rowConstraints.setMinHeight(150);
        rowConstraints.setMaxHeight(200);
        contentPane.getRowConstraints().addAll(rowConstraints, new RowConstraints());

        this.layout.getChildren().add(contentPane);

        loadPatch(contentPane);

    }

    private void loadPatch(GridPane pane) {

        patchPane.setAlignment(Pos.CENTER);
        setGrow(patchPane);
        patchPane.setMinWidth(800);
        patchPane.setPrefWidth(1200);
        patchPane.setMaxWidth(1600);
        patchPane.setMinHeight(150);
        patchPane.setPrefHeight(150);
        patchPane.setMaxHeight(200);
        patchPane.setStyle("-fx-background-color: rgba(0,0,0,1);");

        GridPane patchScrollPane = new GridPane();
        setGrow(patchScrollPane);
        patchScrollPane.setAlignment(Pos.TOP_CENTER);
        patchScrollPane.setMinWidth(800);
        patchScrollPane.setMaxWidth(1400);
        patchScrollPane.setMinHeight(635);
        patchScrollPane.setMaxHeight(1000);
        patchScrollPane.setStyle("-fx-background-color: rgba(255,255,255,0.85);");

        GridPane patchVBoxPane = new GridPane();
        setGrow(patchVBoxPane);
        patchVBoxPane.setAlignment(Pos.TOP_CENTER);
        patchVBoxPane.setMinWidth(800);
        patchVBoxPane.setMaxWidth(1400);
        patchVBoxPane.setMinHeight(635);
        patchVBoxPane.setMaxHeight(5000);
        patchVBoxPane.setTranslateY(100);

        Label titleLabel = patchLoader.getTitleLabel();
        setAlignment(titleLabel, HPos.LEFT, VPos.TOP);
        setGrow(titleLabel);
        titleLabel.setStyle("-fx-text-size: 45; -fx-text-fill: rgb(255, 255, 255);");
        titleLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 60));

        Label patchLabel = patchLoader.getPatchLabel();
        setAlignment(patchLabel, HPos.CENTER, VPos.TOP);
        setGrow(titleLabel);
        patchLabel.setStyle("-fx-text-size: 17; -fx-text-fill: rgb(0, 0, 0);");
        patchLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));

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
        setAlignment(vBox, HPos.CENTER, VPos.TOP);
        vBox.setMinWidth(patchScrollPane.getMinWidth());
        vBox.setMaxWidth(patchScrollPane.getMaxWidth() + 75);
        vBox.setMinHeight(patchScrollPane.getMinHeight() + 500);
        vBox.setMaxHeight(patchScrollPane.getMaxHeight() + 500);
        vBox.setTranslateY(10);

        patchVBoxPane.getChildren().add(patchLabel);

        patchScrollPane.getChildren().add(scrollPane);
        scrollPane.setContent(vBox);
        vBox.getChildren().add(0, patchVBoxPane);

        patchPane.getChildren().addAll(titleLabel);

        pane.add(patchPane,0,0);
        pane.add(patchScrollPane,0,1);

    }
}
