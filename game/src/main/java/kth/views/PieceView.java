package kth.views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import kth.models.PieceModel;

import static kth.App.TILE_SIZE;
import static kth.models.PieceModel.PIECE_RADIUS;

public class PieceView extends Circle {
    private final PieceModel piece;

    public PieceView(PieceModel piece, int x, int y) {
        super(PIECE_RADIUS, piece.getColor());
        this.piece = piece;

        setStroke(Color.WHITE);
        setStrokeWidth(PIECE_RADIUS * 0.1);

        setTranslateX((TILE_SIZE - 2 * PIECE_RADIUS) / 2);
        setTranslateY((TILE_SIZE - 2 * PIECE_RADIUS) / 2);

        relocate(x * TILE_SIZE, y * TILE_SIZE);
    }
}
