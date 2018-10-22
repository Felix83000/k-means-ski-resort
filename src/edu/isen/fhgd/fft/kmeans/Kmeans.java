package edu.isen.fhgd.fft.kmeans;

import edu.isen.fhgd.fft.parseur.parseur_ski;
import edu.isen.fhgd.fft.stationsSki.StationSki;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.parsers.ParserConfigurationException;
import java.util.ArrayList;
import java.util.Observable;

public class Kmeans extends Observable{

    static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(Kmeans.class) ;

    // Creation du model Kmeans

    private StationSki model = new StationSki() ;

    // liste de des points
    private static ArrayList<Point> pointsDuCluster  = new ArrayList<>() ;

    // liste des cluster
    private static ArrayList<Cluster> Cluster = new ArrayList<>() ;

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
    }

    public void initialisation (int nombreCluster)
    {

        LOGGER.debug("lenght parse  : " + String.valueOf(model.getLength()));

        for (int i=0; i<model.getLength();i++){
            pointsDuCluster.add(new Point(model.getFacile(i),model.getDifficile(i)));
        }
        createClusters(nombreCluster);
    }

    /**create clusters well separated*/
    private static void createClusters(int _nbClusters) {
        Point centroid = pointsDuCluster.get((int)(pointsDuCluster.size()*Math.random()));
        Cluster firstCluster = new Cluster(centroid);
        Cluster.add(firstCluster);
        for(int c = 1; c < _nbClusters; c++) {
            Point farData = new Point();
            double maxDist = Double.NEGATIVE_INFINITY;
            for(Point data:pointsDuCluster) {
                double minDist = Double.POSITIVE_INFINITY;
                for(Cluster cluster:Cluster) {
                    double dist = data.distance(cluster.getBarycentre());
                    if(minDist>dist) minDist = dist;
                }
                if (maxDist<minDist) {
                    maxDist  = minDist;
                    farData = data;
                }
            }

            Cluster cluster = new Cluster(farData.clonee());
            Cluster.add(cluster);
        }

        System.out.println("Initially, centroids are:");
        Cluster.forEach(c->System.out.println(c.getBarycentre().toString()));
    }

    public void kMeanCluster()
    {
        pointsDuCluster.forEach(data -> searchCluster(data).addPoint(data));
        boolean moving = true;
        while(moving)
        {
            moving = false;
            Cluster.forEach(c -> c.CalculBaycentre());
            for(Point data:pointsDuCluster)
            {
                Cluster bestCluster = searchCluster(data);
                if(data.getCluster() != bestCluster){
                    moving = true;
                    data.getCluster().removePoint(data);
                    bestCluster.addPoint(data);
                }
            }
        }
        this.notifyObservers();
    }

    private static Cluster searchCluster(Point data) {
        Cluster bestCluster = null;
        double minimum = Double.POSITIVE_INFINITY;
        double distance=0;
        for(Cluster cluster:Cluster)
        {
            distance = data.distance(cluster.getBarycentre());
            if(distance < minimum){ minimum = distance; bestCluster = cluster; }
        }
        return bestCluster;
    }

    @Override
    public void notifyObservers() {
        LOGGER.debug("Notification de fin de calcul");
        setChanged(); // Set the changed flag to true, otherwise observers won't be notified.
        super.notifyObservers();
    }


    public  ArrayList<Point> getPointsDuCluster() {
        return pointsDuCluster;
    }

    public static void setPointsDuCluster(ArrayList<Point> pointsDuCluster) {
        Kmeans.pointsDuCluster = pointsDuCluster;
    }

    public  ArrayList<edu.isen.fhgd.fft.kmeans.Cluster> getCluster() {
        return Cluster;
    }

    public static void setCluster(ArrayList<edu.isen.fhgd.fft.kmeans.Cluster> cluster) {
        Cluster = cluster;
    }
}
