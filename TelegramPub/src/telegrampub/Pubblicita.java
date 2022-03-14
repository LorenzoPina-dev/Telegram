/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telegrampub;

/**
 *
 * @author user
 */
public class Pubblicita {
    public String citta;
    public int raggio;
    public String testo;
    
    public Pubblicita(String citta,int raggio,String testo){
        this.citta=citta;
        this.raggio=raggio;
        this.testo=testo;
        
    }
    public String toCSV(){
        return citta+";"+raggio+";"+testo;
    }
}
