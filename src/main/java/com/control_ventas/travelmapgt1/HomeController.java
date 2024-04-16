package com.control_ventas.travelmapgt1;
import com.modelo.Grafo;
import com.modelo.NodoGrafo;
import com.resource.Alerta;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

public class HomeController implements Initializable{
    Grafo grafo;
    Alerta alerta= new Alerta();
    String tipoRuta=" ";
    String origen;
    @FXML
    private RadioButton Caminar;

    @FXML
    private ComboBox<String> Destino;

    @FXML
    private Button Empezar;

    @FXML
    private ComboBox<String> Origen;

    @FXML
    private Label Reloj;

    @FXML
    private RadioButton Vehiculo;

    public HomeController(Grafo grafo) {
        this.grafo = grafo;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Vehiculo.setDisable(false);
        Caminar.setDisable(false);
            NodoGrafo temp = grafo.getPrimero();
            while (temp != null) {
                Origen.getItems().add(temp.getNombre());
                Destino.getItems().add(temp.getNombre());
                temp = temp.getSiguiente();
            }
        
    }

    @FXML
    public void empezar() {
        try {
            if (!Origen.getValue().equals(Destino.getValue()) && !Origen.getValue().equals("Origen") && !Destino.getValue().equals("Destino")) {
                Vehiculo.selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        System.out.println("ruta para vehiculo");
                        Caminar.setSelected(false);
                        tipoRuta = "vehiculo";
                    }
                    else{tipoRuta=" ";}
                });
                Caminar.selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue) {
                        System.out.println("para Caminar");
                        Vehiculo.setSelected(false);
                        tipoRuta = "Caminar";
                    }
                     else{tipoRuta=" ";}
                });
                if (tipoRuta.equals(" ")) {
                    alerta.showAlert("Por favor, Selecione un tipo de ruta", "Error de seleccion de ruta");
                }
            } else {
                alerta.showAlert("No se puede calcular una ruta porque el origen y el destino tienen la misma ubicacion", "Error de Ubicacion");
            }
        } catch (Exception e) {
            alerta.showAlert("Por favor, Selecione una Ubicacion", "Error de selecion");
            Vehiculo.setSelected(false);
            Caminar.setSelected(false);
        }

    }

}
