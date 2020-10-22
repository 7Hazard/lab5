package kth.views;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import kth.controllers.GameController;
import kth.models.BoardModel;

public class GameView extends VBox {

    private final BoardView boardView;

    public GameView() {

        MenuBar menuBar = new MenuBar();
        {
            Menu menu = new Menu("Game");
            MenuItem restart = new MenuItem("Restart");
            restart.setOnAction(this::onClickRestart);
            menu.getItems().add(restart);
            menuBar.getMenus().add(menu);
        }

        boardView = new BoardView(new BoardModel());
        getChildren().addAll(menuBar, boardView);
    }

    public BoardView getBoardView() {
        return boardView;
    }

    private void onClickRestart(ActionEvent actionEvent) {
        GameController.get().restart();
    }
}
