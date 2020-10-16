module kth {
    requires javafx.controls;
    requires javafx.fxml;
    requires plantuml.generator.util;

    opens kth to javafx.fxml;
    exports kth;
}