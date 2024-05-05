/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.resource;

import java.util.Timer;
import java.util.TimerTask;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Gerardo Tax
 */
public class ImagenIcon {
    public void img( ImageView  img, String ruta){
                    Image image = new Image(getClass().getResourceAsStream(ruta));
                    img.setImage(image);
    }
}
