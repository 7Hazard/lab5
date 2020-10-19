package kth;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kth.controllers.GameController;
import kth.models.BoardModel;
import kth.views.BoardView;

public class Game {
    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private static Game singleton = new Game();
    private Stage stage;
    private GameController gameController = new GameController();
    private VBox vbox;
    private BoardView boardview;

    public static Game get() {
        return singleton;
    }
    
    void init(Stage stage) {
        this.stage = stage;

        stage.setTitle("Turkisk Dam");

        MenuBar menuBar = new MenuBar();
        {
            Menu menu = new Menu("Game");
            MenuItem restart = new MenuItem("Restart");
            restart.setOnAction(actionEvent -> gameController.restart());
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

    public void draw() {
        stage.show();
    }

    public BoardView getBoardView() {
        return boardview;
    }
    
    public static boolean posIsValid(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }
}
