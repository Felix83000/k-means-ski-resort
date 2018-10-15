package edu.isen.fhgd.fft.vue;

import edu.isen.fhgd.fft.fft.FFT;
import edu.isen.fhgd.fft.controller.FFTController;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.Observable;
import java.util.Observer;

/**
 * Fenêtre de l'application
 */
public class Fenetre extends JFrame implements Observer {
    /**
     * Logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(Fenetre.class);

    /**
     * Contrôleur de l'application
     */
    private FFTController controller;
    /**
     * Choix d'action à effectuer
     */
    private int choixActuel;
    /**
     * Affichage des valeurs
     */
    private JFreeChart graphique;
    private ChartPanel plotPanel;
    private static final Shape circle = new Ellipse2D.Double(-3, -3, 6, 6);

    /**
     * Default constructor
     *
     * @param controller Contrôleur de l'application
     */
    public Fenetre(FFTController controller) {
        this.setTitle("Projet Java-Maths");
        this.setSize(800, 800);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.controller = controller;
        this.choixActuel = 0;

        graphique = ChartFactory.createXYLineChart(
                "Spectre", "N", "Magnitude",
                null, PlotOrientation.VERTICAL, true, true, false);
        plotPanel = new ChartPanel(graphique) {

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(300, 300);
            }
        };
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        Container pane = this.getContentPane();

        plotPanel.setMouseWheelEnabled(true);
        pane.add(plotPanel, BorderLayout.NORTH);
        JButton button = new JButton("Sinus Réel");
        button.addActionListener(actionEvent -> {
            this.choixActuel = 1;
            this.controller.notifyAction(choixActuel);
        });
        JButton button2 = new JButton("Exponentielle Complexe");
        button2.addActionListener(actionEvent -> {
            this.choixActuel = 2;
            this.controller.notifyAction(choixActuel);
        });
        JButton button3 = new JButton("Cosinus réel");
        button3.addActionListener(actionEvent -> {
            this.choixActuel = 3;
            this.controller.notifyAction(choixActuel);
        });
        JButton button4 = new JButton("Exponentielle réelle");
        button4.addActionListener(actionEvent -> {
            this.choixActuel = 4;
            this.controller.notifyAction(choixActuel);
        });
        JButton button5 = new JButton("Constante complexe");
        button5.addActionListener(actionEvent -> {
            this.choixActuel = 5;
            this.controller.notifyAction(choixActuel);
        });
        JButton button6 = new JButton("Inverse FFT (CONSTANTE)");
        button6.addActionListener(actionEvent -> {
            this.choixActuel = 6;
            this.controller.notifyAction(choixActuel);
        });

        GridLayout grid = new GridLayout(2, 3);
        JPanel panelSouth = new JPanel(grid);
        panelSouth.add(button);
        panelSouth.add(button2);
        panelSouth.add(button3);
        panelSouth.add(button4);
        panelSouth.add(button5);
        panelSouth.add(button6);
        pane.add(panelSouth, BorderLayout.SOUTH);
        this.setVisible(true);
    }

    /**
     * Mise à jour de la vue
     *
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        LOGGER.debug("Mise à jour de la vue");
        if (o instanceof FFT) {
            LOGGER.debug("Affichage des valeurs");
            FFT fft = (FFT) o;
            XYSeries serieDePoints = new XYSeries("FFT");
            // Remplissage série de points
            for (int i = 0; i < fft.getTailleP2(); i++) {
                serieDePoints.add(i, fft.getSortie(i).module());
            }
            XYDataset xyDataset = new XYSeriesCollection(serieDePoints);

            graphique = ChartFactory.createXYLineChart(
                    "FFT", "N", "Magnitude",
                    xyDataset, PlotOrientation.VERTICAL, true, false, false);
            XYPlot plot = graphique.getXYPlot();
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(false, true);
            renderer.setSeriesShape(0, circle);
            plot.setRenderer(renderer);
            this.plotPanel.setChart(graphique);
        }
    }
}
