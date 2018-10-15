package edu.isen.fhgd.fft.complexe;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ComplexeTest {

    @Test
    public void addition() throws Exception {
        Complexe a = new Complexe(1, 1);
        Complexe b = new Complexe(10, 5);
        Complexe c = new Complexe(11, 6);
        assertEquals(c, a.addition(b));
    }

    @Test
    public void soustraction() throws Exception {
        Complexe a = new Complexe(1, 1);
        Complexe b = new Complexe(10, 5);
        Complexe c = new Complexe(-9, -4);
        assertEquals(c, a.soustraction(b));
    }

    @Test
    public void conjugue() throws Exception {
        Complexe a = new Complexe(5, 1);
        Complexe b = new Complexe(5, -1);
        Complexe c = new Complexe(5, 0);
        assertEquals(b, a.conjugue());
        assertEquals(c, c.conjugue());
    }

    @Test
    public void multiplication() throws Exception {
        Complexe a = new Complexe(3, 4);
        Complexe b = new Complexe(2, 6);
        Complexe r = a.multiplication(b);
        assertEquals(-18, r.Re(), Math.pow(10, -5));
        assertEquals(26, r.Im(), Math.pow(10, -5));
    }

    @Test
    public void re() throws Exception {
        Complexe a = new Complexe(1, 1);
        Complexe b = new Complexe(10, 5);
        assertEquals(1, a.Re(), Math.pow(10, -5));
        assertEquals(10, b.Re(), Math.pow(10, -5));
    }

    @Test
    public void im() throws Exception {
        Complexe a = new Complexe(1, 1);
        Complexe b = new Complexe(10, 5);
        assertEquals(1, a.Im(), Math.pow(10, -5));
        assertEquals(5, b.Im(), Math.pow(10, -5));
    }

}