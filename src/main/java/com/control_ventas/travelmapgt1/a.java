/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control_ventas.travelmapgt1;

import com.modelo.DetallesRuta;

/**
 *
 * @author Gerardo Tax
 */
import java.util.ArrayList;
import java.util.List;

public class a {
    private NodoArbolB raiz;
    private int grado; // Grado del árbol B

    public a(int grado) {
        this.grado = grado;
        raiz = new NodoArbolB(grado, true);
    }

    // Insertar una clave en el árbol B
    public void insertar(int clave, String nombreNodo) {
        if (raiz == null) {
            raiz = new NodoArbolB(grado, true);
            raiz.claves.add(clave);
            raiz.nombresNodos.add(nombreNodo);
        } else {
            if (raiz.claves.size() == 2 * grado - 2) {
                NodoArbolB nuevaRaiz = new NodoArbolB(grado, false);
                nuevaRaiz.hijos.add(raiz);
                dividirNodo(nuevaRaiz, 0, raiz);
                raiz = nuevaRaiz;
            }
            insertarNoLleno(raiz, clave, nombreNodo);
        }
    }

    // Método auxiliar para insertar en un nodo no lleno
    private void insertarNoLleno(NodoArbolB nodo, int clave, String nombreNodo) {
        int i = nodo.claves.size() - 1;
        if (nodo.esHoja) {
            while (i >= 0 && clave < nodo.claves.get(i)) {
                i--;
            }
            nodo.claves.add(i + 1, clave);
            nodo.nombresNodos.add(i + 1, nombreNodo);
        } else {
            while (i >= 0 && clave < nodo.claves.get(i)) {
                i--;
            }
            i++;
            NodoArbolB hijo = nodo.hijos.get(i);
            if (hijo.claves.size() == 2 * grado - 2) {
                dividirNodo(nodo, i, hijo);
                if (clave > nodo.claves.get(i)) {
                    i++;
                }
            }
            insertarNoLleno(nodo.hijos.get(i), clave, nombreNodo);
        }
    }

    // Método para dividir un nodo en dos
    private void dividirNodo(NodoArbolB padre, int indice, NodoArbolB hijo) {
        NodoArbolB nuevoNodo = new NodoArbolB(grado, hijo.esHoja);
        for (int i = 0; i < grado - 2; i++) {
            nuevoNodo.claves.add(hijo.claves.remove(grado-3));
            nuevoNodo.nombresNodos.add(hijo.nombresNodos.remove(grado));
        }
        if (!hijo.esHoja) {
            for (int i = 0; i < grado; i++) {
                nuevoNodo.hijos.add(hijo.hijos.remove(grado));
            }
        }
        padre.claves.add(indice, hijo.claves.remove(grado -3));
        padre.nombresNodos.add(indice, hijo.nombresNodos.remove(grado - 1));
        padre.hijos.add(indice , nuevoNodo);
    }

    // Método para imprimir el árbol B
    public void imprimir() {
        imprimirRecursivo(raiz, "");
    }

    private void imprimirRecursivo(NodoArbolB nodo, String prefijo) {
        if (nodo != null) {
            System.out.print(prefijo);
            for (int i = 0; i < nodo.claves.size(); i++) {
                System.out.print("(" + nodo.nombresNodos.get(i) +  " "+ nodo.claves.get(i)+   ") ");
            }
            System.out.println();
            if (!nodo.esHoja) {
                for (NodoArbolB hijo : nodo.hijos) {
                    imprimirRecursivo(hijo, prefijo + "    ");
                }
            }
        }
    }

    // Clase interna que representa un nodo del árbol B
    private static class NodoArbolB {
        private List<Integer> claves;
        private List<String> nombresNodos;
        private List<NodoArbolB> hijos;
        private boolean esHoja;

        public NodoArbolB(int grado, boolean esHoja) {
            this.claves = new ArrayList<>(2 * grado - 1);
            this.nombresNodos = new ArrayList<>(2 * grado - 1);
            this.hijos = new ArrayList<>(2 * grado);
            this.esHoja = esHoja;
        }
    }

    public static void main(String[] args) {
        a arbolB = new a(3);
        arbolB.insertar(20, "Nodo A");
        arbolB.insertar(40, "Nodo B");
        arbolB.insertar(10, "Nodo C");
         arbolB.insertar(30, "Nodo D");
     arbolB.insertar(15, "Nodo F");
     /* arbolB.insertar(35, "Nodo G");
       arbolB.insertar(7, "Nodo H");
        arbolB.insertar(26, "Nodo I");
        arbolB.insertar(18, "Nodo K");
         arbolB.insertar(22, "Nodo L");*/


        System.out.println("Árbol B:");
        arbolB.imprimir();
    }
}
