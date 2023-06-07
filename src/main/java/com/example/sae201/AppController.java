package com.example.sae201;

import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;

/**
 * Contrôle l'application dans son ensemble. Récupère les événements de la vue et les données triées du modèle
 * pour les transmettre à la vue qui les affichera.
 */
public class AppController {
    @FXML
    private ToggleButton btnMenuDeroulant;
    @FXML
    private HBox menuDeroulant;

    private final SimpleBooleanProperty menuVisible = new SimpleBooleanProperty(false);

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

                case "btnMenuDeroulant" -> showMenu();
                case "btnPageAccueil" -> switchToAccueil();
                case "btnDashboard" -> switchToDashboard();
                case "btnSismograph" -> reloadCurrentPage();

                // Ajoute d'autres cas pour d'autres éléments si nécessaire
            }
        }
    }

    /**
     * Initialise ce qui doit être lancé au chargement de la classe.
     */
    public void initialize() {
        createBindings();
    }

    /**
     * Crée les bindings nécessaires pour le bon fonctionnement de l'application.
     */
    private void createBindings() {
        // Binding de la visibilité du menu déroulant
        BooleanBinding menuVisibleBinding = btnMenuDeroulant.selectedProperty().not();
        menuDeroulant.visibleProperty().bind(menuVisibleBinding);
    }

    /**
     * Affiche le menu déroulant qui sert de barre de navigation.
     */
    private void showMenu() {
        menuDeroulant.setVisible(true);
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
