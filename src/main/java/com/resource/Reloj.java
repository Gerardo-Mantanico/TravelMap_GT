/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.resource;

import javafx.application.Platform;
import javafx.scene.control.Label;

/**
 *
 * @author Gerardo Tax
 */
public class Reloj {

    int nuevaHora = 23;
    int nuevosMinutos = 58;
    int nuevosSegundos = 23;
    boolean estado = true;

    //metodo para generar la  hora actual
    public void horaActual(Label Reloj) {
        Thread hiloHora = new Thread(() -> {
            while (true) {
                if (estado) {
                    nuevosSegundos++;
                    if (nuevosSegundos == 59) {
                        nuevosMinutos++;
                        nuevosSegundos = 0;
                    }
                    if (nuevosMinutos == 60) {
                        nuevaHora++;
                        nuevosMinutos = 0;
                    }
                    if (nuevaHora == 24) {
                        nuevaHora = 0;
                    }
                }
                String horaFormateada = String.valueOf(nuevaHora) + ":" + String.valueOf(nuevosMinutos) + ":" + String.valueOf(nuevosSegundos);
                Platform.runLater(() -> Reloj.setText(horaFormateada));
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        hiloHora.setDaemon(true);
        hiloHora.start();
    }

    public int getNuevaHora() {
        return nuevaHora;
    }

    public void setNuevaHora(int nuevaHora) {
        this.nuevaHora = nuevaHora;
    }

    public int getNuevosMinutos() {
        return nuevosMinutos;
    }

    public void setNuevosMinutos(int nuevosMinutos) {
        this.nuevosMinutos = nuevosMinutos;
    }

    public int getNuevosSegundos() {
        return nuevosSegundos;
    }

    public void setNuevosSegundos(int nuevosSegundos) {
        this.nuevosSegundos = nuevosSegundos;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
