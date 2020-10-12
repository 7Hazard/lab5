package kth.models;

import javafx.scene.paint.Color;

public class BoardModel {

    private static int HEIGHT = 8;
    private static int WIDTH = 8;

    public TileModel[][] tiles = new TileModel[WIDTH][HEIGHT];

    public BoardModel() {

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                TileModel tile = new TileModel();
                PieceModel piece = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    piece = new PieceModel(Color.RED);
                }
                if (y >= 5 && (x + y) % 2 != 0) {
                    piece = new PieceModel(Color.BLACK);
                }
                if (piece != null) {
                    tile.setPiece(piece);
                }
                tiles[x][y] = tile;
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (tiles[x][y].hasPiece())
                    buffer.append("O");
                else
                    buffer.append("x");
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }
}
