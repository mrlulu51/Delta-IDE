package fr.delta.ide.overlay;

import fr.delta.ide.util.NetworkManager;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

import static fr.delta.ide.util.LangManager.*;
import static fr.delta.ide.util.Graphics.StageFactory.*;
import static fr.delta.ide.util.Graphics.MenuItemFactory.*;

public class MenuBar extends javafx.scene.control.MenuBar {

    public void displayButtonsInBar() throws IOException {
        final var fileButton = new Menu(getString("menu.file", getCurrent));
        final var editButton = new Menu(getString("menu.edit", getCurrent));
        final var runButton = new Menu(getString("menu.run", getCurrent));
        final var viewButton = new Menu(getString("menu.view", getCurrent));
        final var helpButton = new Menu(getString("menu.help", getCurrent));

        createFileMenu(fileButton);
        createEditMenu(editButton);
        createHelpMenu(helpButton);

        getMenus().addAll(fileButton, editButton, runButton, viewButton, helpButton);
    }

    public void createFileMenu(Menu fileMenu) throws IOException {
        createNewButtonInFileMenu(fileMenu);
        createOpenButtonsInFileMenu(fileMenu);
        fileMenu.getItems().add(new SeparatorMenuItem());

        createSaveButtonsInFileMenu(fileMenu);
        fileMenu.getItems().add(new SeparatorMenuItem());

        final var optionsButton = makeMenuItem(getString("menu.file.preferences", getCurrent))
                .setAction(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        OptionWindow optionWindow = new OptionWindow();
                    }
                })
                .get();
        final var exitButton = makeMenuItem(getString("menu.file.exit", getCurrent))
                .setAction(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        Platform.exit();
                        Runtime.getRuntime().exit(0);
                    }
                })
                .get();

        fileMenu.getItems().addAll(optionsButton, exitButton);
    }

    public void createNewButtonInFileMenu(Menu fileMenu) throws IOException {
        final var newButton = new Menu(getString("menu.file.new", getCurrent));
        final var newJavaProjectButton = makeMenuItem(getString("menu.file.new.javaProject", getCurrent)).get();
        final var newEmptyProjectButton = makeMenuItem(getString("menu.file.new.emptyProject", getCurrent)).get();
        final var newPackageButton = makeMenuItem(getString("menu.file.new.package", getCurrent)).get();
        final var newFolder = makeMenuItem(getString("menu.file.new.folder", getCurrent)).get();
        final var newFile = makeMenuItem(getString("menu.file.new.file", getCurrent)).get();
        final var newClass = makeMenuItem(getString("menu.file.new.class", getCurrent)).get();
        final var newInterface = makeMenuItem(getString("menu.file.new.interface", getCurrent)).get();
        final var newAbstract = makeMenuItem(getString("menu.file.new.abstract", getCurrent)).get();
        final var newAnnotation = makeMenuItem(getString("menu.file.new.annotation", getCurrent)).get();

        newButton.getItems().addAll(newJavaProjectButton, newEmptyProjectButton, new SeparatorMenuItem(), newPackageButton, newFolder, newFile,
                new SeparatorMenuItem(), newClass, newAbstract, newInterface, newAnnotation);

        fileMenu.getItems().add(newButton);
    }

    public void createOpenButtonsInFileMenu(Menu fileMenu) {
        final var openRecent = new Menu(getString("menu.file.openrecent", getCurrent));
        final var noneProject = makeMenuItem(getString("menu.file.recent.none", getCurrent)).get();
        openRecent.getItems().add(noneProject);

        final var openProject = makeMenuItem(getString("menu.file.open", getCurrent)).get();

        fileMenu.getItems().addAll(openRecent, openProject);
    }

    public void createSaveButtonsInFileMenu(Menu fileMenu) {
        final var saveAll = makeMenuItem(getString("menu.file.saveall", getCurrent)).get();
        final var saveAs = makeMenuItem(getString("menu.file.saveas", getCurrent)).get();
        final var save = makeMenuItem(getString("menu.file.save", getCurrent)).get();

        fileMenu.getItems().addAll(saveAll, saveAs, save);
    }

    public void createEditMenu(Menu editMenu) {
        final var undo = makeMenuItem(getString("menu.edit.undo", getCurrent)).get();
        final var redo = makeMenuItem(getString("menu.edit.redo", getCurrent)).get();

        final var cut = makeMenuItem(getString("menu.edit.cut", getCurrent)).get();
        final var copy = makeMenuItem(getString("menu.edit.copy", getCurrent)).get();
        final var paste = makeMenuItem(getString("menu.edit.paste", getCurrent)).get();

        final var generate = makeMenuItem(getString("menu.edit.generate", getCurrent)).get();
        final var other = makeMenuItem(getString("menu.edit.other", getCurrent)).get();

        editMenu.getItems().addAll(undo, redo, new SeparatorMenuItem(), cut, copy, paste, new SeparatorMenuItem(), generate, other);
    }

    public void createHelpMenu(Menu helpMenu) {
        final var about = makeMenuItem(getString("menu.help.about", getCurrent))
                .setAction(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        String javaVersion = System.getProperty("java.version");
                        String javaFXVersion = System.getProperty("javafx.version");
                        String currentVersion =
                                "Delta-IDE 1.0-SNAPSHOT\n" +
                                "Running on Java " + javaVersion + ", based on JavaFX " + javaFXVersion;
                        Label elements = new Label(currentVersion);
                        Scene aboutScene = new Scene(new StackPane(elements));
                        //PopUp aboutPopup = new PopUp("About Delta IDE", 350, 200);

                        Stage aboutStage = makeStage(new Stage())
                                .title("About")
                                .setWidth(350)
                                .setHeight(200)
                                .scene(aboutScene)
                                .show()
                                .get();
                        aboutStage.setResizable(false);
                    }
                })
                .get();
        final var docs = makeMenuItem(getString("menu.help.docs", getCurrent))
                .setAction(new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        NetworkManager.getOS().openURI("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
                    }
                })
                .get();

        helpMenu.getItems().addAll(about, docs);
    }
}
