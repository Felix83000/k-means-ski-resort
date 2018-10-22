package edu.isen.fhgd.fft.kmeans;

import java.util.ArrayList;

public class Cluster {

    // liste des point appartenant au cluster
    private ArrayList<Point> pointsDuCluster;

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
        pointsDuCluster = new ArrayList<Point>();
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
        pointsDuCluster.add(point);
        point.setCluster(this);
    }

    // détache un point du cluster
    public void removePoint(Point point) {
        pointsDuCluster.remove(point);
        point.setCluster(null);
    }

    // recalcule le centre du cluster
    public void CalculBaycentre() {
        int nombreElement = pointsDuCluster.size();
        if (nombreElement > 0) {
            int dimmension = pointsDuCluster.get(0).length;
            int[] tabIndice = {0};

            for (int i = 0; i < dimmension; i++) {
                tabIndice[0] = i;

                double somme = pointsDuCluster.stream().parallel().mapToDouble(d -> d.getCoords(tabIndice[0])).sum();
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
        for (Point point: pointsDuCluster)
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
        if(pointsDuCluster.size()>0)
        {
            moyDistance = sommeDistance/ pointsDuCluster.size();
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

    public ArrayList<Point> getPointsDuCluster() {
        return pointsDuCluster;
    }

    public float getMoyDistance() {
        return moyDistance;
    }
}
