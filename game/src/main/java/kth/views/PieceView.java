package kth.views;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import kth.Game;
import kth.PieceColor;
import kth.controllers.GameController;
import kth.models.*;

import static kth.Game.TILE_SIZE;
import static kth.models.PieceModel.PIECE_RADIUS;

public class PieceView extends Circle {
    private final PieceModel model;
    public TileView tileView;

    public PieceView(PieceModel model, TileView tile) {
        super(PIECE_RADIUS, model.getColor() == PieceColor.Black ? Color.BLACK : Color.RED);
        this.model = model;
        this.tileView = tile;

        setStroke(Color.WHITE);
        setStrokeWidth(PIECE_RADIUS * 0.1);

        relocate((TILE_SIZE - 2 * PIECE_RADIUS) / 2, (TILE_SIZE - 2 * PIECE_RADIUS) / 2);
        
        setOnMouseClicked(this::onClick);
    }

    private void onClick(MouseEvent mouseEvent) {
        Game.get().getController().onSelectPiece(this);
    }

    public void setTileView(TileView view) {
        tileView = view;
    }

    public PieceModel getModel() {
        return this.model;
    }
    public void makeKing(){
            model.setKing(true);
            setStroke(Color.YELLOW);
            setStrokeWidth(PIECE_RADIUS * 0.2);
    }
}
