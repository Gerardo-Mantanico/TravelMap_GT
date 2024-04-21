package com.control_ventas.travelmapgt1;
import com.resource.Alerta;
import com.resource.ImagenIcon;
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
import javafx.stage.StageStyle;

/**
 *
 * @author Gerardo Tax
 */
public class ImportFileController implements Initializable {
    Alerta alerta = new Alerta();
    ArchivoEntrada archivo = new ArchivoEntrada();
    ImagenIcon img= new ImagenIcon();
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
            img.img(Mapa, "/img/mapaInicial.png");
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
        App.setRoot(root);
    }
}
