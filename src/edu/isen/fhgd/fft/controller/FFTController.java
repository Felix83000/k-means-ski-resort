package edu.isen.fhgd.fft.controller;


import edu.isen.fhgd.fft.complexe.Complexe;
import edu.isen.fhgd.fft.fft.FFT;
import edu.isen.fhgd.fft.vue.Fenetre;

/**
 * Contrôleur de la FFT
 */
public class FFTController {
    /**
     * Modèle de données FFT
     */
    private FFT model = null;
    /**
     * Fenêtre d'affichage
     */
    private Fenetre fen;

    /**
     * Default constructor
     *
     * @param model
     */
    public FFTController(FFT model) {
        this.model = model;
    }

    /**
     * Setter de la FFT
     *
     * @param fft
     */
    public void setFft(FFT fft) {
        this.model = fft;
    }

    /**
     * Récupération de la fenêtre
     *
     * @return
     */
    public Fenetre getFen() {
        return fen;
    }

    /**
     * Setter de fenêtre
     *
     * @param fen
     */
    public void setFen(Fenetre fen) {
        this.fen = fen;
    }

    /**
     * Notification d'une action par la vue
     *
     * @param choix
     */
    public void notifyAction(int choix) {
        switch (choix) {
            case 1:
                float sinus[] = generateSinusR();
                this.model.setNewSignalR(sinus, 8);
                this.model.FFTRelle();
                break;
            case 2:
                Complexe[] signal = generateExpo();
                this.model.setNewSignal(signal, 8);
                this.model.FFTComplexe();
                break;
            case 3:
                float cosinusR[] = generateCosinusR();
                this.model.setNewSignalR(cosinusR, 8);
                this.model.FFTRelle();
                break;
            case 4:
                float expoR[] = generateExpoR();
                this.model.setNewSignalR(expoR, 8);
                this.model.FFTRelle();
                break;
            case 5:
                Complexe[] cst = generateCst();
                this.model.setNewSignal(cst, 8);
                this.model.FFTComplexe();
                break;
            case 6:
                Complexe[] entree = new Complexe[8];
                for (int i = 0; i < 8; i++) {
                    entree[i] = new Complexe(0, 0);
                }
                entree[1] = new Complexe(8, 0);
                this.model.setNewSignal(entree, 8);
                this.model.inverseFFT();
                break;
            default:
                break;
        }
    }

    /**
     * Générer un tableau complexe par exponentielle
     *
     * @return
     */
    private Complexe[] generateExpo() {
        Complexe[] signal = new Complexe[8];
        for (int i = 0; i < 8; i++) {
            signal[i] = new Complexe((float) (2 * Math.PI * i / signal.length));
        }
        return signal;
    }

    /**
     * Générer un tableau réel par sinus
     *
     * @return
     */
    private float[] generateSinusR() {
        float sinus[] = new float[8];
        for (int i = 0; i < sinus.length; i++) {
            sinus[i] = (float) Math.sin(2 * Math.PI * i / sinus.length);
        }
        return sinus;
    }

    /**
     * Générer un tableau réel par cosinus
     *
     * @return
     */
    private float[] generateCosinusR() {
        float cos[] = new float[8];
        for (int i = 0; i < cos.length; i++) {
            cos[i] = (float) Math.cos(2 * Math.PI * i / cos.length);
        }
        return cos;
    }

    /**
     * Générer un tableau réel par exponentielle
     *
     * @return
     */
    private float[] generateExpoR() {
        float expo[] = new float[8];
        for (int i = 0; i < expo.length; i++) {
            expo[i] = (float) Math.exp(2 * Math.PI * i / expo.length);
        }
        return expo;
    }

    /**
     * Générer un tableau complexe par constante
     *
     * @return
     */
    private Complexe[] generateCst() {
        Complexe cst[] = new Complexe[8];
        for (int indice = 0; indice < cst.length; indice++) {
            cst[indice] = new Complexe(0, 1);
        }
        return cst;
    }

}
