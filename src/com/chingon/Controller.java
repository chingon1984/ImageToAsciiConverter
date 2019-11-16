package com.chingon;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;


import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    Pane pane;

    @FXML
    TextArea textArea;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
            ImageProcessor imageProcessor = new ImageProcessor(Main.parameters.get(0));
    }
}

