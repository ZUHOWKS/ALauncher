package zuhowks.asiluxteam.fr.launcher.ui.assets.effects;

import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import zuhowks.asiluxteam.fr.launcher.ui.assets.Colors;

public class BlurDropShadow extends DropShadow {

    public BlurDropShadow(Color color, int radius, double spread) {
        super(BlurType.GAUSSIAN, color, radius, spread, 0, 0);
    }

    public BlurDropShadow(Color color, int radius, int spread) {
        this(color, radius, (double) spread);
    }

    public BlurDropShadow(Colors color, int radius, double spread) {
        this(color.getColor(), radius, spread);
    }

    public BlurDropShadow(Colors color, int radius, int spread) {
        this(color.getColor(), radius, spread);
    }
}