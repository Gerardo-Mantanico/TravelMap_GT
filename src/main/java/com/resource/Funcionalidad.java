
package com.resource;
import com.modelo.DetallesRuta;
import com.modelo.TipoDeRuta;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javafx.scene.control.ComboBox;

/**
 *
 * @author Gerardo Tax
 */
public class Funcionalidad {
         ArrayList<TipoDeRuta> listaVehiculo = new ArrayList<>();
         ArrayList<TipoDeRuta> listaCaminar = new ArrayList<>();
         
         public Funcionalidad(){
                  Vehiculo();
                  Caminar();
         }
    
         //meto para poder cargar los diferentes tipos de rutas para vehiculo
        void Vehiculo() {
                listaVehiculo.add(new TipoDeRuta("La mejor ruta en base a la gasolina",1));
                listaVehiculo.add(new TipoDeRuta("La mejor ruta en base a la distancia",2));
                listaVehiculo.add(new TipoDeRuta("La mejor ruta en base a la distacia y gasolina", 3));
                listaVehiculo.add(new TipoDeRuta("La ruta mas rapida en base a la distancia, tiempo, y probabilidad de trafico", 4));
        
    }

         //metodo para poder cargar las diferetnes rutas para el modo caminar
         void Caminar() {
                listaCaminar.add(new TipoDeRuta("La mejor ruta en base al desgaste fisico ", 9));
                listaCaminar.add(new TipoDeRuta("La mejor ruta en base a la distancia", 10));
                listaCaminar.add(new TipoDeRuta("La mejor ruta en base al desgaste fisico y la distancia", 11));
    }
        
         //este metdo lo que realiza es poder implementar  las diferentes listas al combobox
         public void cargar(ComboBox<TipoDeRuta> combobox, String tipo) {
                  if (tipo.equals("Vehiculo")) 
                           combobox.getItems().addAll(listaVehiculo);
                  else 
                          combobox.getItems().addAll(listaCaminar);    
    }
         
    public List<DetallesRuta> buscarRuta(List<DetallesRuta> listaRuta ,int tipo ){
            switch (tipo){
                case 1:
                    Collections.sort(listaRuta, Comparator.comparingInt(DetallesRuta::getConsumoGas));
                    break;
                case 2:
                    Collections.sort(listaRuta, Comparator.comparingInt(DetallesRuta::getDistancia));
                    break;
                case 3:
                    Collections.sort(listaRuta, Comparator.comparingInt(DetallesRuta::getConsumoGas).thenComparingInt(DetallesRuta::getDistancia));
                    break;
                case 4:
                    Collections.sort(listaRuta,Comparator.comparingInt(DetallesRuta::getDistancia).thenComparingInt(DetallesRuta::getTiempoVehiculo));
                    break;
                case 9:
                    Collections.sort(listaRuta,Comparator.comparingInt(DetallesRuta::getDesgastePersona));
                    break;
                case 10:
                    Collections.sort(listaRuta,Comparator.comparingInt(DetallesRuta::getDistancia));
                    break;
                case 11:
                    Collections.sort(listaRuta,Comparator.comparingInt(DetallesRuta::getDesgastePersona).thenComparingInt(DetallesRuta::getDistancia));
                    break;
                case 12:
            }

        for (DetallesRuta in : listaRuta) {
            System.out.println(in);
        }
        return listaRuta;
    }

}
