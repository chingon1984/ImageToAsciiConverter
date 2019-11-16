package com.chingon;

import javafx.application.Platform;
import javafx.scene.image.Image;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class ImageProcessor{


    ImageProcessor(String path) {
//            try {
//                Image image = new Image(path,false);
//                System.out.println("Height: " + image.getHeight());
//                System.out.println("Width: " + image.getWidth());
//            } catch(NullPointerException ne) {
//                System.out.println("Nullpointer");
//            } catch(IllegalArgumentException iae) {
//                System.out.println("Please provide valid image URL");
//                Platform.exit();
//                System.exit(0);
//            }

        try {
            String totalString = convertTextfileToString(path);
            int i = 0;
            for(char c : logDiffChars(totalString)) {
            i++;
            System.out.print(c + ",");
        }
        System.out.println(i);
    } catch(IOException e) {
        System.out.println("No vaild path");
        Platform.exit();
        System.exit(0);
    }


    }



    private String convertTextfileToString(String path) throws IOException {

        File file = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder total = new StringBuilder();
            String line;

            while((line = br.readLine()) != null) {
                total.append(line);
            }
            return total.toString();
        }


    }

    private List<Character> logDiffChars(String input) {
        List<Character> container = new ArrayList<>();
        for(char charInput : input.toCharArray()) {
           if(!container.contains(charInput)) {
               container.add(charInput);
           }
        }

        return container;
    }


}
