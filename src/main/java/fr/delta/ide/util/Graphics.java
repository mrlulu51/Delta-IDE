package fr.delta.ide.util;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;
import java.util.function.Supplier;

public interface Graphics {

    static ImageView createImage(String path) throws IOException {
        InputStream input = Graphics.class.getResourceAsStream("/assets/textures/" + path + ".png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);

        return imageView;
    }

    interface MenuItemFactory extends Supplier<MenuItem> {
        private static MenuItemFactory makeMenuItem(MenuItem item) {
            item.setId(item.getText());
            return () -> item;
        }

        static MenuItemFactory makeMenuItem(Object text) {
            return makeMenuItem(new MenuItem(text.toString()));
        }

        default MenuItemFactory setAction(EventHandler event) {
            accept(item -> item.setOnAction(event));
            return this;
        }

        default MenuItemFactory graphic(ImageView graphic) {
            accept(item -> item.setGraphic(graphic));
            return this;
        }

        default void accept(Consumer<MenuItem> consumer) {
            consumer.accept(get());
        }
    }

    interface StageFactory extends Supplier<Stage> {
        static StageFactory makeStage(Stage stage) {
            return () -> stage;
        }

        default StageFactory title(Object title) {
            accept(stage -> stage.setTitle(title.toString()));
            return this;
        }

        default StageFactory setWidth(int width) {
            accept(stage -> stage.setWidth(width));
            return this;
        }

        default StageFactory setHeight(int height) {
            accept(stage -> stage.setHeight(height));
            return this;
        }

        default StageFactory setIcon(ImageView icon) {
            accept(stage -> stage.getIcons().addAll(icon.getImage()));
            return this;
        }

        default StageFactory scene(Scene scene) {
            accept(stage -> stage.setScene(scene));
            return this;
        }

        default StageFactory show() {
            accept(Stage::show);
            return this;
        }

        default void accept(Consumer<Stage> consumer) {
            consumer.accept(get());
        }
    }
}
