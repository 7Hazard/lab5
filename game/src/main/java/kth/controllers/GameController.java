package kth.controllers;

import javafx.scene.paint.*;
import kth.*;
import kth.models.*;
import kth.views.*;

import static kth.Game.HEIGHT;
import static kth.Game.WIDTH;

public class GameController {
    private static final GameController singleton = new GameController();
    private PieceView selectedPiece;
    private Color currentTurn = Color.BLACK;

    public static GameController get() {
        return singleton;
    }

    public void restart() {
        System.out.println("Restarting");
        selectedPiece = null;
        currentTurn = Color.BLACK;
        Game.get().reset();
        Game.get().draw();
    }

    public void markIfValid(int dx, int dy) {
        var currentTile = selectedPiece.tileView;
        var x = currentTile.getX() + dx;
        var y = currentTile.getY() + dy;
        var currentPiece = selectedPiece.getModel();
        var board = Game.get().getGameView().getBoardView();

        if (board.posIsValid(x, y)) {
            var mtile = board.getTile(x, y);
            var tilemodel = mtile.getModel();
            if (!tilemodel.hasPiece()) {
                board.mark(x, y);
            } else {
                if (!(currentPiece.getColor() == tilemodel.getPieceModel().getColor())) {
                    x = x + dx;
                    y = y + dy;
                    if (board.posIsValid(x,y) &&!board.getTile(x, y).getModel().hasPiece()) {
                        board.mark(x, y);
                    }
                }

            }
        }
    }

    public void onSelectPiece(PieceView view) {

        var tile = view.tileView;
        var board = Game.get().getGameView().getBoardView();
        var model = view.getModel();

        System.out.println("ON CLICK " + tile.getX() + " " + tile.getY());

        board.unmarkAll();

        // if clicked on own piece while selected, deselect
        if (selectedPiece == view) {
            selectedPiece = null;
            Game.get().draw();
            return;
        }

        if(view.getModel().getColor() != currentTurn) {
            return;
        }
        selectedPiece = view;

        // down
        if (model.isKing() || model.isRed()) {
            // left
            var x = tile.getX() - 1;
            var y = tile.getY() + 1;
            markIfValid(-1, +1);

            // right
            x = tile.getX() + 1;
            y = tile.getY() + 1;
            markIfValid(+1, +1);
        }

        if (model.isKing() || model.isBlack()) {
            // left
            var x = tile.getX() - 1;
            var y = tile.getY() - 1;
            markIfValid(-1, -1);

            // right
            x = tile.getX() + 1;
            y = tile.getY() - 1;
            markIfValid(+1, -1);
        }

        Game.get().draw();
    }

    public void onSelectTile(TileView selectedView) {
        System.out.println("TILE ON CLICK");
        var board = Game.get().getGameView().getBoardView();
        var model = selectedView.model;

        if (selectedView.isMarked()) {

            var currentX = selectedPiece.tileView.getX();
            var currentY = selectedPiece.tileView.getY();

            var futureX = selectedView.getX();
            var futureY = selectedView.getY();

            var diffX = (futureX - currentX);
            var diffY = (futureY - currentY);
            if (Math.abs(diffX) > 1 && Math.abs(diffY) > 1) {
                var dx = diffX / 2;
                var dy = diffY / 2;
                var removePieceX = selectedPiece.tileView.getX() + dx;
                var removePieceY = selectedPiece.tileView.getY() + dy;
                if (board.posIsValid(removePieceX, removePieceY)) {
                    var tile = board.getTile(removePieceX, removePieceY);
                    tile.getModel().setPieceModel(null);
                    tile.removePiece();
                }
            }
            selectedPiece.tileView.model.setPieceModel(null);
            selectedView.setPiece(selectedPiece);
            selectedView.model.setPieceModel(selectedPiece.getModel());
            int counterRed = 0;
            int counterBlack = 0;

            for(int y = 0; y < HEIGHT; y++){
                for(int x = 0 ; x < WIDTH; x++){
                    if(board.getTile(x,y).getModel().hasPiece()){
                        var piece = board.getTile(x,y).getModel().getPieceModel();
                        if(piece.getColor() == Color.BLACK){
                            counterBlack++;
                        }
                        if(piece.getColor() == Color.RED){
                            counterRed++;
                        }
                    }
                }
            }
            if(counterRed == 0){
                System.out.println("RED: Game over");
            }
            if(counterBlack == 0){
                System.out.println("BLACK: Game over");
            }

            if(selectedPiece.tileView.getY() == HEIGHT -1 && selectedPiece.getModel().isRed()){
                selectedPiece.makeKing();
            }
            if(selectedPiece.tileView.getY() == 0 && selectedPiece.getModel().isBlack()){
                selectedPiece.makeKing();
            }
            if(currentTurn == Color.BLACK){
                currentTurn = Color.RED;
            }else if(currentTurn == Color.RED){
                currentTurn = Color.BLACK;
            }
        }

        selectedPiece = null;
        board.unmarkAll();

        Game.get().draw();
    }
}
