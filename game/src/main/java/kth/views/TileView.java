package kth.views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import kth.App;
import kth.models.TileModel;

public class TileView extends Rectangle {
    private final TileModel tile;

    public TileView(TileModel tile, int x, int y, boolean colored) {
        this.tile = tile;

        setWidth(App.TILE_SIZE);
        setHeight(App.TILE_SIZE);

        relocate(x * App.TILE_SIZE, y * App.TILE_SIZE);

        if (colored)
            setFill(Color.BROWN);
        else
            setFill(Color.DARKGRAY);
    }
}
