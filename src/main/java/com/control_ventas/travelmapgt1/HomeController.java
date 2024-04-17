package com.control_ventas.travelmapgt1;
import com.modelo.Grafo;
import com.modelo.NodoGrafo;
import com.resource.Alerta;
import com.resource.GenerarRuta;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;

public class HomeController implements Initializable{
    Grafo grafo;
    Alerta alerta= new Alerta();
    String tipoRuta=" ";
    ArrayList<String>   lista =new ArrayList<>();
    GenerarRuta  generadorRuta= new GenerarRuta(this);
  
    @FXML
    private RadioButton Caminar;

    @FXML
    private ComboBox<String> Destino;

    @FXML
    private Button Empezar;

    @FXML
    private ImageView ImgActual;

    @FXML
    private ImageView ImgMejorRuta;

    @FXML
    private ComboBox<String> Origen;

    @FXML
    private ComboBox<String> PosiblesRutas;

    @FXML
    private Label Reloj;

    @FXML
    private ImageView SiguienteRuta;

    @FXML
    private RadioButton Vehiculo;

    @FXML
    private TextArea textAreaDetalle;

    public HomeController(Grafo grafo) {
        this.grafo = grafo;
    }
    
        @FXML
    private void close() {
        System.exit(0);
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Vehiculo.setDisable(false);
        Caminar.setDisable(false);
        SiguienteRuta.setVisible(false);
        
        PosiblesRutas.setVisible(false);
            NodoGrafo temp = grafo.getPrimero();
            while (temp != null) {
                Origen.getItems().add(temp.getNombre());
                Destino.getItems().add(temp.getNombre());
                temp = temp.getSiguiente();
            }
            Vehiculo.setOnAction(event ->{
                if(!Vehiculo.isSelected()){
                    tipoRuta=" ";
                    return;
                }
                    else{
                     Caminar.setSelected(false);
                        tipoRuta = "Caminar";
                }
            });
               Caminar.setOnAction(event ->{
                if(!Caminar.isSelected()){
                    tipoRuta=" ";
                    return;
                }
                    else{
                     Vehiculo.setSelected(false);
                        tipoRuta = "Caminar";
                }
            });
            
        
    }

    @FXML
    public void empezar() {
        try {
            if (!Origen.getValue().equals(Destino.getValue()) && !Origen.getValue().equals(null) && !Destino.getValue().equals(null)) {
                if (tipoRuta.equals(" ")) {
                    alerta.showAlert("Por favor, Selecione un tipo de ruta", "Error de seleccion de ruta");
                }
                else{
                    PosiblesRutas.setVisible(true);
                    SiguienteRuta.setVisible(true);
                    generadorRuta.encontrarRutas(grafo, Origen.getValue(), Destino.getValue(), lista);
                    generadorRuta.imprimir();
                    generadorRuta.crearRuta(grafo.buscarNodo(Origen.getValue()));
                }
            } else {
                alerta.showAlert("No se puede calcular una ruta porque el origen y el destino tienen la misma ubicacion", "Error de Ubicacion");
            }
        } catch (Exception e) {
            System.out.println(e);
            alerta.showAlert("Por favor, Selecione una Ubicacion", "Error de selecion");
            Vehiculo.setSelected(false);
            Caminar.setSelected(false);

        }

    }


    public Grafo getGrafo() {
        return grafo;
    }



    public Alerta getAlerta() {
        return alerta;
    }

    public String getTipoRuta() {
        return tipoRuta;
    }


    public RadioButton getCaminar() {
        return Caminar;
    }

    public ComboBox<String> getDestino() {
        return Destino;
    }

    public Button getEmpezar() {
        return Empezar;
    }

    public ImageView getImgActual() {
        return ImgActual;
    }

    public ImageView getImgMejorRuta() {
        return ImgMejorRuta;
    }

    public ComboBox<String> getPosiblesRutas() {
        return PosiblesRutas;
    }

    public Label getReloj() {
        return Reloj;
    }

    public ImageView getSiguienteRuta() {
        return SiguienteRuta;
    }

    public RadioButton getVehiculo() {
        return Vehiculo;
    }

    public TextArea getTextAreaDetalle() {
        return textAreaDetalle;
    }


   
}
