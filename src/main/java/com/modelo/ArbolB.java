
package com.modelo;

import com.control_ventas.travelmapgt1.a;

/**
 *
 * @author Gerardo Tax
 */
public class ArbolB {
      public NodoArbolB raiz;
    private int grado; // Grado del árbol B

    public ArbolB(int grado) {
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
            if (raiz.claves.size() == 2 * grado - 1) {
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
            if (hijo.claves.size() == 2 * grado - 1) {
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
        for (int i = 0; i < grado - 1; i++) {
            nuevoNodo.claves.add(hijo.claves.remove(grado));
            nuevoNodo.nombresNodos.add(hijo.nombresNodos.remove(grado));
        }
        if (!hijo.esHoja) {
            for (int i = 0; i < grado; i++) {
                nuevoNodo.hijos.add(hijo.hijos.remove(grado));
            }
        }
        padre.claves.add(indice, hijo.claves.remove(grado - 1));
        padre.nombresNodos.add(indice, hijo.nombresNodos.remove(grado - 1));
        padre.hijos.add(indice + 1, nuevoNodo);
    }

    // Método para imprimir el árbol B
    public void imprimir() {
        imprimirRecursivo(raiz, "");
    }

    private void imprimirRecursivo(NodoArbolB nodo, String prefijo) {
        if (nodo != null) {
            System.out.print(prefijo);
            
            for (int i = 0; i < nodo.claves.size(); i++) {
                System.out.print("(" + nodo.nombresNodos.get(i) +nodo.claves.get(i)+ ") ");
            }
            System.out.println();
            if (!nodo.esHoja) {
                for (NodoArbolB hijo : nodo.hijos) {
                    imprimirRecursivo(hijo, prefijo + "    ");
                }
            }
        }
    }

}


