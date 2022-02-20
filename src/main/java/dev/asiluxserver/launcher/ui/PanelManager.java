package dev.asiluxserver.launcher.ui;

import com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;

import dev.asiluxserver.launcher.AUpdater;
import dev.asiluxserver.launcher.ui.panel.IPanel;
import dev.asiluxserver.launcher.ui.panels.partials.TopBar;

import fr.flowarg.flowcompat.Platform;

import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PanelManager {
    private final AUpdater launcher;
    private final Stage stage;
    private GridPane layout;
    private final GridPane contentPane = new GridPane();

    public PanelManager(AUpdater launcher, Stage stage) {
        this.launcher = launcher;
        this.stage = stage;
    }

    public void init() {
        this.stage.setTitle("Asilux");
        this.stage.setMinWidth(320);
        this.stage.setMinHeight(480);
        this.stage.setWidth(320);
        this.stage.setHeight(480);
        this.stage.setMaxWidth(320);
        this.stage.setMaxHeight(480);
        this.stage.centerOnScreen();
        this.stage.getIcons().add(new Image("images/asilux-icon.png"));

        this.layout = new GridPane();

        this.stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(this.layout);
        this.stage.setScene(scene);

        this.layout.add(this.contentPane, 0,1);
        this.layout.setStyle("-fx-background-color: rgba(153, 189, 95, 1);");
        GridPane.setVgrow(this.contentPane, Priority.ALWAYS);
        GridPane.setHgrow(this.contentPane, Priority.ALWAYS);

        this.stage.show();
    }

    public void showPanel(IPanel panel) {
        this.contentPane.getChildren().clear();
        this.contentPane.getChildren().add(panel.getLayout());
        if (panel.getStyleSheetPath() != null) {
            this.stage.getScene().getStylesheets().clear();
            this.stage.getScene().getStylesheets().add(panel.getStyleSheetPath());
        }
        panel.init(this);
        panel.onShow();
    }

    public Stage getStage() {
        return stage;
    }

    public AUpdater getLauncher() {
        return launcher;
    }
}
