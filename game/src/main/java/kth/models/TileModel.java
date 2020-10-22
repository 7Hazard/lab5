package kth.models;

import java.io.Serializable;

/**
 * Model for the tiles
 */
public class TileModel implements Serializable {
    private PieceModel pieceModel;

    /**
     * Creates a default tile
     */
    TileModel() {
        
    }

    /**
     *
     * @return if the tile holds a piece
     */
    public boolean hasPiece() {
        return pieceModel != null;
    }

    /**
     *
     * @return the piece in the tile
     */
    public PieceModel getPieceModel() {
        return pieceModel;
    }

    /**
     * sets the piece in the tile
     * @param pieceModel the piece
     */
    public void setPieceModel(PieceModel pieceModel) {
        this.pieceModel = pieceModel;
    }
}
