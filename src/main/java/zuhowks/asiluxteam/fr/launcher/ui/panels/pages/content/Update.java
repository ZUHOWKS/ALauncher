package zuhowks.asiluxteam.fr.launcher.ui.panels.pages.content;

import fr.theshark34.openlauncherlib.util.Saver;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.util.Duration;
import zuhowks.asiluxteam.fr.launcher.ALauncher;
import zuhowks.asiluxteam.fr.launcher.Main;
import zuhowks.asiluxteam.fr.launcher.ui.PanelManager;
import zuhowks.asiluxteam.fr.launcher.ui.assets.Colors;
import zuhowks.asiluxteam.fr.launcher.ui.assets.Fonts;
import zuhowks.asiluxteam.fr.launcher.utils.patch.PatchMessage;
import zuhowks.asiluxteam.fr.launcher.utils.patch.PatchNote;

import java.net.URL;
import java.util.ArrayList;

public class Update extends ContentPanel{

    ALauncher launcher = ALauncher.getInstance();

    private final Saver saver = launcher.getSaver();
    PatchMessage patchMessage = launcher.getPatchMessage();

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
        contentPane.setMinHeight(720);

        rowConstraints.setValignment(VPos.TOP);
        rowConstraints.setMinHeight(150);
        rowConstraints.setMaxHeight(175);
        contentPane.getRowConstraints().addAll(rowConstraints, new RowConstraints());

        this.layout.getChildren().add(contentPane);

        loadPatch(contentPane);

    }

    private void loadPatch(GridPane pane) {

        ArrayList<PatchNote> patchNotes = patchMessage.getPatchNotes();

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
        patchScrollPane.setStyle("-fx-background-color: rgb(45,45,45)");
        patchScrollPane.setMaxHeight(50000);

        Label titleLabel = new Label(patchMessage.getTitle());
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

        int line= 0;

        /* SCROLL BAR */
        VBox vBox = new VBox();
        setGrow(vBox);
        setAlignment(vBox, HPos.LEFT, VPos.TOP);
        vBox.setMinWidth(minWidth - 25);
        vBox.setMaxWidth(maxWidth - 25);
        vBox.setMaxHeight(50000);

        /* SCROLL PANE STYLE */
        ScrollPane scrollPane = new ScrollPane();
        setGrow(scrollPane);
        setAlignment(scrollPane, HPos.CENTER, VPos.CENTER);
        URL resource = Main.class.getResource("/css/scroll-pane.css");
        if (resource != null) {
            scrollPane.getStylesheets().addAll(resource.toExternalForm());
        }

        for (int i = 0; i < patchNotes.size(); i++) {

            GridPane patchVBoxPane = new GridPane();
            setGrow(patchVBoxPane);
            patchVBoxPane.setAlignment(Pos.TOP_LEFT);
            patchVBoxPane.setMinWidth(minWidth);
            patchVBoxPane.setMaxWidth(maxWidth);

            patchVBoxPane.add(new Label("  "), 0, line);
            line++;

            double widthOfOneGradientCycleS = 600;
            double gradientSlopeDegreeS = 20;
            double xStartStaticS = 10;
            double yStartStaticS = 10;
            double xEndStaticS = xStartStaticS + (widthOfOneGradientCycleS * Math.cos(Math.toRadians(gradientSlopeDegreeS)));
            double yEndStaticS = yStartStaticS + (widthOfOneGradientCycleS * Math.sin(Math.toRadians(gradientSlopeDegreeS)));




            //line += i > 0 ? patchNotes.get(i-1).getNote().size() + 4 : 0;

            Label categoriesLabel = new Label(patchNotes.get(i).getCategories());
            categoriesLabel.setAlignment(Pos.TOP_LEFT);
            GridPane.setHalignment(categoriesLabel, HPos.LEFT);
            GridPane.setValignment(categoriesLabel, VPos.TOP);
            GridPane.setHgrow(categoriesLabel, Priority.ALWAYS);
            GridPane.setVgrow(categoriesLabel, Priority.ALWAYS);
            categoriesLabel.setStyle("-fx-text-size: 40; -fx-text-fill: rgb(240, 240, 240);");
            categoriesLabel.setFont(Font.loadFont(Fonts.SELAWK_SEMI_BOLD.get(), 40));
            categoriesLabel.setTranslateX(50);

            patchVBoxPane.add(categoriesLabel,0, line);

            Rectangle separator = new Rectangle();
            setGrow(separator);
            setAlignment(separator, HPos.LEFT ,VPos.TOP);
            //separator.setEffect(new BlurDropShadow(Color.rgb(37,37, 37), 4, 0));
            separator.setHeight(15);
            separator.setWidth(categoriesLabel.getFont().getSize() * categoriesLabel.getText().length() * 0.65 + 15d);
            separator.setTranslateX(44d);
            separator.setTranslateY(50);
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

            patchVBoxPane.add(separator,0, line);

            patchVBoxPane.add(new Label("\n "), 0, line+1);
            line+=2;
            for (int k = 0; k < patchNotes.get(i).getNote().size(); k++) {
                String[] strings = patchNotes.get(i).getNote().get(k).split("\n");
                for (String string : strings) {
                    Label noteLabel = new Label(string);
                    noteLabel.setAlignment(Pos.TOP_LEFT);
                    GridPane.setHalignment(noteLabel, HPos.LEFT);
                    GridPane.setValignment(noteLabel, VPos.TOP);
                    GridPane.setHgrow(noteLabel, Priority.ALWAYS);
                    GridPane.setVgrow(noteLabel, Priority.ALWAYS);
                    noteLabel.setStyle("-fx-text-size: 28; -fx-text-fill: rgb(230, 230, 230);");
                    noteLabel.setFont(Font.loadFont(Fonts.SELAWK.get(), 28));
                    noteLabel.setTranslateX(50);
                    patchVBoxPane.add(noteLabel, 0, line);
                    line++;
                }
            }
            patchVBoxPane.add(new Label("\n "), 0, line);
            line++;

            vBox.getChildren().add(patchVBoxPane);
        }

        //patchVBoxPane.getChildren().add(patchLabel);

        patchScrollPane.getChildren().add(scrollPane);
        scrollPane.setContent(vBox);

        patchPane.getChildren().addAll(separator1, titleLabel);

        pane.add(patchPane,0,0);
        pane.add(patchScrollPane,0,1);

    }
}
