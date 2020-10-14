package kth;

import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import kth.models.BoardModel;
import kth.views.BoardView;

/**
 * JavaFX App
 */
public class App extends Application {
    private static App app;
    public static App get() {
        return app;
    }
    
    private Stage stage;
    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    public void draw() {
        var boardview = new BoardView(BoardModel.get());
        Scene scene = new Scene(boardview);
        stage.setScene(scene);
        
        stage.setTitle("Turkisk Dam");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        app = this;
        this.stage = stage;
        draw();
    }
}