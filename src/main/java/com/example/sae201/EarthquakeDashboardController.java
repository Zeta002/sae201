package com.example.sae201;

import com.example.sae201.DonneesSeisme;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class EarthquakeDashboardController {
    @FXML
    private Slider filtreMagnitudeSlider;
    @FXML
    private PieChart graphique;
    @FXML
    private TableView<DonneesSeisme> tableau; // renamed from "table" to "tableau"
    @FXML
    private BarChart<String, Number> graphiqueRegions;

    @FXML
    private TableColumn<DonneesSeisme, String> colonneRegion;
    @FXML
    private TableColumn<DonneesSeisme, Double> colonneLatitude;
    @FXML
    private TableColumn<DonneesSeisme, Double> colonneLongitude;
    @FXML
    private TableColumn<DonneesSeisme, Double> colonneMagnitude;
    @FXML
    private VBox menuDeroulant;
    @FXML
    private BorderPane root;

    private Stage stage;
    private Scene scene;

    private boolean menuVisibility = true;

    @FXML
    private void initialize() {
        EarthquakeDashboardViewModel viewModel = new EarthquakeDashboardViewModel();
        viewModel.chargerDonneesDepuisCSV(EarthquakeDashboardViewModel.CHEMIN_FICHIER_CSV);

        colonneRegion.setCellValueFactory(data -> data.getValue().regionProperty());
        colonneLatitude.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getLatitude()).asObject());
        colonneLongitude.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getLongitude()).asObject());
        colonneMagnitude.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getMagnitude()).asObject());


        tableau.setItems(viewModel.filtrerTableau(filtreMagnitudeSlider));
        graphiqueRegions.setData(viewModel.getDonneesGraphiqueRegions());
        graphique.setData(viewModel.getDonneesGraphique());

    }

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