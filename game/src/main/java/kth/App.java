package kth;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.stage.*;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private Board gameBoard;
    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

   private Parent createContent(){
       gameBoard = new Board();
       Pane root = new Pane();
       root.setPrefSize(WIDTH*TILE_SIZE, HEIGHT * TILE_SIZE);
       root.getChildren().addAll(gameBoard.getGroupTile(),gameBoard.getGroupPiece());

       System.out.println(gameBoard.toString());
       return root;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Chekers");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}