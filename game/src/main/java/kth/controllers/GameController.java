package kth.controllers;

import kth.App;
import kth.models.BoardModel;
import kth.views.BoardView;
import kth.views.GameView;
import kth.views.PieceView;
import kth.views.TileView;

public class GameController {

    private static GameController singleton = new GameController();
    public static GameController get() {
        return singleton;
    }

    public void onSelectTile(TileView tileView) {
//        if(tileView.isMarked())
//        {
//            tileView.unmark();
//        } else tileView.mark();
//        GameView.draw();

//        System.out.println("ON CLICK " + tile.x + " " + tile.y);
//
//        board.unmarkAll();
//
//        // if clicked on own piece while selected, deselect
//        if(board.selectedPiece == piece)
//        {
//            board.selectedPiece = null;
//            App.get().draw();
//            return;
//        }
//
//        board.selectedPiece = piece;
//
//        // down
//        if(piece.isKing() || piece.isRed())
//        {
//            // left
//            var x = tile.x-1;
//            var y = tile.y+1;
//            if(board.posIsValid(x, y))
//                board.tiles[x][y].setMarked(true);
//
//            // right
//            x = tile.x+1;
//            y = tile.y+1;
//            if(board.posIsValid(x, y))
//                board.tiles[x][y].setMarked(true);
//        }
//
//        if(piece.isKing() || piece.isBlack())
//        {
//            // left
//            var x = tile.x-1;
//            var y = tile.y-1;
//            if(board.posIsValid(x, y))
//                board.tiles[x][y].setMarked(true);
//
//            // right
//            x = tile.x+1;
//            y = tile.y-1;
//            if(board.posIsValid(x, y))
//                board.tiles[x][y].setMarked(true);
//        }
//
        GameView.draw();
    }

    public void onSelectPiece(PieceView pieceView) {
        pieceView.getTile().mark();

//        System.out.println("TILE ON CLICK");
//        var board = BoardModel.get();
//        if (tile.isMarked())
//        {
//            board.selectedPiece.tile.setPiece(null);
//            tile.setPiece(board.selectedPiece);
//            board.selectedPiece.tile = tile;
//            board.selectedPiece = null;
//        }
//
//        board.unmarkAll();
//
//        App.get().draw();
    }

    public void restart() {
        System.out.println("Restarting");
        BoardModel.get().reset();
        GameView.get().boardView.reset();
        GameView.draw();
    }
}
