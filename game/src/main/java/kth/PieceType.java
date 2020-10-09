package kth;

public enum PieceType {

    RED(1),BLACK(-1);

    final int movingDirection;
    PieceType(int movingDirection){
        this.movingDirection = movingDirection;
    }
}
