package dev.asiluxserver.launcher.ui.panels.partials;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.assets.Fonts;
import dev.asiluxserver.launcher.ui.panel.Panel;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class TopBar extends Panel {
    private GridPane topBar;

    @Override
    public String getName() {
        return "TopBar";
    }

    @Override
    public String getStyleSheetPath() {
        return null;
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);
        this.topBar = this.layout;
        this.layout.setStyle("-fx-background-color: rgb(35, 40, 40);");
        setCanTakeAllWidth(this.topBar);

        /*
         * TopBar separation
         */
        // TopBar: left side
        ImageView imageView = new ImageView();
        imageView.setImage(new Image("images/asilux-icon.png"));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(25);
        setLeft(imageView);
        this.layout.getChildren().add(imageView);

        // TopBar: center
        Label title = new Label("Asilux Launcher");
        title.setFont(Font.loadFont(Fonts.SELAWK_SEMI_BOLD.get(), 18f));
        title.setStyle("-fx-text-fill: white;");
        setCenterH(title);
        this.layout.getChildren().add(title);

        // TopBar: right side
        GridPane topBarButton = new GridPane();
        topBarButton.setMinWidth(100d);
        topBarButton.setMaxWidth(100d);
        setCanTakeAllSize(topBarButton);
        setRight(topBarButton);
        this.layout.getChildren().add(topBarButton);

        /*
         * TopBar buttons configuration
         */
        FontAwesomeIconView closeBtn = new FontAwesomeIconView(FontAwesomeIcon.CLOSE);
        FontAwesomeIconView fullscreenBtn = new FontAwesomeIconView(FontAwesomeIcon.EXPAND);
        FontAwesomeIconView minimizeBtn = new FontAwesomeIconView(FontAwesomeIcon.MINUS);
        setCanTakeAllWidth(closeBtn, fullscreenBtn, minimizeBtn);

        closeBtn.setFill(Color.WHITE);
        closeBtn.setOpacity(0.70f);
        closeBtn.setSize("14px");
        setCenterV(closeBtn);
        closeBtn.setTranslateX(70f);
        closeBtn.setTranslateY(5.0d);
        closeBtn.setOnMouseEntered(e -> closeBtn.setOpacity(1.0f));
        closeBtn.setOnMouseExited(e -> closeBtn.setOpacity(0.70f));
        closeBtn.setOnMouseClicked(e -> System.exit(0));

        fullscreenBtn.setFill(Color.WHITE);
        fullscreenBtn.setOpacity(0.70f);
        fullscreenBtn.setSize("14px");
        setCenterV(fullscreenBtn);
        fullscreenBtn.setTranslateX(48.0d);
        fullscreenBtn.setTranslateY(5.0d);
        fullscreenBtn.setOnMouseEntered(e -> fullscreenBtn.setOpacity(1.0f));
        fullscreenBtn.setOnMouseExited(e -> fullscreenBtn.setOpacity(0.70f));
        fullscreenBtn.setOnMouseClicked(e -> {
            this.panelManager.getStage().setMaximized(!this.panelManager.getStage().isMaximized());
            if (this.panelManager.getStage().isMaximized()) {
                fullscreenBtn.setIcon(FontAwesomeIcon.COMPRESS);
            } else {
                fullscreenBtn.setIcon(FontAwesomeIcon.EXPAND);
            }
        });

        minimizeBtn.setFill(Color.WHITE);
        minimizeBtn.setOpacity(0.70f);
        minimizeBtn.setSize("14px");
        setCenterV(minimizeBtn);
        minimizeBtn.setTranslateX(26.0d);
        minimizeBtn.setTranslateY(6.0d);
        minimizeBtn.setOnMouseEntered(e -> minimizeBtn.setOpacity(1.0f));
        minimizeBtn.setOnMouseExited(e -> minimizeBtn.setOpacity(0.70f));
        minimizeBtn.setOnMouseClicked(e -> this.panelManager.getStage().setIconified(true));

        topBarButton.getChildren().addAll(closeBtn, fullscreenBtn, minimizeBtn);
    }
}
