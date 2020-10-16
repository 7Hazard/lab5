package kth;

import javafx.application.*;
import javafx.stage.*;
import java.util.ArrayList;
import java.util.List;
import de.elnarion.util.plantuml.generator.PlantUMLClassDiagramGenerator;
import kth.views.GameView;

/**
 * JavaFX App
 */
public class App extends Application {
    GameView gameView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // uml generator
//        List<String> scanpackages = new ArrayList<>();
//        scanpackages.add("kth");
//        List<String> hideClasses = new ArrayList<>();
//        PlantUMLClassDiagramGenerator generator =
//                new PlantUMLClassDiagramGenerator(this.getClass().getClassLoader(),
//                        scanpackages,null, hideClasses, false, false);
//        String result = generator.generateDiagramText();
//        System.out.println(result);
        // uml generator

        gameView = new GameView(stage);
        GameView.draw();
    }
}