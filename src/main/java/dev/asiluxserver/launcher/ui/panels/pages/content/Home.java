package dev.asiluxserver.launcher.ui.panels.pages.content;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import dev.asiluxserver.launcher.Launcher;
import dev.asiluxserver.launcher.Main;
import dev.asiluxserver.launcher.game.MinecraftInfos;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.assets.Colors;
import dev.asiluxserver.launcher.ui.assets.effects.BlurDropShadow;
import dev.asiluxserver.launcher.utils.ZProgressBar;

import fr.flowarg.flowupdater.FlowUpdater;
import fr.flowarg.flowupdater.download.IProgressCallback;
import fr.flowarg.flowupdater.download.Step;
import fr.flowarg.flowupdater.download.json.Mod;
import fr.flowarg.flowupdater.versions.AbstractForgeVersion;
import fr.flowarg.flowupdater.versions.ForgeVersionBuilder;
import fr.flowarg.flowupdater.versions.VanillaVersion;

import fr.theshark34.openlauncherlib.external.ExternalLaunchProfile;
import fr.theshark34.openlauncherlib.external.ExternalLauncher;
import fr.theshark34.openlauncherlib.minecraft.*;
import fr.theshark34.openlauncherlib.util.Saver;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

import java.net.URL;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.List;

public class Home extends ContentPanel{

    private final Saver saver = Launcher.getInstance().getSaver();
    ZProgressBar progressBar;

    GridPane contentPane = new GridPane();
    GridPane mainMenuPanel = new GridPane();

    Button playButton = new Button("");
    Label stepLabel = new Label();
    Label fileLabel = new Label();

    boolean isDownloading = false;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        setCanTakeAllSize(this.layout);

        setCanTakeAllSize(contentPane);
        this.layout.getChildren().add(contentPane);

        /* ACCUEIL */
        ColumnConstraints mainContraints = new ColumnConstraints();
        mainContraints.setHalignment(HPos.RIGHT);
        mainContraints.setMinWidth(480);
        mainContraints.setMaxWidth(1760);
        contentPane.getColumnConstraints().addAll(mainContraints, new ColumnConstraints());

        setGrow(mainMenuPanel);
        setAlignment(mainMenuPanel, HPos.RIGHT, VPos.TOP);
        mainMenuPanel.setMinWidth(700);
        mainMenuPanel.setMaxWidth(700);
        mainMenuPanel.setMinHeight(700);
        mainMenuPanel.setMinHeight(700);
        addToMainMenuPanel(mainMenuPanel);

        /* NEWS ACCUEIL */
        GridPane actuMainMenuPanel = new GridPane();
        setGrow(actuMainMenuPanel);
        setAlignment(actuMainMenuPanel, HPos.RIGHT, VPos.CENTER);
        actuMainMenuPanel.setMinWidth(200);
        actuMainMenuPanel.setMaxWidth(300);
        actuMainMenuPanel.setMinHeight(320);
        actuMainMenuPanel.setMaxHeight(320);
        addNewsToMainPanel(actuMainMenuPanel);

