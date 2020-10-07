module kth {
    requires javafx.controls;
    requires javafx.fxml;

    opens kth to javafx.fxml;
    exports kth;
}