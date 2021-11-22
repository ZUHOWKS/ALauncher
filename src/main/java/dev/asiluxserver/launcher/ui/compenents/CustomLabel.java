package dev.asiluxserver.launcher.ui.compenents;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import dev.asiluxserver.launcher.ui.assets.Colors;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class CustomLabel extends javafx.scene.control.Label {

    public CustomLabel(String label, Font font, Paint textColor) {
        super(label);
        setFont(font);
        setTextFill(textColor);
    }

    public CustomLabel(String label, Font font, Colors color) {
        this(label, font, color.getColor());
    }

    public CustomLabel(String label, Font font, Colors color, FontAwesomeIconView icon) {
        this(label, font, color);
        setGraphic(icon);
    }
}
