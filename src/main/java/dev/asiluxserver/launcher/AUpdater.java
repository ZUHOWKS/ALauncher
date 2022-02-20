package dev.asiluxserver.launcher;

import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.panels.pages.App;
import dev.asiluxserver.launcher.utils.XML.XMLPatchParser;
import fr.flowarg.flowlogger.ILogger;
import fr.flowarg.flowlogger.Logger;
import fr.theshark34.openlauncherlib.minecraft.util.GameDirGenerator;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.application.Application;
import javafx.stage.Stage;

import java.nio.file.Path;

public class AUpdater extends Application {
    private static AUpdater instance;
    private final ILogger logger;
    private final Path launcherDir = GameDirGenerator.createGameDir("ALauncher", true);
    private final Saver saver;
    private PanelManager panelManager;
    private XMLPatchParser patchParser;
    private String version;

    public AUpdater() {
        instance = this;
        this.patchParser = new XMLPatchParser("https://zuhowks.github.io/patch.xml");
        this.patchParser.readEvent();
        this.version = patchParser.getVersion();
        this.logger = new Logger("[ALauncher]", this.launcherDir.resolve("launcher.log"));
        if (!this.launcherDir.toFile().exists()) {
            if (!this.launcherDir.toFile().mkdir()) {
                this.logger.err("Unable to create launcher folder");
            }
        }
        saver = new Saver(this.launcherDir.resolve("config.properties"));
        saver.load();
    }

    @Override
    public void start(Stage stage) {
        this.logger.info("Check to update");
        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();
        this.panelManager.showPanel(new App());
    }

    public ILogger getLogger() {
        return logger;
    }

    public static AUpdater getInstance() {
        return instance;
    }

    public Saver getSaver() {
        return saver;
    }

    public Path getLauncherDir() {
        return launcherDir;
    }

    public String getVersion() {
        return version;
    }
}
