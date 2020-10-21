package kth.views;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import kth.controllers.GameController;
import kth.models.PieceModel;

import static kth.Game.TILE_SIZE;
import static kth.models.PieceModel.PIECE_RADIUS;

public class PieceView extends Circle {
    public final PieceModel model;
    public TileView tileView;

    public PieceView(PieceModel model, TileView tile) {
        super(PIECE_RADIUS, model.getColor());
        this.model = model;
        this.tileView = tile;

        setStroke(Color.WHITE);
        setStrokeWidth(PIECE_RADIUS * 0.1);

        relocate((TILE_SIZE - 2 * PIECE_RADIUS) / 2, (TILE_SIZE - 2 * PIECE_RADIUS) / 2);
        
        setOnMouseClicked(mouseEvent -> {
            GameController.get().onSelectPiece(this);
        });
    }

    public void setTileView(TileView view) {
        tileView = view;
    }
}
