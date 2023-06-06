package com.example.sae201;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class appController {
    @FXML
    private ComboBox typeComboBox;
    @FXML
    private Button btnMenuDeroulant;
    @FXML
    private HBox menuDeroulant;
    @FXML
    private Slider slider;
    @FXML
    private TextField firstDateField;
    @FXML
    private TextField secondDateField;
    @FXML
    private TextField paysField;
    @FXML
    private TextField departementField;
    @FXML
    private TextField latitudeField;
    @FXML
    private TextField longitudeField;
    @FXML
    private TextField rayonField;

    boolean menuVisibility = false;

    public appController() {
        this.typeComboBox = new ComboBox<>();
        this.btnMenuDeroulant = new Button();
        this.departementField = new TextField();
        this.firstDateField = new TextField();
        this.secondDateField = new TextField();
        this.longitudeField = new TextField();
        this.latitudeField = new TextField();
        this.paysField = new TextField();
        this.rayonField = new TextField();
    }

    public void initialize() {
        createBindings();
    }

    private void createBindings() {

    }
}

