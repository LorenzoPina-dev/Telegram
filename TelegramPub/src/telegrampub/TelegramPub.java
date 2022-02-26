/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrampub;
import Telegram.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pina_lorenzo
 */
public class TelegramPub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here+
            List<Messaggio> updates=Interfaccia.Instance().GetUpdates();
            for(Messaggio m:updates)
                System.out.println(m.ToCsv());
        } catch (Exception ex) {
            Logger.getLogger(TelegramPub.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
