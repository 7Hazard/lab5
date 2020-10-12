package kth;

public class MovePiece {

    private static int TILE_SIZE = 100;

    private double mouseX;
    private double mouseY;
    //private CheckersPiece piece;
    private double oldX,oldY;

    public MovePiece(CheckersPiece piece){

        piece.setOnMousePressed(e->{
            mouseX = e.getSceneX();
            mouseY = e.getSceneY();
            System.out.println("X: "+mouseX+", Y: "+mouseY);
        });
    }



}
