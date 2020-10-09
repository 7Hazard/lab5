package kth;

import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

//TODO: Add own Macros to piece size

public class Piece extends StackPane {
        private static int PIECE_RADIUS = 40;
        private static int TILE_SIZE = 100;
        private PieceType gamePieceType;

        public PieceType getGamePieceType(){
                return this.gamePieceType;
        }

        public Piece(PieceType gamePieceType, int x,int y ){
                this.gamePieceType = gamePieceType;

                relocate((TILE_SIZE) * x  , (TILE_SIZE) * y );

                Circle circle = new Circle(PIECE_RADIUS,null);

                if(gamePieceType == PieceType.RED){
                        circle.setFill(Color.RED);
                }else {
                        circle.setFill(Color.BLACK);
                }
                circle.setStroke(Color.WHITE);
                circle.setStrokeWidth(PIECE_RADIUS * 0.1);

               circle.setTranslateX((TILE_SIZE - 2*PIECE_RADIUS )/2);
               circle.setTranslateY((TILE_SIZE - 2*PIECE_RADIUS )/2);

                getChildren().addAll(circle);
        }
}
