package kth.views;

import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import kth.PieceColor;
import kth.controllers.GameController;
import kth.models.BoardModel;

import static kth.Game.TILE_SIZE;
import static kth.Game.WIDTH;

public class GameView extends VBox {

    private final BoardView boardView;
    private final Pane infoBar;

    public BoardView getBoardView() {
        return boardView;
    }

    public GameView() {
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Game");
        // Buttons
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

        menuBar.getMenus().addAll(menu);

        boardView = new BoardView(new BoardModel());

        infoBar = new Pane();
        infoBar.setPrefSize(WIDTH * TILE_SIZE, 20);
        updateInfo();

        getChildren().addAll(menuBar, infoBar, boardView);
    }

    public void updateInfo() {
        infoBar.getChildren().clear();

        var turn = boardView.getModel().getCurrentTurn();
        Text text = new Text(10, 15, turn.name() + "'s turn");

        infoBar.getChildren().addAll(text);
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
