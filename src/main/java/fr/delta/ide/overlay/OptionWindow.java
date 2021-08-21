package fr.delta.ide.overlay;

import javafx.scene.Scene;
import javafx.stage.Stage;

import static fr.delta.ide.util.Graphics.StageFactory.*;

public class OptionWindow {

    private Scene optionScene;

    public OptionWindow() {
        Stage options = makeStage(new Stage())
                .title("Preferences")
                .setWidth(800)
                .setHeight(600)
                .scene(this.optionScene)
                .show()
                .get();
    }
}
