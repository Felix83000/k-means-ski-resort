package edu.isen.fhgd.fft;

import edu.isen.fhgd.fft.controller.FFTController;
import edu.isen.fhgd.fft.controller.KmeansController;
import edu.isen.fhgd.fft.fft.FFT;
import edu.isen.fhgd.fft.kmeans.Kmeans;
import edu.isen.fhgd.fft.stationsSki.StationSki;
import edu.isen.fhgd.fft.vue.Fenetre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.ParserConfigurationException;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        /*
        // Création d'un tableau vide pour initialisé l'objet FFT
        float sinus[] = new float[0];
        FFT fft = new FFT(2, sinus);
        // Création du contrôleur
        FFTController controller = new FFTController(fft);
        // Création de la fenêtre
        Fenetre fen = new Fenetre(controller);
        // Ajout de l'observer
        fft.addObserver(fen);
        controller.setFft(fft);
        controller.setFen(fen);
        */

        // Creation du model Kmeans

         Kmeans model = new Kmeans() ;

        //Création du controller  Kmeans

        KmeansController controller = new KmeansController(model) ;

        // Parsing des données pour inserer le model de Kmeans
        try {
            LOGGER.info("Parsing !!");
            controller.setKmeans(new StationSki().getModel_parsing());

        } catch (ParserConfigurationException e) {
            LOGGER.error("erreur d'initialisation du model pour Kmeans");
            e.printStackTrace();
        }

    }
}
