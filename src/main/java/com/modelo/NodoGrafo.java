
package com.modelo;

/**
 *
 * @author Gerardo Tax
 */
public class NodoGrafo {
    String nombre;
    NodoGrafo siguiente;
    //lista de los nodos que se van a conectar
    ListaAdyacencia lista;
    
    public NodoGrafo(String nombre){
        this.nombre=nombre;
        this.lista= new ListaAdyacencia();
        this.siguiente=null;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public NodoGrafo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoGrafo siguiente) {
        this.siguiente = siguiente;
    }

    public ListaAdyacencia getLista() {
        return lista;
    }

    public void setLista(ListaAdyacencia lista) {
        this.lista = lista;
    }
    
    
}
