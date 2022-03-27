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
import LibUtil.*;
import Services.*;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author user
 */
public class Gestore {
    public static Place GetCitta(String citta) throws ParserConfigurationException, SAXException, IOException{  
        Map<String,String> parametri=new HashMap();
        parametri.put("q", citta);
        parametri.put("format", "xml");
        parametri.put("addressdetails", 1+"");
        return Parse(GestioneService.CreateUrl("https://nominatim.openstreetmap.org/search", parametri),"place");
    }
    
    private static Place Parse(String file,String tag) throws ParserConfigurationException, SAXException, IOException{
        return new Place((Element) ParseFile.ParseXml(file).getElementsByTagName(tag).item(0));
    }
    
    public static double calcolaDistanza(Place p1,Place p2){
        return Math.acos(Math.cos(Math.toRadians(90-p1.lat))*Math.cos(Math.toRadians(90-p2.lat))+Math.sin(Math.toRadians(90-p1.lat))*Math.sin(Math.toRadians(90-p2.lat))*Math.cos(Math.toRadians(p1.lon-p2.lon)))*6371*1000;
    }
    
}
