package edu.isen.fhgd.fft.kmeans;

import edu.isen.fhgd.fft.controller.KmeansController;
import edu.isen.fhgd.fft.parseur.parseur_ski;
import edu.isen.fhgd.fft.stationsSki.StationSki;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.ParserConfigurationException;
import java.util.Observable;

public class Kmeans extends Observable{

    static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Kmeans.class) ;

    // Creation du model Kmeans

    private StationSki model = new StationSki() ;

    public Kmeans() {

    }

    public StationSki getModel() {
        return model;
    }

    public void parse()
    {
        // Parsing des données pour inserer le model de Kmeans
        try {
            LOGGER.info("Parsing !!");
            model =(new parseur_ski().getModel_parsing());

        } catch (ParserConfigurationException e) {
            LOGGER.error("erreur d'initialisation du model pour Kmeans");
            e.printStackTrace();
        }
        this.notifyObservers();
    }

    public void Kmeans_algorithm()
    {

    }

    @Override
    public void notifyObservers() {
        LOGGER.debug("Notification de fin de calcul");
        setChanged(); // Set the changed flag to true, otherwise observers won't be notified.
        super.notifyObservers();
    }
}
