package zuhowks.asiluxteam.fr.launcher.ui.assets;

import zuhowks.asiluxteam.fr.launcher.Main;

public enum Fonts {

    SELAWK(Main.class.getResource("/font/selawk.ttf").toExternalForm()),
    SELAWK_SEMI_LIGHT(Main.class.getResource("/font/selawksl.ttf").toExternalForm()),
    SELAWK_LIGHT(Main.class.getResource("/font/selawkl.ttf").toExternalForm()),
    SELAWK_SEMI_BOLD(Main.class.getResource("/font/selawksb.ttf").toExternalForm()),
    SELAWK_BOLD(Main.class.getResource("/font/selawkb.ttf").toExternalForm());


    private final String resourceAsStream;

    Fonts(String resourceAsStream) {
        this.resourceAsStream = resourceAsStream;
    }

    public String get() {
        return resourceAsStream;
    }
}