        contentPane.add(mainMenuPanel, 0, 0);
        contentPane.add(actuMainMenuPanel, 1, 0);
    }

    private void addToMainMenuPanel(GridPane pane) {

        Image asiluxLogo = new Image("images/asilux-logo-500px.png");
        ImageView asiluxView = new ImageView(asiluxLogo);
        asiluxView.setFitWidth(500);
        asiluxView.setFitHeight(250);
        asiluxView.setEffect(new BlurDropShadow(Colors.GREY_2, 4, 0));
        setGrow(asiluxView);
        GridPane.setValignment(asiluxView, VPos.CENTER);
        GridPane.setHalignment(asiluxView, HPos.CENTER);
        asiluxView.setTranslateY(-25);


        stepLabel.setStyle(
                "-fx-text-alignment: center;" +
                        "-fx-text-fill: rgb(255, 255, 255);"
        );
        setAlignment(stepLabel, HPos.CENTER ,VPos.CENTER);
        setCanTakeAllSize(stepLabel);
        stepLabel.setTranslateY(80);


        fileLabel.setStyle(
                "-fx-text-alignment: center;" +
                        "-fx-text-fill: rgb(255, 255, 255);"
        );
        setCenterH(fileLabel);
        setAlignment(fileLabel, HPos.CENTER ,VPos.CENTER);
        setCanTakeAllSize(fileLabel);
        fileLabel.setTranslateY(94);


        progressBar = new ZProgressBar(450, 18);
        setGrow(progressBar);
        setAlignment(progressBar, HPos.CENTER ,VPos.CENTER);
        progressBar.setBackgroundColor(Colors.DARK_GREY_1.getColor());
        Stop[] stops = new Stop[]{
                new Stop(0, Colors.LIGHT_GREEN_2.getColor()),
                new Stop(1, Colors.LIGHT_GREEN_4.getColor())
        };
        LinearGradient lg = new LinearGradient(
                0,0,1,0,true,
                CycleMethod.NO_CYCLE, stops
        );
        progressBar.setForegroundColor(lg);
        progressBar.setEffect(new BlurDropShadow(Colors.DARK_GREY_2, 12, 0));
        progressBar.setTranslateY(112);


        FontAwesomeIconView playIcon = new FontAwesomeIconView(FontAwesomeIcon.PLAY);
        playIcon.setFill(Colors.DEFAULT_WHITE.getColor());
        playIcon.setScaleX(2.7);
        playIcon.setScaleY(2.7);
        playIcon.setTranslateX(-15);

        Label playLabel = new Label("JOUER");
        playLabel.setStyle("-fx-font-size: 42;");
        playLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        playLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_2, 6, 0));
        playLabel.setGraphic(playIcon);
        setGrow(playLabel);
        setAlignment(playLabel, HPos.CENTER ,VPos.CENTER);
        playLabel.setTranslateY(170);
        playLabel.setTranslateX(15);
        playLabel.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        playLabel.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        playLabel.setOnMouseClicked(e-> {
            Timeline clickedAnimation = new Timeline(
                    new KeyFrame(
                            Duration.ZERO,
                            new KeyValue(
                                    playButton.backgroundProperty(),
                                    new Background(
                                            new BackgroundFill(Color.valueOf("#74923AFF"),
                                            CornerRadii.EMPTY, Insets.EMPTY
                                            )
                                    )
                            )
                    ),
                    new KeyFrame(
                            Duration.millis(700),
                            new KeyValue(
                                    playButton.backgroundProperty(),
                                    new Background(
                                            new BackgroundFill(Color.valueOf("#91B248FF"),
                                                    CornerRadii.EMPTY, Insets.EMPTY
                                            )
                                    )
                            )
                    )
            );
            clickedAnimation.setOnFinished(ev -> playButton.setStyle(
                    "-fx-background-color: #91B248FF;" +
                            "-fx-font-size: 32;" +
                            "-fx-text-fill: rgba(255,255,255,1);"
                    )
            );
            clickedAnimation.play();
            if (!isDownloading())
                this.play();
        });

        playButton.setMinWidth(220);
        playButton.setMinHeight(60);
        playButton.setMaxWidth(220);
        playButton.setMaxHeight(60);
        playButton.setStyle(
                "-fx-background-color: #91B248FF;" +
                        "-fx-font-size: 34; " +
                        "-fx-text-fill: rgba(255,255,255,1);"
        );
        playButton.setTextAlignment(TextAlignment.CENTER);
        playButton.setEffect(new BlurDropShadow(Colors.DARK_GREY_4, 3, 0.3));
        setGrow(playButton);
        setAlignment(playButton, HPos.CENTER ,VPos.CENTER);
        playButton.setTranslateY(170);
        playButton.setOnMouseEntered(e-> this.layout.setCursor(Cursor.HAND));
        playButton.setOnMouseExited(e-> this.layout.setCursor(Cursor.DEFAULT));
        playButton.setOnMouseClicked(e-> {
            Timeline clickedAnimation = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(playButton.backgroundProperty(),
                                    new Background(
                                            new BackgroundFill(Color.valueOf("#74923AFF"),
                                                    CornerRadii.EMPTY, Insets.EMPTY
                                            )
                                    )
                            )
                    ),
                    new KeyFrame(Duration.millis(700),
                            new KeyValue(playButton.backgroundProperty(),
                                    new Background(
                                            new BackgroundFill(Color.valueOf("#91B248FF"),
                                                    CornerRadii.EMPTY, Insets.EMPTY
                                            )
                                    )
                            )
                    )
            );
            clickedAnimation.setOnFinished(
                    ev -> playButton.setStyle(
                            "-fx-background-color: #91B248FF;" +
                                    "-fx-font-size: 32;" +
                                    "-fx-text-fill: rgba(255,255,255,1);")
            );
            clickedAnimation.play();
            if (!isDownloading())
                this.play();
        });


        pane.getChildren().addAll(asiluxView, playButton, playLabel, progressBar);
    }

    /* Tableau des News */
    private void addNewsToMainPanel(GridPane pane) {

        /* RECTANGLE BACKGROUND */
        Rectangle windowsBackground = new Rectangle();
        setGrow(windowsBackground);
        setAlignment(windowsBackground, HPos.CENTER, VPos.BOTTOM);
        windowsBackground.setWidth(220);
        windowsBackground.setHeight(340);
        windowsBackground.setFill(Colors.BLACK_1.getColor());
        windowsBackground.setArcHeight(25);
        windowsBackground.setArcWidth(25);
        windowsBackground.setSmooth(true);

        /* PANEL NEWS MAIN */
        GridPane windowsPanel = new GridPane();
        setGrow(windowsPanel);
        setAlignment(windowsPanel, HPos.CENTER, VPos.TOP);
        windowsPanel.setMinWidth(200);
        windowsPanel.setMaxWidth(220);
        windowsPanel.setMinHeight(275);
        windowsPanel.setMaxHeight(275);
        windowsPanel.setTranslateY(45);

        /* PANEL NEWS LABEL */
        GridPane newsPanel = new GridPane();
        setGrow(newsPanel);
        setAlignment(newsPanel, HPos.LEFT, VPos.TOP);
        newsPanel.setMinWidth(200);
        newsPanel.setMaxWidth(220);
        newsPanel.setMinHeight(250);
        newsPanel.setMaxHeight(1000);
        newsPanel.setTranslateY(45);

        /* SCROLL PANE STYLE */
        ScrollPane scrollPane = new ScrollPane();
        setGrow(scrollPane);
        setAlignment(scrollPane, HPos.CENTER, VPos.CENTER);
        URL resource = Main.class.getResource("/css/scroll-pane.css");
        if (resource != null) {
            scrollPane.getStylesheets().addAll(resource.toExternalForm());
        }

        /* SCROLL BAR */
        VBox vBox = new VBox();
        setGrow(vBox);
        vBox.setMinWidth(200);
        vBox.setMaxWidth(300);
        vBox.setMinHeight(250);
        vBox.setMaxHeight(newsPanel.getMaxHeight());
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setTranslateY(10);

        /* TITLE */
        Label NewsTitle = new Label("PATCH NOTE");
        setGrow(NewsTitle);
        setAlignment(NewsTitle, HPos.CENTER ,VPos.TOP);
        NewsTitle.setTextFill(Colors.DEFAULT_WHITE.getColor());
        NewsTitle.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
        NewsTitle.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_2, 6, 0));
        NewsTitle.setTranslateY(-35);

        /* SEPARATOR */
        Separator separator1 = new Separator();
        setGrow(separator1);
        setAlignment(separator1, HPos.CENTER ,VPos.TOP);
        separator1.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_2, 12, 0));
        separator1.setOpacity(10);
        separator1.setMaxHeight(38);
        separator1.setMinHeight(38);
        separator1.setMaxWidth(195);
        separator1.setMinWidth(195);
        separator1.setTranslateY(-25);

        Separator separator2 = new Separator();
        setGrow(separator2);
        setAlignment(separator2, HPos.CENTER ,VPos.BOTTOM);
        separator2.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_2, 12, 0));
        separator2.setOpacity(10);
        separator2.setMaxHeight(38);
        separator2.setMinHeight(38);
        separator2.setMaxWidth(195);
        separator2.setMinWidth(195);
        separator2.setTranslateY(25);

        /* LABEL NEWS */
        //TODO: METHODE STRING BUILDER | Créer une méthode afin de lire un fichier (le fichier des news),
        // qui va créer un string builder avec une longueur de chaine de caractère maximale de 18.
        Label newsLabel = new Label("[ANNONCE] BETA OUVERTE\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n" +
                "..................\n");
        setGrow(newsLabel);
        setAlignment(newsLabel, HPos.CENTER ,VPos.TOP);
        newsLabel.setStyle("-fx-font-size: 14;");
        newsLabel.setTextFill(Colors.DEFAULT_WHITE.getColor());
        newsLabel.setEffect(new BlurDropShadow(Colors.LIGHT_GREY_2, 6, 0));
        newsLabel.setTranslateX(2);
        newsLabel.setTranslateY(-45);

        /* REGISTRY MAIN PANEL */
        windowsPanel.getChildren().addAll(NewsTitle, separator1, separator2);
        newsPanel.getChildren().add(newsLabel);

        windowsPanel.getChildren().add(scrollPane);
        scrollPane.setContent(vBox);
        vBox.getChildren().add(0, newsPanel);

        /* REGISTRY @PANE PANEL */
        pane.getChildren().addAll(windowsBackground, windowsPanel);


    }

    private void play() {
        isDownloading = true;
        setProgress(0, 0);
        mainMenuPanel.getChildren().addAll(stepLabel, fileLabel);

        Platform.runLater(() -> new Thread(this::update).start());
    }

    public void update() {
        IProgressCallback callback = new IProgressCallback() {
            private final DecimalFormat decimalFormat = new DecimalFormat("#.#");
            private String stepTxt = "";
            private String percentTxt = "0.0%";

            @Override
            public void step(Step step) {
                Platform.runLater(() -> {
                    stepTxt = StepInfo.valueOf(step.name()).getDetails();
                    setStatus(String.format("%s (%s)", stepTxt, percentTxt));
                });
            }

            @Override
            public void update(long downloaded, long max) {
                Platform.runLater(() -> {
                    percentTxt = decimalFormat.format(downloaded * 100.d / max) + "%";
                    setStatus(String.format("%s (%s)", stepTxt, percentTxt));
                    setProgress(downloaded, max);
                });
            }

            @Override
            public void onFileDownloaded(Path path) {
                Platform.runLater(() -> {
                    String p = path.toString();
                    fileLabel.setText("..." + p.replace(Launcher.getInstance().getLauncherDir().toFile().getAbsolutePath(), ""));
                });
            }
        };

        try {
            final VanillaVersion vanillaVersion = new VanillaVersion.VanillaVersionBuilder()
                    .withName(MinecraftInfos.GAME_VERSION)
                    .withVersionType(MinecraftInfos.VERSION_TYPE)
                    .build();

            //List<CurseFileInfos> curseMods = CurseFileInfos.getFilesFromJson(MinecraftInfos.CURSE_MODS_LIST_URL);
            List<Mod> mods = Mod.getModsFromJson(MinecraftInfos.MODS_LIST_URL);

            final AbstractForgeVersion forge = new ForgeVersionBuilder(MinecraftInfos.FORGE_VERSION_TYPE)
                    .withForgeVersion(MinecraftInfos.FORGE_VERSION)
                    .withMods(mods)
                    .build();
                    //.withFileDeleter(new ModFileDeleter(true, "jei.jar"))
                    //.withCurseMods(curseMods)


            final FlowUpdater updater = new FlowUpdater.FlowUpdaterBuilder()
                    .withVanillaVersion(vanillaVersion)
                    .withForgeVersion(forge)
                    .withLogger(Launcher.getInstance().getLogger())
                    .withProgressCallback(callback)
                    .build();

            updater.update(Launcher.getInstance().getLauncherDir());
            this.startGame(updater.getVanillaVersion().getName());
        } catch (Exception exception) {
            Launcher.getInstance().getLogger().err(exception.toString());
            exception.printStackTrace();
            Platform.runLater(() -> panelManager.getStage().show());
        }
    }

    public void startGame(String gameVersion) {
        GameInfos infos = new GameInfos(
                MinecraftInfos.SERVER_NAME,
                Launcher.getInstance().getLauncherDir(),
                new GameVersion(gameVersion, MinecraftInfos.OLL_GAME_TYPE),
                new GameTweak[]{GameTweak.FORGE}
        );

        try {
            ExternalLaunchProfile profile = MinecraftLauncher.createExternalProfile(infos, GameFolder.FLOW_UPDATER, Launcher.getInstance().getAuthInfos());
            profile.getVmArgs().add(this.getRamArgsFromSaver());
            ExternalLauncher launcher = new ExternalLauncher(profile);

            Platform.runLater(() -> panelManager.getStage().hide());

            Process p = launcher.launch();

            Platform.runLater(() -> {
                try {
                    p.waitFor();
                    Platform.exit();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception exception) {
            exception.printStackTrace();
            Launcher.getInstance().getLogger().err(exception.toString());
        }
    }

    public String getRamArgsFromSaver() {
        int val = 1024;
        try {
            if (saver.get("maxRam") != null) {
                val = Integer.parseInt(saver.get("maxRam"));
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException error) {
            saver.set("maxRam", String.valueOf(val));
            saver.save();
        }

        return "-Xmx" + val + "M";
    }

    public void setStatus(String status) {
        this.stepLabel.setText(status);
    }

    public void setProgress(double current, double max) {
        this.progressBar.setProgress(current, max);
    }

    public boolean isDownloading() {
        return isDownloading;
    }

    public enum StepInfo {
        READ("Lecture du fichier json..."),
        DL_LIBS("Téléchargement des libraries..."),
        DL_ASSETS("Téléchargement des ressources..."),
        EXTRACT_NATIVES("Extraction des natives..."),
        FORGE("Installation de forge..."),
        FABRIC("Installation de fabric..."),
        MODS("Téléchargement des mods..."),
        EXTERNAL_FILES("Téléchargement des fichier externes..."),
        POST_EXECUTIONS("Exécution post-installation..."),
        INTEGRATION("Chargement des intégrations"),
        END("Finit !");
        String details;

        StepInfo(String details) {
            this.details = details;
        }

        public String getDetails() {
            return details;
        }
    }
}
