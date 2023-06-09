package com.example.sae201;

import com.example.sae201.DonneesSeisme;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
}