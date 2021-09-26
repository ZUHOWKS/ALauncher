package dev.asiluxserver.launcher.ui.panels;

import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.panel.Panel;
import javafx.geometry.HPos;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class HomePanel extends Panel {

    private GridPane centerPane = new GridPane();

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        ColumnConstraints menuPainContraint = new ColumnConstraints();
        menuPainContraint.setHalignment(HPos.LEFT);
        menuPainContraint.setMinWidth(300);
        menuPainContraint.setMaxWidth(300);
        this.layout.getColumnConstraints().addAll(menuPainContraint, new ColumnConstraints());

        GridPane leftBarPanel = new GridPane();
        setGrow(leftBarPanel);
        setGrow(leftBarPanel);

        this.layout.add(leftBarPanel, 0 , 0);
        this.layout.add(centerPane, 1, 0);
    }

}
