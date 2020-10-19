package kth.controllers;

import kth.Game;
import kth.models.BoardModel;
import kth.views.PieceView;
import kth.views.TileView;

public class GameController {
    private static GameController singleton = new GameController();
    private PieceView selectedPiece;

    public static GameController get() {
        return singleton;
    }

    public void restart() {
        System.out.println("Restarting");
        BoardModel.get().reset();
        Game.get().draw();
    }

    public void onSelectPiece(PieceView view) {
        var tile = view.tile;
        var board = Game.get().getBoardView();
        var model = view.model;
        
        System.out.println("ON CLICK " + tile.getX() + " " + tile.getY());

        board.unmarkAll();

        // if clicked on own piece while selected, deselect
        if(selectedPiece == view)
        {
            selectedPiece = null;
            Game.get().draw();
            return;
        }

        selectedPiece = view;

        // down
        if(model.isKing() || model.isRed())
        {
            // left
            var x = tile.getX()-1;
            var y = tile.getY()+1;
            if(board.posIsValid(x, y))
                board.mark(x, y);

            // right
            x = tile.getX()+1;
            y = tile.getY()+1;
            if(board.posIsValid(x, y))
                board.mark(x, y);
        }

        if(model.isKing() || model.isBlack())
        {
            // left
            var x = tile.getX()-1;
            var y = tile.getY()-1;
            if(board.posIsValid(x, y))
                board.mark(x, y);

            // right
            x = tile.getX()+1;
            y = tile.getY()-1;
            if(board.posIsValid(x, y))
                board.mark(x, y);
        }

        Game.get().draw();
    }

    public void onSelectTile(TileView view) {
        System.out.println("TILE ON CLICK");
        var board = Game.get().getBoardView();
        var tile = view.tile;
        
//        if (view.isMarked())
//        {
//            board.selectedPiece.tile.setPiece(null);
//            tile.setPiece(board.selectedPiece);
//            board.selectedPiece.tile = tile;
//            board.selectedPiece = null;
//        }

        selectedPiece = null;
        board.unmarkAll();

        Game.get().draw();
    }
}
