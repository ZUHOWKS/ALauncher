package dev.asiluxserver.launcher.ui.panels.pages.content;

import dev.asiluxserver.launcher.Launcher;
import dev.asiluxserver.launcher.Main;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.utils.patch.PatchLoader;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;

public class Update extends ContentPanel{

    Launcher launcher = Launcher.getInstance();

    private final Saver saver = launcher.getSaver();
    PatchLoader patchLoader = launcher.getPatchLoader();

    GridPane contentPane = new GridPane();
    GridPane patchPane = new GridPane();

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
        this.layout.getChildren().add(contentPane);

    }

    private void loadPatch(GridPane pane) {

        ArrayList<Label> labels = patchLoader.getLabel();
        Label patchTitleLabel = labels.get(0);
        patchTitleLabel.setAlignment(Pos.TOP_LEFT);
        setCanTakeAllSize(patchTitleLabel);
        patchTitleLabel.setMinWidth(350);
        patchTitleLabel.setMaxWidth(1630);
        patchTitleLabel.setMinHeight(600);
        patchTitleLabel.setMaxHeight(1800);
        patchTitleLabel.setStyle("-fx-text-size: 46; -fx-text-fill: rgb(0, 0, 0);");

        setAlignment(patchPane);
        patchPane.setStyle("-fx-background-color: rgba(255, 255, 255, 0.95);");
        patchPane.setMinWidth(420);
        patchPane.setMaxWidth(1700);
        patchPane.setMinHeight(660);
        patchPane.setMaxHeight(1860);

        GridPane patchScrollPane = new GridPane();
        setCanTakeAllSize(patchScrollPane);
        patchScrollPane.setAlignment(Pos.BOTTOM_CENTER);
        patchScrollPane.setMinWidth(400);
        patchScrollPane.setMaxWidth(1680);
        patchScrollPane.setMinHeight(640);
        patchScrollPane.setMaxHeight(1840);


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
        vBox.setMinWidth(pane.getMinWidth() - 40);
        vBox.setMaxWidth(pane.getMinWidth() - 40);
        vBox.setMinHeight(pane.getMinHeight() - 100);
        vBox.setMaxHeight(pane.getMaxHeight() - 100);
        vBox.setAlignment(Pos.CENTER);
        vBox.setTranslateY(10);

        for (int i=1; i < labels.size(); i++) {

            Label patchNote = labels.get(i);
            setAlignment(patchNote);
            setCanTakeAllSize(patchNote);
            patchNote.setMinWidth(350);
            patchNote.setMaxWidth(1630);
            patchNote.setMinHeight(600);
            patchNote.setMaxHeight(1800);
            patchNote.setStyle("-fx-text-size: 27; -fx-text-fill: rgb(0, 0, 0);");

            patchScrollPane.getChildren().add(patchNote);

        }

        patchPane.getChildren().addAll(patchTitleLabel, scrollPane);
        scrollPane.setContent(vBox);
        vBox.getChildren().add(0, patchScrollPane);

        pane.getChildren().addAll(patchPane);

    }
}
