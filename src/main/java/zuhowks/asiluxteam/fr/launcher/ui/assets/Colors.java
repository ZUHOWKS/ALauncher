package zuhowks.asiluxteam.fr.launcher.ui.assets;

import javafx.scene.paint.Color;

public enum Colors {
    DEFAULT_WHITE(Color.rgb(255,255,255)),
    WHITE_1(Color.rgb(255,255,255, 0.85)),

    LIGHT_GREY_1(Color.rgb(255, 255, 255, 0.35)),
    LIGHT_GREY_2(Color.rgb(255, 255, 255, 0.3)),
    LIGHT_GREY_3(Color.rgb(255, 255, 255, 0.2)),
    LIGHT_GREY_4(Color.rgb(255, 255, 255, 0.15)),

    GREY_1(Color.rgb(225, 225, 225)),
    GREY_2(Color.rgb(210, 210, 210, 0.2)),

    DARK_GREY_1(Color.rgb(67, 67, 67, 0.4)),
    DARK_GREY_2(Color.rgb(67, 67, 67, 0.15)),
    DARK_GREY_3(Color.rgb(37, 37, 37, 0.3)),
    DARK_GREY_4(Color.rgb(37, 37, 37, 0.2)),
    DARK_GREY_5(Color.rgb(37, 37, 37, 0.17)),
    DARK_GREY_6(Color.rgb(47, 47, 47)),
    DARK_GREY_7(Color.rgb(67, 67, 67)),

    BLACK_1(Color.rgb(27,27,27,0.95)),
    BLACK_2(Color.rgb(27,27,27,0.3)),

    LIGHT_GREEN_1(Color.rgb(245, 255, 245)),
    LIGHT_GREEN_2(Color.rgb(97, 136, 56)),
    LIGHT_GREEN_3(Color.rgb(143, 168, 95)),
    LIGHT_GREEN_4(Color.rgb(155, 190, 82)),
    LIGHT_GREEN_5(Color.rgb(165, 210, 72)),
    LIGHT_GREEN_6(Color.rgb(177, 228, 82)),
    LIGHT_GREEN_7(Color.rgb(167, 220, 82, 0.5)),

    RED_1(Color.rgb(225,53,53)),
    ;

    private final Color color;

    Colors(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }
}
