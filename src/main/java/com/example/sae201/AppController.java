package com.example.sae201;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Contrôle l'application dans son ensemble. Récupère les événements de la vue et les données triées du modèle
 * pour les transmettre à la vue qui les affichera.
 */
public class AppController {
    @FXML
    private BorderPane root;
    @FXML
    private ToggleButton btnMenuDeroulant;
    @FXML
    private VBox menuDeroulant;
    @FXML
    private ComboBox departement;

    private Stage stage;
    private Scene scene;


    private boolean menuVisibility = true;

    private ArrayList<String> departementList = new ArrayList<>();

    /**
     * Gestionnaire d'événement du contrôleur, appelle la fonction associée à l'événement demandé.
     *
     * @param event : événement capturé
     */
    @FXML
    private void handleEventHandler(ActionEvent event) throws IOException {
        // Récupère la source de l'événement
        Object sourceOfEvent = event.getSource();

        // Récupère l'ID de l'élément
        if (sourceOfEvent instanceof Button bouton) {
            String id = bouton.getId();

            // Effectue l'action en fonction de l'ID du bouton
            switch (id) {

                case "btnDashboard" -> switchToDashboard(event);
                case "btnQuakescope" -> switchToSismograph(event);
            }
        }

        if (sourceOfEvent instanceof ToggleButton toggleButton) {
            String id = toggleButton.getId();

            switch (id) {

                case "btnMenuDeroulant" -> showMenu();
            }
        }
    }


    /**
     * Initialise ce qui doit être lancé au chargement de la classe.
     */
    public void initialize() {
        showMenu();
    }

/*
  ______ _    _ _   _  _____ _______ _____ ____  _   _
 |  ____| |  | | \ | |/ ____|__   __|_   _/ __ \| \ | |
 | |__  | |  | |  \| | |       | |    | || |  | |  \| |
 |  __| | |  | | . ` | |       | |    | || |  | | . ` |
 | |    | |__| | |\  | |____   | |   _| || |__| | |\  |
 |_|     \____/|_| \_|\_____|  |_|  |_____\____/|_| \_|
                                                        */
    /**
     * Affiche le menu déroulant qui sert de barre de navigation.
     */
    private void showMenu() {
        menuVisibility = !menuVisibility;

        // Rend le menu "inexistant" et invisible
        menuDeroulant.setManaged(menuVisibility);
        menuDeroulant.setVisible(menuVisibility);

        menuDeroulant.setPrefWidth(0.0);
    }

    /**
     * Change la page FXML en la page du tableau de bord.
     */
    private void switchToDashboard(ActionEvent event) throws IOException {
        System.out.println("Test");
        root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Recharge la page actuelle.
     */
    private void switchToSismograph(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("sismograph.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
