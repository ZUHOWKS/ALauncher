package dev.asiluxserver.launcher.ui.panels.pages.content;

import dev.asiluxserver.launcher.Launcher;
import dev.asiluxserver.launcher.Main;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.assets.Colors;
import dev.asiluxserver.launcher.ui.assets.Fonts;
import dev.asiluxserver.launcher.ui.assets.effects.BlurDropShadow;
import dev.asiluxserver.launcher.ui.panel.Panel;
import dev.asiluxserver.launcher.ui.panels.partials.TopBar;
import dev.asiluxserver.launcher.utils.patch.PatchLoader;
import dev.asiluxserver.launcher.utils.patch.PatchMessage;
import dev.asiluxserver.launcher.utils.patch.PatchNote;
import fr.theshark34.openlauncherlib.util.Saver;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;
import org.w3c.dom.css.Rect;

import java.io.*;
import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;

public class Update extends ContentPanel{

    Launcher launcher = Launcher.getInstance();

    private final Saver saver = launcher.getSaver();
    PatchLoader patchLoader = launcher.getPatchLoader();
    PatchMessage patchMessage = launcher.getPatchLoader().getPatch();

    GridPane contentPane = new GridPane();
    GridPane patchPane = new GridPane();
    RowConstraints rowConstraints = new RowConstraints();

    private Double minWidth;
    private Double prefWidth;
    private Double maxWidth;
    private Double minHeight;
    private Double prefHeight;
    private Double maxHeight;

    private Timeline timeline;

    private double widthOfOneGradientCycle = 2500;
    private double gradientSlopeDegree = 20;
    private double xStartStatic = 10;
    private double yStartStatic = 10;
    private double xEndStatic = xStartStatic + (widthOfOneGradientCycle * Math.cos(Math.toRadians(gradientSlopeDegree)));
    private double yEndStatic = yStartStatic + (widthOfOneGradientCycle * Math.sin(Math.toRadians(gradientSlopeDegree)));

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public void init(PanelManager panelManager) {
        super.init(panelManager);

        minWidth = panelManager.getStage().getMinWidth() - 160;
        prefWidth = panelManager.getStage().getWidth() - 160;
        maxWidth = panelManager.getStage().getMaxWidth() - 160;
        minHeight = panelManager.getStage().getMinHeight() - 100;
        prefHeight = panelManager.getStage().getHeight() - 100;
        maxHeight = panelManager.getStage().getMaxHeight() - 100;

        /* CONTENT */
        setCanTakeAllSize(contentPane);
        contentPane.setStyle("-fx-background-color: transparent;");
        contentPane.setMinWidth(480);
        contentPane.setMaxWidth(1760);
        contentPane.setMaxHeight(1080);
        contentPane.setMinHeight(720);

        rowConstraints.setValignment(VPos.TOP);
        rowConstraints.setMinHeight(150);
        rowConstraints.setMaxHeight(175);
        contentPane.getRowConstraints().addAll(rowConstraints, new RowConstraints());

        this.layout.getChildren().add(contentPane);

        loadPatch(contentPane);

    }

