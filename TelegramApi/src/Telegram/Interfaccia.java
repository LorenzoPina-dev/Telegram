/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Telegram;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;

/**
 *
 * @author user
 */
public class Interfaccia {
    public static Interfaccia instance=null;
    public String ApiKey;
    private Interfaccia() {
        try {
            BufferedReader in = new BufferedReader(new FileReader("./Secret.txt"));
        try {
            ApiKey=in.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Interfaccia.class.getName()).log(Level.SEVERE, null, ex);
        }
        in.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Interfaccia.class.getName()).log(Level.SEVERE, null, ex);
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
            URL u=new URL("https://api.telegram.org/bot"+ApiKey+"/getUpdates");
            BufferedReader sr= new BufferedReader(new InputStreamReader(u.openStream()));
            String testo="",line="";
            while((line=sr.readLine())!=null)
                testo+=line;
            return parseJson(testo);
            
        } catch (IOException ex) {
            Logger.getLogger(Interfaccia.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public List<Messaggio> parseJson(String json) throws Exception{
        
        List<Messaggio> ris=new ArrayList();
        JSONObject obj=new JSONObject(json);
        if(!obj.getBoolean("ok"))
            throw new Exception("chiamata errata");
        JSONArray result=obj.getJSONArray("result");
        for(int i=0;i<result.length();i++)
            ris.add(new Messaggio(result.getJSONObject(i).getJSONObject("message")));
        return ris;
    }
}
