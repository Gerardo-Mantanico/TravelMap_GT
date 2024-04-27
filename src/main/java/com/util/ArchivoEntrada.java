package com.util;
import com.modelo.Arco;
import com.modelo.DetallesArco;
import com.modelo.Grafo;
import com.modelo.NodoGrafo;
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
    Grafo grafo2= new Grafo();
    Alerta alerta = new Alerta();

    public boolean Lectura(int opcion, Button buttonImport) {
        boolean estado=false;
        int lineaCont=1;
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
                    lineaCont++;
                }
                scanner.close();
                alerta.showAlert("Archivo importado con exito","Mensaje Informatipo");
                estado=true;
            } catch (Exception ex) {
                alerta.showAlert("No se pudo leer el archivo porque en la linea  "+lineaCont+" existe un error ","Error");
            }
        }
        return estado;
    }

    void cargarRuta(String texto) {
        String campo[] = texto.split("\\|");
        grafo.nuevoNodo(campo[0]);
        grafo.nuevoNodo(campo[1]);
        grafo2.nuevoNodo(campo[0]);
        grafo2.nuevoNodo(campo[1]);
        DetallesArco detalles = new DetallesArco(
                Integer.valueOf(campo[2]),
                Integer.valueOf(campo[3]),
                Integer.valueOf(campo[4]),
                Integer.valueOf(campo[5]),
                Integer.valueOf(campo[6])
        );
        grafo.NuevaArco(campo[0], campo[1], detalles);
        //metodo para generar diferentes tipo de rutas
         grafo2.NuevaArco(campo[0], campo[1], detalles);
        grafo2.NuevaArco(campo[1], campo[0], detalles);
    }

    //este metodo es el encargado de importar el trafico
    void cargarTrafico(String texto) {
        String campo[] = texto.split("\\|");
        NodoGrafo temp = grafo.buscarNodo(campo[0]);
        if (temp != null) {
            Arco tempArco = temp.getLista().buscar(campo[1]);
            if(tempArco!=null){
                 Trafico trafico = new Trafico(
                    Integer.valueOf(campo[2]),
                    Integer.valueOf(campo[3]),
                    Integer.valueOf(campo[4])
            );
            tempArco.getDetalle().getListaTrafico().add(trafico);
            }
        }
    }
    public Grafo getGrafo() {
        return grafo;
    }

    public Grafo getGrafo2() {
        return grafo2;
    }

    
}
