/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

/**
 *
 * @author Gerardo Tax
 */
public class TipoDeRuta {
    String tipo;
    int id;
    public TipoDeRuta(String tipo ,  int id){
        this.tipo=tipo;
        this.id=id;
    }

    public String getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return  tipo;
    }
    
}
