package kth.models;

import javafx.scene.paint.Color;

//TODO: Add own Macros to piece size

public class PieceModel {
    public static int PIECE_RADIUS = 40;
    private Color color;

    public Color getColor() {
        return this.color;
    }

    public PieceModel(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        if (color == Color.RED) buffer.append("R");
        else buffer.append("B");
        return buffer.toString();
    }
}
