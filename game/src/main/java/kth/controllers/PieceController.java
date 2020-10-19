package kth.controllers;

import kth.Game;
import kth.models.BoardModel;
import kth.models.PieceModel;

public class PieceController {
    private PieceModel piece;
    
    public PieceController(PieceModel piece) {
        this.piece = piece;
    }

    public void onClick() {
        var tile = piece.tile;
        var board = BoardModel.get();
        
        System.out.println("ON CLICK " + tile.x + " " + tile.y);
        
        board.unmarkAll();
        
        // if clicked on own piece while selected, deselect
        if(board.selectedPiece == piece)
        {
            board.selectedPiece = null;
            Game.get().draw();
            return;
        }
        
        board.selectedPiece = piece;

        // down
        if(piece.isKing() || piece.isRed())
        {
            // left
            var x = tile.x-1;
            var y = tile.y+1;
            if(board.posIsValid(x, y))
                board.tiles[x][y].setMarked(true);

            // right
            x = tile.x+1;
            y = tile.y+1;
            if(board.posIsValid(x, y))
                board.tiles[x][y].setMarked(true);
        }
        
        if(piece.isKing() || piece.isBlack())
        {
            // left
            var x = tile.x-1;
            var y = tile.y-1;
            if(board.posIsValid(x, y))
                board.tiles[x][y].setMarked(true);

            // right
            x = tile.x+1;
            y = tile.y-1;
            if(board.posIsValid(x, y))
                board.tiles[x][y].setMarked(true);
        }

        Game.get().draw();
    }
}
