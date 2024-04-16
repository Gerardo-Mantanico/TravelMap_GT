/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

/**
 *
 * @author Gerardo Tax
 */
public class DetallesArco {
    int tiempoVehiculo;
    int tiempoPie;
    int consumoGas;
    int desgastePersona;
    int distancia; 

    public DetallesArco(int tiempoVehiculo, int tiempoPie, int consumoGas, int desgastePersona, int distancia) {
        this.tiempoVehiculo = tiempoVehiculo;
        this.tiempoPie = tiempoPie;
        this.consumoGas = consumoGas;
        this.desgastePersona = desgastePersona;
        this.distancia = distancia;
    }

    public int getTiempoVehiculo() {
        return tiempoVehiculo;
    }

    public void setTiempoVehiculo(int tiempoVehiculo) {
        this.tiempoVehiculo = tiempoVehiculo;
    }

    public int getTiempoPie() {
        return tiempoPie;
    }

    public void setTiempoPie(int tiempoPie) {
        this.tiempoPie = tiempoPie;
    }

    public int getConsumoGas() {
        return consumoGas;
    }

    public void setConsumoGas(int consumoGas) {
        this.consumoGas = consumoGas;
    }

    public int getDesgastePersona() {
        return desgastePersona;
    }

    public void setDesgastePersona(int desgastePersona) {
        this.desgastePersona = desgastePersona;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }
    
}


