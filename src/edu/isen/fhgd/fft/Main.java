package edu.isen.fhgd.fft;

import edu.isen.fhgd.fft.controller.KmeansController;
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

        // Création d'un tableau vide pour initialisé l'objet FFT
        float sinus[] = new float[0];
        Kmeans kmeans = new Kmeans();
        // Création du contrôleur
        KmeansController controller = new KmeansController(kmeans);
        // Création de la fenêtre
        Fenetre fen = new Fenetre(controller);
        // Ajout de l'observer
        kmeans.addObserver(fen);
        controller.setKmeans(kmeans);
        controller.setFen(fen);


        /*Kmeans kmaens= new Kmeans();
        kmaens.parse();

         kmaens.initialisation(3);

         kmaens.kMeanCluster();

        ArrayList<Cluster> Cluster = kmaens.getCluster() ;

        Cluster.forEach(cluster -> cluster.getStatistique());

        double error = 0 ;
        int nb = 0 ;
         ArrayList<Point> Cluster1;
         ArrayList<Point> Cluster2;
         ArrayList<Point> Cluster3;

        for(Cluster cluster:Cluster)
        {
            if (cluster.getPointsDuCluster().size() > 0 ){
                System.out.println(cluster);
                nb++ ;
                error += cluster.getMoyDistance();
                System.out.println("Cluster : "+ nb);
                for (Point point: cluster.getPointsDuCluster())
                {
                    System.out.println("Point : "+point.getCoords(0)+" , "+point.getCoords(1));
                }
                Cluster1 = cluster.getPointsDuCluster();
            }

            System.out.println("barycentre : x=" + cluster.getBarycentre().getCoords(0) +" y=" +cluster.getBarycentre().getCoords(1));

            System.out.println("Moyenne distance :" + cluster.getMoyDistance());

        }

        error = error/(2*250);
        System.out.println("gobal error= " + String.format(Locale.ENGLISH, "%.2f", (error*100)) + " % ");
        */
    }



}
