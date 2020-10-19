package kth;

import javafx.application.*;
import javafx.stage.*;

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