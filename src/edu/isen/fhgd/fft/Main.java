package edu.isen.fhgd.fft;

import edu.isen.fhgd.fft.controller.FFTController;
import edu.isen.fhgd.fft.fft.FFT;
import edu.isen.fhgd.fft.kmeans.Cluster;
import edu.isen.fhgd.fft.kmeans.Kmeans;
import edu.isen.fhgd.fft.kmeans.Point;
import edu.isen.fhgd.fft.vue.Fenetre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Locale;


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

        Kmeans kmaens= new Kmeans();
        kmaens.parse();

         kmaens.initialisation(3);

         kmaens.kMeanCluster();

        ArrayList<Cluster> Cluster = kmaens.getCluster() ;

        Cluster.forEach(cluster -> cluster.getStatistique());

        double error = 0 ;
        int nb = 0 ;

        for(Cluster cluster:Cluster)
        {
            if (cluster.getPointDuCluster().size() >0 ){

                System.out.println(cluster);
                nb++ ;
                error += cluster.getMoyDistance();
            }
        }
        error = error/(2*250);
        System.out.println("gobal error= " + String.format(Locale.ENGLISH, "%.2f", (error*100)) + " % ");
    }
}
