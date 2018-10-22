package edu.isen.fhgd.fft.kmeans;

import java.util.ArrayList;

public class Cluster {

    // liste des point appartenant au cluster
    ArrayList<point> pointDuCluster ;

    // data qui représente le barycentre du cluster
    Point barycentre ;

    // nombre de cluster
    private static int nombreCluster ;

    // numero du cluster
    private int numCluster ;

    // distance minimal entre le centre du cluster et un des point du cluster
    private float minDistance ;

    // distance maximal entre le centre du cluster et un des point du cluster
    private float maxDistance ;

    // distance maximal entre le centre du cluster et un des point du cluster
    private float moyDistance ;

    // initialisation du cluster
    public Cluster()
    {
        pointDuCluster = new ArrayList<Point>() ;
        barycentre = new Point();

        numCluster = nombreCluster ++ ;
    }

    // initialisation du cluster avec un barycentre

    public Cluster ( Point barycentre )
    {
        this() ;
        this.barycentre = barycentre ;

    }


    // ajout d'un point au cluster
    public void addPoint (Point point)
    {
        pointDuCluster.add(point);
        point.setCluster(this) ;
    }




}
