package edu.isen.fhgd.fft.fft;

import edu.isen.fhgd.fft.complexe.Complexe;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class FFTTest {

    @Test
    public void FFTRelleSinus() throws Exception {
        float sinus[] = new float[8];
        for (int i = 0; i < sinus.length; i++) {
            sinus[i] = (float) Math.sin(2 * Math.PI * i / sinus.length);
        }
        FFT sinusTransform = new FFT(8, sinus);
        sinusTransform.FFTRelle();
        float resultatsComplexes[][] = {{0, 0}, {0, -4}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 4}};
        for (int i = 0; i < sinus.length; i++) {
            assertEquals(resultatsComplexes[i][0], sinusTransform.getSortie(i).Re(), Math.pow(10, -3));
            assertEquals(resultatsComplexes[i][1], sinusTransform.getSortie(i).Im(), Math.pow(10, -3));
        }
    }

    @Test
    public void FFTReelleCosinus() {
        float cosinus[] = new float[8];
        for (int i = 0; i < cosinus.length; i++) {
            cosinus[i] = (float) Math.cos(2 * Math.PI * i / cosinus.length);
        }
        FFT cosinusTransform = new FFT(8, cosinus);
        cosinusTransform.FFTRelle();
        float resultatsComplexes[][] = {{0, 0}, {4, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {4, 0}};
        for (int i = 0; i < cosinus.length; i++) {
            assertEquals(resultatsComplexes[i][0], cosinusTransform.getSortie(i).Re(), Math.pow(10, -3));
            assertEquals(resultatsComplexes[i][1], cosinusTransform.getSortie(i).Im(), Math.pow(10, -3));
        }
    }

    @Test
    public void FFTComplexeConstante() {
        Complexe cst[] = new Complexe[8];
        for (int indice = 0; indice < cst.length; indice++) {
            cst[indice] = new Complexe(0, 1);
        }
        FFT cstTransform = new FFT(8, cst);
        cstTransform.FFTComplexe();
        float resultatsComplexes[][] = {{0, 8}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};
        for (int i = 0; i < cst.length; i++) {
            assertEquals(resultatsComplexes[i][0], cstTransform.getSortie(i).Re(), Math.pow(10, -3));
            assertEquals(resultatsComplexes[i][1], cstTransform.getSortie(i).Im(), Math.pow(10, -3));
        }
    }

    @Test
    public void FFTComplexeExpo() throws Exception {
        Complexe[] signal = new Complexe[8];
        for (int i = 0; i < 8; i++) {
            signal[i] = new Complexe((float) (2 * Math.PI * i / signal.length));
        }
        FFT tranformee = new FFT(8, signal);
        tranformee.FFTComplexe();
        float resultatsComplexes[][] = {{0, 0}, {8, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};
        for (int i = 0; i < 8; i++) {
            assertEquals(resultatsComplexes[i][0], tranformee.getSortie(i).Re(), Math.pow(10, -3));
            assertEquals(resultatsComplexes[i][1], tranformee.getSortie(i).Im(), Math.pow(10, -3));
        }
    }

    @Test
    public void FFTComplexeDirac() throws Exception {
        Complexe[] valeurDirac = new Complexe[8];
        valeurDirac[0] = new Complexe(1, 0);
        for (int i = 1; i < 8; i++) {
            valeurDirac[i] = new Complexe(0, 0);
        }
        FFT dirac = new FFT(8, valeurDirac);
        dirac.FFTComplexe();
        for (int i = 0; i < valeurDirac.length; i++) {
            assertEquals(1, dirac.getSortie(i).Re(), Math.pow(10, -3));
            assertEquals(0, dirac.getSortie(i).Im(), Math.pow(10, -3));
        }
    }

    @Test
    public void inverseFFT() throws Exception {
        Complexe[] entree = new Complexe[8];
        for (int i = 0; i < 8; i++) {
            entree[i] = new Complexe(0, 0);
        }
        entree[1] = new Complexe(8, 0);
        FFT ifft = new FFT(8, entree);
        ifft.inverseFFT();
        float resultatsComplexes[][] = {{1, 0}, {0.7f, 0.7f}, {0, 1}, {-0.7f, 0.7f}, {-1, 0}, {-0.7f, -0.7f}, {0, -1}, {0.7f, -0.7f}};
        for (int i = 0; i < 8; i++) {
            assertEquals(resultatsComplexes[i][0], ifft.getSortie(i).Re(), 0.1);
            assertEquals(resultatsComplexes[i][1], ifft.getSortie(i).Im(), 0.1);
        }
    }

    @Test
    public void FFT() throws Exception {
        try {
            float test[] = {0, 0};
            FFT objet = new FFT(51, test);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException aExp) {
            assert (aExp.getMessage().contains("La taille 51 doit être une puissance de 2 !"));
        }

        try {
            float test[] = {0, 0};
            FFT objet = new FFT(64, test);
        } catch (IllegalArgumentException aExp) {
            assert (aExp.getMessage().contains("La taille n'est pas une puissance de 2"));
            fail("No error expected");
        }
    }
}