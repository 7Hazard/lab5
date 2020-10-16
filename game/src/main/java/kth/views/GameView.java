package kth.views;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import kth.controllers.GameController;
import kth.models.BoardModel;

public class GameView extends VBox {

    private static GameView singleton;
    public static GameView get() {
        return singleton;
    }

    private static Stage stage;
    public static void draw() {
        stage.show();
    }

    public final BoardView boardView;

    public GameView(Stage stage) {
        singleton = this;
        stage.setTitle("Turkisk Dam");

        MenuBar menuBar = new MenuBar();
        {
            Menu menu = new Menu("Game");
            MenuItem restart = new MenuItem("Restart");
            restart.setOnAction(this::onClickRestart);
            menu.getItems().add(restart);
            menuBar.getMenus().add(menu);
        }

        boardView = new BoardView();
        this.getChildren().add(menuBar);
        this.getChildren().add(boardView);

        Scene scene = new Scene(this);
        stage.setScene(scene);

        GameView.stage = stage;
    }

    private void onClickRestart(ActionEvent actionEvent) {
        GameController.get().restart();
    }
}
