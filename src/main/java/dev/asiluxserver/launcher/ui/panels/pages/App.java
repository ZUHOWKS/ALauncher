package dev.asiluxserver.launcher.ui.panels.pages;

import dev.asiluxserver.launcher.AUpdater;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.assets.Colors;
import dev.asiluxserver.launcher.ui.assets.Fonts;
import dev.asiluxserver.launcher.ui.assets.effects.BlurDropShadow;
import dev.asiluxserver.launcher.ui.panel.Panel;

import fr.litarvan.openauth.AuthPoints;
import fr.litarvan.openauth.AuthenticationException;
import fr.litarvan.openauth.Authenticator;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.litarvan.openauth.model.AuthAgent;
import fr.litarvan.openauth.model.response.AuthResponse;

import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import fr.theshark34.openlauncherlib.util.Saver;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicBoolean;

public class App extends Panel {

    GridPane mainPane = new GridPane();

    Saver saver = AUpdater.getInstance().getSaver();

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

    }
}
