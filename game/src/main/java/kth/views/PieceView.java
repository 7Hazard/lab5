package kth.views;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import kth.controllers.GameController;
import kth.models.PieceModel;

public class PieceView extends Circle {
    public static int PIECE_RADIUS = 40;

    int x, y;

    public PieceView(PieceModel piece, int x, int y) {
        super(PIECE_RADIUS, piece.getColor());

        this.x = x;
        this.y = y;

        setStroke(Color.WHITE);
        setStrokeWidth(PIECE_RADIUS * 0.1);

        relocate(PIECE_RADIUS/5, PIECE_RADIUS/5);

        setOnMouseClicked(this::onClick);
    }

    public TileView getTile() {
        return GameView.get().boardView.getTile(x, y);
    }

    private void onClick(MouseEvent mouseEvent) {
        GameController.get().onSelectPiece(this);
    }
}
