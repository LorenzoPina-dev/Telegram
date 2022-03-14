/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Openstreetmap;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author user
 */
public class Gestore {
    public static Place GetCitta(String citta) throws ParserConfigurationException, SAXException, IOException{
        String url="https://nominatim.openstreetmap.org/search?q="+URLEncoder.encode(citta, StandardCharsets.UTF_8)+"&format=xml&addressdetails=1";
        return Parse(url);
    }
    
    
    private static Place Parse(String file) throws ParserConfigurationException, SAXException, IOException{
        DocumentBuilderFactory factory;
        DocumentBuilder builder;
        Element root, element;
        factory=DocumentBuilderFactory.newInstance();
        builder=factory.newDocumentBuilder();
        Document document=builder.parse(file);
        root=document.getDocumentElement();
        element = (Element) root.getElementsByTagName("place").item(0); 
        return new Place(element);
    }
    
    public static double calcolaDistanza(Place p1,Place p2){
        return Math.acos(Math.cos(Math.toRadians(90-p1.lat))*Math.cos(Math.toRadians(90-p2.lat))+Math.sin(Math.toRadians(90-p1.lat))*Math.sin(Math.toRadians(90-p2.lat))*Math.cos(Math.toRadians(p1.lon-p2.lon)))*6371*1000;
        //float DM=0.9996f;
        //return (1/Math.cos(Math.sin(Math.min(p1.lat, p2.lat))*Math.sin(Math.max(p1.lat, p2.lat))+Math.cos(Math.min(p1.lat, p2.lat))*Math.cos(Math.max(p1.lat, p2.lat))*Math.cos(Math.max(p1.lat, p2.lat)-Math.min(p1.lat, p2.lat))))*6371;
        //return (float) (Math.sqrt(Math.pow(Math.abs(p1.lat-p2.lat), 2)+Math.pow(Math.abs(p1.lon-p2.lon), 2))+DM);
    }
}