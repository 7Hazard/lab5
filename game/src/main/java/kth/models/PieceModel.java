package kth.models;

import javafx.scene.paint.Color;
import kth.PieceColor;

import java.io.Serializable;

/**
 * A model for pieces
 */
public class PieceModel implements Serializable {
    public static int PIECE_RADIUS = 40;
    private final PieceColor color;
    private boolean isKing;
    public TileModel tileModel;

    /**
     * Creates a default piece model
     * @param tile the tile of the piece
     * @param color color of the piece
     */
    PieceModel(TileModel tile, PieceColor color) {
        this.tileModel = tile;
        this.color = color;
    }

    /**
     *
     * @return Formatted string of the piece
     */
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        if (color == PieceColor.Red) buffer.append("R");
        else buffer.append("B");
        return buffer.toString();
    }

    /**
     *
     * @return if is king
     */
    public boolean isKing() {
        return isKing;
    }

    /**
     * sets the king state
     * @param king true for king, false for not king
     */
    public void setKing(boolean king) {
        isKing = king;
    }

    /**
     *
     * @return the color of the piece
     */
    public PieceColor getColor() {
        return this.color;
    }

    /**
     *
     * @return if the piece is red
     */
    public boolean isRed() {
        return color == PieceColor.Red;
    }

    /**
     *
     * @return if the piece is black
     */
    public boolean isBlack() {
        return color == PieceColor.Black;
    }
}
