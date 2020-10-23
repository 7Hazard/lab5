package kth;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import kth.controllers.GameController;
import kth.models.BoardModel;
import kth.views.BoardView;
import kth.views.GameView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class Game {
    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private static Game singleton = new Game();
    private GameController controller = new GameController();
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

    public void reset() {
        gameView.getBoardView().reset(new BoardModel());
        gameView.updateInfo();
    }

    public void save() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(Paths.get("./").toFile());
        fileChooser.setTitle("Spara spelet i en fil");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Save Files", "*.save"));
        var res = fileChooser.showSaveDialog(stage);
        if(res == null) return;
        var savename = res.getAbsolutePath();

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
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(Paths.get("./").toFile());
        fileChooser.setTitle("Öppna sparfilen");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Save Files", "*.save"));
        var res = fileChooser.showOpenDialog(stage);
        if(res == null) return;
        var savename = res.getAbsolutePath();

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
        alert.setTitle("Vi har en vinnare");
        alert.setHeaderText(winner.localName() + " är vinnaren!");
        alert.showAndWait();

        controller.restart();
    }

    public GameController getController() {
        return controller;
    }
}
