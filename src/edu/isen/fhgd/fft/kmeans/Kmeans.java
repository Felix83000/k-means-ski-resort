package edu.isen.fhgd.fft.kmeans;

public class Kmeans {

    private int taille_previsions = 300 ;

    //----------------- DATA --------------------------------------

    private String Nomstation[] = new String[taille_previsions];
    private float altitude[] = new float[taille_previsions] ;
    private float facile[] = new float[taille_previsions] ;
    private float intermedaire[] = new float[taille_previsions] ;
    private float difficile[] = new float[taille_previsions] ;
    private float expert[] = new float[taille_previsions] ;



    // ------------- GET -----------------------------------------


    public String getNomstation(int i ) {
        return Nomstation[i];
    }

    public float getAltitude(int i) {
        return  altitude[i];
    }

    public float getFacile(int i ) {
        return facile[i];
    }

    public float getIntermedaire(int i ) {
        return intermedaire[i];
    }

    public float getDifficile(int i) {
        return difficile[i];
    }

    public float getExpert(int i) {
        return expert[i];
    }

    //------------- SET -----------------------------------------


    public void setNomstation(String nomstation , int i) {
        this.Nomstation[i] = nomstation;
    }

    public void setAltitude( float altitude , int i) {
        this.altitude[i] = altitude;
    }

    public void setFacile(float facile , int i ) {
        this.facile[i] = facile;
    }

    public void setIntermedaire(float intermedaire , int i) {
        this.intermedaire[i] = intermedaire;
    }

    public void setDifficile(float difficile  ,int i) {
        this.difficile[i] = difficile;
    }

    public void setExpert(float expert ,  int i  ) {
        this.expert[i] = expert;
    }

}
