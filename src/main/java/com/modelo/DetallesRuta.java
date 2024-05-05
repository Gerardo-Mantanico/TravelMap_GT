/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

import java.util.List;

/**
 *
 * @author Gerardo Tax
 */
public class DetallesRuta {
    int llave;
    int tiempoVehiculo;
    int tiempoPie;
    int consumoGas;
    int desgastePersona;
    int distancia; 
    float rapidezVehiculo;
    float rapidezCaminando;
    List<String> lista;
    String ubicacionActual;


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

    public List<String> getLista() {
        return lista;
    }

    public void setLista(List<String> lista) {
        this.lista = lista;
    }
    
    public void inicializar(){
        this.consumoGas=0;
        this.desgastePersona=0;
        this.distancia=0;
        this.tiempoPie=0;
        this.tiempoVehiculo=0;
        this.rapidezCaminando=0;
        this.rapidezVehiculo=0;

    }

    public int getLlave() {
        return llave;
    }

    public void setLlave(int llave) {
        this.llave = llave;
    }

    public float getRapidezVehiculo() {
        return rapidezVehiculo;
    }

    public void setRapidezVehiculo(float rapidezVehiculo) {
        this.rapidezVehiculo = rapidezVehiculo;
    }

    public float getRapidezCaminando() {
        return rapidezCaminando;
    }

    public void setRapidezCaminando(float rapidezCaminando) {
        this.rapidezCaminando = rapidezCaminando;
    }
  
    public String getUbicacionAcutal(){
        return this.lista.get(0);
    }
 
    
}