    private void loadPatch(GridPane pane) {

        ArrayList<ArrayList<Label>> patchNoteLabel = patchLoader.getPatchNoteLabel();
        int patchNoteLabelSize = patchNoteLabel.size();

        Stop[] GREEN_1 = new Stop[]{
                new Stop(0, Colors.LIGHT_GREEN_5.getColor()),
                new Stop(1, Colors.LIGHT_GREEN_2.getColor())
        };
        LinearGradient LG_GREEN_1 = new LinearGradient(
                0,0,1.5,0,true,
                CycleMethod.NO_CYCLE, GREEN_1
        );

        Stop[] GREEN_2 = new Stop[]{
                new Stop(0, Colors.LIGHT_GREEN_5.getColor()),
                new Stop(1, Colors.LIGHT_GREEN_3.getColor())
        };
        LinearGradient LG_GREEN_2 = new LinearGradient(
                0,0,2.2,0,true,
                CycleMethod.NO_CYCLE, GREEN_1
        );

        patchPane.setAlignment(Pos.CENTER);
        setGrow(patchPane);
        patchPane.setMinWidth(minWidth);
        patchPane.setMaxWidth(maxWidth);
        patchPane.setMinHeight(150);
        patchPane.setMaxHeight(175);
        timeline = new Timeline();
        for (int i = 0; i < 500; i++) {
            int innerIterator = i;
            KeyFrame kf = new KeyFrame(Duration.millis(10 * innerIterator), new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent ae) {

                    double runningRadius = innerIterator * (widthOfOneGradientCycle * 0.002);
                    double xStartDynamic = xStartStatic + (runningRadius * Math.cos(Math.toRadians(gradientSlopeDegree)));
                    double yStartDynamic = yStartStatic + (runningRadius * Math.sin(Math.toRadians(gradientSlopeDegree)));
                    double xEndDynamic = xEndStatic + (runningRadius * Math.cos(Math.toRadians(gradientSlopeDegree)));
                    double yEndDynamic = yEndStatic + (runningRadius * Math.sin(Math.toRadians(gradientSlopeDegree)));

                    LinearGradient gradient = new LinearGradient(xStartDynamic, yStartDynamic, xEndDynamic, yEndDynamic,
                            false, CycleMethod.REPEAT, new Stop(0, Colors.LIGHT_GREEN_2.getColor()),
                            new Stop(0.35, Colors.LIGHT_GREEN_5.getColor()),
                            new Stop(0.75, Colors.LIGHT_GREEN_5.getColor()),
                            new Stop(1, Colors.LIGHT_GREEN_2.getColor()));
                    patchPane.setBackground(new Background(new BackgroundFill(gradient, CornerRadii.EMPTY, Insets.EMPTY)));
                }
            });
            timeline.getKeyFrames().add(kf);
        }
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();


        GridPane patchScrollPane = new GridPane();
        setGrow(patchScrollPane);
        patchScrollPane.setAlignment(Pos.TOP_CENTER);
        patchScrollPane.setMinWidth(minWidth);
        patchScrollPane.setMaxWidth(maxWidth);
        patchScrollPane.setMinHeight(minHeight);
        patchScrollPane.setMaxHeight(maxHeight);
        patchScrollPane.setStyle("-fx-background-color: rgb(45,45,45)");

        GridPane patchVBoxPane = new GridPane();
        setGrow(patchVBoxPane);
        patchVBoxPane.setAlignment(Pos.TOP_LEFT);
        patchVBoxPane.setMinWidth(minWidth);
        patchVBoxPane.setMaxWidth(maxWidth);
        patchVBoxPane.setMinHeight(minHeight);
        patchVBoxPane.setMaxHeight(5000);

        Label titleLabel = patchLoader.getTitleLabel();
        setAlignment(titleLabel, HPos.LEFT, VPos.CENTER);
        setGrow(titleLabel);
        titleLabel.setStyle("-fx-text-size: 80; -fx-text-fill: rgb(255, 255, 255);");
        titleLabel.setFont(Font.loadFont(Fonts.SELAWK_BOLD.get(), 80));
        titleLabel.setTranslateX(75);

        Rectangle separator1 = new Rectangle();
        setGrow(separator1);
        setAlignment(separator1, HPos.LEFT ,VPos.CENTER);
        //separator.setEffect(new BlurDropShadow(Color.rgb(37,37, 37), 4, 0));
        separator1.setHeight(80);
        separator1.setWidth(15);
        separator1.setTranslateX(50);
        separator1.setArcHeight(15);
        separator1.setArcWidth(15);
        separator1.setFill(Color.rgb(255, 255, 255));

        /* VERSION CLASSIQUE */
        Label patchLabel = patchLoader.getPatchLabel();
        setAlignment(patchLabel, HPos.LEFT, VPos.TOP);
        setGrow(titleLabel);
        patchLabel.setStyle("-fx-text-size: 17; -fx-text-fill: rgb(0, 0, 0);");
        patchLabel.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 20));
        patchLabel.setTranslateX(50);

        for (int i = 0; i < patchNoteLabelSize; i++) {

            double widthOfOneGradientCycleS = 600;
            double gradientSlopeDegreeS = 20;
            double xStartStaticS = 10;
            double yStartStaticS = 10;
            double xEndStaticS = xStartStaticS + (widthOfOneGradientCycleS * Math.cos(Math.toRadians(gradientSlopeDegreeS)));
            double yEndStaticS = yStartStaticS + (widthOfOneGradientCycleS * Math.sin(Math.toRadians(gradientSlopeDegreeS)));

            ArrayList<Label> labels = patchNoteLabel.get(i);
            int labelsSize = labels.size();
            double length = labels.get(0).getText().replace(" ","").length();
            double separatorLength = 0;
            Rectangle separator = new Rectangle();
            setGrow(separator);
            setAlignment(separator, HPos.LEFT ,VPos.TOP);
            //separator.setEffect(new BlurDropShadow(Color.rgb(37,37, 37), 4, 0));
            separator.setHeight(15);
            separator.setWidth(length * 27.25 + 6 * (length - labels.get(0).getText().length()));
            separator.setTranslateX(44d);
            separator.setTranslateY(75);
            separator.setArcHeight(15);
            separator.setArcWidth(15);
            separator.setFill(LG_GREEN_2);

            Timeline timelineS = new Timeline();
            for (int k = 0; k < 500; k++) {
                int innerIterator = k;
                KeyFrame kf = new KeyFrame(Duration.millis(30 * innerIterator), new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent ae) {

                        double runningRadius = innerIterator * (widthOfOneGradientCycleS * 0.002);
                        double xStartDynamic = xStartStaticS + (runningRadius * Math.cos(Math.toRadians(gradientSlopeDegreeS)));
                        double yStartDynamic = yStartStaticS + (runningRadius * Math.sin(Math.toRadians(gradientSlopeDegreeS)));
                        double xEndDynamic = xEndStaticS + (runningRadius * Math.cos(Math.toRadians(gradientSlopeDegreeS)));
                        double yEndDynamic = yEndStaticS + (runningRadius * Math.sin(Math.toRadians(gradientSlopeDegreeS)));

                        LinearGradient gradient = new LinearGradient(xStartDynamic, yStartDynamic, xEndDynamic, yEndDynamic,
                                false, CycleMethod.REPEAT, new Stop(0, Colors.LIGHT_GREEN_5.getColor()),
                                new Stop(0.45, Colors.LIGHT_GREEN_2.getColor()),
                                new Stop(0.6, Colors.LIGHT_GREEN_2.getColor()),
                                new Stop(1, Colors.LIGHT_GREEN_5.getColor()));
                        separator.setFill(gradient);
                    }
                });
                timelineS.getKeyFrames().add(kf);
            }
            timelineS.setCycleCount(Timeline.INDEFINITE);
            timelineS.play();

            Label categories = labels.get(0);

            Label note = labels.get(1);

            patchVBoxPane.add(categories,0, i);

            if (categories.getWidth() > 15) {
                separator.setWidth(categories.getWidth() + 15);
            }

            patchVBoxPane.add(separator,0, i);
            patchVBoxPane.add(note,0, i);
        }

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
        setAlignment(vBox, HPos.LEFT, VPos.TOP);
        vBox.setMinWidth(patchScrollPane.getMinWidth() - 25);
        vBox.setMaxWidth(patchScrollPane.getMaxWidth() - 25);
        vBox.setMinHeight(patchScrollPane.getMinHeight() + 500);
        vBox.setMaxHeight(patchScrollPane.getMaxHeight() + 500);
        vBox.setTranslateY(10);

        //patchVBoxPane.getChildren().add(patchLabel);

        patchScrollPane.getChildren().add(scrollPane);
        scrollPane.setContent(vBox);
        vBox.getChildren().add(0, patchVBoxPane);

        patchPane.getChildren().addAll(separator1, titleLabel);

        pane.add(patchPane,0,0);
        pane.add(patchScrollPane,0,1);

    }
}
