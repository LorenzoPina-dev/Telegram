/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Openstreetmap;

import org.json.JSONObject;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author user
 */
public class Place {
    public double lat, lon;
    
    public Place(double lat,double lon){
        this.lat=lat;
        this.lon=lon;
    }
    
    public Place(Element n){
        lat=Double.parseDouble(n.getAttribute("lat"));
        lon=Double.parseDouble(n.getAttribute("lon"));
    }
    public Place(JSONObject n){
        lat=n.getDouble("latitude");
        lon=n.getDouble("longitude");
    }
    public String toCSV(){
        return lat+";"+lon;
        //return amenity+";"+road+";"+town+";"+county+";"+state+";"+postcode+";"+country+";"+country_code;
    }
}
