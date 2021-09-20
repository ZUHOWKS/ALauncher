package dev.asiluxserver.launcher;

import dev.asiluxserver.launcher.ui.PanelManager;
import javafx.stage.Stage;

public class AsiluxLauncher {

    private PanelManager panelManager;

    public void init(Stage stage) {
        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();
    }
}
