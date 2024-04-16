package com.util;
import com.modelo.DetallesArco;
import com.modelo.Grafo;
import com.modelo.Trafico;
import com.resource.Alerta;
import java.io.File;
import java.util.Scanner;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Gerardo Tax
 */
public class ArchivoEntrada {
    Grafo grafo = new Grafo();
    Alerta alerta = new Alerta();

    public boolean Lectura(int opcion, Button buttonImport) {
        boolean estado=false;
        Stage stage = (Stage) buttonImport.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de texto (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String linea = scanner.nextLine();
                    if (opcion == 1) {
                        cargarRuta(linea);
                    } else {
                        cargarTrafico(linea);
                    }

                }
                scanner.close();
                alerta.showAlert("Archivo importado con exito","Mensaje Informatipo");
                estado=true;
            } catch (Exception ex) {
                alerta.showAlert("Error no se pudo selecionar un archivo","Error");
            }
        }
        return estado;
    }

    void cargarRuta(String texto) {
        String campo[] = texto.split("\\|");
        grafo.nuevoNodo(campo[0]);
        grafo.nuevoNodo(campo[1]);
        DetallesArco detalles = new DetallesArco(
                Integer.valueOf(campo[2]),
                Integer.valueOf(campo[3]),
                Integer.valueOf(campo[4]),
                Integer.valueOf(campo[5]),
                Integer.valueOf(campo[6])
        );
        grafo.NuevaArco(campo[0], campo[1], detalles);
    }

    void cargarTrafico(String texto) {
        String campo[] = texto.split("\\|");
        Trafico trafico = new Trafico(
                campo[0],
                campo[1],
                Integer.valueOf(campo[2]),
                Integer.valueOf(campo[3]),
                Integer.valueOf(campo[4])
        );
    }

    public Grafo getGrafo() {
        return grafo;
    }
    

}
