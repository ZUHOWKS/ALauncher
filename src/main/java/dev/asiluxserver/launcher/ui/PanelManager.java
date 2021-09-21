package dev.asiluxserver.launcher.ui;

import dev.asiluxserver.launcher.AsiluxLauncher;
import dev.asiluxserver.launcher.ui.panel.IPanel;
import dev.asiluxserver.launcher.ui.panels.include.TopPanel;
import fr.arinonia.arilibfx.AriLibFX;
import fr.arinonia.arilibfx.ui.utils.ResizeHelper;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PanelManager {

    private final AsiluxLauncher asiluxLauncher;
    private final Stage stage;
    private GridPane layout;
    private TopPanel topPanel = new TopPanel();
    private GridPane centerPanel = new GridPane();

    public PanelManager(AsiluxLauncher asiluxLauncher, Stage stage) {
        this.asiluxLauncher = asiluxLauncher;
        this.stage = stage;
    }

    public void init() {
        this.stage.setTitle("Asilux");
        this.stage.setMinWidth(1280);
        this.stage.setWidth(1280);
        this.stage.setMinHeight(720);
        this.stage.setHeight(720);
        this.stage.initStyle(StageStyle.UNDECORATED);
        this.stage.centerOnScreen();
        this.stage.show();

        this.layout = new GridPane();
        this.layout.setStyle(AriLibFX.setResponsiveBackground("https://fr-minecraft.net/upload/wallpapers/images/fr-minecraft_wallpaper_4SIF.png"));
        this.stage.setScene(new Scene(this.layout));

        RowConstraints topPanelConstraints = new RowConstraints();
        topPanelConstraints.setValignment(VPos.TOP);
        topPanelConstraints.setMinHeight(25);
        topPanelConstraints.setMaxHeight(25);
        this.layout.getRowConstraints().addAll(topPanelConstraints, new RowConstraints());
        this.layout.add(this.topPanel.getLayout(), 0, 0);
        this.topPanel.init(this);

        this.layout.add(this.centerPanel, 0, 1);
        GridPane.setVgrow(this.centerPanel, Priority.ALWAYS);
        GridPane.setHgrow(this.centerPanel, Priority.ALWAYS);
        ResizeHelper.addResizeListener(this.stage);
    }

    public void showPanel(IPanel panel) {
        this.centerPanel.getChildren().clear();
        this.centerPanel.getChildren().add(panel.getLayout());
        panel.init(this);
        panel.onShow();
    }

    public Stage getStage() {
        return stage;
    }
    public AsiluxLauncher getAsiluxLauncher() { return asiluxLauncher; }
    public TopPanel getTopPanel() { return topPanel; }
}
