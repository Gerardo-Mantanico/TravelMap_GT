package com.control_ventas.travelmapgt1;
import com.resource.Alerta;
import com.util.ArchivoEntrada;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Gerardo Tax
 */
public class ImportFileController implements Initializable {
    private Stage stage;
    Alerta alerta = new Alerta();
    ArchivoEntrada archivo = new ArchivoEntrada();
    @FXML
    private Pane Bienvenida;

    @FXML
    private Button ImportarRuta;

    @FXML
    private Button ImportarTrafico;

    @FXML
    private Pane PanelMapa;

    @FXML
    private ImageView Mapa;
    @FXML
    private Button Siguiente;

    @FXML
    public void initialize(URL url, ResourceBundle rb) {
        PanelMapa.setVisible(false);
        ImportarTrafico.setVisible(false);
        Siguiente.setVisible(false);
    }

    @FXML
    private void close() {
        System.exit(0);
    }

    @FXML
    private void ImportarRuta() {
        if (archivo.Lectura(1, ImportarRuta)) {
            PanelMapa.setVisible(true);
            Siguiente.setVisible(true);
            archivo.getGrafo().imprimir();
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    Image image = new Image(getClass().getResourceAsStream("/img/mapaInicial.png"));
                    Mapa.setImage(image);
                }
            }, 2000);
        }
    }

    @FXML
    private void ImportarTrafico() {
        if (archivo.Lectura(2, ImportarTrafico)) {
            PanelMapa.setVisible(true);
            Image image = new Image(getClass().getResourceAsStream("@../../../../resources/img/mapaInicial.png"));
            Mapa.setImage(image);
        }
    }

    @FXML
    private void siguienteVentana() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Home.fxml"));
        loader.setControllerFactory(controllerClass -> {
        return new HomeController(archivo.getGrafo());
        });
        Parent root = loader.load();
        HomeController controllerB = loader.getController();  
        System.out.print("El grafo es "+archivo.getGrafo().getPrimero().getNombre());
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }


    // Método para establecer el Stage desde la clase principal
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
