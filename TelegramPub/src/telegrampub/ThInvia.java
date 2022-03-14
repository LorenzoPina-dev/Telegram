/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrampub;

import Openstreetmap.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author user
 */
public class ThInvia extends Thread{
    public Pubblicita pubblicita;
    public ThInvia(Pubblicita p){
        pubblicita=p;
    }
    public void run(){
        List<String> ris=DatiCondivisi.Intance().LeggiInfoUtenti();
        for(String line:ris)
        {
            String[] split=line.split(";");
            double distanza;
            try {
                Place paeseP=Gestore.GetCitta(pubblicita.citta);
                distanza = Gestore.calcolaDistanza(paeseP, new Place(Float.parseFloat(split[2]),Float.parseFloat(split[3])));
                System.out.println(distanza);
                if(distanza<pubblicita.raggio)
                {
                    Telegram.Interfaccia.Instance().sendLocation(Integer.parseInt(split[1]), paeseP.lat, paeseP.lon);
                    Telegram.Interfaccia.Instance().SendMessage(Integer.parseInt(split[1]), "OFFERTA SPECIALE A "+pubblicita.citta.toUpperCase()+"\r\n"+pubblicita.testo+"\r\nDistanza: "+distanza);
                }
            } catch (ParserConfigurationException ex) {
                Logger.getLogger(ThInvia.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SAXException ex) {
                Logger.getLogger(ThInvia.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ThInvia.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ThInvia.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
