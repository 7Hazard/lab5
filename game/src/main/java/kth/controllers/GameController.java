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
    
    public void markIfValid(int x, int y) {
        var board = Game.get().getBoardView();
        if(board.posIsValid(x, y)){
            var mtile = board.getTile(x, y);
            var tilemodel = mtile.getModel();
            if(!tilemodel.hasPiece()) {
                board.mark(x, y);
            }
        }
    }

    public void onSelectPiece(PieceView view) {
        var tile = view.tileView;
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
            markIfValid(x, y);

            // right
            x = tile.getX()+1;
            y = tile.getY()+1;
            markIfValid(x, y);
        }

        if(model.isKing() || model.isBlack())
        {
            // left
            var x = tile.getX()-1;
            var y = tile.getY()-1;
            markIfValid(x, y);

            // right
            x = tile.getX()+1;
            y = tile.getY()-1;
            markIfValid(x, y);
        }

        Game.get().draw();
    }

    public void onSelectTile(TileView selectedView) {
        System.out.println("TILE ON CLICK");
        var board = Game.get().getBoardView();
        var model = selectedView.model;
        
        if (selectedView.isMarked())
        {
            selectedPiece.tileView.model.setPiece(null);
            selectedView.setPiece(selectedPiece);
            selectedView.model.setPiece(selectedPiece.model);
            selectedPiece = null;   
        }

        selectedPiece = null;
        board.unmarkAll();

        Game.get().draw();
    }
}
