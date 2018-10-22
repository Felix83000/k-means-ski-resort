package edu.isen.fhgd.fft.stationsSki;

public class StationSki  {

    private int taille_previsions = 300 ;

    //----------------- DATA --------------------------------------

    private String Nomstation[] = new String[taille_previsions];
    private float altitude[] = new float[taille_previsions] ;
    private float facile[] = new float[taille_previsions] ;
    private float intermedaire[] = new float[taille_previsions] ;
    private float difficile[] = new float[taille_previsions] ;

    private int length ;

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

    public int getLength() {
        return length;
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

    public void setLength(int length) {
        this.length = length;
    }
}
