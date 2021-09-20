package dev.asiluxserver.launcher.ui.panel;

import dev.asiluxserver.launcher.ui.PanelManager;
import javafx.animation.FadeTransition;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

public class Panel implements IPanel {

    protected GridPane layout = new GridPane();
    protected PanelManager panelManager;

    @Override
    public void init(PanelManager panelManager) {
        this.panelManager = panelManager;
        setGrow(layout);
    }

    @Override
    public GridPane getLayout() {
        return this.layout;
    }

    @Override
    public void onShow() {
        FadeTransition transition = new FadeTransition(Duration.seconds(1), this.layout);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.setAutoReverse(true);
        transition.play();
    }
    
    public void setGrow(GridPane layout){
        setGrow(layout, Priority.ALWAYS, Priority.ALWAYS);
    }
    public void setGrow(GridPane layout, Priority Hgrow, Priority Vgrow) {
        GridPane.setHgrow(layout, Hgrow);
        GridPane.setVgrow(layout, Vgrow);
    }
}
