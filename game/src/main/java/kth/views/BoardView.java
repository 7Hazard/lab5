package kth.views;

import javafx.scene.layout.Pane;
import kth.controllers.GameController;
import kth.models.BoardModel;

import static kth.App.*;
import static kth.views.TileView.TILE_SIZE;

public class BoardView extends Pane {
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;

    TileView[][] tileViews;

    public BoardView() {
        reset();
    }

    public void reset() {
        BoardModel board = BoardModel.get();
        tileViews = new TileView[WIDTH][HEIGHT];
        var children = this.getChildren();
        this.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);

        var colored = false;
        for (int y = 0; y < board.tiles.length; y++) {
            for (int x = 0; x < board.tiles[y].length; x++) {
                var tile = board.tiles[x][y];

                var tileview = new TileView(tile, x, y, colored);
                tileViews[x][y] = tileview;
                children.add(tileview);

                colored = !colored;
            }
            colored = !colored;
        }
    }

    public boolean posIsValid(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }

    TileView getTile(int x, int y) {
        return tileViews[x][y];
    }

}
