package kth.models;

import javafx.scene.paint.Color;

public class PieceModel {
    public static int PIECE_RADIUS = 40;
    private final Color color;
    private boolean isKing;
    public TileModel tileModel;

    public Color getColor() {
        return this.color;
    }

    PieceModel(TileModel tile, Color color) {
        this.tileModel = tile;
        this.color = color;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        if (color == Color.RED) buffer.append("R");
        else buffer.append("B");
        return buffer.toString();
    }

    public boolean isKing() {
        return isKing;
    }

    public void setKing(boolean king) {
        isKing = king;
    }

    public boolean isRed() {
        return color == Color.RED;
    }

    public boolean isBlack() {
        return color == Color.BLACK;
    }
}
