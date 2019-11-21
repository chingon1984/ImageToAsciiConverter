package com.chingon;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.paint.Color;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class ImageProcessor {

    private String imgPath, txtPath;

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


//        try {
//            String totalString = convertTextfileToString(path);
//            int i = 0;
//            for(char c : logDiffChars(totalString)) {
//            i++;
//            System.out.print(c + ",");
//        }
//        System.out.println(i);
//    } catch(IOException e) {
//        System.out.println("No vaild path");
//        Platform.exit();
//        System.exit(0);
//    }
        this.imgPath = "file://" + path;
        this.txtPath = buildTextFilePath(path);


//        String url = "file:///home/chingon/Repos/Java/ImageToAsciiConverter/Samples/cat.jpg";
        String res = convertGreyscaleListToString(getColorList(imgPath, 4,7));

        try {
            saveToFile(res);
        } catch (IOException ioe) {
            System.out.println("Wrong path");
        }

    }

    private String buildTextFilePath(String imgPath) {
        String pathWithoutExtension = imgPath.substring(0, imgPath.lastIndexOf("."));
        String res =  pathWithoutExtension + ".txt";
        System.out.println(res);
        return  res;
    }


    private String convertTextfileToString(String path) throws IOException {

        File file = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder total = new StringBuilder();
            String line;

            int i = 0;
            while ((line = br.readLine()) != null) {
                System.out.println(line.length());
                total.append(line);
                i++;
            }
            System.out.println(i);
            return total.toString();
        }


    }

    private List<Character> logDiffChars(String input) {
        List<Character> container = new ArrayList<>();
        for (char charInput : input.toCharArray()) {
            if (!container.contains(charInput)) {
                container.add(charInput);
            }
        }

        return container;
    }


    private List<List<List<Double>>> getColorList(String url, int sizeX, int sizeY) {
        Image image = new Image(url);
        PixelReader pixelReader = image.getPixelReader();
        List<List<List<Double>>> resArr = new ArrayList<>();
        List<List<Double>> columnList = new ArrayList<>();

        for (int yVal = 0; yVal < image.getHeight(); yVal++) {
            for (int xVal = 0; xVal < image.getWidth(); xVal++) {
                Color color = pixelReader.getColor(xVal,yVal);
                double greyScaleValue = getGreyScale(color);
                int div = xVal/sizeX;

                if(div == columnList.size())
                    columnList.add(new ArrayList<>());

                columnList.get(div).add(greyScaleValue);
            }
            if((yVal + 1)/ sizeY > resArr.size()) {
                resArr.add(columnList);
                columnList = new ArrayList<>();
            }
        }
        return resArr;
    }


    private void saveToFile(String str) throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(txtPath)))) {
            bufferedWriter.write(str);
        }
        System.out.println("finished");
    }

//    private <E> List<List<E>> groupIt(List<List<E>> list, int groupBy) {
//
//        List<List<E>> result = new ArrayList<>();
//
//        for (List<E> subList : list) {
//            for (int i=0; i<subList.size(); i++) {
//                int div = i/groupBy;
//                if(div >= result.size()) {
//                    result.add(new ArrayList<>());
//               }
//
//                result.get(div).add(subList.get(i));
//            }
//        }
//        return result;
//    }



    private double getGreyScale(Color color) {
        double red = color.getRed() * 0.299;
        double green = color.getGreen() * 0.11;
        double blue = color.getBlue() * 0.59;

        return red + green + blue;
    }


    private String convertGreyscaleListToString(List<List<List<Double>>> grayList) {
        StringBuilder stringBuilder = new StringBuilder();


        for (List<List<Double>> columnList: grayList) {
            for(List<Double> block : columnList) {
                double sum = 0;

                for(double grayValue : block) {
                    sum += grayValue;
                }
                double average = sum / block.size();
                char c = convertGrayscaleToChar(average);

                stringBuilder.append(c);
//                stringBuilder.append(' ');
            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }



    private char convertGrayscaleToChar(double grayscale) {
    final char c;

       if(grayscale > 0.9) {
           c = ' ';
       }else if(grayscale > 0.8) {
           c = '\'';
       }else if(grayscale > 0.7) {
           c = '=';
       }else if(grayscale > 0.6) {
           c = ':';
       }else if(grayscale > 0.5) {
           c = '*';
       }else if(grayscale > 0.4) {
           c = '|';
       }else if(grayscale > 0.3) {
           c = 'o';
       }else if(grayscale > 0.2) {
           c = '$';
       }else if(grayscale > 0.1) {
           c = 'M';
       }else  {
           c = '@';
       }

       return c;
    }
}
