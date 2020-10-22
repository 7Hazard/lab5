package kth;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kth.controllers.GameController;
import kth.models.BoardModel;
import kth.views.BoardView;
import kth.views.GameView;

import java.io.IOException;

public class Game {
    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private static Game singleton = new Game();
    private Stage stage;
    private GameView gameView;

    public static Game get() {
        return singleton;
    }
    
    void init(Stage stage) {
        this.stage = stage;

        stage.setTitle("Turkisk Dam");

        gameView = new GameView();

        Scene scene = new Scene(gameView);
        stage.setScene(scene);

        draw();
    }

    public void draw() {
        stage.show();
    }

    public GameView getGameView() {
        return gameView;
    }
    
    public static boolean posIsValid(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    public void reset() {
        gameView.getBoardView().reset(new BoardModel());
    }

    public void save() {
        gameView.getBoardView().getModel().save("tmp");
    }

    public void load() {
        var savename = "tmp";

        try {
            gameView.getBoardView().reset(BoardModel.load("tmp"));
        } catch (Throwable e) {
            System.err.println("Could not load from "+savename);
            e.printStackTrace();
        }
    }
}
