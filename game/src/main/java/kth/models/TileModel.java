package kth.models;

public class TileModel {
    private PieceModel pieceModel;

    TileModel() {
        
    }
    
    public boolean hasPiece() {
        return pieceModel != null;
    }

    public PieceModel getPieceModel() {
        return pieceModel;
    }

    public void setPieceModel(PieceModel pieceModel) {
        this.pieceModel = pieceModel;
    }
}
