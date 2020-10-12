package kth.views;

import javafx.scene.layout.Pane;
import kth.models.BoardModel;

import static kth.App.*;

public class BoardView extends Pane {

    public BoardView(BoardModel board) {
        var children = this.getChildren();
        this.setPrefSize(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);

        var colored = false;
        for (int y = 0; y < board.tiles.length; y++) {
            for (int x = 0; x < board.tiles[y].length; x++) {
                var tile = board.tiles[x][y];

                children.add(new TileView(tile, x, y, colored));

                if (tile.hasPiece()) {
                    children.add(new PieceView(tile.getPiece(), x, y));
                }

                colored = !colored;
            }
            colored = !colored;
        }
    }

}
