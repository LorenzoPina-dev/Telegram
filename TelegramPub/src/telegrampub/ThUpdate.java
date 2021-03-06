/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrampub;
import Openstreetmap.*;
import Telegram.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
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
        utenti=new HashMap();
        IDUpate=new HashMap();
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
                    if((m.text!=null && m.text.contains("/citta"))|| m.location!=null)
                    {
                        Place p=null;
                        if((m.text!=null && m.text.contains("/citta")))
                        {
                            String citta=m.text.substring(m.text.indexOf(' '), m.text.length());
                            if(citta.length()>0)
                             p=Gestore.GetCitta(citta);
                        }
                        else if(m.location!=null)
                            p=m.location;
                        
                        if(p!=null &&(!IDUpate.containsKey(m.from.first_name)||(IDUpate.containsKey(m.from.first_name)&&IDUpate.get(m.from.first_name)<m.id)))
                        {
                            if(!utenti.get(m.from.first_name).equals(m.chat.id+";"+ p.toCSV()))
                                Aggiorna(m.from.first_name, m.chat.id+";"+ p.toCSV());
                            IDUpate.put(m.from.first_name, m.id);
                            System.out.println("Aggiornamento update");
                        }
                    }
                }
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(ThUpdate.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException | IOException ex) {
                Logger.getLogger(ThUpdate.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ThUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.sleep(secondi);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void Aggiorna(String key,String value)
    {
        utenti.put(key,value);
        DatiCondivisi.Intance().ScriviInfoUtenti(utenti);
        System.out.println("Aggiornamento posizione");
    }

}
