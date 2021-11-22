package dev.asiluxserver.launcher.ui.panels.pages.tabs;

import dev.asiluxserver.launcher.ui.panel.Panel;

import javafx.animation.FadeTransition;
import javafx.util.Duration;

public abstract class Tab extends Panel {

    @Override
    public void onShow() {
        FadeTransition transition = new FadeTransition(Duration.millis(250), this.layout);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.setAutoReverse(true);
        transition.play();
    }
}
