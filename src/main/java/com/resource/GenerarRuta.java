package com.resource;
import com.control_ventas.travelmapgt1.HomeController;
import com.grafica.GeneradorDotFile;
import com.modelo.Arco;
import com.modelo.DetallesRuta;
import com.modelo.Grafo;
import com.modelo.NodoGrafo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gerardo Tax
 */
public class GenerarRuta {

    List<DetallesRuta> rutasEncontradas = new ArrayList<>();
    HomeController homeController;
    List<NodoGrafo> visitados;
    GeneradorDotFile dot = new GeneradorDotFile();
    ImagenIcon img = new ImagenIcon();

    public GenerarRuta(HomeController homeController) {
        this.homeController = homeController;
    }

    public boolean imgNodoActual(NodoGrafo origen, NodoGrafo destino) {
        boolean estado=false;
        NodoGrafo primero = homeController.getGrafo().getPrimero();  //todo el mapa
        dot.archivodot(homeController.getGrafo().getPrimero(), "src/main/resources/img/mapaInicial.dot", rutasEncontradas.get(0).getLista());
        img.img(homeController.getImgActual(), "/img/mapaInicial.png");
        if (origen == destino) {
            estado=true;
            rutasEncontradas.clear();
        } else {
            Arco temp = origen.getLista().getPrimero();
            homeController.getPosiblesRutas().getItems().clear();
            while (temp != null) {
                homeController.getPosiblesRutas().getItems().add(temp.getDestino());
                temp = temp.getSiguiente();
            }
        }
        homeController.getGrafo().setPrimero(primero);
        return estado;
    }
    
    //metodo para mostrar la ruta en la imagen
    public void crearRuta() {
        dot.ruta(rutasEncontradas.get(0).getLista(), "src/main/resources/img/ubicacionActual.dot");
        img.img(homeController.getImgMejorRuta(), "/img/ubicacionActual.png");
    }

    public void mapa() {
        img.img(homeController.getImgActual(), "/img/mapaInicial.png");

    }

        //este metodo es el encargado de buscar las diferentes tipos de rutas
    public void encontrarRutas(Grafo grafo, String inicio, String destino, List<String> visitados, DetallesRuta detalles) {
        visitados.add(inicio);
        if (inicio.equals(destino)) {
            DetallesRuta detalles1 = new DetallesRuta();
            detalles1.setConsumoGas(detalles.getConsumoGas());
            detalles1.setDesgastePersona(detalles.getDesgastePersona());
            detalles1.setDistancia(detalles.getDistancia());
            detalles1.setTiempoPie(detalles.getTiempoPie());
            detalles1.setTiempoVehiculo(detalles.getTiempoVehiculo());
            detalles1.setLista(new ArrayList<>(visitados));
            rutasEncontradas.add(detalles1);
            detalles.inicializar();
        } else {
            NodoGrafo temp = grafo.getPrimero();
            while (temp != null) {
                if (temp.getNombre().equals(inicio)) {
                    Arco arco = temp.getLista().getPrimero();
                    while (arco != null) {
                        //verifica que si en la lista existe el destino
                        if (!visitados.contains(arco.getDestino())) {
                            detalles.setConsumoGas(detalles.getConsumoGas() + arco.getDetalle().getConsumoGas());
                            detalles.setDesgastePersona(detalles.getDesgastePersona() + arco.getDetalle().getDesgastePersona());
                            detalles.setDistancia(detalles.getDistancia() + arco.getDetalle().getDistancia());
                            detalles.setTiempoPie(detalles.getTiempoPie() + arco.getDetalle().getTiempoPie());
                            detalles.setTiempoVehiculo(detalles.getTiempoVehiculo() + arco.getDetalle().getTiempoVehiculo());
                            encontrarRutas(grafo, arco.getDestino(), destino, new ArrayList<String>(visitados), detalles);
                        }
                        arco = arco.getSiguiente();
                    }
                }
                temp = temp.getSiguiente();
            }

        }
    }

    //metodo para impimir las rutas y caracteristicas 
    public void imprimir() {
        for (DetallesRuta ruta : rutasEncontradas) {
            System.out.println("Distancia Total   " + ruta.getDistancia() + " Km   Total de tiempo vehiculo " + ruta.getTiempoVehiculo() + " s   Caminando"
                    + ruta.getTiempoPie() + " s  Consumo de Gas " + ruta.getConsumoGas() + " galones  desgaste fisico " + ruta.getDesgastePersona());
            for (String nodo : ruta.getLista()) {
                System.out.print(nodo + " -> ");
            }
            System.out.println(); // Para imprimir un salto de línea al final de cada ruta
        }
    }

    
    //metodo para mostrar los detalles de las rutas
    public void VerDetalles(String tipo) {
        this.homeController.getDetallesRuta().setText("");
        DetallesRuta ruta = rutasEncontradas.get(0);
        if (tipo.equals("Vehiculo")) {
            homeController.getDetallesRuta().setText("Distancia: " + ruta.getDistancia() + " Km  \n\nTiempo: " + ruta.getTiempoVehiculo() + " Minutos   \n\nConsumo de gasolina: " + ruta.getConsumoGas() + " Galones \n");
        } else {
            homeController.getDetallesRuta().setText("Distancia: " + ruta.getDistancia() + " Km  \n\nTiempo: " + ruta.getTiempoPie() + " Minutos \n\nDesgaste fisico: " + ruta.getDesgastePersona() + " Caloria \n");
        }
    }

    public List<DetallesRuta> getRutasEncontradas() {
        return rutasEncontradas;
    }

}
