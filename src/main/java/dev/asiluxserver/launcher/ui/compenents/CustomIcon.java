package dev.asiluxserver.launcher.ui.compenents;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import dev.asiluxserver.launcher.ui.assets.Colors;

import javafx.scene.paint.Paint;

public class CustomIcon extends FontAwesomeIconView {

    public CustomIcon(FontAwesomeIcon icon, Paint color, double scaleX, double scaleY) {
        super(icon);
        setFill(color);
        setScaleX(scaleX);
        setScaleY(scaleY);
    }

    public CustomIcon(FontAwesomeIcon icon, Colors color, double scaleX, double scaleY) {
        this(icon, color.getColor(), scaleX, scaleY);
    }
}
