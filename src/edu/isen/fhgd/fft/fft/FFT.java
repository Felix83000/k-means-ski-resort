package edu.isen.fhgd.fft.fft;

import edu.isen.fhgd.fft.complexe.Complexe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Observable;

/**
 * Class contenant les différentes fft
 */
public class FFT extends Observable {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(FFT.class);

    /**
     * Taille du tableau (correspond à une puissance de 2)
     */
    private int tailleP2;

    /**
     * Signal complexe d'entrée
     */
    private Complexe[] signal;

    /**
     * Sortie de l'algorithme
     */
    private Complexe[] sortie;

    /**
     * Signal d'entrée réel
     */
    private float[] signalR;

    /**
     * Constructeur d'objet à partir d'un signal complexe
     *
     * @param tailleP2 Taille de la série
     * @param signal   Série de données
     */
    public FFT(int tailleP2, Complexe[] signal) throws IllegalArgumentException {
        if (estPuissance2(tailleP2)) {
            this.tailleP2 = tailleP2;
            this.signal = signal;
            LOGGER.debug("Création d'un objet FFT de taille : " + tailleP2);
        } else {
            LOGGER.error("La taille " + tailleP2 + " doit être une puissance de 2 !");
            throw new IllegalArgumentException("La taille " + tailleP2 + " doit être une puissance de 2 !");
        }
    }

    /**
     * Constructeur d'objet à partir d'un signal réel
     *
     * @param tailleP2 Taille de la série
     * @param tabReel  Série de données
     */
    public FFT(int tailleP2, float[] tabReel) throws IllegalArgumentException {
        if (estPuissance2(tailleP2)) {
            this.tailleP2 = tailleP2;
            this.signalR = tabReel;
            LOGGER.debug("Création d'un objet FFT de taille : " + tailleP2);
        } else {
            LOGGER.error("La taille " + tailleP2 + " doit être une puissance de 2 !");
            throw new IllegalArgumentException("La taille " + tailleP2 + " doit être une puissance de 2 !");
        }
    }

    /**
     * Méthode calculant une FFT à partir de valeurs réelles
     *
     * @return
     */
    public Complexe[] FFTRelle() {
        this.sortie = new Complexe[this.tailleP2];
        if (tailleP2 == 1) {
            this.sortie[0] = new Complexe(this.signalR[0], 0);
        } else {
            float[] tabPairs = new float[tailleP2 / 2];
            float[] tabImpairs = new float[tailleP2 / 2];

            for (int i = 0; i < this.tailleP2 / 2; i++) {
                tabPairs[i] = this.signalR[2 * i];
                tabImpairs[i] = this.signalR[2 * i + 1];
            }

            FFT paire = new FFT(this.tailleP2 / 2, tabPairs);
            FFT impaire = new FFT(this.tailleP2 / 2, tabImpairs);
            LOGGER.debug("Calcul transofrmées de Fourrier pour tableau paires et tableau impaires");
            // On calcule la FFT des deux listes
            paire.FFTRelle();
            impaire.FFTRelle();

            LOGGER.debug("Remplissage tableau final");
            for (int k = 0; k <= (tailleP2 / 2) - 1; k++) {
                Complexe M = new Complexe((float) (-2 * Math.PI * k) / tailleP2);
                this.sortie[k] = (paire.sortie[k]).addition(impaire.sortie[k].multiplication(M));
                this.sortie[k + (tailleP2 / 2)] = (paire.sortie[k]).soustraction(impaire.sortie[k].multiplication(M));
            }
        }
        this.notifyObservers();
        return this.sortie;
    }

    /**
     * Méthode calculant une FFT à partir de valeurs complexes
     *
     * @return
     */
    public Complexe[] FFTComplexe() {
        this.sortie = new Complexe[this.tailleP2];
        if (tailleP2 == 1) {
            this.sortie[0] = this.signal[0];
        } else {
            Complexe[] tabPairs = new Complexe[tailleP2 / 2];
            Complexe[] tabImpairs = new Complexe[tailleP2 / 2];

            for (int i = 0; i < this.tailleP2 / 2; i++) {
                tabPairs[i] = this.signal[2 * i];
                tabImpairs[i] = this.signal[2 * i + 1];
            }

            FFT paire = new FFT(this.tailleP2 / 2, tabPairs);
            FFT impaire = new FFT(this.tailleP2 / 2, tabImpairs);
            LOGGER.debug("Calcul transofrmées de Fourrier pour tableau paires et tableau impaires");
            // On calcule la FFT des deux listes
            paire.FFTComplexe();
            impaire.FFTComplexe();

            LOGGER.debug("Remplissage tableau final");
            for (int k = 0; k <= (tailleP2 / 2) - 1; k++) {
                Complexe M = new Complexe((float) (-2 * Math.PI * k) / tailleP2);
                this.sortie[k] = (paire.getSortie(k)).addition(impaire.getSortie(k).multiplication(M));
                this.sortie[k + (tailleP2 / 2)] = (paire.getSortie(k)).soustraction(impaire.getSortie(k).multiplication(M));
            }
        }
        this.notifyObservers();
        return this.sortie;
    }

