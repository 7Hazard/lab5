package kth.models;

import javafx.scene.paint.Color;

import static kth.Game.HEIGHT;
import static kth.Game.WIDTH;

public class BoardModel {
    public TileModel[][] tileModels = new TileModel[WIDTH][HEIGHT];

    public BoardModel() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                TileModel tile = new TileModel();

                if (y <= 2 && (x + y) % 2 != 0) {
                    tile.setPieceModel(new PieceModel(tile, Color.RED));
                }
                if (y >= 5 && (x + y) % 2 != 0) {
                    tile.setPieceModel(new PieceModel(tile, Color.BLACK));
                }
                tileModels[x][y] = tile;
            }
        }
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (tileModels[x][y].hasPiece())
                    buffer.append("O");
                else
                    buffer.append("x");
            }
            buffer.append("\n");
        }
        return buffer.toString();
    }
    
    public static void load(String filename) {
        // TODO: 2020-10-20  
    }
}
