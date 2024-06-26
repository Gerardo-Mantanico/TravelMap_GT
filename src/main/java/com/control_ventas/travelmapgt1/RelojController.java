/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.control_ventas.travelmapgt1;

import com.resource.Alerta;
import com.resource.Reloj;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Gerardo Tax
 */
public class RelojController implements Initializable {
    boolean estado=true;
    Reloj temp;
    Alerta alerta; 
     @FXML
    private TextField hora;

    @FXML
    private TextField minutos;

    @FXML
    private Button pausar;

    @FXML
    private TextField segundos;

    
    public RelojController(Reloj temp, Alerta alerta){
        this.temp=temp;
        this.alerta=alerta;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       cargarDatos();
    }  
    
    public void cargarDatos(){
        hora.setText(String.valueOf(temp.getNuevaHora()));
        minutos.setText(String.valueOf(temp.getNuevosMinutos()));
        segundos.setText(String.valueOf(temp.getNuevosSegundos()));
    }
    
    @FXML
    public void pausar() {
        if (estado) {
            temp.setEstado(false);
            estado = false;
            pausar.setText("Reanudar");
        } else {
            temp.setEstado(true);
            estado = true;
            pausar.setText("Pausar");
        }
        cargarDatos();
    }
    
    @FXML
    public void editar() {
        try {
            
            temp.setNuevaHora(Integer.valueOf(hora.getText()));
            temp.setNuevosMinutos(Integer.valueOf(minutos.getText()));
            temp.setNuevosSegundos(Integer.valueOf(segundos.getText()));
            cargarDatos();
        } catch (Exception e) {
            alerta.showAlert("Lo siento, solo se permiten números en este campo", "Error  de ingreso de hora");
            cargarDatos();
        }

    }
   
}
