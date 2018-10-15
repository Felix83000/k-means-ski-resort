package edu.isen.fhgd.fft;

import edu.isen.fhgd.fft.controller.FFTController;
import edu.isen.fhgd.fft.fft.FFT;
import edu.isen.fhgd.fft.vue.Fenetre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
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
    }
}
