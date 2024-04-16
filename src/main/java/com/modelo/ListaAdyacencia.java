/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.modelo;

/**
 *
 * @author Gerardo Tax
 */
public class ListaAdyacencia {
    Arco primero;
    Arco ultimo;
    
    public ListaAdyacencia(){
        this.primero=null;
        this.ultimo=null;
    }
    //estado de la lista
    public boolean listaVacia(){
      return primero==null;
    }
    //metodo para crear un nuevo nodo
    public void nuevaAdyacencia(String destino,DetallesArco detalle){
       if(!existenciaArco(destino)){
            Arco nodo= new Arco(destino,detalle);
            insertar(nodo, destino);
        }
    }
    //insetar el nuevo arco en la vertice
    private void insertar(Arco nodo, String destino){
        if(listaVacia()){
            primero=nodo;
            ultimo=nodo;
        }
        else{
           ultimo.siguiente=nodo;
           ultimo=nodo;
        }
    }
    
    
    //verificar si el arco existe    
    boolean existenciaArco(String destino){
        boolean existente=false;
         if(!listaVacia()){
            Arco temporal= primero;
            while(temporal!=null && !existente){
                if(temporal.destino.equals(destino)){
                    existente=true;
                }
                temporal=temporal.siguiente;
            }
        }
        return existente;
    }

    public Arco getPrimero() {
        return primero;
    }

    public void setPrimero(Arco primero) {
        this.primero = primero;
    }

    public Arco getUltimo() {
        return ultimo;
    }

    public void setUltimo(Arco ultimo) {
        this.ultimo = ultimo;
    }

 
    
}
