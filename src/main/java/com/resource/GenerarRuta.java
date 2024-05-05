package com.resource;
import com.control_ventas.travelmapgt1.HomeController;
import com.grafica.GeneradorDotFile;
import com.modelo.ArbolB;
import com.modelo.Arco;
import com.modelo.DetallesArco;
import com.modelo.DetallesRuta;
import com.modelo.Grafo;
import com.modelo.NodoGrafo;
import com.modelo.Trafico;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Gerardo Tax
 */
public class GenerarRuta {

    List<DetallesRuta> rutasEncontradas = new ArrayList<>();
    HomeController homeController;
    GeneradorDotFile dot = new GeneradorDotFile();
    ImagenIcon img = new ImagenIcon();

    public GenerarRuta(HomeController homeController) {
        this.homeController = homeController;
    }

    public boolean imgNodoActual(Grafo grafo, NodoGrafo origen, NodoGrafo destino) {
        boolean estado = false;
        NodoGrafo primero = grafo .getPrimero();  //todo el mapa
        dot.archivodot(primero, "src/main/resources/img/mapaInicial.dot", rutasEncontradas.get(0).getLista(),homeController.getImgActual()); //realize cambio
        if (origen == destino) {
                  estado = true;
                  rutasEncontradas.clear();
        } else {
                if (origen.getLista().listaVacia()) {
                          rutasEncontradas.get(0).getLista().remove(0);
                           homeController.getPosiblesRutas().getItems().add(rutasEncontradas.get(0).getLista().get(0));
                           rutasEncontradas.get(0).getLista().remove(0);
                } else {
                           Arco temp = origen.getLista().getPrimero();
                           homeController.getPosiblesRutas().getItems().clear();
                            while (temp != null) {
                                    homeController.getPosiblesRutas().getItems().add(temp.getDestino());
                                    temp = temp.getSiguiente();
                           }
                  }
        }
        return estado;
    }

    
    //metodo para mostrar la ruta en la imagen
    public void crearRuta(boolean estado) {
        if (estado) 
                  dot.ruta(rutasEncontradas.get(0).getLista(), "src/main/resources/img/ubicacionActual.dot");
         else {
                  Collections.reverse(rutasEncontradas.get(0).getLista());
                  Collections.reverse(rutasEncontradas.get(rutasEncontradas.size()-1) .getLista());
                  dot.ruta(rutasEncontradas.get(0).getLista(), "src/main/resources/img/ubicacionActual.dot");
        }
        img.img(homeController.getImgMejorRuta(), "/img/ubicacionActual.png");
    }

    public void mapa() {
        img.img(homeController.getImgActual(), "/img/mapaInicial.png");
    }

        //este metodo es el encargado de buscar las diferentes tipos de rutas
   public void encontrarRutas(Grafo grafo, String inicio, String destino, List<String> visitados, DetallesRuta detalles, int hora) {
    visitados.add(inicio);
    if (inicio.equals(destino)) {
        DetallesRuta detalles1 = new DetallesRuta();
        detalles1.setConsumoGas(detalles.getConsumoGas());
        detalles1.setDesgastePersona(detalles.getDesgastePersona());
        detalles1.setDistancia(detalles.getDistancia());
        detalles1.setTiempoPie(detalles.getTiempoPie());
        detalles1.setTiempoVehiculo(detalles.getTiempoVehiculo());
        detalles1.setRapidezVehiculo(detalles.getRapidezVehiculo());
        detalles1.setRapidezCaminando(detalles.getRapidezCaminando());
        detalles1.setLista(new ArrayList<>(visitados));
        rutasEncontradas.add(detalles1);
    } else {
        NodoGrafo temp = grafo.buscarNodo(inicio);
        if (temp != null) {
            Arco arco = temp.getLista().getPrimero();
            while (arco != null) {
                // Verifica si el destino del arco no ha sido visitado
                if (!visitados.contains(arco.getDestino())) {
                    detalles.setConsumoGas(detalles.getConsumoGas() + arco.getDetalle().getConsumoGas());
                    detalles.setDesgastePersona(detalles.getDesgastePersona() + arco.getDetalle().getDesgastePersona());
                    detalles.setDistancia(detalles.getDistancia() + arco.getDetalle().getDistancia());
                    detalles.setTiempoPie(detalles.getTiempoPie() + arco.getDetalle().getTiempoPie());
                    funcionalidadTrafico(hora, arco.getDetalle());  // Verificar si existe tráfico
                     detalles.setTiempoVehiculo(detalles.getTiempoVehiculo() + arco.getDetalle().getTiempo());
                    detalles.setRapidezVehiculo(detalles.getRapidezVehiculo() + arco.getDetalle().getRapidez());
                    detalles.setRapidezCaminando(detalles.getRapidezCaminando() + arco.getDetalle().getRapidezCaminando());
                    // Llamada recursiva con el destino del arco como nuevo inicio
                    encontrarRutas(grafo, arco.getDestino(), destino, visitados, detalles, hora);
                    // Revertir los cambios realizados en detalles después de la llamada recursiva
                    detalles.setConsumoGas(detalles.getConsumoGas() - arco.getDetalle().getConsumoGas());
                    detalles.setDesgastePersona(detalles.getDesgastePersona() - arco.getDetalle().getDesgastePersona());
                    detalles.setDistancia(detalles.getDistancia() - arco.getDetalle().getDistancia());
                    detalles.setTiempoPie(detalles.getTiempoPie() - arco.getDetalle().getTiempoPie());
                    detalles.setTiempoVehiculo(detalles.getTiempoVehiculo() - arco.getDetalle().getTiempoVehiculo());
                    detalles.setRapidezVehiculo(detalles.getRapidezVehiculo() - arco.getDetalle().getRapidez());
                    detalles.setRapidezCaminando(detalles.getRapidezCaminando() - arco.getDetalle().getRapidezCaminando());
                }
                arco = arco.getSiguiente();
            }
        }
    }
    // Cuando terminamos de explorar desde este nodo, lo eliminamos de la lista de visitados
    visitados.remove(visitados.size() - 1);
}


