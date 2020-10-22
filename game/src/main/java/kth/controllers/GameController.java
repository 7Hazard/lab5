package kth.controllers;

import kth.Game;
import kth.PieceColor;
import kth.views.BoardView;
import kth.views.PieceView;
import kth.views.TileView;

import static kth.Game.HEIGHT;
import static kth.Game.WIDTH;

public class GameController {
    private static final GameController singleton = new GameController();
    private PieceView selectedPiece;

    public static GameController get() {
        return singleton;
    }

    public void restart() {
        var game = Game.get();
        System.out.println("Restarting");
        selectedPiece = null;
        game.reset();
        game.draw();
    }

    public void save() {
        System.out.println("Saving");
        Game.get().save();
    }

    public void load() {
        Game.get().load();
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
                    if (board.posIsValid(x, y) && !board.getTile(x, y).getModel().hasPiece()) {
                        board.mark(x, y);
                    }
                }
            }
        }
    }

    public void onSelectPiece(PieceView view) {

        var tile = view.tileView;
        var board = Game.get().getGameView().getBoardView();

        System.out.println("ON CLICK " + tile.getX() + " " + tile.getY());

        board.unmarkAll();

        // if clicked on own piece while selected, deselect
        if (selectedPiece == view) {
            selectedPiece = null;
            Game.get().draw();
            return;
        }

        var currentTurn = board.getModel().getCurrentTurn();
        if (view.getModel().getColor() != currentTurn) {
            return;
        }

        selectedPiece = view;
        markValidMoves();

        Game.get().draw();
    }

    private void markValidMoves() {
        var model = selectedPiece.getModel();
        var tile = selectedPiece.tileView;

        // down || king
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

        // up || king
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
    }

    public void onSelectTile(TileView selectedView) {
        System.out.println("TILE ON CLICK");
        var board = Game.get().getGameView().getBoardView();

        boolean jumpedOver = false;
        if (selectedView.isMarked()) {
            var currentX = selectedPiece.tileView.getX();
            var currentY = selectedPiece.tileView.getY();

            var futureX = selectedView.getX();
            var futureY = selectedView.getY();

            var diffX = (futureX - currentX);
            var diffY = (futureY - currentY);
            if (Math.abs(diffX) > 1 && Math.abs(diffY) > 1) {
                jumpedOver = true;

                var dx = diffX / 2;
                var dy = diffY / 2;
                var removePieceX = selectedPiece.tileView.getX() + dx;
                var removePieceY = selectedPiece.tileView.getY() + dy;

                var tile = board.getTile(removePieceX, removePieceY);
                tile.getModel().setPieceModel(null);
                tile.removePiece();
            }

            selectedPiece.tileView.getModel().setPieceModel(null);
            selectedView.setPiece(selectedPiece);
            selectedView.getModel().setPieceModel(selectedPiece.getModel());

            int counterRed = 0;
            int counterBlack = 0;
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    if (board.getTile(x, y).getModel().hasPiece()) {
                        var piece = board.getTile(x, y).getModel().getPieceModel();
                        if (piece.getColor() == PieceColor.Black) {
                            counterBlack++;
                        }
                        if (piece.getColor() == PieceColor.Red) {
                            counterRed++;
                        }
                    }
                }
            }
            if (counterRed == 0) {
                Game.get().end(PieceColor.Black);
                return;
            }
            if (counterBlack == 0) {
                Game.get().end(PieceColor.Red);
                return;
            }

            if (selectedPiece.tileView.getY() == HEIGHT - 1 && selectedPiece.getModel().isRed()) {
                selectedPiece.makeKing();
            }
            if (selectedPiece.tileView.getY() == 0 && selectedPiece.getModel().isBlack()) {
                selectedPiece.makeKing();
            }

            // if cant jump over, switch turn
            if (!jumpedOver) {
                endTurn(board);
            } else {
                board.unmarkAll();
                if (!markIfCanJumpOver())
                    endTurn(board);
            }

            Game.get().getGameView().updateInfo();
        }

        if (!jumpedOver) {
            selectedPiece = null;
            board.unmarkAll();
        }

        Game.get().draw();
    }

    private void endTurn(BoardView board) {
        var currentTurn = board.getModel().getCurrentTurn();
        if (currentTurn == PieceColor.Black) {
            board.getModel().setCurrentTurn(PieceColor.Red);
        } else if (currentTurn == PieceColor.Red) {
            board.getModel().setCurrentTurn(PieceColor.Black);
        }
    }

    private boolean markIfCanJumpOver() {
        boolean marked = false;
        var model = selectedPiece.getModel();

        // down || king
        if (model.isKing() || model.isRed()) {
            // left
            if (markIfCanJumpOver(-1, +1)) marked = true;

            // right
            if (markIfCanJumpOver(+1, +1)) marked = true;
        }

        // up || king
        if (model.isKing() || model.isBlack()) {
            // left
            if (markIfCanJumpOver(-1, -1)) marked = true;

            // right
            if (markIfCanJumpOver(+1, -1)) marked = true;
        }

        return marked;
    }

    private boolean markIfCanJumpOver(int dx, int dy) {
        var tile = selectedPiece.tileView;
        var board = Game.get().getGameView().getBoardView();

        var x = tile.getX() + dx;
        var y = tile.getY() + dy;
        if (board.posIsValid(x, y)) {
            var cpiece = board.getTile(x, y).getModel();
            if (cpiece.hasPiece()
                    && cpiece.getPieceModel().getColor() != selectedPiece.getModel().getColor()
                    && board.posIsValid(x + dx, y + dy)
                    && !board.getTile(x + dx, y + dy).getModel().hasPiece())
            {
                board.mark(x + dx, y + dy);
                return true;
            }
        }

        return false;
    }
}
