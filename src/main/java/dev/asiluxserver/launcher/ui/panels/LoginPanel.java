package dev.asiluxserver.launcher.ui.panels;

import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.panel.Panel;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;

import java.util.concurrent.atomic.AtomicBoolean;

public class LoginPanel extends Panel {

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        GridPane loginPanel = new GridPane();
        GridPane mainPanel = new GridPane();
        GridPane bottomPanel = new GridPane();

        AtomicBoolean connectWithMojang = new AtomicBoolean(false);

        loginPanel.setMaxWidth(400);
        loginPanel.setMinWidth(400);
        loginPanel.setMaxHeight(580);
        loginPanel.setMinHeight(580);
        setGrow(loginPanel);
        GridPane.setValignment(loginPanel, VPos.CENTER);
        GridPane.setHalignment(loginPanel, HPos.CENTER);

        RowConstraints bottomConstraints = new RowConstraints();
        bottomConstraints.setValignment(VPos.BOTTOM);
        bottomConstraints.setMaxHeight(55d);
        loginPanel.getRowConstraints().addAll(new RowConstraints(), bottomConstraints);
        loginPanel.add(mainPanel, 0 , 0);
        loginPanel.add(bottomPanel, 0 , 0);

        setGrow(mainPanel);
        setGrow(bottomPanel);

        mainPanel.setStyle("-fx-background-color: #181818;");
        bottomPanel.setStyle("-fx-background-color: #181818; -fx-opacity: 50%");
        Label noAccount = new Label("Vous n'avez pas encore de compte ?");
        Label registerHere = new Label("S'inscrire ici");

        setGrow(noAccount);
        GridPane.setValignment(loginPanel, VPos.CENTER);
        GridPane.setHalignment(loginPanel, HPos.CENTER);


        this.layout.getChildren().add(loginPanel);
    }
}
