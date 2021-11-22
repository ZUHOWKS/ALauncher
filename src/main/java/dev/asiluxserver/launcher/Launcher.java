package dev.asiluxserver.launcher;

import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.panels.pages.MainPage;
import dev.asiluxserver.launcher.ui.panels.pages.LoginPage;
import dev.asiluxserver.launcher.utils.XML.XMLPatchParser;
import dev.asiluxserver.launcher.utils.patch.PatchLoader;

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

public class Launcher extends Application {

    private static Launcher instance;
    private final ILogger logger;
    private final Path launcherDir = GameDirGenerator.createGameDir("asiluxdev", true);
    private final Saver saver;
    private PanelManager panelManager;
    private AuthInfos authInfo = null;
    private XMLPatchParser patchParser = new XMLPatchParser("https://zuhowks.github.io/patch.xml");
    private PatchLoader patchLoader = new PatchLoader(patchParser);

    // Pages du launcher
    private final MainPage mainPage;
    private final LoginPage loginPage;

    public Launcher() {
        instance = this;
        this.patchParser.readEvent();
        this.patchLoader.load();
        this.logger = new Logger("[AsiluxDev]", this.launcherDir.resolve("launcher.log"));
        if (!this.launcherDir.toFile().exists()) {
            if (!this.launcherDir.toFile().mkdir()) {
                this.logger.err("Unable to create launcher folder");
            }
        }

        saver = new Saver(this.launcherDir.resolve("config.properties"));
        saver.load();

        mainPage = new MainPage();
        loginPage = new LoginPage();
    }

    @Override
    public void start(Stage stage) {
        this.logger.info("Starting launcher");
        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();


        if (this.isUserAlreadyLoggedIn()) {
            logger.info("Hello " + authInfo.getUsername());
            this.panelManager.showPanel(mainPage);
        } else {
            this.panelManager.showPanel(loginPage);
        }
    }

    public boolean isUserAlreadyLoggedIn() {
        if (saver.get("accessToken") != null && saver.get("clientToken") != null) {
            Authenticator authenticator = new Authenticator(Authenticator.MOJANG_AUTH_URL, AuthPoints.NORMAL_AUTH_POINTS);

            try {
                RefreshResponse response = authenticator.refresh(saver.get("accessToken"), saver.get("clientToken"));
                saver.set("accessToken", response.getAccessToken());
                saver.set("clientToken", response.getClientToken());
                saver.save();
                this.setAuthInfo(new AuthInfos(
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
                this.setAuthInfo(new AuthInfos(
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

    public AuthInfos getAuthInfo() {
        return authInfo;
    }

    public static Launcher getInstance() {
        return instance;
    }

    public Path getLauncherDir() {
        return launcherDir;
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public ILogger getLogger() {
        return logger;
    }

    public MainPage getMainPage() {
        return mainPage;
    }

    public PatchLoader getPatchLoader() { return patchLoader;}

    public Saver getSaver() {
        return saver;
    }

    public void setAuthInfo(AuthInfos authInfo) {
        this.authInfo = authInfo;
    }
}
