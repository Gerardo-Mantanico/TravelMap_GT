    
package com.modelo;

/**
 *
 * @author Gerardo Tax
 */

// es el objeto que conecta dos vertices (nodos)
public class Arco {
    String destino;
    DetallesArco detalle;
    Arco siguiente;
    
   /* public Arco (String destino){
        this.destino=destino;
        this.siguiente=null;
    }*/

    public Arco(String destino,DetallesArco detalle) {
        this.destino = destino;
        this.detalle=detalle;
        this.siguiente = null;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public DetallesArco getDetalle() {
        return detalle;
    }

    public void setDetalle(DetallesArco detalle) {
        this.detalle = detalle;
    }

    public Arco getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Arco siguiente) {
        this.siguiente = siguiente;
    }
    

    
}