    //metodo para impimir las rutas y caracteristicas 
    public void imprimir() {
        for (DetallesRuta ruta : rutasEncontradas) {
            System.out.println("Distancia Total   " + ruta.getDistancia() + " Km   Total de tiempo vehiculo " + ruta.getTiempoVehiculo() + " s   Caminando"
                    + ruta.getTiempoPie() + " s  Consumo de Gas " + ruta.getConsumoGas() + " galones  desgaste fisico " + ruta.getDesgastePersona()+"    rapidez V  "+ruta.getRapidezVehiculo()+
                        "radiez caminando "+ruta.getRapidezCaminando());
            for (String nodo : ruta.getLista()) {
                System.out.print(nodo + " -> ");
            }
            System.out.println(); // Para imprimir un salto de línea al final de cada ruta
        }

        //Valores a ingresar primera ronda
        ArbolB arbol = new ArbolB(3);
        for (int i = 0; i < rutasEncontradas.size(); i++) {
            String ruta = " ";
            for (String dato : rutasEncontradas.get(i).getLista()) {
                ruta = ruta + dato + " ";
            }
            arbol.insertar(i + 1, ruta);
        }
        //arbol.imprimir();
        dot.generarArbol(arbol, " ", "src/main/resources/img/arbolB.dot");
      //  rutasEncontradas.clear();
    }

    
    //metodo para mostrar los detalles de las rutas
    public void VerDetalles(String tipo ,String hora) {
        this.homeController.getDetallesRuta().setText("");
        DetallesRuta ruta = rutasEncontradas.get(0);
        if (tipo.equals("Vehiculo")) {
            homeController.getDetallesRuta().setText("Distancia: " + ruta.getDistancia() + " Km  \n\nTiempo: " + convertirMinutosAHorasYMinutos(ruta.getTiempoVehiculo() )+ "   \n\nConsumo de gasolina: " + ruta.getConsumoGas() + " Galones \n\nRapidez : "+ruta.getRapidezVehiculo()+" Km/Min\n\n "
                    + cacularTiempo(hora,convertirMinutosAHorasYMinutos(ruta.getTiempoVehiculo() )));
        } else {
            homeController.getDetallesRuta().setText("Distancia: " + ruta.getDistancia() + " Km  \n\nTiempo: " +convertirMinutosAHorasYMinutos(ruta.getTiempoPie() ) + " \n\nDesgaste fisico: " + ruta.getDesgastePersona() + " Caloria \n\nRapidez : "+ruta.getRapidezCaminando()+" Km/Min\n\n"
                    +cacularTiempo(hora,convertirMinutosAHorasYMinutos(ruta.getTiempoPie() )));
        }
        homeController.getUActual().setText("Ubicacion Actual: "+ruta.getUbicacionAcutal());
    }

    //metodo encargado  de monitorear el trafico
    void funcionalidadTrafico(int horaActual, DetallesArco detalles) {
        if (detalles.getListaTrafico().size() != 0) {
            for (Trafico trafico : detalles.getListaTrafico()) {
                if (horaActual >= trafico.getHoraInicio() && horaActual <= trafico.getHoraFinal()) {
                    double porcentaje = ((double) trafico.getProbabilidadTrafico()) / 100.0;
                    double m = detalles.getTiempoVehiculo() * (1 + porcentaje);
                    double rapidez_vehiculo = detalles.getDistancia() / m;
                    detalles.setRapidez((int) rapidez_vehiculo);
                    detalles.setTiempo((int)m);
                } else {
                    detalles.setRapidez(detalles.getRapidezVehiculo());
                    detalles.setTiempo(detalles.getTiempoVehiculo());
                }
            }
        } else {
            detalles.setRapidez(detalles.getRapidezVehiculo());
            detalles.setTiempo(detalles.getTiempoVehiculo());
        }
    }
    
        public List<DetallesRuta> getRutasEncontradas() {
        return rutasEncontradas;
    }

        //metodo par trasformar minutos a horas
    String convertirMinutosAHorasYMinutos(int minutos) {
        if (minutos < 0) 
            return "Error: los minutos no pueden ser negativos";
        int horas = minutos / 60;
        int minutosRestantes = minutos % 60;
        String tiempoFormateado = horas + " horas y " + minutosRestantes + " minutos";
        return tiempoFormateado;
    }

    String cacularTiempo(String horaActual, String duracion) {
        String formato = "AM";
        String horaPartida;
        String[] ActualHora = horaActual.split(":");
        String[] tiempo = duracion.split(" ");
        //hora actual
        int hora = Integer.valueOf(ActualHora[0]);
        int min = Integer.valueOf(ActualHora[1]);
            horaPartida = hora + ":" + min + ":00";
 
        //hora de duracion
        int hora1 = Integer.valueOf(tiempo[0]);
        int min1 = Integer.valueOf(tiempo[3]);
        int sumarMinutos = min + min1;
        int horaFinal = sumarMinutos / 60;
        hora = hora + horaFinal + hora1;
        int minFinal = sumarMinutos % 60;
        if (hora==24 ) {
            hora =0;
        }

        System.out.println("Calulos hora " + horaFinal + "   residuo es " + minFinal);
        return "Hora de partida  " + horaPartida + " \n\n Hora de llegada " + hora + ":" + minFinal + ":00 ";
    }

}
