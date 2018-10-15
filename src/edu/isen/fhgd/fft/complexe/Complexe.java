package edu.isen.fhgd.fft.complexe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;

/**
 * Création d'un type de données : Complexe
 */
public class Complexe {

    private static final Logger LOGGER = LoggerFactory.getLogger(Complexe.class);

    /**
     * Nombre réel du complexe
     */
    private final float reel;

    /**
     * Nombre imaginaire du complexe
     */
    private final float imaginaire;

    /**
     * Constructeur par défaut
     *
     * @param reel
     * @param imaginaire
     */
    public Complexe(float reel, float imaginaire) {
        this.reel = reel;
        this.imaginaire = imaginaire;
        LOGGER.debug("Création du nombre complexe : " + this.toString());
    }

    /**
     * Constructeur par argument du nombre complexe
     *
     * @param argument
     */
    public Complexe(float argument) {
        this.reel = (float) Math.cos(argument);
        this.imaginaire = (float) Math.sin(argument);
        LOGGER.debug("Création du nombre complexe à partir de l'argument : " + this.toString());
    }

    /**
     * Renvoie le module du nombre complexe
     *
     * @return
     */
    public float module() {
        LOGGER.debug("Calcul du module");
        return (float) Math.hypot(this.reel, this.imaginaire);
    }

    /**
     * Renvoie un complexe comprenant le résultat de l'addition de ce complexe et de b
     *
     * @param b
     * @return
     */
    public Complexe addition(Complexe b) {
        LOGGER.debug("Addition de " + this.toString() + " et " + b.toString());
        float reel = this.reel + b.reel;
        float imaginaire = this.imaginaire + b.imaginaire;
        return new Complexe(reel, imaginaire);
    }

    /**
     * Renvoie un complexe comprenant le résultat de la soustraction de ce complexe et de b
     *
     * @param b
     * @return
     */
    public Complexe soustraction(Complexe b) {
        LOGGER.debug("Soustraction de " + this.toString() + " et " + b.toString());
        float reel = this.reel - b.reel;
        float imaginaire = this.imaginaire - b.imaginaire;
        return new Complexe(reel, imaginaire);
    }

    /**
     * Renvoie le complexe conjugué de ce nombre
     *
     * @return
     */
    public Complexe conjugue() {
        LOGGER.debug("Calcul du conjugué");
        return new Complexe(reel, -imaginaire);
    }

    /**
     * Renvoie un complexe comprenant le résultat de la multiplication de ce nombre et de b
     *
     * @param b
     * @return
     */
    public Complexe multiplication(Complexe b) {
        LOGGER.debug("Multiplication de " + this.toString() + " et " + b.toString());
        float reel = this.reel * b.reel - this.imaginaire * b.imaginaire;
        float imaginaire = this.reel * b.imaginaire + this.imaginaire * b.reel;
        return new Complexe(reel, imaginaire);
    }

    /**
     * Accesseur sur les réels
     *
     * @return
     */
    public float Re() {
        return this.reel;
    }

    /**
     * Accesseur sur les imaginaires
     *
     * @return
     */
    public float Im() {
        return this.imaginaire;
    }

    @Override
    public boolean equals(Object o) {
        if ((this.reel == ((Complexe) (o)).reel) && (this.imaginaire == ((Complexe) (o)).imaginaire)) return true;
        else return false;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(4);
        return "Nombre complexe => Re : " + df.format(reel) + " | Im : " + df.format(imaginaire);
    }
}
