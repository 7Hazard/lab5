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
    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    private static App app;
    private BoardView boardview;
    private Stage stage;
    private VBox vbox;

    public static App get() {
        return app;
    }
    
    public void draw() {
        vbox.getChildren().remove(boardview);
        boardview = new BoardView(BoardModel.get());
        vbox.getChildren().add(boardview);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        app = this;
        this.stage = stage;

        stage.setTitle("Turkisk Dam");

        MenuBar menuBar = new MenuBar();
        {
            Menu menu = new Menu("Game");
            MenuItem restart = new MenuItem("Restart");
            menu.getItems().add(restart);
            menuBar.getMenus().add(menu);
        }

        vbox = new VBox(menuBar);
        boardview = new BoardView(BoardModel.get());
        vbox.getChildren().add(boardview);
        
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
        
        draw();
    }
}