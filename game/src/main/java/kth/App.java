package kth;

import javafx.application.*;
import javafx.stage.*;
import java.util.ArrayList;
import java.util.List;
import de.elnarion.util.plantuml.generator.PlantUMLClassDiagramGenerator;

/**
 * JavaFX App
 */
public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // uml generator
//        try {
//            List<String> scanpackages = new ArrayList<>();
//            scanpackages.add("kth");
//            List<String> hideClasses = new ArrayList<>();
//            PlantUMLClassDiagramGenerator generator =
//                    new PlantUMLClassDiagramGenerator(this.getClass().getClassLoader(),
//                            scanpackages,null, hideClasses, false, false);
//            String result = generator.generateDiagramText();
//            System.out.println(result);
//        } catch (Throwable e) {
//            System.out.println("Error generating plantuml diagram");
//        }
        // uml generator

        Game.get().init(stage);
    }
}