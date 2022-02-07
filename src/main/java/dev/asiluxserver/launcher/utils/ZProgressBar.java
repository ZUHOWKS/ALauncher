package dev.asiluxserver.launcher.utils;

import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class ZProgressBar extends GridPane {
    private final Rectangle backgroundRect = new Rectangle();
    private final Rectangle foregroundRect = new Rectangle();

    public ZProgressBar() {
        this.init(100, 40);
    }

    public ZProgressBar(int width, int height) {
        this.init(width, height);
    }

    private void init(int width, int height) {
        this.backgroundRect.setArcHeight((double) (height));
        this.backgroundRect.setArcWidth((double) (height));
        this.foregroundRect.setArcHeight((double) (height));
        this.foregroundRect.setArcWidth((double) (height));
        GridPane.setVgrow(this.foregroundRect, Priority.ALWAYS);
        GridPane.setHgrow(this.foregroundRect, Priority.ALWAYS);
        GridPane.setValignment(this.foregroundRect, VPos.CENTER);
        GridPane.setHalignment(this.foregroundRect, HPos.LEFT);
        this.setBarWidth((double)width);
        this.setBarHeight((double)height);
        this.getChildren().addAll(this.backgroundRect, this.foregroundRect);
    }

    public void setBarWidth(double width) {
        this.backgroundRect.setWidth(width);
        this.foregroundRect.setWidth(0.0D);
        this.setPrefWidth(width);
        this.setMaxWidth(width);
    }

    public void setBarHeight(double height) {
        this.backgroundRect.setHeight(height);
        this.foregroundRect.setHeight(height);
        this.setPrefHeight(height);
        this.setMaxHeight(height);
    }

    public double getBarWidth() {
        return this.backgroundRect.getWidth();
    }

    public void setBackgroundColor(Color fill) {
        this.backgroundRect.setFill(fill);
    }

    public void setForegroundColor(Paint fill) {
        this.foregroundRect.setFill(fill);
    }

    private float percentage(double val, double max) {
        return (float) (val * this.getBarWidth() / max);
    }

    public void setProgress(double val, double max) {
        this.foregroundRect.setWidth((double)this.percentage(val, max));
    }
}
