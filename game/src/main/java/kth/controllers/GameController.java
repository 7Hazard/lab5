package kth.controllers;

import kth.*;
import kth.models.*;
import kth.views.*;

public class GameController {
    private static final GameController singleton = new GameController();
    private PieceView selectedPiece;

    public static GameController get() {
        return singleton;
    }

    public void restart() {
        System.out.println("Restarting");
        BoardModel.get().reset();
        Game.get().draw();
    }

    public void markIfValid(int dx, int dy) {
        var currentTile = selectedPiece.tileView;
        var x = currentTile.getX() + dx;
        var y = currentTile.getY() + dy;
        var currentPiece = selectedPiece.getModel();
        var board = Game.get().getBoardView();

        if (board.posIsValid(x, y)) {
            var mtile = board.getTile(x, y);
            var tilemodel = mtile.getModel();
            if (!tilemodel.hasPiece()) {
                board.mark(x, y);
            } else {
                if (!(currentPiece.getColor() == tilemodel.getPiece().getColor())) {
                    x = x + dx;
                    y = y + dy;
                    if (!board.getTile(x, y).getModel().hasPiece()) {
                        board.mark(x, y);
                    }
                }

            }
        }
    }

    public void onSelectPiece(PieceView view) {
        var tile = view.tileView;
        var board = Game.get().getBoardView();
        var model = view.getModel();

        System.out.println("ON CLICK " + tile.getX() + " " + tile.getY());

        board.unmarkAll();

        // if clicked on own piece while selected, deselect
        if (selectedPiece == view) {
            selectedPiece = null;
            Game.get().draw();
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
        var board = Game.get().getBoardView();
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
                    tile.getModel().setPiece(null);
                    tile.removePiece();
                }
            }
            selectedPiece.tileView.model.setPiece(null);
            selectedView.setPiece(selectedPiece);
            selectedView.model.setPiece(selectedPiece.getModel());
            selectedPiece = null;
        }

        selectedPiece = null;
        board.unmarkAll();

        Game.get().draw();
    }
}
