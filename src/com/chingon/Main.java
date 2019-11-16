package com.chingon;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.List;


public class Main extends Application {
     static List<String> parameters;


//    @Override
//    public void init() throws Exception {
//        super.init();
//
//        System.out.println("init()");
//        Parameters parameters = getParameters();
//
//        Map<String, String> namedParameters = parameters.getNamed();
//        List<String> rawArguments = parameters.getRaw();
//        List<String> unnamedParameters = parameters.getUnnamed();
//
//        System.out.println("\nnamedParameters -");
//        for (Map.Entry<String,String> entry : namedParameters.entrySet()) {
//            System.out.println(entry.getKey() + " : " + entry.getValue());
//        }
//
//        System.out.println("\nrawArguments -");
//        for(String raw : rawArguments) {
//            System.out.println(raw);
//        }
//
//        System.out.println("\nunnamedParameters -");
//        for(String unnamed : unnamedParameters) {
//            System.out.println(unnamed);
//        }
//    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        parameters = getParameters().getRaw();

        if(parameters.isEmpty()) {
            System.out.println("\n\n***No image URL provided! - Please provide valid, non empty URL of image to convert***");
            Platform.exit();
            System.exit(0);
        }

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        Scene scene = new Scene(root, 800,600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
