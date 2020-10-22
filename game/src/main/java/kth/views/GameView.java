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
    public BoardView getBoardView() {
        return boardView;
    }

    public GameView() {

        MenuBar menuBar = new MenuBar();
        {
            Menu menu = new Menu("Game");

            {
                MenuItem btn = new MenuItem("Load");
                btn.setOnAction(this::onClickLoad);
                menu.getItems().add(btn);
            }
            {
                MenuItem btn = new MenuItem("Save");
                btn.setOnAction(this::onClickSave);
                menu.getItems().add(btn);
            }
            {
                MenuItem btn = new MenuItem("Restart");
                btn.setOnAction(this::onClickRestart);
                menu.getItems().add(btn);
            }

            menuBar.getMenus().add(menu);
        }

        boardView = new BoardView(new BoardModel());
        getChildren().addAll(menuBar, boardView);
    }

    private void onClickLoad(ActionEvent actionEvent) {
        GameController.get().load();
    }

    private void onClickSave(ActionEvent actionEvent) {
        GameController.get().save();
    }

    private void onClickRestart(ActionEvent actionEvent) {
        GameController.get().restart();
    }
}
