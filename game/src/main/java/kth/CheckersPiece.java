package kth;

import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

//TODO: Add own Macros to piece size

public class CheckersPiece extends StackPane {
        private static int PIECE_RADIUS = 40;
        private static int TILE_SIZE = 100;
        private PieceType gamePieceType;
        private MovePiece move;

        private double mouseX, mouseY;
        private double oldX, oldY;

        public PieceType getGamePieceType(){
                return this.gamePieceType;
        }

        public void move(int x, int y) {
                oldX = x * TILE_SIZE;
                oldY = y * TILE_SIZE;
                relocate(oldX, oldY);
        }

        public CheckersPiece(PieceType gamePieceType, int x, int y ){
                this.gamePieceType = gamePieceType;

                move( x  , y );
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
        @Override
        public String toString(){
                StringBuffer buffer = new StringBuffer();
                switch (getGamePieceType()){
                        case RED: buffer.append("R");
                        break;
                        case BLACK: buffer.append("B");
                        break;
                }
                return buffer.toString();
        }
}
