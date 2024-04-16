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
      dot.generateDotFile(primero, "src/main/resources/img/mapaInicial.dot");
   }

    public NodoGrafo getPrimero() {
        return primero;
    }
   
}
