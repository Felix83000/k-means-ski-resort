package edu.isen.fhgd.fft.controller;

import edu.isen.fhgd.fft.kmeans.Kmeans;
import edu.isen.fhgd.fft.vue.Fenetre;

public class KmeansController {
    /**
     * Modèle de données Kmeans
     */
    private Kmeans model = new Kmeans();
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
     * @param kmeans
     */
    public void setKmeans( Kmeans kmeans) {
        this.model = kmeans;
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
                this.model.parse();
                this.model.initialisation(3);
                this.model.kMeanCluster();
                break;
            default:

                break;
        }
    }
}
