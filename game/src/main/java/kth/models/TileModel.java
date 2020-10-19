package kth.models;

public class TileModel {
    private PieceModel piece;

    TileModel() {
        
    }
    
    public boolean hasPiece() {
        return piece != null;
    }

    public PieceModel getPiece() {
        return piece;
    }

    public void setPiece(PieceModel piece) {
        this.piece = piece;
    }
}
