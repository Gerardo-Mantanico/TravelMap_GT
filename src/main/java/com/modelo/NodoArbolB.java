package com.modelo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gerardo Tax
 */
public class NodoArbolB {
    public List<Integer> claves;
    public List<String> nombresNodos;
    public List<NodoArbolB> hijos;
    public boolean esHoja;

    public NodoArbolB(int grado, boolean esHoja) {
        this.claves = new ArrayList<>(2 * grado - 1);
        this.nombresNodos = new ArrayList<>(2 * grado - 1);
        this.hijos = new ArrayList<>(2 * grado);
        this.esHoja = esHoja;
    }
}
