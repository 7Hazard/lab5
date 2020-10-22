package kth.models;

import kth.PieceColor;

import java.io.*;

import static kth.Game.HEIGHT;
import static kth.Game.WIDTH;

public class BoardModel implements Serializable {
    public TileModel[][] tileModels = new TileModel[WIDTH][HEIGHT];
    private PieceColor currentTurn = PieceColor.Black;

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

    public static BoardModel load(String savename) throws IOException, ClassNotFoundException {
        var filepath = savename+".save";
        var fileStream = new FileInputStream(filepath);
        var inputStream = new ObjectInputStream(fileStream);
        var model = (BoardModel)inputStream.readObject();
        inputStream.close();

        System.out.println("Loaded");
        return model;
    }

    public void save(String name) {
        var filepath = name+".save";
        try {
            var fileStream = new FileOutputStream(filepath);
            var outputStream = new ObjectOutputStream(fileStream);
            outputStream.writeObject(this);
            outputStream.flush();
            outputStream.close();

            System.out.println("Saved");
        } catch (IOException e) {
            System.err.println("Could not save to "+filepath);
            e.printStackTrace();
        }
    }

    public PieceColor getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(PieceColor currentTurn) {
        this.currentTurn = currentTurn;
    }
}
