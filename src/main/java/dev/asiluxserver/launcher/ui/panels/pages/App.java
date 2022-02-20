package dev.asiluxserver.launcher.ui.panels.pages;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import dev.asiluxserver.launcher.AUpdater;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.assets.Colors;
import dev.asiluxserver.launcher.ui.assets.Fonts;
import dev.asiluxserver.launcher.ui.assets.effects.BlurDropShadow;
import dev.asiluxserver.launcher.ui.panel.Panel;
import dev.asiluxserver.launcher.utils.ZProgressBar;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class App extends Panel {

    GridPane contentPane = new GridPane();
    Label infoLabel = new Label("CHECKING TO UPDATING");


    AUpdater updater = AUpdater.getInstance();
    String version = updater.getVersion();
    Saver saver = updater.getSaver();
    File launcherFile = new File(updater.getLauncherDir().toFile(), "ALauncher.jar");

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);



        setCanTakeAllSize(contentPane);
        contentPane.setMaxHeight(this.layout.getMaxHeight());
        contentPane.setMinHeight(this.layout.getMinHeight());
        contentPane.setMaxWidth(this.layout.getMaxWidth());
        contentPane.setMinWidth(this.layout.getMinWidth());
        this.layout.getChildren().add(contentPane);

        FontAwesomeIconView closeBtn = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);
        setCanTakeAllSize(closeBtn);

        closeBtn.setFill(Color.WHITE);
        closeBtn.setOpacity(0.70f);
        closeBtn.setSize("20px");
        setTop(closeBtn);
        setRight(closeBtn);
        closeBtn.setCursor(Cursor.HAND);
        closeBtn.setTranslateX(-5);
        closeBtn.setOnMouseEntered(e -> closeBtn.setOpacity(1.0f));
        closeBtn.setOnMouseExited(e -> closeBtn.setOpacity(0.70f));
        closeBtn.setOnMouseClicked(e -> System.exit(0));

        ImageView asiluxImage = new ImageView(new Image("images/asilux-icon.png", 200, 200, true, true));
        setCanTakeAllSize(asiluxImage);
        setCenterV(asiluxImage);
        setCenterH(asiluxImage);
        asiluxImage.setTranslateY(-20d);

        setGrow(this.infoLabel);
        setAlignment(this.infoLabel, HPos.CENTER, VPos.CENTER);
        this.infoLabel.setFont(Font.loadFont(Fonts.SELAWK.get(), 25));
        this.infoLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        this.infoLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_3, 4, 0));
        this.infoLabel.setAlignment(Pos.TOP_CENTER);
        this.infoLabel.setTranslateY(85d);

        contentPane.getChildren().addAll(closeBtn, asiluxImage, infoLabel);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(this::updateIfNeeded, 2, TimeUnit.SECONDS);

    }

    public void updateIfNeeded() {
        if (!version.equals(saver.get("version")) || !launcherFile.exists()) {
            saver.set("version", version);
            if (launcherFile.exists()) {
                launcherFile.delete();
            }
            System.out.println(launcherFile.toString());
            URL fetchWebsite = null;
            try {
                fetchWebsite = new URL("https://zuhowks.github.io/ALauncher.jar");
                Platform.runLater(() -> {
                    this.infoLabel.setText(InfoLabel.DOWNLOAD.getMessage());
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            try {
                FileUtils.copyURLToFile(fetchWebsite, launcherFile);
                Platform.runLater(() -> {
                    this.infoLabel.setText(InfoLabel.FINISH.getMessage());
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(this::startALauncher, 2, TimeUnit.SECONDS);
    }

    public void startALauncher() {
        Platform.runLater(() -> {
            this.infoLabel.setText(InfoLabel.START.getMessage());
        });
        try {
            Runtime.getRuntime().exec("java -jar " + launcherFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.exit(0);

    }

    public enum InfoLabel {
        DOWNLOAD("DOWNLOAD LATEST"),
        FINISH("SUCESSFULLY UPDATED"),
        START("STARTING ALAUNCHER");

        private String message;

        InfoLabel(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
