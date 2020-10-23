package kth.models;

import kth.PieceColor;

import java.io.*;

import static kth.Game.HEIGHT;
import static kth.Game.WIDTH;

/**
 * Model for the whole board and it's tiles and pieces
 */
public class BoardModel implements Serializable {
    public TileModel[][] tileModels = new TileModel[WIDTH][HEIGHT];
    private PieceColor currentTurn = PieceColor.Black;

    /**
     * Creates a default board model
     */
    public BoardModel() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                TileModel tile = new TileModel();

                if (y <= 2 && (x + y) % 2 != 0) {
                    tile.setPieceModel(new PieceModel(tile, PieceColor.Red));
                }
                if (y >= 5 && (x + y) % 2 != 0) {
                    tile.setPieceModel(new PieceModel(tile, PieceColor.Black));
                }
                tileModels[x][y] = tile;
            }
        }
    }

    /**
     *
     * @return Formatted string of the entire board
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                if (tileModels[x][y].hasPiece())
                    builder.append("O");
                else
                    builder.append("x");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * Loads a board from a file
     * @param path
     * @return The loaded model of the board
     * @throws IOException If file could not be loaded
     * @throws ClassNotFoundException if file could not be deserialize
     */
    public static BoardModel load(String path) throws IOException, ClassNotFoundException {
        var filepath = path;
        var fileStream = new FileInputStream(filepath);
        var inputStream = new ObjectInputStream(fileStream);
        var model = (BoardModel)inputStream.readObject();
        inputStream.close();

        System.out.println("Loaded");
        return model;
    }

    /**
     * Saves the model into a file via serialization
     * @param path the filepath to the save
     * @throws IOException if file could not be written to
     */
    public void save(String path) throws IOException {
        var filepath = path;

        var fileStream = new FileOutputStream(filepath);
        var outputStream = new ObjectOutputStream(fileStream);
        outputStream.writeObject(this);
        outputStream.flush();
        outputStream.close();

        System.out.println("Saved");
    }

    public PieceColor getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(PieceColor currentTurn) {
        this.currentTurn = currentTurn;
    }

    public boolean posIsValid(int x, int y) {
        return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT;
    }
}
