package com.example.sae201;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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

    private boolean menuVisibility = true;

    private ArrayList<String> departementList = new ArrayList<>();

    /**
     * Gestionnaire d'événement du contrôleur, appelle la fonction associée à l'événement demandé.
     *
     * @param event : événement capturé
     */
    @FXML
    private void handleEventHandler(ActionEvent event) {
        // Récupère la source de l'événement
        Object sourceOfEvent = event.getSource();

        // Récupère l'ID de l'élément
        if (sourceOfEvent instanceof Button bouton) {
            String id = bouton.getId();

            // Effectue l'action en fonction de l'ID du bouton
            switch (id) {

                case "btnAcceuil" -> switchToAccueil();
                case "btnSismograph" -> switchToDashboard();
                case "btnQuakescope" -> reloadCurrentPage();

                // Ajoute d'autres cas pour d'autres éléments si nécessaire
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
        createBindings();
    }

    /**
     * Crée les bindings nécessaires pour le bon fonctionnement de l'application.
     */
    private void createBindings() {
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

        // Assombrir l'arrière plan quand le menu est visible
        if (menuVisibility) {
            backgroundDarkening(true);
        } else {
            backgroundDarkening(false);
            menuDeroulant.setPrefWidth(0.0);
        }
    }

    /**
     * Si le booléen est vrai, le background est assombifié
     * @param b : boolean
     */
    private void backgroundDarkening(boolean b) {
        root.setStyle(b ? "-fx-background-color: rgba(0, 0, 0, 0.3);" : "-fx-background-color: null;");
    }

    /**
     * Change la page FXML en la page d'accueil.
     */
    private void switchToAccueil() {
        // @TODO
    }

    /**
     * Change la page FXML en la page du tableau de bord.
     */
    private void switchToDashboard() {
        // @TODO
    }

    /**
     * Recharge la page actuelle.
     */
    private void reloadCurrentPage() {
        // @TODO
    }
}
