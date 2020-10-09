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
    //private static final int DIAMETER = 8;

    private Group groupTile = new Group();
    private Group groupPiece = new Group();
    private Tile[][] board = new Tile[WIDTH][HEIGHT];

   private Parent createContent(){
       Pane root = new Pane();
       root.setPrefSize(WIDTH*TILE_SIZE, HEIGHT * TILE_SIZE);
       root.getChildren().addAll(groupTile,groupPiece);

       for(int y = 0; y < HEIGHT; y++){
           for(int x = 0; x < WIDTH; x++){
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                groupTile.getChildren().add(tile);

                Piece piece = null;
                board[x][y] = tile;

                if(y <= 2 && (x+y)%2!=0){
                    piece = makePiece(PieceType.RED,x,y);
                }
                if(y >= 5 && (x+y)%2!=0 ){
                    piece = makePiece(PieceType.BLACK,x,y);
                }
                if(piece != null){
                    tile.setPiece(piece);
                    groupPiece.getChildren().add(piece);
                }
           }
       }

       return root;
    }

    private Piece makePiece(PieceType gamePieceType ,int x , int y){
        Piece gamePiece = new Piece(gamePieceType,x,y);
        return gamePiece;
    }

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        primaryStage.setTitle("Chekers");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    @Override
    public String toString(){
       StringBuffer buffer = new StringBuffer();
       buffer.append(this.board);
       return buffer.toString();
    }
}