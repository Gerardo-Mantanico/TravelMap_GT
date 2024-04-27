/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

import java.util.ArrayList;
import java.util.List;

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
    int rapidezVehiculo;
    int rapidezCaminando;
    int rapidez;
    List<Trafico> listaTrafico= new ArrayList<>();
   
    public DetallesArco(int tiempoVehiculo, int tiempoPie, int consumoGas, int desgastePersona, int distancia) {
        this.tiempoVehiculo = tiempoVehiculo;
        this.tiempoPie = tiempoPie;
        this.consumoGas = consumoGas;
        this.desgastePersona = desgastePersona;
        this.distancia = distancia;
        this.rapidezVehiculo=distancia/tiempoVehiculo;
        this.rapidezCaminando=distancia/tiempoPie;
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


    public List<Trafico> getListaTrafico() {
        return listaTrafico;
    }

    public void setListaTrafico(List<Trafico> listaTrafico) {
        this.listaTrafico = listaTrafico;
    }

    public int getRapidezVehiculo() {
        return rapidezVehiculo;
    }

    public void setRapidezVehiculo(int rapidezVehiculo) {
        this.rapidezVehiculo = rapidezVehiculo;
    }

    public int getRapidezCaminando() {
        return rapidezCaminando;
    }

    public void setRapidezCaminando(int rapidezCaminando) {
        this.rapidezCaminando = rapidezCaminando;
    }

    public int getRapidez() {
        return rapidez;
    }

    public void setRapidez(int rapidez) {
        this.rapidez = rapidez;
    }
     
    
}


