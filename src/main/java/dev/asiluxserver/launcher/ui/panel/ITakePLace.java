package dev.asiluxserver.launcher.ui.panel;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import static javafx.scene.layout.GridPane.*;

public interface ITakePLace {
    default void setCanTakeAllSize(Node node) {
        setHgrow(node, Priority.ALWAYS);
        setVgrow(node, Priority.ALWAYS);
    }

    default void setCanTakeAllWidth(Node... nodes) {
        for (Node n : nodes) {
            setHgrow(n, Priority.ALWAYS);
        }
    }
}
