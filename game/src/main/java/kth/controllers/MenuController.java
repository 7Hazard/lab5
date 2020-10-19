package kth.controllers;

import kth.Game;
import kth.models.BoardModel;

public class MenuController {

    public void restart() {
        System.out.println("Restarting");
        BoardModel.get().reset();
        Game.get().draw();
    }
}
