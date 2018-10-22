package edu.isen.fhgd.fft.kmeans;

public class Point {

    protected int length = 2;

    private float[] coords = new float[length];

    //Cluster
    Cluster cluster ;

    public Point(float easy, float difficult) {
        this.coords[0] = easy;
        this.coords[1] = difficult;
    }

    public Point() {
    }

    public float getCoords(int i) {
        return coords[i];
    }

    public void setCoords(int i, float x) {
        this.coords[i] = x;
    }

    public double distance(Point o)
    {
        double dist, sum = 0;
        for(int i=0; i<length; i++)
            sum += Math.pow(coords[i] - o.coords[i], 2);
        dist = Math.sqrt(sum);
        return  dist;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public void setCluster(Cluster cluster) {
        this.cluster = cluster;
    }
}
