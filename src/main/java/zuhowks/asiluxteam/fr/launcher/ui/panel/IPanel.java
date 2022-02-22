package zuhowks.asiluxteam.fr.launcher.ui.panel;

import javafx.scene.layout.GridPane;
import zuhowks.asiluxteam.fr.launcher.ui.PanelManager;

public interface IPanel {
    void init(PanelManager panelManager);
    GridPane getLayout();
    void onShow();
    String getName();
    String getStyleSheetPath();
}
