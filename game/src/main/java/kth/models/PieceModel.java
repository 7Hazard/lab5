package kth.models;

import javafx.scene.paint.Color;
import kth.PieceColor;

import java.io.Serializable;

public class PieceModel implements Serializable {
    public static int PIECE_RADIUS = 40;
    private final PieceColor color;
    private boolean isKing;
    public TileModel tileModel;

    PieceModel(TileModel tile, PieceColor color) {
        this.tileModel = tile;
        this.color = color;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        if (color == PieceColor.Red) buffer.append("R");
        else buffer.append("B");
        return buffer.toString();
    }

    public boolean isKing() {
        return isKing;
    }

    public void setKing(boolean king) {
        isKing = king;
    }

    public PieceColor getColor() {
        return this.color;
    }

    public boolean isRed() {
        return color == PieceColor.Red;
    }

    public boolean isBlack() {
        return color == PieceColor.Black;
    }
}
