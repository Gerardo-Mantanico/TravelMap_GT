package com.resource;
import com.control_ventas.travelmapgt1.HomeController;
import com.grafica.GeneradorDotFile;
import com.modelo.Arco;
import com.modelo.Grafo;
import com.modelo.NodoGrafo;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Gerardo Tax
 */
public class GenerarRuta {
    private List<List<String>> rutasEncontradas = new ArrayList<>();
    HomeController homeController;
    List<NodoGrafo> visitados;
   GeneradorDotFile dot= new GeneradorDotFile();
   ImagenIcon img = new ImagenIcon();
    
    public  GenerarRuta(HomeController homeController){
        this.homeController=homeController;
    }
    
    public void crearRuta(NodoGrafo origen) {
        dot.UbicacionActual(origen, "src/main/resources/img/ubicacionActual.dot");
        img.img(homeController.getImgActual(), "/img/ubicacionActual.png");
        Arco temp = origen.getLista().getPrimero();
        while (temp != null) {
            homeController.getPosiblesRutas().getItems().add(temp.getDestino());
            temp = temp.getSiguiente();
        }
    }
    
    public void encontrarRutas(Grafo grafo, String inicio, String destino, List<String> visitados) {
        visitados.add(inicio);
        if (inicio.equals(destino)) {
            //System.out.println(String.join(" -> ", visitados));
              rutasEncontradas.add(new ArrayList<>(visitados));
        } else {
            NodoGrafo temp = grafo.getPrimero();
            while (temp!=null) {
                if(temp.getNombre().equals(inicio)){
                    Arco arco=temp.getLista().getPrimero();
                    while(arco!=null){
                         if (!visitados.contains(arco.getDestino())) {
                            encontrarRutas(grafo, arco.getDestino(), destino,  new ArrayList<String>(visitados));
                        }
                         arco=arco.getSiguiente();
                    }
                }
                temp=temp.getSiguiente();
            }

        }
    }
 
    public void imprimir(){
       for (List<String> ruta : rutasEncontradas) {
    for (String nodo : ruta) {
        System.out.print(nodo + " -> ");
    }
    System.out.println(); // Para imprimir un salto de línea al final de cada ruta
}
    }
}
