package kth.models;

import javafx.scene.paint.Color;

public class PieceModel {
    private Color color;
    private boolean isKing;
    public TileModel tile;

    public Color getColor() {
        return this.color;
    }

    PieceModel(TileModel tile, Color color) {
        this.tile = tile;
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
