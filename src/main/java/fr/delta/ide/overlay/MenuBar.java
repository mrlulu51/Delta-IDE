package fr.delta.ide.overlay;

import javafx.scene.control.Menu;
import javafx.scene.control.SeparatorMenuItem;

import static fr.delta.ide.util.LangManager.*;
import static fr.delta.ide.util.Graphics.MenuItemFactory.*;

public class MenuBar extends javafx.scene.control.MenuBar {

    public void displayButtonsInBar() {
        final var fileButton = new Menu(getString("menu.file", getCurrent));
        final var editButton = new Menu(getString("menu.edit", getCurrent));
        final var runButton = new Menu(getString("menu.run", getCurrent));
        final var viewButton = new Menu(getString("menu.view", getCurrent));
        final var helpButton = new Menu(getString("menu.help", getCurrent));

        createFileMenu(fileButton);

        getMenus().addAll(fileButton, editButton, runButton, viewButton, helpButton);
    }

    public void createFileMenu(Menu fileMenu) {
        createNewButtonInFileMenu(fileMenu);
        fileMenu.getItems().add(new SeparatorMenuItem());
    }

    public void createNewButtonInFileMenu(Menu fileMenu) {
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
}
