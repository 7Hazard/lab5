package kth;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import kth.controllers.MenuController;
import kth.models.BoardModel;
import kth.views.BoardView;

/**
 * JavaFX App
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Game.get().init(stage);
    }
}