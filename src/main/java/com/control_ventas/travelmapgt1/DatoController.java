/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.control_ventas.travelmapgt1;

import com.modelo.DetallesRuta;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author Gerardo Tax
 */
public class DatoController implements Initializable {
    List<DetallesRuta> lista = new ArrayList<>();
    String tipo;

    @FXML
    private TextArea detalles;

    @FXML
    private Label tipoReporte;

    /**
     * Initializes the controller class.
     */
    public DatoController(List<DetallesRuta> lista, String tipo){
        this.tipo=tipo;
        this.lista=lista;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        detalles.setEditable(false);
        if (tipo.equals("Vehiculo")) {
            tipoReporte.setText("Informacion de ruta en vehiculo");
            cargarDetalles("Mejor Ruta", lista.getFirst(), true);
            cargarDetalles("Peor Ruta", lista.getLast(),true);

        }
        else{
             tipoReporte.setText("Informacion de ruta caminando");
            cargarDetalles("Mejor Ruta", lista.getFirst(),false);
            cargarDetalles("Peor Ruta", lista.getLast(),false);
        }

    }    
    
    void cargarDetalles(String tipo, DetallesRuta list, boolean estado) {
        detalles.appendText(""
                + tipo + " \n"
                + "Distancia: " + list.getDistancia() + "Km");
        if (estado) {
            detalles.appendText("\nTiempo : " + list.getTiempoVehiculo() + " Minutos"
                    + "\nConsumo de gasolina: " + list.getConsumoGas() + " Galones");
        } else {
            detalles.appendText("\nTiempo : " + list.getTiempoPie() + " Minutos"
                    + "\nDesgaste fisico : " + list.getDesgastePersona() + " Calorias");
        }
        detalles.appendText(" \n\nRuta \n");
        for (int i = 0; i < list.getLista().size(); i++) {
            if (i == list.getLista().size() - 1) {
                detalles.appendText(list.getLista().get(i) + "\n");
                detalles.appendText("---------------------------------------------------------------------------------------------- \n\n\n");
            } else {
                detalles.appendText(list.getLista().get(i) + "-->  ");
            }
        }
    }




}
