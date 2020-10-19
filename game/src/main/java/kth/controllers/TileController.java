package kth.controllers;

import kth.Game;
import kth.models.BoardModel;
import kth.models.TileModel;

public class TileController {
    TileModel tile;
    
    public TileController(TileModel tile) {
        this.tile = tile;
    }

    public void onClick() {
        System.out.println("TILE ON CLICK");
        var board = BoardModel.get();
        if (tile.isMarked())
        {
            board.selectedPiece.tile.setPiece(null);
            tile.setPiece(board.selectedPiece);
            board.selectedPiece.tile = tile;
            board.selectedPiece = null;
        }
        
        board.unmarkAll();

        Game.get().draw();
    }
}
