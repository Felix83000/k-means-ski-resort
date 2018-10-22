package edu.isen.fhgd.fft.kmeans;

import java.util.ArrayList;
import java.util.Locale;

public class Cluster {

    // liste des point appartenant au cluster
    private ArrayList<Point> pointDuCluster;

    // data qui représente le barycentre du cluster
    private Point barycentre;

    // nombre de cluster
    private static int nombreCluster;

    // numero du cluster
    private int numCluster;

    // distance minimal entre le centre du cluster et un des point du cluster
    private float minDistance;

    // distance maximal entre le centre du cluster et un des point du cluster
    private float maxDistance;

    // distance maximal entre le centre du cluster et un des point du cluster
    private float moyDistance;

    // initialisation du cluster
    public Cluster() {
        pointDuCluster = new ArrayList<Point>();
        barycentre = new Point();


        numCluster = nombreCluster++;
    }

    // initialisation du cluster avec un barycentre

    public Cluster(Point barycentre) {
        this();
        this.barycentre = barycentre;

    }


    // ajout d'un point au cluster
    public void addPoint(Point point) {
        pointDuCluster.add(point);
        point.setCluster(this);
    }

    // détache un point du cluster
    public void removePoint(Point point) {
        pointDuCluster.remove(point);
        point.setCluster(null);
    }

    // recalcule le centre du cluster
    public void CalculBaycentre() {
        int nombreElement = pointDuCluster.size();
        if (nombreElement > 0) {
            int dimmension = pointDuCluster.get(0).length;
            int[] tabIndice = {0};

            for (int i = 0; i < dimmension; i++) {
                tabIndice[0] = i;

                double somme = pointDuCluster.stream().parallel().mapToDouble(d -> d.getCoords(tabIndice[0])).sum();
                float moyenne = (float) somme / (float) nombreElement;
                if (barycentre.getCoords(i) != moyenne) {
                    barycentre.setCoords(i, (float) somme / (float) nombreElement);
                }

            }
        }
    }



    public void getStatistique()
    {
        float sommeDistance = 0 ;
        minDistance = -1 ;
        maxDistance = -1 ;
        for (Point point:pointDuCluster)
        {
            float distance = (float) point.distance(barycentre);
            if(distance<minDistance)
            {
                minDistance = distance ;
            }
            if(distance>maxDistance) {
                maxDistance = distance ;
            }
            sommeDistance = sommeDistance + distance ;
        }
        if(pointDuCluster.size()>0)
        {
            moyDistance = sommeDistance/pointDuCluster.size();
        }
    }

    // ----------------- get ------------------------

    public Point getBarycentre() {
        return barycentre;
    }

    public int getNumCluster() {
        return numCluster;
    }

    // ---------------- set ------------------
    public void setBarycentre(Point barycentre) {
        this.barycentre = barycentre;
    }

    public ArrayList<Point> getPointDuCluster() {
        return pointDuCluster;
    }

    public float getMoyDistance() {
        return moyDistance;
    }
}
