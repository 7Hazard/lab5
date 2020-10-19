package kth.views;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import kth.Game;
import kth.controllers.TileController;
import kth.models.TileModel;

public class TileView extends Group {
    private final TileModel tile;
    private TileController controller;

    public TileView(TileModel tile, int x, int y, boolean colored) {
        this.tile = tile;
        this.controller = new TileController(tile);

        Rectangle rect = new Rectangle();
        rect.setWidth(Game.TILE_SIZE);
        rect.setHeight(Game.TILE_SIZE);

        relocate(x * Game.TILE_SIZE, y * Game.TILE_SIZE);

        if(tile.isMarked())
            rect.setFill(Color.GREEN);
        else if (colored)
            rect.setFill(Color.BROWN);
        else
            rect.setFill(Color.DARKGRAY);
        getChildren().add(rect);
        
        if(tile.hasPiece())
        {
            var piece = new PieceView(tile.getPiece());
            getChildren().add(piece);
        }
        else {
            setOnMouseClicked(mouseEvent -> controller.onClick());
        }
    }
}
