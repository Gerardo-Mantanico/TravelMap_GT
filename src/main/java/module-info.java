module com.control_ventas.travelmapgt1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.control_ventas.travelmapgt1 to javafx.fxml;
    exports com.control_ventas.travelmapgt1;
}
