package dev.asiluxserver.launcher;

import dev.asiluxserver.launcher.files.FileManager;
import dev.asiluxserver.launcher.ui.PanelManager;
import dev.asiluxserver.launcher.ui.panels.LoginPanel;
import fr.arinonia.arilibfx.updater.DownloadJob;
import fr.arinonia.arilibfx.updater.DownloadManager;
import fr.arinonia.arilibfx.updater.Updater;
import javafx.stage.Stage;

public class AsiluxLauncher {

    private final FileManager fileManager = new FileManager("asiluxTest");


    private PanelManager panelManager;
    private LoginPanel loginPanel;

    public void init(Stage stage) {
        if (!fileManager.createGameDir().exists())fileManager.createGameDir().mkdir();

        this.panelManager = new PanelManager(this, stage);
        this.panelManager.init();
        this.panelManager.showPanel(new LoginPanel());
    }

    public void launchGame(){
        Updater updater = new Updater();
        DownloadJob game = new DownloadJob("game", this.loginPanel.getHomePanel());
        game.setExecutorService(5);
        updater.addJobToDownload(new DownloadManager("http://127.0.0.1/instances/instance.json", game , fileManager.getGameFolder()));
        updater.setFileDeleter(true);
        Thread thread = new Thread(updater::start);
        thread.start();
    }
}
