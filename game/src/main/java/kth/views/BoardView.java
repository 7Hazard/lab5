package kth.views;

import javafx.scene.layout.Pane;
import kth.Game;
import kth.models.BoardModel;

import static kth.Game.*;

public class BoardView extends Pane {
    private final TileView[][] tileviews;
    private BoardModel model;

    public BoardView(BoardModel model) {
        this.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
        this.tileviews = new TileView[WIDTH][HEIGHT];

        reset(model);
    }
    
    public void mark(int x, int y) throws IndexOutOfBoundsException {
        if (!model.posIsValid(x, y)) throw new IndexOutOfBoundsException();
        tileviews[x][y].setMarked(true);
    }

    public void unmark(int x, int y) throws IndexOutOfBoundsException {
        if (!model.posIsValid(x, y)) throw new IndexOutOfBoundsException();
        tileviews[x][y].setMarked(false);
    }

    public void unmarkAll() {
        for (var row : tileviews) {
            for (var tile : row) {
                tile.setMarked(false);
            }
        }
    }

    public TileView getTile(int x, int y) {
        return tileviews[x][y];
    }

    public void reset(BoardModel model) {
        this.model = model;
        var children = this.getChildren();
        children.clear();

        var colored = false;
        for (int y = 0; y < model.tileModels.length; y++) {
            for (int x = 0; x < model.tileModels[y].length; x++) {
                var tile = model.tileModels[x][y];
                var tileview = new TileView(tile, x, y, colored);
                tileviews[x][y] = tileview;
                children.add(tileview);

                colored = !colored;
            }
            colored = !colored;
        }
    }

    public BoardModel getModel() {
        return model;
    }
}
