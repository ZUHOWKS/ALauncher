package dev.asiluxserver.launcher.ui.panels.pages.content;

import dev.asiluxserver.launcher.Launcher;
import dev.asiluxserver.launcher.Main;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.assets.Colors;
import dev.asiluxserver.launcher.ui.assets.effects.BlurDropShadow;
import dev.asiluxserver.launcher.ui.panel.Panel;
import dev.asiluxserver.launcher.ui.panels.partials.TopBar;
import dev.asiluxserver.launcher.utils.patch.PatchLoader;
import dev.asiluxserver.launcher.utils.patch.PatchMessage;
import dev.asiluxserver.launcher.utils.patch.PatchNote;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
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
    PatchMessage patchMessage = launcher.getPatchLoader().getPatch();

    GridPane contentPane = new GridPane();
    GridPane patchPane = new GridPane();
    RowConstraints rowConstraints = new RowConstraints();

    private Double minWidth;
    private Double prefWidth;
    private Double maxWidth;
    private Double minHeight;
    private Double prefHeight;
    private Double maxHeight;

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        minWidth = panelManager.getStage().getMinWidth() - 160;
        prefWidth = panelManager.getStage().getWidth() - 160;
        maxWidth = panelManager.getStage().getMaxWidth() - 160;
        minHeight = panelManager.getStage().getMinHeight() - 100;
        prefHeight = panelManager.getStage().getHeight() - 100;
        maxHeight = panelManager.getStage().getMaxHeight() - 100;

        /* CONTENT */
        setCanTakeAllSize(contentPane);
        contentPane.setStyle("-fx-background-color: transparent;");
        contentPane.setMinWidth(480);
        contentPane.setMaxWidth(1760);

        rowConstraints.setValignment(VPos.TOP);
        rowConstraints.setMinHeight(100);
        rowConstraints.setMaxHeight(150);
        contentPane.getRowConstraints().addAll(rowConstraints, new RowConstraints());

        this.layout.getChildren().add(contentPane);

        loadPatch(contentPane);

    }

    private void loadPatch(GridPane pane) {

        ArrayList<ArrayList<Label>> patchNoteLabel = patchLoader.getPatchNoteLabel();
        int patchNoteLabelSize = patchNoteLabel.size();


        patchPane.setAlignment(Pos.CENTER);
        setGrow(patchPane);
        patchPane.setMinWidth(minWidth);
        patchPane.setMaxWidth(maxWidth);
        patchPane.setMinHeight(100);
        patchPane.setPrefHeight(100);
        patchPane.setMaxHeight(150);
        patchPane.setStyle("-fx-background-color: rgba(155,190,82,0.90);");

        GridPane patchScrollPane = new GridPane();
        setGrow(patchScrollPane);
        patchScrollPane.setAlignment(Pos.TOP_CENTER);
        patchScrollPane.setMinWidth(minWidth);
        patchScrollPane.setMaxWidth(maxWidth);
        patchScrollPane.setMinHeight(minHeight);
        patchScrollPane.setMaxHeight(maxHeight);
        patchScrollPane.setStyle("-fx-background-color: rgba(255,255,255,0.95);");

        GridPane patchVBoxPane = new GridPane();
        setGrow(patchVBoxPane);
        patchVBoxPane.setAlignment(Pos.TOP_LEFT);
        patchVBoxPane.setMinWidth(minWidth);
        patchVBoxPane.setMaxWidth(maxWidth);
        patchVBoxPane.setMinHeight(minHeight);
        patchVBoxPane.setMaxHeight(5000);

        Label titleLabel = patchLoader.getTitleLabel();
        setAlignment(titleLabel, HPos.LEFT, VPos.CENTER);
        setGrow(titleLabel);
        titleLabel.setStyle("-fx-text-size: 50; -fx-text-fill: rgb(255, 255, 255);");
        titleLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 50));
        titleLabel.setTranslateX(15);

        /* VERSION CLASSIQUE */
        Label patchLabel = patchLoader.getPatchLabel();
        setAlignment(patchLabel, HPos.LEFT, VPos.TOP);
        setGrow(titleLabel);
        patchLabel.setStyle("-fx-text-size: 17; -fx-text-fill: rgb(0, 0, 0);");
        patchLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
        patchLabel.setTranslateX(50);

        for (int i = 0; i < patchNoteLabelSize; i++) {
            ArrayList<Label> labels = patchNoteLabel.get(i);
            int labelsSize = labels.size();

            Separator separator = new Separator();
            setGrow(separator);
            setAlignment(separator, HPos.LEFT ,VPos.TOP);
            separator.setEffect(new BlurDropShadow(Color.rgb(37,37, 37), 4, 0));
            separator.setMaxHeight(50);
            separator.setMinHeight(50);
            separator.setMaxWidth(patchScrollPane.getMaxWidth() - 25);
            separator.setMinWidth(patchScrollPane.getMinWidth() - 25);
            separator.setTranslateX(49d);
            separator.setTranslateY(25);

            patchVBoxPane.add(labels.get(0),0, i);
            patchVBoxPane.add(separator,0, i);
            patchVBoxPane.add(labels.get(1),0, i);
        }

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
        setAlignment(vBox, HPos.LEFT, VPos.TOP);
        vBox.setMinWidth(patchScrollPane.getMinWidth() - 25);
        vBox.setMaxWidth(patchScrollPane.getMaxWidth() - 25);
        vBox.setMinHeight(patchScrollPane.getMinHeight() + 500);
        vBox.setMaxHeight(patchScrollPane.getMaxHeight() + 500);
        vBox.setTranslateY(10);

        //patchVBoxPane.getChildren().add(patchLabel);

        patchScrollPane.getChildren().add(scrollPane);
        scrollPane.setContent(vBox);
        vBox.getChildren().add(0, patchVBoxPane);

        patchPane.getChildren().addAll(titleLabel);

        pane.add(patchPane,0,0);
        pane.add(patchScrollPane,0,1);

    }
}
