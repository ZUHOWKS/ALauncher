package dev.asiluxserver.launcher;

import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.panels.pages.App;
import dev.asiluxserver.launcher.utils.XML.XMLPatchParser;
import fr.flowarg.flowlogger.ILogger;
import fr.flowarg.flowlogger.Logger;
import fr.litarvan.openauth.AuthPoints;
import fr.litarvan.openauth.AuthenticationException;
import fr.litarvan.openauth.Authenticator;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.litarvan.openauth.model.response.RefreshResponse;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
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
    private AuthInfos authInfos = null;
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

    public boolean isUserAlreadyLoggedIn() {
        if (saver.get("accessToken") != null && saver.get("clientToken") != null) {
            Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);

            try {
                RefreshResponse response = authenticator.refresh(saver.get("accessToken"), saver.get("clientToken"));
                saver.set("accessToken", response.getAccessToken());
                saver.set("clientToken", response.getClientToken());
                saver.save();
                this.setAuthInfos(new AuthInfos(
                        response.getSelectedProfile().getName(),
                        response.getAccessToken(),
                        response.getClientToken(),
                        response.getSelectedProfile().getId()
                ));

                return true;
            } catch (AuthenticationException ignored) {
                saver.remove("accessToken");
                saver.remove("clientToken");
                saver.save();
            }
        } else if (saver.get("msAccessToken") != null && saver.get("msRefreshToken") != null) {
            try {
                MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
                MicrosoftAuthResult response = authenticator.loginWithRefreshToken(saver.get("msRefreshToken"));

                saver.set("msAccessToken", response.getAccessToken());
                saver.set("msRefreshToken", response.getRefreshToken());
                saver.save();
                this.setAuthInfos(new AuthInfos(
                        response.getProfile().getName(),
                        response.getAccessToken(),
                        response.getProfile().getId()
                ));
                return true;
            } catch (MicrosoftAuthenticationException e) {
                saver.remove("msAccessToken");
                saver.remove("msRefreshToken");
                saver.save();
            }
        }

        return false;
    }

    public void setAuthInfos(AuthInfos authInfos) {
        this.authInfos = authInfos;
    }

    public AuthInfos getAuthInfos() {
        return authInfos;
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
