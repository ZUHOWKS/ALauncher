package dev.asiluxserver.launcher.ui;

import dev.asiluxserver.launcher.AsiluxLauncher;
import javafx.stage.Stage;

public class PanelManager {

    private final AsiluxLauncher asiluxLauncher;
    private final Stage stage;

    public PanelManager(AsiluxLauncher asiluxLauncher, Stage stage) {
        this.asiluxLauncher = asiluxLauncher;
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }
}
