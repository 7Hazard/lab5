package kth.models;

public class TileModel {
    private PieceModel piece;
    private boolean isMarked = false;
    public int x;
    public int y;

    TileModel(int x, int y) {
        this.x = x;
        this.y = y;
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

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        isMarked = marked;
    }
}
