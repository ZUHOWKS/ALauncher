package dev.asiluxserver.launcher.ui.assets;

import dev.asiluxserver.launcher.Main;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

import java.io.InputStream;

public enum Fonts {
    ARIAL_14(Font.font("Arial", FontWeight.SEMI_BOLD, 14)),
    ARIAL_17(Font.font("Arial", FontWeight.SEMI_BOLD, 17)),
    ARIAL_18(Font.font("Arial", FontWeight.SEMI_BOLD, 18)),
    ARIAL_20(Font.font("Arial", FontWeight.SEMI_BOLD, 20)),

    CONSOLAS_18F(Font.font("Consolas", FontWeight.BOLD, FontPosture.REGULAR, 18f)),
    CONSOLAS_25F(Font.font("Consolas", FontWeight.BOLD, FontPosture.REGULAR, 25f)),

    SELAWK(Main.class.getResource("/font/selawk.ttf").toExternalForm()),
    SELAWK_SEMI_LIGHT(Main.class.getResource("/font/selawksl.ttf").toExternalForm()),
    SELAWK_LIGHT(Main.class.getResource("/font/selawkl.ttf").toExternalForm()),
    SELAWK_SEMI_BOLD(Main.class.getResource("/font/selawksb.ttf").toExternalForm()),
    SELAWK_BOLD(Main.class.getResource("/font/selawkb.ttf").toExternalForm());


    private final Font font;
    private final String resourceAsStream;

    Fonts(String resourceAsStream) {
        this.resourceAsStream = resourceAsStream;
    }

    Fonts(Font font) {
        this.font = font;
    }

    public String get() {
        return resourceAsStream;
    }

    public Font getFont() {
        return this.font;
    }
}