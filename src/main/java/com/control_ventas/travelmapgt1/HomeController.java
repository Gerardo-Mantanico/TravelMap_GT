package com.control_ventas.travelmapgt1;
import com.modelo.DetallesRuta;
import com.modelo.Grafo;
import com.modelo.NodoGrafo;
import com.modelo.TipoDeRuta;
import com.resource.Alerta;
import com.resource.Funcionalidad;
import com.resource.GenerarRuta;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HomeController implements Initializable{
    Grafo grafo;
    Alerta alerta= new Alerta();
    Funcionalidad funcionalidad= new Funcionalidad();
    String tipoRuta=" ";
    List<DetallesRuta> listaRutas=null;
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
    private TextArea DetallesRuta;
    
    @FXML
    private ComboBox<TipoDeRuta> SelecionarRuta;
    

    public HomeController(Grafo grafo) {
        this.grafo = grafo;
    }
    
        @FXML
    private void close() {
        System.exit(0);
    }
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Cargar();
    }

    @FXML
    //meto para encontrar  una ruta
    public void empezar() {
        try {
            if (!Origen.getValue().equals(Destino.getValue()) && !Origen.getValue().equals(null) && !Destino.getValue().equals(null)) {
                if (tipoRuta.equals(" ")) {
                    alerta.showAlert("Por favor, Selecione un tipo de ruta", "Error de seleccion de ruta");
                } else {
                    if (SelecionarRuta.getValue() != null) {
                      if(listaRutas!= null){
                          listaRutas.clear();
                      }
                        generadorRuta.encontrarRutas(grafo, Origen.getValue(), Destino.getValue(), new ArrayList<String> (), new DetallesRuta()); //busca las posibles rutas
                        this.listaRutas=  new ArrayList<>(funcionalidad.buscarRuta(generadorRuta.getRutasEncontradas(),SelecionarRuta.getValue().getId()));  //busca  las posibles rutas en base la funcionalidad
                        PosiblesRutas.setVisible(true);
                        SiguienteRuta.setVisible(true);
                        generadorRuta.imgNodoActual(grafo.buscarNodo(Origen.getValue()),grafo.buscarNodo(Destino.getValue()));
                        generadorRuta.crearRuta();
                        generadorRuta.VerDetalles(tipoRuta);
                        generadorRuta.imprimir();
                    }
                    else{alerta.showAlert("Por favor, Selecione un tipo de ruta", "Error de seleccion de ruta"); }
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

    @FXML
    //metodo para ver reportes de funcionalidad
    public void verDatos() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Dato.fxml"));
        loader.setControllerFactory(controllerClass -> {
            return new DatoController(listaRutas,tipoRuta);
        });
        Parent root = loader.load();
        DatoController controllerB = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
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
    public TextArea getDetallesRuta() {
        return DetallesRuta;
    }
   
    //metodo que se encarga de cargar los recursos al inicio
    void Cargar() {
        generadorRuta.mapa();
        Vehiculo.setDisable(false);
        Caminar.setDisable(false);
        SiguienteRuta.setVisible(false);
        PosiblesRutas.setVisible(false);
        SelecionarRuta.setVisible(false);
        NodoGrafo temp = grafo.getPrimero();
        while (temp != null) {
            Origen.getItems().add(temp.getNombre());
            Destino.getItems().add(temp.getNombre());
            temp = temp.getSiguiente();
        }
        Vehiculo.setOnAction(event -> {
            if (!Vehiculo.isSelected()) {
                tipoRuta = " ";
                return;
            } else {
                Caminar.setSelected(false);
                tipoRuta = "Vehiculo";
                SelecionarRuta.getItems().clear();
                funcionalidad.cargar(SelecionarRuta, tipoRuta);
                SelecionarRuta.setVisible(true);
            }
        });
        Caminar.setOnAction(event -> {
            if (!Caminar.isSelected()) {
                tipoRuta = " ";
                return;
            } else {
                Vehiculo.setSelected(false);
                tipoRuta = "Caminar";
                SelecionarRuta.getItems().clear();
                funcionalidad.cargar(SelecionarRuta, tipoRuta);
                SelecionarRuta.setVisible(true);
            }
        });
    }

    @FXML
    public void recalcular() {
        generadorRuta.encontrarRutas(grafo, PosiblesRutas.getValue(), Destino.getValue(), new ArrayList<String>(), new DetallesRuta()); //busca las posibles rutas
        funcionalidad.buscarRuta(generadorRuta.getRutasEncontradas(), SelecionarRuta.getValue().getId()); //busca  las posibles rutas en base la funcionalidad
        if (generadorRuta.imgNodoActual(grafo.buscarNodo(PosiblesRutas.getValue()), grafo.buscarNodo(Destino.getValue()))) {
            PosiblesRutas.setVisible(false);
            SiguienteRuta.setVisible(false);
            alerta.showAlert("¡Excelente! Has alcanzado tu destino.", "Informativo");
        }
        generadorRuta.VerDetalles(tipoRuta);
        generadorRuta.imprimir();
    }
}
