/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrampub;
import Openstreetmap.*;
import Telegram.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author user
 */

public class ThUpdate extends Thread{
    Map<String,String> utenti;
    Map<String, Long> IDUpate;
    int secondi;
    public ThUpdate(int secondi){
        this.secondi=secondi;
        utenti=new Hashtable();
        IDUpate=new Hashtable();
        List<String> file=DatiCondivisi.Intance().LeggiInfoUtenti();
        for(String line:file)
        {
            String[] split=line.split(";");
            utenti.put(split[0], split[1]+";"+split[2]+";"+split[3]);
        }
    }
    @Override
    public void run(){
        while(true)
        {
           try {
                List<Messaggio> updates=Telegram.Interfaccia.Instance().GetUpdates();
                for(Messaggio m:updates)
                {
                    if(m.text.contains("/citta"))
                    {
                        String citta=m.text.substring(m.text.indexOf(' '), m.text.length());
                        if(citta.length()>0)
                        { Place p=Gestore.GetCitta(citta);
                        if(utenti.containsKey(m.from.first_name))
                        {
                            if(!IDUpate.containsKey(m.from.first_name))
                            {
                                Aggiorna(m.from.first_name, m.chat.id+";"+ p.toCSV());
                                IDUpate.put(m.from.first_name, m.id);
                            }
                            else if(!utenti.get(m.from.first_name).equals(m.chat.id+";"+ p.toCSV()) && IDUpate.get(m.from.first_name)<m.id)
                            {
                                Aggiorna(m.from.first_name, m.chat.id+";"+ p.toCSV());
                                IDUpate.put(m.from.first_name, m.id);
                            }
                        }
                        else
                            Aggiorna(m.from.first_name, m.chat.id+";"+ p.toCSV());
                        }
                    }
                }
                Thread.sleep(secondi);
            } catch (Exception ex) {
                Logger.getLogger(ThUpdate.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    private void Aggiorna(String key,String value)
    {
        utenti.put(key,value);
        DatiCondivisi.Intance().ScriviInfoUtenti(utenti);
        System.out.println("aggiornato");
    }

}
