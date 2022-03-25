/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrampub;

import Openstreetmap.Place;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import LibUtil.*;
/**
 *
 * @author user
 */
public class DatiCondivisi {
    private static DatiCondivisi instance;
    private String file= "./utenti.csv";
    public static DatiCondivisi Intance(){
        if(instance==null)
            instance=new DatiCondivisi();
        return instance;
    }
    private DatiCondivisi(){
    }
    public  List LeggiInfoUtenti(){
        synchronized(this){
            try {
                return GestioneFile.LeggiFileList(file);
            } catch (IOException ex) {
                Logger.getLogger(DatiCondivisi.class.getName()).log(Level.SEVERE, null, ex);
            }
            return new ArrayList<>();
        }
    }
    public void ScriviInfoUtenti(Map<String,String> utenti){
        
        synchronized(this){
            try {
                String testo="";
                for(Map.Entry<String, String> pair:utenti.entrySet())
                    testo+=pair.getKey()+";"+pair.getValue()+"\r\n";
                GestioneFile.ScriviFile(file, testo);
            } catch (IOException ex) {
                Logger.getLogger(DatiCondivisi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
