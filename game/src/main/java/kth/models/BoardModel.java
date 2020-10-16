package kth.models;

import javafx.scene.paint.Color;

public class BoardModel {
    private static BoardModel singleton;

    public static BoardModel get() {
        if (singleton == null)
            singleton = new BoardModel();
        return singleton;
    }

    private static int HEIGHT = 8;
    private static int WIDTH = 8;

    public TileModel[][] tiles = new TileModel[WIDTH][HEIGHT];
    public PieceModel selectedPiece = null;

    public BoardModel() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                TileModel tile = new TileModel();

                if (y <= 2 && (x + y) % 2 != 0) {
                    tile.setPiece(new PieceModel(tile, Color.RED));
                }
                if (y >= 5 && (x + y) % 2 != 0) {
                    tile.setPiece(new PieceModel(tile, Color.BLACK));
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

//    public void mark(int x, int y) throws IndexOutOfBoundsException {
//        if (!posIsValid(x, y)) throw new IndexOutOfBoundsException();
//        tiles[x][y].setMarked(true);
//    }

//    public void unmark(int x, int y) throws IndexOutOfBoundsException {
//        if (!posIsValid(x, y)) throw new IndexOutOfBoundsException();
//        tiles[x][y].setMarked(false);
//    }

//    public void unmarkAll() {
//        for (var row : tiles) {
//            for (var tile : row) {
//                tile.setMarked(false);
//            }
//        }
//    }

    public void reset() {
        singleton = new BoardModel();
    }
}
