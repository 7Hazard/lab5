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
    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    private Group groupTile = new Group();
    private Group groupPiece = new Group();

   private Parent createContent(){
       Pane root = new Pane();
       root.setPrefSize(WIDTH*TILE_SIZE, HEIGHT * TILE_SIZE);
       root.getChildren().addAll(groupTile,groupPiece);

       for(int y = 0; y < HEIGHT; y++){
           for(int x = 0; x < WIDTH; x++){
                Tile tile = new Tile((x + y) % 2 == 0, x, y);

                groupTile.getChildren().add(tile);
           }
       }

       return root;
    }
    
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Turkish Chekers");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}