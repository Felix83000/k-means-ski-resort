package edu.isen.fhgd.fft.stationsSki;

import edu.isen.fhgd.fft.controller.KmeansController;
import edu.isen.fhgd.fft.kmeans.Kmeans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList ;
import  org.w3c.dom.Node;


public class StationSki  {

    static Logger Logger = LoggerFactory.getLogger(StationSki.class) ;

    private Kmeans model_parsing = new Kmeans() ;

  public StationSki() { }


    private void getInfoSki() throws ParserConfigurationException{

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {

            db = dbf.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            Logger.error("erreur dbf.newDocumentBuilder !!");
            e.printStackTrace();
        }

        try {
            Document document = db.parse("./src/edu/isen/fhgd/fft/dataXml/ski.xml");
            document.getDocumentElement().normalize();


            // insertion des noms de stations
            NodeList listeStations = document.getElementsByTagName("row") ;
            for( int index = 0 ; index < listeStations.getLength() ; index++ ) {

                Element XmlStation = (Element) document.getElementsByTagName("Resort").item(index);
                String stations = XmlStation.getTextContent();
                model_parsing.setNomstation(stations, index);

                //log
                Logger.debug(" Nomstations :"+ stations);


                Element Xmlfacile = (Element) document.getElementsByTagName("Easy").item(index);
                // Verification si la données est vide
                if(Xmlfacile.getTextContent().isEmpty())
                {
                    float facile = 0 ;
                    model_parsing.setFacile(facile , index);

                    Logger.debug(" facile :"+ facile);
                }else {
                    float facile = Float.parseFloat((Xmlfacile.getTextContent()));
                    model_parsing.setFacile(facile , index);

                    Logger.debug(" facile :"+ facile);
                }


                Element Xmlintermediare = (Element) document.getElementsByTagName("Intermediate").item(index);
                // Verification si la données est vide
                if(Xmlintermediare.getTextContent().isEmpty())
                {
                    float intermediaire = 0 ;
                    model_parsing.setFacile( intermediaire, index);

                    Logger.debug(" intermédiare :"+ intermediaire);
                }else {
                    float intermediaire = Float.parseFloat((Xmlintermediare.getTextContent()));
                    model_parsing.setFacile( intermediaire , index);

                    Logger.debug(" intermédiare :"+ intermediaire);
                }






                Element Xmldificile = (Element) document.getElementsByTagName("Difficult").item(index);
                // Verification si la données est vide
                if(Xmldificile.getTextContent().isEmpty())
                {
                    float dificile = 0 ;
                    model_parsing.setFacile( dificile, index);

                    Logger.debug(" dificile :"+ dificile);
                }else {
                    float dificile = Float.parseFloat((Xmldificile.getTextContent()));
                    model_parsing.setFacile( dificile , index);

                    Logger.debug(" dificile :"+ dificile);
                }



                // log



            }

         Logger.info("Fin du parsing !!");


        } catch (Exception e) {

            Logger.error("Erreur de récupération des données !!");
            e.printStackTrace();


        }
    }

    // fonction de réxupérattion des données parsée
    public Kmeans getModel_parsing() throws ParserConfigurationException {

        Logger.info("Lancement du parsing !!");
        getInfoSki();

        return model_parsing;
    }

}
