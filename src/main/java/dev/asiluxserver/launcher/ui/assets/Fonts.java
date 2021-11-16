package dev.asiluxserver.launcher.ui.assets;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public enum Fonts {
    ARIAL_14(Font.font("Arial", FontWeight.SEMI_BOLD, 14)),
    ARIAL_17(Font.font("Arial", FontWeight.SEMI_BOLD, 17)),
    ARIAL_18(Font.font("Arial", FontWeight.SEMI_BOLD, 18)),
    ARIAL_20(Font.font("Arial", FontWeight.SEMI_BOLD, 20)),

    CONSOLAS_18F(Font.font("Consolas", FontWeight.BOLD, FontPosture.REGULAR, 18f)),
    CONSOLAS_25F(Font.font("Consolas", FontWeight.BOLD, FontPosture.REGULAR, 25f))
    ;

    private final Font font;

    Fonts(Font font) {
        this.font = font;
    }

    public Font getFont() {
        return this.font;
    }
}
