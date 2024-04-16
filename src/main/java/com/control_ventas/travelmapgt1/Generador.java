/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.control_ventas.travelmapgt1;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Gerardo Tax
 */
public class Generador {

        
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
    
}
