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
    private ImageView Mapas;

    @FXML
    private Button Siguiente;

    @FXML
    private ImageView close;


    @FXML
    public void initialize(URL url, ResourceBundle rb) {
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
           ImportarTrafico.setVisible(true);
           ImportarRuta.setVisible(false);
        }
    }

    @FXML
    private void ImportarTrafico() {
        if (archivo.Lectura(2, ImportarTrafico)) {
            Siguiente.setVisible(true);
            archivo.getGrafo().imprimir(Mapas);
            //img.img(Mapas, "/img/mapaInicial.png");
            ImportarTrafico.setVisible(false);
        }
    }

    @FXML
    private void siguienteVentana() throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("Home_1.fxml"));
         loader.setControllerFactory(controllerClass -> {
            return new HomeController(archivo.getGrafo(), archivo.getGrafo2());
        });
         Parent root = loader.load();
        App.setRoot(root);
    }
}
