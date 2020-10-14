package kth.views;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import kth.App;
import kth.models.TileModel;

public class TileView extends Group {
    private final TileModel tile;

    public TileView(TileModel tile, int x, int y, boolean colored) {
        this.tile = tile;

        Rectangle rect = new Rectangle();
        rect.setWidth(App.TILE_SIZE);
        rect.setHeight(App.TILE_SIZE);

        relocate(x * App.TILE_SIZE, y * App.TILE_SIZE);

        if (colored)
            rect.setFill(Color.BROWN);
        else
            rect.setFill(Color.DARKGRAY);
        getChildren().add(rect);
        
        if(tile.hasPiece())
        {
            var piece = new PieceView(tile.getPiece(), x, y);
            getChildren().add(piece);
        }
    }
}
