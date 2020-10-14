package kth.views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import kth.controllers.PieceController;
import kth.models.PieceModel;

import static kth.App.TILE_SIZE;
import static kth.models.PieceModel.PIECE_RADIUS;

public class PieceView extends Circle {
    private final PieceModel piece;
    private final PieceController controller;

    public PieceView(PieceModel piece) {
        super(PIECE_RADIUS, piece.getColor());
        this.piece = piece;
        this.controller = new PieceController(piece);

        setStroke(Color.WHITE);
        setStrokeWidth(PIECE_RADIUS * 0.1);

        relocate((TILE_SIZE - 2 * PIECE_RADIUS) / 2, (TILE_SIZE - 2 * PIECE_RADIUS) / 2);
        
        setOnMouseClicked(mouseEvent -> {
            controller.onClick();
        });
    }
}
