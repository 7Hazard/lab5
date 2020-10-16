package kth.views;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import kth.controllers.GameController;
import kth.models.TileModel;

public class TileView extends Group {
    public static final int TILE_SIZE = 100;
    private boolean isMarked = false;
    boolean colored;
    Rectangle rect;
    PieceView pieceView;

    public TileView(TileModel tile, int x, int y, boolean colored) {
        this.colored = colored;

        rect = new Rectangle();
        rect.setWidth(TILE_SIZE);
        rect.setHeight(TILE_SIZE);

        relocate(x * TILE_SIZE, y * TILE_SIZE);

        if (colored)
            rect.setFill(Color.BROWN);
        else
            rect.setFill(Color.DARKGRAY);
        getChildren().add(rect);
        
        if(tile.hasPiece())
        {
            pieceView = new PieceView(tile.getPiece(), x, y);
            getChildren().add(pieceView);
        }
        else {
            setOnMouseClicked(this::onClick);
        }
    }

    private void onClick(MouseEvent mouseEvent) {
        GameController.get().onSelectTile(this);
    }

    public boolean isMarked() {
        return isMarked;
    }

    public void mark() {
        isMarked = true;
        rect.setFill(Color.GREEN);
    }

    public void unmark() {
        isMarked = false;
        if (colored)
            rect.setFill(Color.BROWN);
        else
            rect.setFill(Color.DARKGRAY);
    }
}
