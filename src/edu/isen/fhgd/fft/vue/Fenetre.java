package edu.isen.fhgd.fft.vue;

import edu.isen.fhgd.fft.controller.KmeansController;
import edu.isen.fhgd.fft.kmeans.Cluster;
import edu.isen.fhgd.fft.kmeans.Kmeans;
import edu.isen.fhgd.fft.kmeans.Point;
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
import java.util.ArrayList;
import java.util.Locale;
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
    private KmeansController controller;
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
    public Fenetre(KmeansController controller) {
        this.setTitle("Projet RNA - K-means");
        this.setSize(900, 900);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.controller = controller;
        this.choixActuel = 0;

        graphique = ChartFactory.createXYLineChart(
                "Stations de ski", "km de pistes débutantes","km de pistes expérimentés",
                null, PlotOrientation.VERTICAL, true, true, false);
        plotPanel = new ChartPanel(graphique) {

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(700, 700);
            }
        };
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        Container pane = this.getContentPane();

        plotPanel.setMouseWheelEnabled(true);
        pane.add(plotPanel, BorderLayout.NORTH);
        JButton button = new JButton("K-means Algorithm");
        button.addActionListener(actionEvent -> {
            this.choixActuel = 1;
            this.controller.notifyAction(choixActuel);
        });

        GridLayout grid = new GridLayout(1, 1);
        JPanel panelSouth = new JPanel(grid);
        panelSouth.add(button);
        pane.add(panelSouth, BorderLayout.SOUTH);
        this.setVisible(true);

        Kmeans kmaens= new Kmeans();
        kmaens.parse();
        LOGGER.info(kmaens.getModel().getNomstation(0)+" : easy : "+String.valueOf(kmaens.getModel().getFacile(0)));
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
        if (o instanceof Kmeans) {
            LOGGER.debug("Affichage des valeurs");
            Kmeans kmeans = (Kmeans) o;

            ArrayList<Cluster> Cluster = kmeans.getCluster() ;

            Cluster.forEach(cluster -> cluster.getStatistique());

            double error = 0 ;
            int nb = 0, count;
            XYSeries serieDePoints = new XYSeries("Petites Stations");
            XYSeries serieDePoints1 = new XYSeries("Grandes Stations");
            XYSeries serieDePoints2 = new XYSeries("Très Grandes Stations");
            XYSeries baricentres = new XYSeries("Baricentres");

            for(Cluster cluster:Cluster)
            {
                if (cluster.getPointsDuCluster().size() > 0 ){
                    nb++ ;
                    error += cluster.getMoyDistance();
                    LOGGER.debug("Cluster : "+ nb);
                    baricentres.add(cluster.getBarycentre().getCoords(0),cluster.getBarycentre().getCoords(1));
                    count = 0;
                    for (Point point: cluster.getPointsDuCluster())
                    {
                        // En fonction du numéro du cluster on affiche les points dans différentes séries
                        switch (nb)
                        {
                            case 1:
                                // Remplissage série de points
                                serieDePoints.add(point.getCoords(0),point.getCoords(1));
                                break;
                            case 2:
                                // Remplissage série de points
                                serieDePoints2.add(point.getCoords(0),point.getCoords(1));
                                break;
                            case 3:
                                // Remplissage série de points
                                serieDePoints1.add(point.getCoords(0),point.getCoords(1));
                                break;
                        }
                        count+=1;
                        LOGGER.debug("Station"+count+" : "+point.getCoords(0)+" , "+point.getCoords(1));
                    }
                }
            }

            error = error/(2*250);
            LOGGER.debug("gobal error= " + String.format(Locale.ENGLISH, "%.2f", (error*100)) + " % ");

            XYDataset xyDataset = new XYSeriesCollection();
            ((XYSeriesCollection) xyDataset).addSeries(baricentres);
            ((XYSeriesCollection) xyDataset).addSeries(serieDePoints);
            ((XYSeriesCollection) xyDataset).addSeries(serieDePoints1);
            ((XYSeriesCollection) xyDataset).addSeries(serieDePoints2);


            graphique = ChartFactory.createXYLineChart(
                    "Stations de ski", "km de pistes débutantes","km de pistes expérimentés",
                    xyDataset, PlotOrientation.VERTICAL, true, false, false);
            XYPlot plot = graphique.getXYPlot();
            XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(false, true);
            renderer.setSeriesShape(0, circle);
            plot.setRenderer(renderer);
            this.plotPanel.setChart(graphique);
        }
    }
}