    /**
     * Méthode calculant la FFT inverse
     *
     * @return
     */
    public Complexe[] inverseFFT() {
        LOGGER.debug("Remplissage du tableau de signal avec les conjugués");
        for (int i = 0; i < signal.length; i++) {
            signal[i] = signal[i].conjugue();
        }
        LOGGER.debug("Calcul de la FFT");
        this.FFTComplexe();
        LOGGER.debug("Remplissage du tableau de sortie avec le conjugué");
        for (int i = 0; i < this.sortie.length; i++) {
            this.sortie[i] = this.getSortie(i).conjugue();
            this.sortie[i] = this.getSortie(i).multiplication(new Complexe(1 / (float) this.sortie.length, 0));
        }
        this.notifyObservers();
        return this.sortie;
    }

    /**
     * Méthode vérifiant si une valeur correspond bien à une puissance de 2
     *
     * @param value Valeur à tester
     * @return
     */
    private boolean estPuissance2(int value) {
        //bitCount compte le nombre de bits à l'état haut
        //Pour la représentation d'une puissance de 2 en binaire, seul 1 bit est à l'état haut
        LOGGER.debug("Vérification que le nombre " + value + " est une puissance de 2");
        return Long.bitCount(value) == 1;
    }

    /**
     * Renvoie la taille du tableau
     *
     * @return tailleP2
     */
    public int getTailleP2() {
        LOGGER.debug("Récupération de la taille du tableau");
        return tailleP2;
    }

    /**
     * Renvoie le tableau de sortie
     *
     * @return
     */
    public Complexe[] getSortie() {
        LOGGER.debug("Récupération du tableau complet de sortie");
        return sortie;
    }

    /**
     * Renvoie une valeur du tableau de sortie
     *
     * @param lecture Case à récupérer
     * @return
     * @throws IllegalArgumentException
     */
    public Complexe getSortie(int lecture) throws IllegalArgumentException {
        if (lecture >= 0 && lecture < sortie.length) {
            LOGGER.debug("Récupération de la case " + lecture + " du tableau de sortie");
            return sortie[lecture];
        }
        throw new IllegalArgumentException("Lecture à la mauvaise case");
    }

    /**
     * Renvoie une valeur du tableau d'entrée
     *
     * @param lecture Case à récupérer
     * @return
     * @throws IllegalArgumentException
     */
    public Complexe getSignalComplexe(int lecture) throws IllegalArgumentException {
        if (lecture >= 0 && lecture < sortie.length) {
            LOGGER.debug("Récupération de la case " + lecture + " du tableau de sortie");
            return signal[lecture];
        }
        throw new IllegalArgumentException("Lecture à la mauvaise case");
    }

    /**
     * Renvoie une valeur du tableau d'entrée
     *
     * @param lecture Case à récupérer
     * @return
     * @throws IllegalArgumentException
     */
    public float getSignalReel(int lecture) throws IllegalArgumentException {
        if (lecture >= 0 && lecture < sortie.length) {
            LOGGER.debug("Récupération de la case " + lecture + " du tableau de sortie");
            return signalR[lecture];
        }
        throw new IllegalArgumentException("Lecture à la mauvaise case");
    }

    /**
     * Permet de donner un nouveau signal complexe à executer
     *
     * @param signal   Nouveau signal à ajouter
     * @param tailleP2 Nouvelle taille du signal
     * @throws IllegalArgumentException
     */
    public void setNewSignal(Complexe[] signal, int tailleP2) throws IllegalArgumentException {
        if (estPuissance2(tailleP2)) {
            this.tailleP2 = tailleP2;
            this.signal = signal;
            LOGGER.debug("Création d'un objet FFT de taille : " + tailleP2);
        } else {
            LOGGER.error("La taille " + tailleP2 + " doit être une puissance de 2 !");
            throw new IllegalArgumentException("La taille " + tailleP2 + " doit être une puissance de 2 !");
        }
    }

    /**
     * Permet de donner un nouveau signal réel à executer
     *
     * @param signalR  Nouveau signal réel à ajouter
     * @param tailleP2 Nouvelle taille du signal
     * @throws IllegalArgumentException
     */
    public void setNewSignalR(float[] signalR, int tailleP2) throws IllegalArgumentException {
        if (estPuissance2(tailleP2)) {
            this.tailleP2 = tailleP2;
            this.signalR = signalR;
            LOGGER.debug("Création d'un objet FFT de taille : " + tailleP2);
        } else {
            LOGGER.error("La taille " + tailleP2 + " doit être une puissance de 2 !");
            throw new IllegalArgumentException("La taille " + tailleP2 + " doit être une puissance de 2 !");
        }
    }

    @Override
    public void notifyObservers() {
        LOGGER.debug("Notification de fin de calcul");
        setChanged(); // Set the changed flag to true, otherwise observers won't be notified.
        super.notifyObservers();
    }
}
