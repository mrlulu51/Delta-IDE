package fr.delta.ide;

import fr.delta.ide.overlay.MenuBar;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import static fr.delta.ide.util.LangManager.*;
import static fr.delta.ide.util.Graphics.StageFactory.*;

public class Delta extends Application {

    public Scene mainWindow;

    @Override
    public void start(Stage stage) {
        final var menuBar = new MenuBar();
        menuBar.displayButtonsInBar();
        createComponents(menuBar, stage);

        Stage window = makeStage(stage)
                .title(getString("window.title", getCurrent))
                .scene(this.mainWindow)
                .setWidth(800)
                .setHeight(600)
                .show()
                .get();
    }

    public void createComponents(Node topMenu, Stage window) {
        final var borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        mainWindow = new Scene(borderPane);
    }
}
