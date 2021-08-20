package fr.delta.ide;

import fr.delta.ide.overlay.MenuBar;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

import static fr.delta.ide.util.LangManager.*;
import static fr.delta.ide.util.Graphics.StageFactory.*;

public class Delta extends Application {

    public Scene mainWindow;

    @Override
    public void start(Stage stage) throws IOException {
        VBox root = new VBox();

        final var menuBar = new MenuBar();
        menuBar.displayButtonsInBar();
        createComponents(menuBar, stage);

        mainWindow = new Scene(root);
        Stage window = makeStage(stage)
                .title(getString("window.title", getCurrent))
                .scene(this.mainWindow)
                .setWidth(800)
                .setHeight(600)
                //.setIcon(createImage("icon"))
                .show()
                .get();
        window.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void createComponents(Node topMenu, Stage window) {
        final var borderPane = new BorderPane();
        borderPane.setTop(topMenu);
        mainWindow = new Scene(borderPane);
    }
}
