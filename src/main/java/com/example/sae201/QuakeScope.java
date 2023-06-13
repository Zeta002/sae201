package com.example.sae201;

import com.gluonhq.maps.MapPoint;
import com.gluonhq.maps.MapView;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Classe principale de l'application. Charge le fichier FXML, initialise la scène et l'affiche dans le stage.
 */
public class QuakeScope extends Application {

    /**
     * Méthode principale qui charge le fichier FXML, initialise le stage et y place la scène à l'intérieur.
     *
     * @param stage : la fenêtre de l'application
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(QuakeScope.class.getResource("sismograph.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
        stage.setFullScreen(true);
        stage.setTitle("SismoGraph");
        stage.setScene(scene);
        stage.show();

        System.setProperty("javafx.platform", "desktop");
        System.setProperty("http.agent", "Gluon Mobile/1.0.3");
    }

    public static void main(String[] args) {
        launch();
    }
}