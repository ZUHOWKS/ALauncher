package dev.asiluxserver.launcher.ui.compenents;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import dev.asiluxserver.launcher.ui.assets.Colors;
import dev.asiluxserver.launcher.ui.assets.Fonts;

import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class CustomLabel extends javafx.scene.control.Label {

    public CustomLabel(String label, Font font, Paint textColor) {
        super(label);
        setFont(font);
        setTextFill(textColor);
    }

    public CustomLabel(String label, Fonts font, Colors color) {
        this(label, font.getFont(), color.getColor());
    }

    public CustomLabel(String label, Fonts font, Colors color, FontAwesomeIcon icon) {
        this(label, font.getFont(), color.getColor());
        setGraphic(icon);
    }

    public void setGraphic(FontAwesomeIcon icon) {
        setGraphic(new FontAwesomeIconView(icon));
    }
}
