/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Openstreetmap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author user
 */
public class Place {
    public String amenity;
    public String road;
    public String town;
    public String county;
    public String state;
    public int postcode;
    public String country;
    public String country_code;
    public float lat, lon;
    
    public Place(float lat,float lon){
        this.lat=lat;
        this.lon=lon;
    }
    
    public Place(Element n){
        lat=Float.parseFloat(n.getAttribute("lat"));
        lon=Float.parseFloat(n.getAttribute("lon"));
        /*amenity=n.getElementsByTagName("amenity").item(0).getTextContent();
        road=n.getElementsByTagName("road").item(0).getTextContent();
        town=n.getElementsByTagName("town").item(0).getTextContent();
        county=n.getElementsByTagName("county").item(0).getTextContent();
        state=n.getElementsByTagName("state").item(0).getTextContent();
        postcode=Integer.parseInt(n.getElementsByTagName("postcode").item(0).getTextContent());
        country=n.getElementsByTagName("country").item(0).getTextContent();
        country_code=n.getElementsByTagName("country_code").item(0).getTextContent();*/
    }
    public String toCSV(){
        return lat+";"+lon;
        //return amenity+";"+road+";"+town+";"+county+";"+state+";"+postcode+";"+country+";"+country_code;
    }
}
