package kth.views;

import javafx.scene.layout.Pane;
import kth.Game;
import kth.models.BoardModel;
import kth.models.PieceModel;

import static kth.Game.*;

public class BoardView extends Pane {

    private final TileView[][] tileviews;

    public BoardView(BoardModel board) {
        var children = this.getChildren();
        this.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        this.tileviews = new TileView[WIDTH][HEIGHT];

        var colored = false;
        for (int y = 0; y < board.tiles.length; y++) {
            for (int x = 0; x < board.tiles[y].length; x++) {
                var tile = board.tiles[x][y];
                var tileview = new TileView(tile, x, y, colored);
                tileviews[x][y] = tileview;
                children.add(tileview);

                colored = !colored;
            }
            colored = !colored;
        }
    }
    
    public void mark(int x, int y) throws IndexOutOfBoundsException {
        if (!Game.posIsValid(x, y)) throw new IndexOutOfBoundsException();
        tileviews[x][y].setMarked(true);
    }

    public void unmark(int x, int y) throws IndexOutOfBoundsException {
        if (!posIsValid(x, y)) throw new IndexOutOfBoundsException();
        tileviews[x][y].setMarked(false);
    }

    public void unmarkAll() {
        for (var row : tileviews) {
            for (var tile : row) {
                tile.setMarked(false);
            }
        }
    }
    
    public boolean posIsValid(int x, int y) {
        return Game.posIsValid(x, y);
    }
}
