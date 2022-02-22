package zuhowks.asiluxteam.fr.launcher.ui.panel;

import javafx.scene.Node;
import javafx.scene.layout.Priority;

import static javafx.scene.layout.GridPane.setHgrow;
import static javafx.scene.layout.GridPane.setVgrow;

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
