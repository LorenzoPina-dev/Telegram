/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telegram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;
import LibUtil.*;

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
            return parseJson(GestioneUrl.LeggiResponse("https://api.telegram.org/bot"+ApiKey+"/getUpdates"));
        } catch (IOException ex) {
            Logger.getLogger(Interfaccia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>();
    }
    public void SendMessage(long chat_Id,String Text) throws Exception
    {
        try {
            JSONObject obj=new JSONObject(GestioneUrl.LeggiResponse("https://api.telegram.org/bot"+ApiKey+"/sendMessage?text="+ URLEncoder.encode(Text, StandardCharsets.UTF_8)+"&chat_id="+chat_Id));
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
            JSONObject obj=new JSONObject(GestioneUrl.LeggiResponse("https://api.telegram.org/bot"+ApiKey+"/sendLocation?latitude="+ Latitude+"&longitude="+Longitude+"&chat_id="+chat_Id));
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
        JSONObject obj=new JSONObject(json);
        if(!obj.getBoolean("ok"))
            throw new Exception("chiamata errata");
        JSONArray result=obj.getJSONArray("result");
        
        for(int i=0;i<result.length();i++)
            if(result.getJSONObject(i).has("message"))
                ris.add(new Messaggio(result.getJSONObject(i).getJSONObject("message")));
        return ris;
    }
}
