package com.modelo;

import com.grafica.GeneradorDotFile;


/**
 *
 * @author Gerardo Tax
 */
public class Grafo {
    NodoGrafo primero;
    NodoGrafo ultimo;
    
    public Grafo(){
        this.primero= null;
        this.ultimo=null;
    }
    
    //ver si el grafo esta vacio
    boolean grafoVacio(){
        return primero==null;
    }
    
    //verificar la existencia del grafo
    boolean existeVertice(String dato){
        boolean existente = false;
        if(!grafoVacio()){
            NodoGrafo temporal= primero;
            while(temporal!=null && !existente){
                if(temporal.nombre.equals(dato)){
                    existente=true;
                }
                temporal=temporal.siguiente;
            }
        }
        return existente;
    }
    
    //crear nuevo arco
   public void NuevaArco(String origen, String destino, DetallesArco arco){
       if(existeVertice(origen) && existeVertice(destino)){
           NodoGrafo posicion=primero;
           while(!posicion.nombre.equals(origen)){
               posicion=posicion.siguiente;
           }
          posicion.lista.nuevaAdyacencia(destino,arco);
       }
   }
   
   public void nuevoNodo(String dato){
       if(!existeVertice(dato)){
           NodoGrafo nuevoNodo= new NodoGrafo(dato);
           if(grafoVacio()){
               this.primero=nuevoNodo;
               this.ultimo=nuevoNodo;
           }
           else {
               ultimo.siguiente=nuevoNodo;
               ultimo=nuevoNodo;
           }
       }
   }
   
   public void imprimir(){
      GeneradorDotFile dot= new GeneradorDotFile();
      dot.archivodot(primero, "src/main/resources/img/mapaInicial.dot",null);
   }
    public NodoGrafo getPrimero() {
        return primero;
    }
    
    //metodo para buscar un  nodo y retornarlo
    public NodoGrafo buscarNodo(String nodo){
        NodoGrafo prim=primero;
        NodoGrafo temp=null;
        while(primero!=null){
            if(primero.nombre.equals(nodo)){
                temp=primero;
                break;
            }
            else{ primero=primero.getSiguiente();}
        }
        primero=prim;
        return temp;
    }

    public void setPrimero(NodoGrafo primero) {
        this.primero = primero;
    }
    
     @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
