package dev.asiluxserver.launcher.utils;

import java.util.Locale;

public enum OperatingSystem {

    LINUX("linux", new String[]{"linux", "unix"}),
    WINDOWS("windows", new String[]{"win"}),
    MACOS("osx", new String[]{"mac"}),
    UNKNOWN("unknown", new String[0]);

    private final String name;
    private final String[] aliases;

    OperatingSystem(final String name, final String[] aliases) {
        this.name = name;
        this.aliases = ((aliases == null) ? new String[0] : aliases);
    }

    public static OperatingSystem getCurrentPlatforme(){
        final String osName = System.getProperty("os.name").toLowerCase();
        for (final OperatingSystem os : values()){
            for (final String alias : os.aliases) {
                if (osName.contains(alias)){
                    return os;
                }
            }
        }
        return OperatingSystem.UNKNOWN;
    }

    public String getName() {
        return name;
    }

    public String[] getAliases() {
        return aliases;
    }
}
