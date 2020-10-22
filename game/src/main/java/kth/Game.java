package kth;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
        gameView.updateInfo();
    }

    public void save() {
        var savename = "";

        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Skriv namnet på sparningen");
        var s = dialog.showAndWait();
        if(s.isEmpty()) return;
        else if(!s.get().isEmpty()) savename = s.get();
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Fel inmatning på sparnamnet");
            alert.showAndWait();
            return;
        }

        try {
            gameView.getBoardView().getModel().save(savename);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Fel vid sparning");
            alert.setContentText("Kunde inte spara med namnet '"+savename+"'\nException: "+e.getLocalizedMessage());
            alert.showAndWait();

            System.err.println("Could not save to "+savename);
            e.printStackTrace();
        }
    }

    public void load() {
        var savename = "";

        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText("Skriv namnet på sparningen");
        var s = dialog.showAndWait();
        if(s.isEmpty()) return;
        else if(!s.get().isEmpty()) savename = s.get();
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Invalid save name");
            alert.showAndWait();
            return;
        }

        try {
            gameView.getBoardView().reset(BoardModel.load(savename));
            gameView.updateInfo();
        } catch (Throwable e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Fel vid laddning");
            alert.setContentText("Kunde inte ladda från '"+savename+"'\nException: "+e.getLocalizedMessage());
            alert.showAndWait();

            System.err.println("Could not load from "+savename);
            e.printStackTrace();
        }
    }

    public void end(PieceColor winner) {
        gameView.getBoardView().getModel().setCurrentTurn(PieceColor.None);
        gameView.showWinner(winner);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(winner.localName() + " är vinnaren!");
        alert.showAndWait();

        GameController.get().restart();
    }
}
