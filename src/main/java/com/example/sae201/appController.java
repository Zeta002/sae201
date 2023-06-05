package com.example.sae201;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

public class appController {

    @FXML
    private ComboBox typeBox;
    @FXML
    private Button btnMenuDeroulant;

    public appController() {
        this.typeBox = new ComboBox<>();
        this.btnMenuDeroulant = new Button();
    }
}

