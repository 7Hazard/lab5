package kth.views;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import kth.Game;
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
        Menu menu = new Menu("Meny");
        // Buttons
        {
            MenuItem btn = new MenuItem("Ladda");
            btn.setOnAction(this::onClickLoad);
            menu.getItems().add(btn);
        }
        {
            MenuItem btn = new MenuItem("Spara");
            btn.setOnAction(this::onClickSave);
            menu.getItems().add(btn);
        }
        {
            MenuItem btn = new MenuItem("Starta om");
            btn.setOnAction(this::onClickRestart);
            menu.getItems().add(btn);
        }
        {
            MenuItem btn = new MenuItem("Hjälp");
            btn.setOnAction(this::onClickHelp);
            menu.getItems().add(btn);
        }

        menuBar.getMenus().addAll(menu);

        boardView = new BoardView(new BoardModel());

        infoBar = new Pane();
        infoBar.setPrefSize(WIDTH * TILE_SIZE, 20);
        updateInfo();

        getChildren().addAll(menuBar, infoBar, boardView);
    }

    private void onClickHelp(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hjälp och information");
        alert.setHeaderText("Information om spelet och skaparna");
        alert.setContentText("Turkisk dam är ett strategiskt spel som spelas mellan två personer. " +
                "Spelplanen består av en spelbräda som är 8 rutor bred och 8 rutor lång med ljusa respektive mörka rutor. " +
                "Varje spelare startar med 12 stycken spelpjäser(röda och svarta) och turas om att göra drag. " +
                "Syftet med spelet är att erövra motståndarens spelpjäser genom att hoppa över dem diagonalt. " +
                "Lyckas en spelare nå motståndarens sista rad utökas rörelsemöjligheterna till både röra sig framåt och bakåt. " +
                "Detta kallas för att en spelpjäs blir kung. " +
                "Spelaren som lyckas erövra motståndarens alla spelpjäser har vunnit. " +
                "\n\nEtt spel från Leo Zaki och Milan Languric för Kungliga Tekniska Högskolan");
        alert.showAndWait();
    }

    public void updateInfo() {
        infoBar.getChildren().clear();

        var turn = boardView.getModel().getCurrentTurn();
        Text text = new Text(10, 15, turn.localName() + "s tur");

        infoBar.getChildren().addAll(text);
    }

    public void showWinner(PieceColor winner) {
        infoBar.getChildren().clear();

        Text text = new Text(10, 15, winner.localName() + " är vinnaren!");

        infoBar.getChildren().addAll(text);
    }

    private void onClickLoad(ActionEvent actionEvent) {
        Game.get().getController().load();
    }

    private void onClickSave(ActionEvent actionEvent) {
        Game.get().getController().save();
    }

    private void onClickRestart(ActionEvent actionEvent) {
        Game.get().getController().restart();
    }
}
