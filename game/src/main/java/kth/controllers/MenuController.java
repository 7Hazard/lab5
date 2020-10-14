package kth.controllers;

import kth.App;
import kth.models.BoardModel;

public class MenuController {

    public void restart() {
        System.out.println("Restarting");
        BoardModel.get().reset();
        App.get().draw();
    }
}
