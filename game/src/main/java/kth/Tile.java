package kth;

import javafx.scene.paint.*;
import javafx.scene.shape.*;

public class Tile extends Rectangle {
    private CheckersPiece piece;

    public boolean hasPiece(){
        return piece != null;
    }

    public CheckersPiece getPiece(){
        return piece;
    }

    public void setPiece(CheckersPiece piece){
        this.piece = piece;
    }
    public Tile(boolean darkgray, int x, int y){
        setWidth(App.TILE_SIZE);
        setHeight(App.TILE_SIZE);

        relocate(x*App.TILE_SIZE, y*App.TILE_SIZE);

        if(darkgray){
           setFill( Color.DARKGRAY);
        }else
            setFill(Color.BROWN);
    }
}
