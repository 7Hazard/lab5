package kth;

import javafx.scene.*;

public class Board {

    private static int HEIGHT = 8;
    private static int WIDTH = 8;

    private Group groupTile = new Group();
    private Group groupPiece = new Group();
    private Tile[][] boardPieces = new Tile[WIDTH][HEIGHT];

    public Board(){

        for(int y = 0; y < HEIGHT; y++){
            for(int x = 0; x < WIDTH; x++){
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                groupTile.getChildren().add(tile);
                CheckersPiece piece = null;

                if(y <= 2 && (x+y)%2!=0){
                    piece = createPiece(PieceType.RED,x,y);
                }
                if(y >= 5 && (x+y)%2!=0 ){
                    piece = createPiece(PieceType.BLACK,x,y);
                }
                if(piece != null){
                    tile.setPiece(piece);
                    groupPiece.getChildren().add(piece);
                }
                boardPieces[x][y]=tile;
            }
        }
    }

    public Group getGroupPiece() {
        return groupPiece;
    }

    public Group getGroupTile() {
        return groupTile;
    }

    private CheckersPiece createPiece(PieceType gamePieceType , int x , int y){
        CheckersPiece gamePiece = new CheckersPiece(gamePieceType,x,y);
        MovePiece makeMove = new MovePiece(gamePiece);

        return gamePiece;
    }

    @Override
    public String toString(){
        StringBuffer buffer = new StringBuffer();

        for(int y = 0; y < HEIGHT ; y++){
            for(int x = 0; x < WIDTH; x++){
                if(boardPieces[x][y].hasPiece()) {
                    buffer.append("O");
                }
                else
                    buffer.append("x");
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
