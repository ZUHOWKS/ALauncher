package dev.asiluxserver.launcher.ui.panel;

import dev.asiluxserver.launcher.ui.PanelManager;

import javafx.scene.layout.GridPane;

public interface IPanel {
    void init(PanelManager panelManager);
    GridPane getLayout();
    void onShow();
    String getName();
    String getStyleSheetPath();
}
