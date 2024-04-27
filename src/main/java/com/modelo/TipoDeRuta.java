package com.modelo;

/**
 *
 * @author Gerardo Tax
 */
public class TipoDeRuta {

    String tipo;
    int id;

    public TipoDeRuta(String tipo, int id) {
        this.tipo = tipo;
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return tipo;
    }

}
