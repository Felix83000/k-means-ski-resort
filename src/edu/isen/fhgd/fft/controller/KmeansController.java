package edu.isen.fhgd.fft.controller;

import edu.isen.fhgd.fft.kmeans.Kmeans;
import edu.isen.fhgd.fft.stationsSki.StationSki;
import edu.isen.fhgd.fft.vue.Fenetre;

public class KmeansController {
    /**
     * Modèle de données Kmeans
     */
    private Kmeans model = null;
    /**
     * Fenêtre d'affichage
     */
    private Fenetre fen;


    /**
     * Default constructor
     *
     * @param model
     */
    public KmeansController(Kmeans model) {
        this.model = model;
    }

    /**
     * Setter de la Kmeans
     *
     * @param parsing
     */
    public void setKmeans( Kmeans parsing) {
        this.model = parsing;
    }

    /**
     * Récupération de la fenêtre
     *
     * @return
     */
    public Fenetre getFen() {
        return fen;
    }

    /**
     * Setter de fenêtre
     *
     * @param fen
     */
    public void setFen(Fenetre fen) {
        this.fen = fen;
    }

    /**
     * Notification d'une action par la vue
     *
     * @param choix
     */
    public void notifyAction(int choix) {
        switch (choix) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            default:

                break;
        }
    }
}
