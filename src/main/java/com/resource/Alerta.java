package com.resource;

public class  Alerta {
     public void showAlert(String msj, String tipo) {
        javafx.scene.control.Alert alert = new javafx.scene.control.Alert(javafx.scene.control.Alert.AlertType.INFORMATION);
        alert.setTitle(tipo);
        alert.setHeaderText(null);
        alert.setContentText(msj);
        alert.showAndWait();
    }
}
