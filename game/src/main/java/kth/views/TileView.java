package kth.views;

import javafx.scene.*;
import javafx.scene.input.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import kth.*;
import kth.controllers.*;
import kth.models.*;

public class TileView extends Group {
    private final TileModel model;
    private final int x;
    private final int y;
    private final Rectangle rect;
    private final Color color;
    private PieceView pieceView;

    public TileView(TileModel model, int x, int y, boolean colored) {
        this.model = model;
        this.x = x;
        this.y = y;

        rect = new Rectangle();
        rect.setWidth(Game.TILE_SIZE);
        rect.setHeight(Game.TILE_SIZE);

        relocate(x * Game.TILE_SIZE, y * Game.TILE_SIZE);
        
        if (colored)
            color = Color.BROWN;
        else
            color = Color.DARKGRAY;
        rect.setFill(color);
        
        getChildren().add(rect);

        if (model.hasPiece()) {
            pieceView = new PieceView(model.getPieceModel(), this);
            getChildren().add(pieceView);
        } else {
            enableOnClickHandler();
        }
    }
    
    public void enableOnClickHandler() {
        setOnMouseClicked(this::onClick);
    }
    
    public void disableOnClickHandler() {
        setOnMouseClicked(null);
    }

    public void setPiece(PieceView piece) {
        piece.tileView.enableOnClickHandler();
        piece.tileView = this;
        this.pieceView = piece;
        getChildren().add(piece);
        this.disableOnClickHandler();
    }

    public void removePiece(){
        this.pieceView.setTileView(null);
        getChildren().remove(this.pieceView);
        this.pieceView = null;
        enableOnClickHandler();
    }

    private boolean isMarked = false;

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        if (marked)
            rect.setFill(Color.GREEN);
        else
            rect.setFill(color);
        
        isMarked = marked;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public TileModel getModel() {
        return model;
    }

    private void onClick(MouseEvent mouseEvent) {
        GameController.get().onSelectTile(this);
    }
}
