/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control_ventas.travelmapgt1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Gerardo Tax
 */
public class Generador {

 /*       
        public static void main(String args[]){
                    String[] departamentos = {
            "AltaVerapaz",
            "BajaVerapaz",
            "Chimaltenango",
            "Chiquimula",
            "ElProgreso",
            "Escuintla",
            "Guatemala",
        };
        
            for(int i = 0; i < departamentos.length; i++) {
            for(int j = 0; j < departamentos.length; j++) {
                if(i != j) { // Evitar generar pares de departamentos iguales
                    System.out.println(departamentos[i] + "|" + departamentos[j] + "|" + rand() + "|" + rand() + "|" + rand() + "|" + rand() + "|" + rand());
                }
            }
        }
    }
        
        public static int rand(){
         Random random = new Random();
         int  numeroAleatorio = random.nextInt(5001);
         return numeroAleatorio; //
        }
    */



    public static void main(String[] args) {
        List<String[]> data = cargarDatos("C:/Users/HP/Desktop/primerArchivo.txt");
        String inicio = "SanMarcos";
        String destino = "Petén";

        System.out.println("Posibles rutas de " + inicio + " a " + destino + ":");
        encontrarRutas(data, inicio, destino, new ArrayList<>());
    }

    public static List<String[]> cargarDatos(String archivo) {
        List<String[]> datos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                datos.add(linea.split("\\|"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return datos;
    }

    public static void encontrarRutas(List<String[]> grafo, String inicio, String destino, List<String> visitados) {
        visitados.add(inicio);
        if (inicio.equals(destino)) {
           // System.out.println(String.join(" -> ", visitados.));
        } else {
            for (String[] conexion : grafo) {
                if (conexion[0].equals(inicio) && !visitados.contains(conexion[1])) {
                    encontrarRutas(grafo, conexion[1], destino, new ArrayList<>(visitados));
                }
            }
        }
    }


}
