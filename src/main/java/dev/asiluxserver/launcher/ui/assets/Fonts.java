package dev.asiluxserver.launcher.ui.assets;

import dev.asiluxserver.launcher.Main;

import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.net.URL;

public enum Fonts {

    ARIAL("Arial", FontWeight.SEMI_BOLD),

    CONSOLAS("Consolas", FontWeight.BOLD),

    SELAWK(Main.class.getResource("/font/selawk.ttf")),
    SELAWK_SEMI_LIGHT(Main.class.getResource("/font/selawksl.ttf")),
    SELAWK_LIGHT(Main.class.getResource("/font/selawkl.ttf")),
    SELAWK_SEMI_BOLD(Main.class.getResource("/font/selawksb.ttf")),
    SELAWK_BOLD(Main.class.getResource("/font/selawkb.ttf"))
    ;


    private final String font;
    private final FontWeight weight;
    private final String resource;

    Fonts(String font, FontWeight weight) {
        this.font = font;
        this.weight = weight;
        this.resource = null;
    }

    Fonts(URL resource) {
        if (resource != null) {
            this.font = null;
            this.weight = null;
            this.resource = resource.toExternalForm();
        } else {
            this.font = "Consolas";
            this.weight = FontWeight.SEMI_BOLD;
            this.resource = null;
        }
    }

    public Font getFont(double size) {
        Font font;
        if (this.resource  == null) {
            font = Font.font(this.font, this.weight, size);
        } else {
            font = Font.loadFont(this.resource, size);
        }
        return font;
    }
}