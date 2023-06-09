package com.example.sae201;

import com.example.sae201.DonneesSeisme;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Slider;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EarthquakeDashboardViewModel {

    public static final String CHEMIN_FICHIER_CSV = "SisFrance_seismes_20230604151458.csv";

    private final ObservableList<DonneesSeisme> donnees = FXCollections.observableArrayList();

    public void chargerDonneesDepuisCSV(String nomFichier) {
        // Récupérer l'URL du fichier dans les ressources
        URL urlFichier = getClass().getResource(nomFichier);
        if (urlFichier == null) {
            throw new IllegalArgumentException("Le fichier n'a pas été trouvé : " + nomFichier);
        }

        try (Scanner scanner = new Scanner(new File(urlFichier.getFile()))) {
            // Ignorer la première ligne
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }

            while (scanner.hasNextLine()) {
                String ligne = scanner.nextLine();
                String[] colonnes = ligne.split(",");

                if (colonnes.length >= 11) {
                    String region = colonnes[4].trim();
                    String latitudeString = colonnes[8].trim();
                    String longitudeString = colonnes[9].trim();
                    String magnitudeString = colonnes[10].trim();

                    if (!region.isEmpty() && !latitudeString.isEmpty() && !longitudeString.isEmpty() && !magnitudeString.isEmpty()) {
                        try {
                            double latitude = Double.parseDouble(latitudeString);
                            double longitude = Double.parseDouble(longitudeString);
                            double magnitude = Double.parseDouble(magnitudeString);
                            DonneesSeisme donneesSeisme = new DonneesSeisme(region, latitude, longitude, magnitude);
                            donnees.add(donneesSeisme);
                        } catch (NumberFormatException e) {
                            System.err.println("Valeur numérique invalide dans la ligne CSV : " + ligne);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public ObservableList<PieChart.Data> getDonneesGraphique() {
        ObservableList<PieChart.Data> donneesGraphique = FXCollections.observableArrayList();

        int countLow = 0;
        int countMedium = 0;
        int countHigh = 0;

        for (DonneesSeisme donneesSeisme : donnees) {
            double magnitude = donneesSeisme.getMagnitude();
            if (magnitude < 4.0) {
                countLow++;
            } else if (magnitude < 7.0) {
                countMedium++;
            } else {
                countHigh++;
            }
        }

        donneesGraphique.add(new PieChart.Data("Magnitude Faible", countLow));
        donneesGraphique.add(new PieChart.Data("Magnitude Moyenne", countMedium));
        donneesGraphique.add(new PieChart.Data("Magnitude Élevée", countHigh));

        return donneesGraphique;
    }

    public FilteredList<DonneesSeisme> filtrerTableau(Slider filtreMagnitudeSlider) {

        FilteredList<DonneesSeisme> donneesFiltrees = new FilteredList<>(donnees);


        filtreMagnitudeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            donneesFiltrees.setPredicate(donneesSeisme -> donneesSeisme.getMagnitude() >= newValue.doubleValue());
        });

        return donneesFiltrees;
    }

    public ObservableList<XYChart.Series<String, Number>> getDonneesGraphiqueRegions() {
        ObservableList<XYChart.Series<String, Number>> donneesGraphique = FXCollections.observableArrayList();

        Map<String, Integer> compteRegions = new HashMap<>();
        for (DonneesSeisme donneesSeisme : donnees) {
            String region = donneesSeisme.getRegion();
            compteRegions.put(region, compteRegions.getOrDefault(region, 0) + 1);
        }

        XYChart.Series<String, Number> serie = new XYChart.Series<>();
        serie.setName("Proportion des régions avec des séismes");

        int totalSeismes = donnees.size();
        for (Map.Entry<String, Integer> entry : compteRegions.entrySet()) {
            String region = entry.getKey();
            int count = entry.getValue();
            double proportion = (double) count / totalSeismes;
            serie.getData().add(new XYChart.Data<>(region, proportion));
        }

        donneesGraphique.add(serie);

        return donneesGraphique;
    }
}