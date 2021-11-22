package dev.asiluxserver.launcher.ui.panel;

import dev.asiluxserver.launcher.Launcher;
import dev.asiluxserver.launcher.ui.PanelManager;

import fr.flowarg.flowlogger.ILogger;

import javafx.animation.FadeTransition;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.util.Duration;

public abstract class Panel implements IPanel, IMovable, ITakePLace {

    protected final ILogger logger;
    protected GridPane layout = new GridPane();
    protected PanelManager panelManager;

    public Panel() {
        this.logger = Launcher.getInstance().getLogger();
    }

    @Override
    public void init(PanelManager panelManager) {
        setCanTakeAllSize(this.layout);
    }

    @Override
    public GridPane getLayout() {
        return layout;
    }

    @Override
    public void onShow() {
        FadeTransition transition = new FadeTransition(Duration.seconds(1), this.layout);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.setAutoReverse(true);
        transition.play();
    }

    @Override
    public abstract String getName();

    @Override
    public String getStyleSheetPath() {
        return null;
    }

    public void setGrow(Node layout){
        setGrow(layout, Priority.ALWAYS, Priority.ALWAYS);
    }

    public void setGrow(Node layout, Priority Hgrow, Priority Vgrow) {
        GridPane.setHgrow(layout, Hgrow);
        GridPane.setVgrow(layout, Vgrow);
    }

    public void setAlignment(Node layout){
        setAlignment(layout, HPos.CENTER, VPos.CENTER);
    }

    public void setAlignment(Node layout, HPos Hpos, VPos Vpos) {
        GridPane.setHalignment(layout, Hpos);
        GridPane.setValignment(layout, Vpos);
    }

    @Override
    public void setLeft(Node node) {
        GridPane.setHalignment(node, HPos.LEFT);
    }

    @Override
    public void setRight(Node node) {
        GridPane.setHalignment(node, HPos.RIGHT);
    }

    @Override
    public void setTop(Node node) {
        GridPane.setValignment(node, VPos.TOP);
    }

    @Override
    public void setBottom(Node node) {
        GridPane.setValignment(node, VPos.BOTTOM);
    }

    @Override
    public void setBaseLine(Node node) {
        GridPane.setValignment(node, VPos.BASELINE);
    }

    @Override
    public void setCenterH(Node node) {
        GridPane.setHalignment(node, HPos.CENTER);
    }

    @Override
    public void setCenterV(Node node) {
        GridPane.setValignment(node, VPos.CENTER);
    }
}
