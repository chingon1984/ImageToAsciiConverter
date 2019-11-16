package com.chingon;

import javafx.application.Platform;
import javafx.scene.image.Image;

public class ImageProcessor{


    public ImageProcessor(String path) {
            try {
                Image image = new Image(path,false);
                System.out.println("Height: " + image.getHeight());
                System.out.println("Width: " + image.getWidth());
            } catch(NullPointerException ne) {
                System.out.println("Nullpointer");
            } catch(IllegalArgumentException iae) {
                System.out.println("Please provide valid image URL");
                Platform.exit();
                System.exit(0);
            }
    }


}
