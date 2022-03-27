/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telegram;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;
import LibUtil.*;
import Services.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author user
 */
public class Interfaccia {
    public static Interfaccia instance=null;
    public String ApiKey;
    private Interfaccia() {
        try {
            ApiKey=GestioneFile.LeggiFile("./Secret.txt");
        } catch (IOException ex) {
            Logger.getLogger(Interfaccia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public  static Interfaccia Instance(){
        if(instance==null)
            instance=new Interfaccia();
        return instance;
    }
    public List<Messaggio> GetUpdates() throws Exception{
        try {
            return parseJson("https://api.telegram.org/bot"+ApiKey+"/getUpdates");
        } catch (IOException ex) {
            Logger.getLogger(Interfaccia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }
    public void SendMessage(long chat_Id,String Text) throws Exception
    {
        try {
            Map<String,String> parametri=new HashMap();
            parametri.put("text", Text);
            parametri.put("chat_id", chat_Id+"");
            JSONObject obj=ParseFile.ParseJSON(GestioneService.CreateUrl("https://api.telegram.org/bot"+ApiKey+"/sendMessage",parametri));
            if(!obj.getBoolean("ok"))
                throw new Exception("chiamata errata");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Interfaccia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Interfaccia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendLocation(long chat_Id,double Latitude,double Longitude) throws Exception
    {
        try {
            Map<String,String> parametri=new HashMap();
            parametri.put("latitude", Latitude+"");
            parametri.put("longitude", Longitude+"");
            parametri.put("chat_id", chat_Id+"");
            JSONObject obj=ParseFile.ParseJSON(GestioneService.CreateUrl("https://api.telegram.org/bot"+ApiKey+"/sendLocation",parametri));
            if(!obj.getBoolean("ok"))
                throw new Exception("chiamata errata");
        } catch (MalformedURLException ex) {
            Logger.getLogger(Interfaccia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Interfaccia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private List<Messaggio> parseJson(String json) throws Exception{
        List<Messaggio> ris=new ArrayList();
        JSONObject obj=ParseFile.ParseJSON(json);
        if(!obj.getBoolean("ok"))
            throw new Exception("chiamata errata");
        JSONArray result=obj.getJSONArray("result");
        
        for(int i=0;i<result.length();i++)
            if(result.getJSONObject(i).has("message"))
                ris.add(new Messaggio(result.getJSONObject(i).getJSONObject("message")));
        return ris;
    }
}
