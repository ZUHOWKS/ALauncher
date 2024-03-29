package zuhowks.asiluxteam.fr.launcher.ui;

import com.goxr3plus.fxborderlessscene.borderless.BorderlessScene;
import fr.flowarg.flowcompat.Platform;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import zuhowks.asiluxteam.fr.launcher.ALauncher;
import zuhowks.asiluxteam.fr.launcher.ui.panel.IPanel;
import zuhowks.asiluxteam.fr.launcher.ui.panels.partials.TopBar;

public class PanelManager {
    private final zuhowks.asiluxteam.fr.launcher.ALauncher ALauncher;
    private final Stage stage;
    private GridPane layout;
    private final GridPane contentPane = new GridPane();

    public PanelManager(ALauncher ALauncher, Stage stage) {
        this.ALauncher = ALauncher;
        this.stage = stage;
    }

    public void init() {
        this.stage.setTitle("Asilux");
        this.stage.setMinWidth(1280);
        this.stage.setMinHeight(720);
        this.stage.setWidth(1280);
        this.stage.setHeight(720);
        this.stage.centerOnScreen();
        this.stage.getIcons().add(new Image("images/asilux-icon.png"));

        this.layout = new GridPane();

        if (Platform.isOnLinux()) {
            Scene scene = new Scene(this.layout);
            this.stage.setScene(scene);
        } else {
            this.stage.initStyle(StageStyle.UNDECORATED);

            TopBar topBar = new TopBar();
            BorderlessScene scene = new BorderlessScene(this.stage, StageStyle.UNDECORATED, this.layout);
            scene.setResizable(true);
            scene.setMoveControl(topBar.getLayout());
            scene.removeDefaultCSS();

            this.stage.setScene(scene);

            RowConstraints topPaneContraints = new RowConstraints();
            topPaneContraints.setValignment(VPos.TOP);
            topPaneContraints.setMinHeight(25);
            topPaneContraints.setMaxHeight(25);
            this.layout.getRowConstraints().addAll(topPaneContraints, new RowConstraints());
            this.layout.add(topBar.getLayout(), 0, 0);
            topBar.init(this);
        }

        this.layout.add(this.contentPane, 0,1);
        this.layout.setStyle(
                "-fx-background-image: url('/background.png');" +
                        "-fx-background-repeat: stretch;" +
                        "-fx-background-position: center center;" +
                        "-fx-background-size: cover;"
        );
        GridPane.setVgrow(this.contentPane, Priority.ALWAYS);
        GridPane.setHgrow(this.contentPane, Priority.ALWAYS);

        this.stage.show();
    }

    public void showPanel(IPanel panel) {
        this.contentPane.getChildren().clear();
        this.contentPane.getChildren().add(panel.getLayout());
        if (panel.getStyleSheetPath() != null) {
            this.stage.getScene().getStylesheets().clear();
            this.stage.getScene().getStylesheets().add(panel.getStyleSheetPath());
        }
        panel.init(this);
        panel.onShow();
    }

    public Stage getStage() {
        return stage;
    }

    public ALauncher getLauncher() {
        return ALauncher;
    }
}
